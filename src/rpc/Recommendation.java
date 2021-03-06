package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import algorithm.GeoRecommendation;
import entity.Item;

/**
 * Servlet implementation class Recommendation
 */
@WebServlet("/recommendation")
public class Recommendation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recommendation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
/*		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		String username = "";
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		
		try {
			array.put(new JSONObject().put("name", "abcd").put("address", "san francisco").put("time", "01/01/2017"));
			array.put(new JSONObject().put("name", "1234").put("address", "san jose").put("time", "01/02/2017"));
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		out.print(array);
		out.close();
	}
*/
/*		JSONArray array = new JSONArray();
		try {
			array.put(new JSONObject().put("name", "abcd").put("address", "san francisco").put("time", "01/01/2017"));
			array.put(new JSONObject().put("name", "1234").put("address", "san jose").put("time", "01/02/2017"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);
*/
		String userId = request.getParameter("user_id");
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lon = Double.parseDouble(request.getParameter("lon"));

		GeoRecommendation recommendation = new GeoRecommendation();
		List<Item> recommendedItems = recommendation.recommendItems(userId, lat, lon);

		JSONArray array = new JSONArray();
		try {
			for (Item item : recommendedItems) {
				array.put(item.toJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);	

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
