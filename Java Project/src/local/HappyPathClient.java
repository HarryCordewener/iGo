package local;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.*;

public class HappyPathClient {
	@SuppressWarnings("resource")
	static
	 Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)throws RemoteException {

        String host = (args.length < 1) ? null : args[0];
        try {
        	boolean dbMenu;
            Registry registry = LocateRegistry.getRegistry(12345);
            remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
            
            welcome();
            
            dbMenu = chooseDb();
            
            System.out.println("\nThank you for your input. Your prefrences are set.");
            
            login(stub, dbMenu);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
	
	private static void login(remote.happyPathInterface stub, boolean dbMenu) throws RemoteException, ClassNotFoundException, SQLException, JSONException {

    	int loginChoice;
    	String city = "Chicago", state = "IL";
		System.out.println("\n1. Existing users.. Log In");
        System.out.println("2. New users.. Sign Up");
        System.out.println("3. Exit");
        loginChoice = input.nextInt();
        	if(loginChoice == 2)
        	{
        	addAccount(stub, dbMenu);
        	}
        	else
        		if(loginChoice == 1)
        		{
        			
        			int loginToken = validateLogin(stub, dbMenu);
        			if(loginToken == 0)
        			{
        				System.err.println("Login failed. Please try again.\n");
        				login(stub, dbMenu);
        			}
        			else
        			{
        				setLocation(stub, dbMenu, city, state);
        			}
        		}
        		else
        			if(loginChoice == 3)
        			{
        				System.out.println("\nThank you for using IGo!\n");
        				System.exit(1);
        			}
        		else
        		{
        			System.err.println("Wrong Choice.");
        		}
		
	}

	private static void setLocation(remote.happyPathInterface stub, boolean dbMenu, String city, String state) throws RemoteException, ClassNotFoundException, SQLException, JSONException 
	{
		int itemId = 0;
		int changeLoc = 2;
		System.out.println("\nYour current City is set to " +city+ " and state is set to " +state);
		System.out.println("You can continue with this location or change it."); 
		System.out.println("To change the location input 1 otherwise input 2. To exit the application input 3.");
		changeLoc = input.nextInt();
		
		if(changeLoc == 1)
		{
		System.out.println("\n--Set Location and State--");
		System.out.println("Please enter a location (Eg: Chicago, Naperville etc");
		city = input.next();
		System.out.println("Please enter the State ");
		state = input.next();
		itemId = stub.setLocation(city, state, dbMenu);
		}
		else
			if(changeLoc == 2)
		{
			itemId = stub.setLocation(city, state, dbMenu);
		}
			else
			{
				System.out.println("\nThank you for using IGo!\n");
				System.exit(1);
			}
		
		
		if(itemId != 0)
		{
			String response;
			int ch;
			System.out.println("Please select a business type that you wish to search.. ");
			System.out.println("\n1. Restaurents");
            System.out.println("2. Hospitals\n");
            ch = input.nextInt();
            
            if(ch == 2)
            {
            	response = stub.seeHospitals(itemId, dbMenu);
            	System.out.print(response);
            	final JSONObject obj = new JSONObject(response);
                final JSONArray hosdata = obj.getJSONArray("Hospitals");
                final int n = hosdata.length();
                if(n != 0)
                {
                System.out.println("\nWe found "+n+" Hospitals in " +city+ ", " +state+ ":\n");
                for (int i = 0; i < n; ++i) {
                    final JSONObject hospital = hosdata.getJSONObject(i);
                    System.out.println("Id: " +hospital.getInt("hospitalid"));
                    System.out.println("Name: "+hospital.getString("name"));
                    System.out.println("Type: "+hospital.getString("type"));
                    System.out.println("Coordinates: "+hospital.getString("gps")+"\n");
                    System.out.println("Contact: "+hospital.getString("contactnumber")+"\n");
                  }
                }
                else
                {
                	System.out.println("\nWe found "+n+" Hospitals in " +city+ ", " +state+ "!\n");
                }
            }
            else
            	if(ch == 1)
            	{
            		response = stub.seeRestauraunts(itemId, dbMenu);
            		final JSONObject obj = new JSONObject(response);
                    final JSONArray resdata = obj.getJSONArray("Restaurants");
                    final int n = resdata.length();
                    
                    if(n != 0)
                    {
                    System.out.println("\nWe found "+n+" Restaurents in " +city+ ", " +state+ ":\n");
                    for (int i = 0; i < n; ++i) {
                        final JSONObject restraunt = resdata.getJSONObject(i);
                        System.out.println("Id: " +restraunt.getInt("restaurantid"));
                        System.out.println("Name: "+restraunt.getString("name"));
                        System.out.println("Type: "+restraunt.getString("type"));
                        System.out.println("Coordinates: "+restraunt.getString("gps")+"\n");
                      }
                    }
                    else
                    {
                    	System.out.println("\nWe found "+n+" Restaurents in " +city+ ", " +state+ "!\n");
                    	
                    }
            	}
			
		}
		else
		{
			System.err.println("This City doesnt exixt in our Database. Please try again!\n");
			city="Chicago"; state = "IL";
			
			
			//setLocation(stub,dbMenu, city, state);
		}
		
		System.out.println("What would you like to do now..  ");
		System.out.println("\n1. Another Search");
        System.out.println("2. Exit\n");
		int menuCh = input.nextInt();
		
		if(menuCh == 1)
		{
			setLocation(stub, dbMenu, city, state);
		}
		else
		{
			System.out.println("\nThank you for using IGo!\n");
			System.exit(1);
		}
	}

	private static void addAccount(remote.happyPathInterface stub, boolean dbMenu) throws RemoteException, ClassNotFoundException, SQLException{
		
		String username, password, email, mobile; 
		System.out.println("\n--New Account Creation--");
		System.out.println("Please enter your Username");
		username = input.next();
		System.out.println("Please enter a Password");
		password = input.next();
		System.out.println("Please enter your Email");
		email = input.next();
		System.out.println("Please enter your Mobile Number");
		mobile = input.next();
		try
		{
		stub.addAccount(username, password, email, mobile);
		System.out.println("\nAccount created!");
		
		login(stub,dbMenu);
		}
		catch(Exception e)
		{
			System.err.println("\nSeems like the Server is Down. Please try after some time. ");
		}
		
	}
	
	private static int validateLogin(remote.happyPathInterface stub, boolean db){
		String username, password;
		int loginToken;
		System.out.println("\n--Existing User Login--");
		System.out.println("Please enter your Username");
		username = input.next();
		System.out.println("Please enter a Password");
		password = input.next();
		
		try
		{
		//System.out.print(stub.seeHospitals(1, false));
		 loginToken = stub.validateLogin(username, password, db);
		 
		 
		
         return loginToken;
		}
		catch(Exception e)
		{
			System.err.println("\nSeems like the Server is Down. Please try after some time. ");
			e.printStackTrace();
			return 0;
		}
	}
	
	private static boolean chooseDb() {
		// TODO Auto-generated method stub
		
		int choiceDB;
		System.out.println("\nPlease choose a DataBase that you want to access:");
		System.out.println("1. MySql");
		System.out.println("2. MongoDB");
		System.out.println("3. Exit");
		
		choiceDB = input.nextInt();
		//use switch case here
		if(choiceDB == 1)
			return false;
		else if(choiceDB == 2)
			return true;
		else if(choiceDB == 3)
		{
			System.out.println("\nThank you for using IGo!\n");
			System.exit(1);
		}
		else
		{
			System.err.println("Wrong Input!");
			chooseDb();
		}
		return false;
		
	}

	private static void welcome() {
		String address = null;
		
		try{  
			address = (InetAddress.getLocalHost()).toString();
		}
		catch(Exception e){
			System.err.println("can't get inet address.");
		}
		
		System.out.println("Welcome to IGo");
		System.out.println("This address is " + address);
		
	}

}
