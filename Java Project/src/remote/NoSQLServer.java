package remote;

import java.util.ArrayList;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class NoSQLServer {

	// mongodb variables
	static String mongoDBurl = "localhost";
	static int mongoDBport = 27017;
	static String mongoDBName = "iGo";
	static String mongoDBusername = "root";
	static char[] mongoDBpassword = "cs441".toCharArray();
	static MongoClient mongoClient = null;
	static DB db = null;
	/*
	 * Function only establishes connection to the DB
	 */

	public static boolean connectNoSQL() {
		boolean auth = false;
		try {
			// To connect to mongodb server
			mongoClient = new MongoClient(mongoDBurl, 27017);
			// Now connect to your databases
			db = mongoClient.getDB(mongoDBName);
			System.out.println("Connect to database successfully");
			auth = db.authenticate(mongoDBusername, mongoDBpassword);
			System.out.println("Authentication: " + auth);
			return auth;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return auth;
		}
	}
	
	public static boolean connectNoSQL(String url, int port) {
		mongoDBurl= url;
		mongoDBport= port;
		boolean auth = false;
		try {
			// To connect to mongodb server
			mongoClient = new MongoClient(mongoDBurl, 27017);
			// Now connect to your databases
			db = mongoClient.getDB(mongoDBName);
			System.out.println("Connect to database successfully");
			auth = db.authenticate(mongoDBusername, mongoDBpassword);
			System.out.println("Authentication: " + auth);
			return auth;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return auth;
		}
	}


	/*
	 * Function closes open DB connection
	 */

	public static void closeConnectionNoSQL() {
		try {
			if (mongoClient != null) {
				mongoClient.close();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/*
	 * Function returns <user-count> - if user and password matched, 0 - not matched.
	 */

	public static int validateLogin(String user, String password) {
		int loginToken = 0;

		if (connectNoSQL()) {

			String data = "{\"name\":\"" + user + "\",\"password\":\"" + password + "\"}";
			DBObject queryObj = (DBObject) JSON.parse(data);
			DBCollection collection = db.getCollection("users");
			DBCursor cursor = collection.find(queryObj);
			try{
				while(cursor.hasNext()) {
					loginToken = (int)Float.parseFloat((cursor.next().get("userid").toString()));
				}
			}
			catch(MongoException e){
				System.out.println("Can't get user");
			}
			finally{
				closeConnectionNoSQL();
			}
			
		}
		return loginToken;
	}

	/*
	 * AddAccount: This method adds a new user with credentials to our NoSQL Database.
	 */
	public static void addAccount(String username, String password, String email, String mobile) {
		//System.out.println("Trying to add Account for - "+username);
		int newId = getNextId("users");
		if(newId==-1){
			System.out.println("creation of newid failed!");
			return ;
		}
		
		String query = "{ \"userid\":"+newId+",\"name\":\"" + username + "\", \"email\":\"" + email + "\", \"mobilenumber\":\"" + mobile
				+ "\", \"password\":\"" + password + "\"}";
		//System.out.println("Creating statement- adding user - NoSQL");
		insertNoSQL("users", query);
		findNoSQL("users", "");
	}
	
	public static int setLocation(String loc,String state) {
		int locationId = 0;
		System.out.println("Creating statement- lookin up city - NoSQL");
		
		if (connectNoSQL()) {

			String data = "{\"locationname\":\"" + loc + "\"}";
			//System.out.println(" Data "+data);
			DBObject queryObj = (DBObject) JSON.parse(data);
			DBCollection collection = db.getCollection("locations");
			DBCursor cursor = collection.find(queryObj).limit(1);
			try{
				while(cursor.hasNext()) {
					locationId = (int)Float.parseFloat((cursor.next().get("locationid").toString()));
					System.out.println(" location id "+locationId);
				}
			}
			catch(MongoException e){
				System.out.println("Can't get user");
			}
			if(locationId!=0){
				closeConnectionNoSQL();
				return locationId;
			}
			//Second Search
			data = "{\"state\":\"" + state + "\"}, { \"subtype\": \"General\"}";
			queryObj = (DBObject) JSON.parse(data);
			cursor = collection.find(queryObj);
			try{
				while(cursor.hasNext()) {
					locationId = (int)Float.parseFloat((cursor.next().get("locationid").toString()));
				}
			}
			catch(MongoException e){
				System.out.println("Can't get user");
			}
			closeConnectionNoSQL();
			return locationId;
		}
		return locationId;
		
	}
	
	public static String seeRestaurants(int locationid) {
		StringBuilder restaurantsJSON = new StringBuilder(1000);
		restaurantsJSON.append("{ Restaurants:[");
		if(connectNoSQL()){
			String query = "{\"locationid\":"+locationid+"},{_id:0}";
			
			DBObject queryObj = (DBObject) JSON.parse(query);
			DBCollection collection = db.getCollection("restaurants");
			DBCursor cursor = collection.find(queryObj);
			//STEP 4: Execute a query
			System.out.println("Creating statement - querying restaurants...");
			try {
				   while(cursor.hasNext()) {
					   
					   DBObject obj = cursor.next();
					   int restid  = (int)Float.parseFloat((obj.get("restaurantid").toString()));
						String name = obj.get("name").toString();
						String type = obj.get("type").toString();
						String gps = obj.get("gps").toString();
						Restaurant temp = new Restaurant(gps, name, locationid, restid, type);
						System.out.println(temp.toString());
						restaurantsJSON.append(temp.toString());
			
						restaurantsJSON.append(",");
				   }
				} finally {
					restaurantsJSON.append("]}");
				   cursor.close();
				}
				closeConnectionNoSQL();
				return restaurantsJSON.toString();
		}
		else{
			return "";
		}
	
	}
	
	public static String seeAirports(int locationid){
		return null;
	}
	
	public static String seeBanks(int locationid){
		return null;
	}
	
	public static String seeBars(int locationid){
		return null;
	}
	
	public static String seeHotels(int locationid){
		return null;
	}
	
	public static String seeHospitals(int locationid) {

		if(connectNoSQL()){
			StringBuilder hospitalsJSON= new StringBuilder(10000);
			String query = "{\"locationid\":\""+locationid+"\"},{_id:0}";
			
			DBObject queryObj = (DBObject) JSON.parse(query);
			DBCollection collection = db.getCollection("hospitals");
			DBCursor cursor = collection.find(queryObj);
			//STEP 4: Execute a query
			System.out.println("Creating statement - querying hospitals...");
			try {
				   while(cursor.hasNext()) {
					   hospitalsJSON.append(cursor.next().toString());
					   hospitalsJSON.append(",");
				   }
				} finally {
					hospitalsJSON.append("]}");
				   cursor.close();
				}
				closeConnectionNoSQL();
				return hospitalsJSON.toString();
		}
		else{
			return "";
		}
	}
	
	

	/*
	 * insertNoSQL: This method is used to execute insertion queries
	 */

	public static void insertNoSQL(String collectionName, String data) {
		//System.out.println("Trying to insert "+data + " into "+collectionName);
		if (connectNoSQL()) {
			// convert JSON to DBObject directly
			DBObject dbObject = (DBObject) JSON.parse(data);
			DBCollection collection = db.getCollection(collectionName);
			collection.insert(dbObject);
			closeConnectionNoSQL();
		}
	}

	/*
	 * findNoSQL: This method executes a query and returns the list of DBObjects matching the query.
	 * Query should be of the form
	 * "{"<KEY1>":"<value1>,"<KEY2>":"<value2>"}"
	 */
	public static ArrayList<DBObject> findNoSQL(String collectionName, String query) {
		System.out.println("Displaying "+collectionName);
		if (connectNoSQL()) {
			DBObject queryObj = (DBObject) JSON.parse(query);
			DBCollection collection = db.getCollection(collectionName);
			DBCursor cursor = collection.find(queryObj);
			ArrayList<DBObject> dbObjectArray = new ArrayList<DBObject>();
			int i=0;
			try {
				while (cursor.hasNext()) {
					dbObjectArray.add(cursor.next());
					System.out.println(dbObjectArray.get(i));
					i++;
				}
			} finally {
				cursor.close();
				closeConnectionNoSQL();
			}
			return dbObjectArray;
		} else {
			return null;
		}
	}
	
	public static int getNextId(String collectionName){
		if(connectNoSQL()){
			DBCollection collection = db.getCollection(collectionName);
			int rowCount = collection.find().count();
			closeConnectionNoSQL();
			return rowCount+1;
		}
		return -1;
	}
	
	public static void generateData(){
		//if data already exists do not generate!
		int rowCount = 0;
		if(connectNoSQL()){
			DBCollection collection = db.getCollection("users");
			rowCount = collection.find().count();
			closeConnectionNoSQL();
		}
		else{
			//System.out.println("Adding User  - ");
			db = mongoClient.getDB(mongoDBName);
			WriteResult wr = db.addUser(mongoDBusername, mongoDBpassword);
			closeConnectionNoSQL();
		}
		if(rowCount==0) {
			//System.out.println("Now creating users...");
			generateUsers();
			generateLocations();
			generateRestaurants();
			generateHospitals();
		}
		return;
	}
	
	public static void generateData(String url, int port){
		//if data already exists do not generate!
		int rowCount = 0;
		if(connectNoSQL(url, port)){
			DBCollection collection = db.getCollection("users");
			rowCount = collection.find().count();
			closeConnectionNoSQL();
		}
		else{
			//System.out.println("Adding User  - ");
			db = mongoClient.getDB(mongoDBName);
			WriteResult wr = db.addUser(mongoDBusername, mongoDBpassword);
			closeConnectionNoSQL();
		}
		if(rowCount==0) {
			//System.out.println("Now creating users...");
			generateUsers();
			generateLocations();
			generateRestaurants();
			generateHospitals();
		}
		return;
	}
	
	public static void generateUsers(){
		addAccount("Shouvik", "igo", "abc@example.com", "555-555-5555");
		addAccount("Testuser", "igo", "abc2@example.com", "554-444-4444");
		addAccount("test2", "igo", "abc3@a.com", "123-222-2222");
		addAccount("Chetana", "igo", "cvedan2@uic.edu", "999-999-9999");
	}
	
	public static void generateLocations(){
		System.out.println("Trying to add Locations");
		int newId = getNextId("locations");
		if(newId==-1){
			System.out.println("creation of newid failed!");
			return ;
		}
		String locationname = "Chicago";
		String gps = "41.836944, -87.684722";
		String locationType = "City";
		String subtype = "";
		String active = "Y";
		String state = "IL";
		String data = "{ \"locationid\":"+newId+",\"locationname\":\"" + locationname + "\", \"gps\":\"" + gps + "\", \"locationtype\":\"" + locationType+ "\", \"subtype\":\"" + subtype + "\" , \"active\":\"" + active + "\" , \"state\":\"" + state + "\"}";
		System.out.println("Data : "+data);
		insertNoSQL("locations", data);
		
		newId = getNextId("locations");
		if(newId==-1){
			System.out.println("creation of newid failed!");
			return ;
		}
		
		locationname = "New York";
		 gps = "40.710836, -74.023322";
		 locationType = "City";
		 subtype = "";
		 active = "Y";
		 state = "NY";
		 data = "{ \"locationid\":"+newId+",\"locationName\":\"" + locationname + "\", \"gps\":\"" + gps + "\", \"locationtype\":\"" + locationType+ "\", \"subtype\":\"" + subtype + "\" , \"active\":\"" + active + "\" , \"state\":\"" + state + "\"}";
		 System.out.println("Data : "+data);
		
		 insertNoSQL("locations", data);
	}
	
	public static void generateRestaurants(){
		System.out.println("Trying to add Restaurants");
		int newId = getNextId("restaurants");
		if(newId==-1){
			System.out.println("creation of newid failed!");
			return ;
		}
		String name = "Lou Malnatis";
		String type = "Pizza";
		String gps = "41.871569, -87.627418";
		String active = "Y";
		int locationid = 1;
		String data = "{ \"restaurantid\":"+newId+",\"name\":\"" + name + "\", \"type\":\"" + type + "\", \"gps\":\"" + gps+ "\",  \"active\":\"" + active+ "\" , \"locationid\":" + locationid + "}";
		System.out.println("Data : "+data);
		insertNoSQL("restaurants", data);
		
		 newId = getNextId("restaurants");
		if(newId==-1){
			System.out.println("creation of newid failed!");
			return ;
		}
		 name = "Lou Malnatis";
		 type = "Pizza";
		 gps = "41.871569, -87.627418";
		 active = "Y";
		 locationid = 1;
		 data = "{ \"restaurantid\":"+newId+",\"name\":\"" + name + "\", \"type\":\"" + type + "\", \"gps\":\"" + gps+ "\", \"locationid\":" + locationid + "}";
		System.out.println("Data : "+data);
		insertNoSQL("restaurants", data);
	}
	
	public static void generateHospitals(){
		//TODO : Generate Hospitals
	}
	
	public static void dropData(){
		if (connectNoSQL()) {
			//query = "db.users.drop()";
			DBCollection collection = db.getCollection("users");
			collection.drop();
			System.out.println("Dropped "+ collection.getName());
			// query = "db.locations.drop()";
			collection = db.getCollection("locations");
			collection.drop();
			System.out.println("Dropped "+ collection.getName());
			//query = "db.restaurants.drop()";
			collection = db.getCollection("restaurants");
			collection.drop();
			System.out.println("Dropped "+ collection.getName());
			//query = "db.hospitals.drop()";
			closeConnectionNoSQL();
		}
	}

	public static String seePolice(int locationid) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String seeTheaters(int locationid) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String seeSchools(int locationid) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String seeStores(int locationid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
