package yelp;

import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import remote.NoSQLServer;

import com.mongodb.DBCollection;

@SuppressWarnings("unused")
public class dataInsert {
	
	public static String add2DB(JSONObject firstBusiness, String Tabletype,
			String GPS) {
		JSONObject s = new JSONObject(firstBusiness);
		// String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/igo";
		String USER = "root";
		String PASS = "cs441";

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
			String restype = ((JSONArray) (((JSONArray) (s.get("categories")))
					.get(0))).get(0).toString();
			String contactnumber = s.get("phone").toString();
			JSONObject location;
			String gpscoor;

			try {
				location = (JSONObject) (s.get("location"));
				gpscoor = ((JSONObject) location.get("coordinate")).get(
						"latitude").toString()
						+ ","
						+ ((JSONObject) location.get("coordinate")).get(
								"longitude").toString();
			} catch (Exception e) {
				gpscoor = "NA";
			}

			String is_active = s.get("is_closed").toString();
			int active = 0;
			if (is_active.equals("false")) {
				active = 1;
			} else
				active = 0;

			String locationid = fetchData.queryLocation(s);
			if (locationid == null) {
				
				//adding new location to MySQL DB
				fetchData.addLocation(s, GPS);
				locationid = fetchData.queryLocation(s);
				
				//adding new locatin to noSQL DB
				try {
					
					int newId = NoSQLServer.getNextId("locations");
					JSONObject loc = (JSONObject) (s.get("location"));
					String locationName = loc.get("city").toString();
					String locationType = loc.get("country_code").toString();
					String subType = loc.get("postal_code").toString();
					String state = loc.get("state_code").toString();
					String data = "{ \"locationid\":"+locationid+",\"locationname\":\"" + locationName + "\", \"gps\":\"" + GPS + "\", \"locationtype\":\"" + locationType+ "\", \"subtype\":\"" + subType + "\" , \"active\":\"" + "1" + "\" , \"state\":\"" + state + "\"}";
					NoSQLServer.insertNoSQL("locations", data);
				} catch (Exception e) {
					// gpscoor = null;
				}
				
			}

			String sql = "";
			Tabletype = Tabletype.toLowerCase();
			if (Tabletype.equals("restaurants")) {
				sql = "INSERT INTO restaurants(name,type,gps,Active,locationid) "
						+ "VALUES ('"
						+ restid
						+ "','"
						+ restype
						+ "','"
						+ gpscoor + "','" + active + "','" + locationid + "')";
			} else if (Tabletype.equals("hospitals")) {
				sql = "INSERT INTO hospitals(name,type,gps,Active,locationid,contactnumber) "
						+ "VALUES ('"
						+ restid
						+ "','"
						+ restype
						+ "','"
						+ gpscoor
						+ "','"
						+ active
						+ "','"
						+ locationid
						+ "','"
						+ contactnumber + "')";
			}
			System.out.println("Sql statement is : " + sql);
			stmt.executeUpdate(sql);
			System.out.println(" SUCCESS!\n");
			return locationid;
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Failed........");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed........");
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("Failed........");
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("Failed........");
				se.printStackTrace();
			}
		}
	return "-1";
	}
}