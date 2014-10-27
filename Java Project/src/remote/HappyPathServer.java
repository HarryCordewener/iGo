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
		
		if (databaseSelection ==0){
		initializeMySQL(mySQLurl, mySQLport);
		}
		else if (databaseSelection == 1){
			//connect mongodb here
		}
	}

	private static void initializeMySQL(String url, int port) {
		String conURL= url;
		int conPort= port;
		Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(conURL,"root","");

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM testuser";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("userID");
		         String username = rs.getString("username");
		         String password = rs.getString("password");
		         boolean superuser = rs.getBoolean("super");
		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Username: " + username);
		         System.out.print(", Password " + password);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
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

	@Override
	public void recieveMessage(String x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLogin(String user, String pass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDatabaseType(int selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(String loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seeResteraunts() {
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

		      //STEP 4: Execute a query
		      System.out.println("Creating statement- querying resteraunts...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM resteraunts";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("userID");
		         String username = rs.getString("username");
		         String password = rs.getString("password");
		         boolean superuser = rs.getBoolean("super");
		   
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
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
		
	}

	@Override
	public void seeHospitals() {
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

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM testuser";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("userID");
		         String username = rs.getString("username");
		         String password = rs.getString("password");
		         boolean superuser = rs.getBoolean("super");
		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Username: " + username);
		         System.out.print(", Password " + password);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
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
		
	}

	@Override
	public void addAccount() {
		// TODO Auto-generated method stub
		
	}
	


}