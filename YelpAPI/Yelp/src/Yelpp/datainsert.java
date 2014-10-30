package Yelpp;

import java.sql.*;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class datainsert {

	public static void add2DB(JSONObject firstBusiness) {
		JSONObject s=new JSONObject(firstBusiness);
		//String obj_id=s.get("id").toString();
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost:3306/test";
		String USER = "root";
		String PASS = "loki";
		
				Connection conn = null;
			    Statement stmt = null;
			    try {

			    	Class.forName("com.mysql.jdbc.Driver");
			        System.out.print("\nConnecting to database...");
			        
			        conn = DriverManager.getConnection(DB_URL, USER, PASS);
			        System.out.println(" SUCCESS!\n");
			        
			        System.out.print("\nInserting records into table...");
			        stmt = conn.createStatement();

			        String sql = "INSERT INTO test(RestaurantName,RestaurantType,GPSCoordiante,Active,RestaurantID_Alias) " + 
			        "VALUES ('" + s.get("name") + "','"+s.get("((JSONArray)catergories)")+"','"+s.get("rating")+"','"+s.get("longitude")+"')";
			        System.out.println(sql);
			       // stmt.executeUpdate(sql);

			        System.out.println(" SUCCESS!\n");

			    } catch(SQLException se) {
			        se.printStackTrace();
			    } catch(Exception e) {
			        e.printStackTrace();
			    } finally {
			        try {
			            if(stmt != null)
			                conn.close();
			        } catch(SQLException se) {
			        }
			        try {
			            if(conn != null)
			                conn.close();
			        } catch(SQLException se) {
			            se.printStackTrace();
			        }
			    }	
	}
}
