package Yelpp;
import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unused")
public class datainsert {

	public static void add2DB(JSONObject firstBusiness, String type) {
		JSONObject s=new JSONObject(firstBusiness);
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
			        
			        String restid = s.get("id").toString();
			        String restype = ((JSONArray) (((JSONArray) (s.get("categories"))).get(0))).get(0).toString();
			        String contactnumber=s.get("phone").toString();
			        JSONObject location;
			        String gpscoor;
					
			        try {
						location = (JSONObject) (s.get("location"));
						gpscoor = ((JSONObject) location.get("coordinate")).get("latitude").toString()+","+((JSONObject) location.get("coordinate")).get("longitude").toString();
						} 
					catch (Exception e) {
						gpscoor = "NA";
						}
			        
			        String is_active = s.get("is_closed").toString();
			        int active=0;
			        if(is_active.equals("false"))
			    	   {
			    	   active=1;
			    	   }
			        else active = 0;
			        
			        String locationid=FetchData.queryLocation(s);
			        if(locationid==null)
			        {
			        	FetchData.addLocation(s);
			        	locationid=FetchData.queryLocation(s);
			        }
			        
			        String sql="";	
			        if(type=="restaurants")
			        {
			        sql = "INSERT INTO restaurants(name,type,gps,Active,locationid) " + "VALUES ('"+restid+"','"+restype+"','"+gpscoor+"','"+active+"','"+locationid+"')";
			        }
			        else if(type=="hospitals")
			        {
			        sql = "INSERT INTO hospitals(name,type,gps,Active,locationid,contactnumber) " + "VALUES ('"+restid+"','"+restype+"','"+gpscoor+"','"+active+"','"+locationid+"','"+contactnumber+"')";
			        }
			        System.out.println(sql);
			        stmt.executeUpdate(sql);
			        System.out.println(" SUCCESS!\n");

			    } catch(SQLException se) {
			        se.printStackTrace();
			        System.out.println("Failed........");
			    } catch(Exception e) {
			        e.printStackTrace();
			        System.out.println("Failed........");
			    } finally {
			        try {
			            if(stmt != null)
			                conn.close();
			        } catch(SQLException se) {
			        	System.out.println("Failed........");
			        }
			        try {
			            if(conn != null)
			                conn.close();
			        } catch(SQLException se) {
			        	System.out.println("Failed........");
			            se.printStackTrace();
			        }
			    }	
	}
}