/*
 * iGO
 * CS 442 Project
 * 
 * The happypath server implements a simplified version of our full interface, the happyPathInterface
 * 
 * This server has basic functionality relating to creating accounts, validating logins, adding/viewing friends,
 * setting the city to search in, and searching for restraunts or hospitals in a city.
 */
package remote;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.ArrayList;
import java.sql.*;



/*
 * ASSUMPTIONS:
 * 
 */


@SuppressWarnings("serial")
public class HappyPathServer extends 
java.rmi.server.UnicastRemoteObject implements happyPathInterface{

	int dbselection =1;

	public HappyPathServer() throws RemoteException{


	}

	public static void main(String argv[]){
		Registry registry; 

		//Server arugments
		int serverPort;
		String JDBC_DRIVER= "com.mysql.jdbc.Driver";

		//mySQL connection variables
		String mySQLurl="jdbc:mysql://localhost:3306/igo";
		int mySQLport=3306;
		String mySQLusername="root";
		String mySQLpass="";

		//mongodb variables
		String mongoDBurl;
		int mongoDBport;
		String mongoDBusername;
		String mongoDBpassword;

		//selection will set database type
		int databaseSelection=0;

		//read port argument if present
		if (argv.length == 1){
			serverPort=Integer.parseInt(argv[0]);
		}
		else{
			serverPort = 12345;
		}

		createServer();
		connectRMI(serverPort);

	}



	public static void createServer() {
		try{
			HappyPathServer server = new HappyPathServer();
		}
		catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void connectRMI(int serverPort) {
		Registry registry;
		String address = null;
		//gets address of local host to deploy the server on
		try{  
			address = (InetAddress.getLocalHost()).toString();
		}
		catch(Exception e){
			System.out.println("can't get inet address.");
		}

		int port = serverPort;

		//displays server ip and port on console
		System.out.println("this address=" + address +  ",port=" + port);
		try{

			HappyPathServer obj = new HappyPathServer();
			//ReceiveMessageInterface stub = (ReceiveMessageInterface) UnicastRemoteObject.exportObject(obj, 0);
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("rmiServer", obj);

		}
		catch(RemoteException e){
			System.out.println("remote exception"+ e);
		}
	}

/*Validate login will check the database to match up the users provided username and password and provide the client
 * with the users id if successful, otherwise it will return a zero(non-Javadoc)
 * @see remote.happyPathInterface#validateLogin(java.lang.String, java.lang.String)
 */
	@Override
	public int validateLogin(String user, String pass)throws RemoteException, ClassNotFoundException, SQLException {

		Statement stmt;
		boolean found = false;
		Connection conn = connectMySQL();
		String query = "SELECT * FROM users where  Name= '" +user  + "' limit 1;";

		//STEP 4: Execute a query
		System.out.println("Creating statement- checking password...");
		stmt = conn.createStatement();
		ResultSet rs = queryMySQL(stmt, query);

		while(rs.next()){
			//Retrieve by column name
			int id  = rs.getInt("userID");
			String username = rs.getString("username");
			String password = rs.getString("password");
			if (pass.equals(password)){
				found = true;
				closeConnection(stmt, conn, rs);
				return id;
			}
		}
		closeConnection(stmt, conn, rs);

		return 0;

	}
	
	/*closeConnection - this is used to sever a client connection to mySQL
	 * 
	 * 
	 */

	private static void closeConnection(Statement stmt, Connection conn,
			ResultSet rs) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}


	/*setLocation:
	 * This method is used to lookup a location id in our database and return to the cleint
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#setLocation(java.lang.String, java.lang.String, boolean)
	 */

	@Override
	public int setLocation(String loc, String state, boolean nosql) throws RemoteException, ClassNotFoundException, SQLException{

		if (!nosql){
			Statement stmt;
			Connection conn = connectMySQL();
			String query = "SELECT * FROM locations where LocationName='" + loc + "' limit 1;";

			//STEP 4: Execute a query
			System.out.println("Creating statement- lookin up city...");
			stmt = conn.createStatement();
			ResultSet rs = queryMySQL(stmt, query);

			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("locationID");
				closeConnection(stmt, conn, rs);
				return id;
			}

			String query2 = "SELECT * FROM locations where State='" + state + "' AND SubType='General';";

			//STEP 4: Execute a query
			System.out.println("City Not Found- looking up state...");
			stmt = conn.createStatement();
			ResultSet rs2 = queryMySQL(stmt, query2);

			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("locationID");
				closeConnection(stmt, conn, rs);
				return id;
			}

			closeConnection(stmt, conn, rs);
			return 0;

		}
		else if (nosql){
			return 0;

		}
		return 0;
	}
	
	/*
	 * seeRestraunts:
	 * This method returns a JSON list of restraunts in the selected location
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#seeResteraunts(int, boolean)
	 */

	@Override
	public String seeResteraunts(int locationid, boolean nosql) throws RemoteException, ClassNotFoundException, SQLException {


		Statement stmt;
		StringBuilder restrauntsJSON = null;
		restrauntsJSON.append("Restraunts [");
		Connection conn = connectMySQL();
		String query = "SELECT * FROM restaurants where locationid = " + locationid + " limit 100;";

		//STEP 4: Execute a query
		System.out.println("Creating statement- querying resteraunts...");
		stmt = conn.createStatement();
		ResultSet rs = queryMySQL(stmt, query);
		while(rs.next()){

			int restid  = rs.getInt("restrauntid");
			String name = rs.getString("name");
			String type = rs.getString("type");
			String gps = rs.getString("gps");
			restraunt temp = new restraunt(gps, name, locationid, restid, type);
			restrauntsJSON.append(temp.toString());
			//(String co, String name, int id, int restID, String restType) 

		}

		closeConnection(stmt, conn, rs);
		restrauntsJSON.append("]");

		return restrauntsJSON.toString();

	}

	/*
	 * AddAccount:
	 * This method adds a new user with credentials to our database
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#addAccount(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addAccount(String username, String password, String email, String mobile) throws RemoteException, ClassNotFoundException, SQLException{

		Statement stmt;
		boolean found = false;
		Connection conn = connectMySQL();
		String query = "INSERT INTO users ( `Name`, `Email`, `MobileNumber`, `Password`) VALUES ('"+ username +"', '"+ email +"', '"+ mobile + "', '"+password +"');";


		//STEP 4: Execute a query
		System.out.println("Creating statement- adding user");
		stmt = conn.createStatement();
		insertMySQL(stmt, query);

	}

	/*
	 * queryMySQL:
	 * This method executes a query and returns the result set in an object
	 */
	private ResultSet queryMySQL(Statement stmt, String query) throws SQLException {
		String sql=query;
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	

	/*
	 * insertMySQL:
	 * This method is used to execute insertion queries
	 */
	private void insertMySQL(Statement stmt, String query) throws SQLException {
		String sql=query;
		stmt.executeQuery(sql);
	}
	
	/*
	 * connectMySQL:
	 * This method is used to connect to an instance of mySQL with hardcoded connection credentials and schema name
	 */

	private Connection connectMySQL() throws ClassNotFoundException,
	SQLException {
		String conURL= "jdbc:mysql://localhost:3306/igo";
		int conPort= 3306;
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(conURL,"root","");
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		return conn;
	}


	/*
	 * recieveMessage:
	 * test RMI method
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#recieveMessage(java.lang.String)
	 */

	@Override
	public void recieveMessage(String x) throws RemoteException {
		System.out.print(x+" received!");

	}



	/*displayFriendMenu:
	 * This method displays users in our database and their ids for the client to add friends
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#displayFriendMenu(int)
	 */
	@Override
	public void displayFriendMenu(int db) throws RemoteException {

	}

	/*
	 * addFriend:
	 * This method adds a friendship relationship to the database
	 * (non-Javadoc)
	 * @see remote.happyPathInterface#addFriend(int, int)
	 */
	@Override
	public void addFriend(int userid, int friendid) throws RemoteException {
		// TODO Auto-generated method stub

	}
	
	

	@Override
	public int getUsernamefromEmail(String email) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String seeHospitals(int locationid, boolean nosql)
			throws RemoteException, ClassNotFoundException, SQLException {

		Statement stmt;
		StringBuilder hospitalsJSON = null;
		hospitalsJSON.append("Hospitals [");
		Connection conn = connectMySQL();
		String query = "SELECT * FROM hospitals where locationid = " + locationid + " limit 100;";

		//STEP 4: Execute a query
		System.out.println("Creating statement- querying resteraunts...");
		stmt = conn.createStatement();
		ResultSet rs = queryMySQL(stmt, query);
		while(rs.next()){

			int restid  = rs.getInt("hospitalid");
			String name = rs.getString("name");
			String type = rs.getString("type");
			String gps = rs.getString("gps");
			String contact = rs.getString("contactnumber");
			Hospital temp = new Hospital(gps, name, locationid, restid, type, contact);
			hospitalsJSON.append(temp.toString());
			//(String co, String name, int id, int restID, String restType) 

		}

		closeConnection(stmt, conn, rs);
		hospitalsJSON.append("]");

		return hospitalsJSON.toString();

		// TODO Auto-generated method stub
	}

	@Override
	public void displayMyFriends(int userid) throws RemoteException,
			ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}





}