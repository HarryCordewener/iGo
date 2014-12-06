package yelp;

import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unused")
public class fetchData {

	public static String queryLocation(JSONObject obj) {
		String DB_URL = "jdbc:mysql://localhost:3306/igo";
		String USER = "root";
		String PASS = "cs441";
		String queryitem = ((JSONObject) obj.get("location")).get("city")
				.toString();
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("\nFetch Data: Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println(" SUCCESS!\n");

			System.out.print("\n Querying " + queryitem + " from table...");
			stmt = conn.createStatement();
			String sql = "Select locationid from locations where locationname ="
					+ "'" + queryitem + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(" SUCCESS!\n");
			try {
				if (rs.next()) {
					do {
						return (rs.getString("locationid"));
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println("Failed........");
			}
		}

		catch (SQLException se) {
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
		return null;
	}

	public static void addLocation(JSONObject obj, String gpscoor) {

		JSONObject s = new JSONObject(obj);
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

			String location = null;
			String locationtype = null;
			String subtype = null;
			String state = null;
			try {
				JSONObject loc = (JSONObject) (s.get("location"));
				location = loc.get("city").toString();
				locationtype = loc.get("country_code").toString();
				subtype = loc.get("postal_code").toString();
				state = loc.get("state_code").toString();
				// gpscoor = ((JSONObject)
				// location.get("coordinate")).get("latitude").toString()+","+((JSONObject)
				// location.get("coordinate")).get("longitude").toString();
			} catch (Exception e) {
				// gpscoor = null;
			}

			// String is_active = s.get("is_closed").toString();
			int active = 1;
			/*
			 * if(is_active.equals("false")) { active=1; } else active = 0;
			 */

			String sql = "INSERT INTO locations(LocationName,GPS,LocationType,SubType,Active,State) "
					+ "VALUES ('"
					+ location
					+ "','"
					+ gpscoor
					+ "','"
					+ locationtype
					+ "','"
					+ subtype
					+ "','"
					+ active
					+ "','"
					+ state + "')";
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println(" SUCCESS!\n");

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
	}
}