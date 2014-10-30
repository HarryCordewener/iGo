package remote;

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
        	int loginChoice;
            Registry registry = LocateRegistry.getRegistry(12345);
            happyPathInterface stub = (happyPathInterface) registry.lookup("rmiServer");
            
            welcome();
            
            dbMenu = chooseDb();
            System.out.println("\nThank you for your input. Your prefrences are set.");
            
            System.out.println("\n1. Exixting users.. Log In");
            System.out.println("2. New users.. Sign Up");
            loginChoice = input.nextInt();
            	if(loginChoice == 2)
            	{
            	addAccount(stub);
            	}
            	else
            		if(loginChoice == 1)
            		{
            			
            			int loginToken = validateLogin(stub, dbMenu);
            			if(loginToken == 0)
            			{
            				System.out.println("Login falied. Please try again.\n");
            			}
            			else
            			{
            				setLocation(stub, dbMenu);
            			}
            		}
            		else
            		{
            			System.out.println("Wrong Choice.");
            		}
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
	
	private static void setLocation(happyPathInterface stub, boolean dbMenu) throws RemoteException, ClassNotFoundException, SQLException, JSONException {
		// TODO Auto-generated method stub
		String city, state;
		System.out.println("\n--Set Location and State--");
		System.out.println("Please enter a location (Eg: Chicago, Naperville etc");
		city = input.next();
		System.out.println("Please enter the State ");
		state = input.next();
		int itemId = stub.setLocation(city, state, dbMenu);
		
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
                
                System.out.println("\nWe found "+n+" hospitals:\n");
                for (int i = 0; i < n; ++i) {
                    final JSONObject restraunt = hosdata.getJSONObject(i);
                    System.out.println(restraunt.getInt("hospitalid"));
                    System.out.println(restraunt.getString("name"));
                    System.out.println(restraunt.getString("type"));
                    System.out.println(restraunt.getString("gps")+"\n");
                    System.out.println(restraunt.getString("contactnumber")+"\n");
                  }
            }
            else
            	if(ch == 1)
            	{
            		response = stub.seeRestauraunts(itemId, dbMenu);
            		final JSONObject obj = new JSONObject(response);
                    final JSONArray resdata = obj.getJSONArray("Restaurants");
                    final int n = resdata.length();
                    
                    System.out.println("\nWe found "+n+" restaurents:\n");
                    for (int i = 0; i < n; ++i) {
                        final JSONObject restraunt = resdata.getJSONObject(i);
                        System.out.println(restraunt.getInt("restaurantid"));
                        System.out.println(restraunt.getString("name"));
                        System.out.println(restraunt.getString("type"));
                        System.out.println(restraunt.getString("gps")+"\n");
                       
                       
                      }
            	}
			
		}
		
	}

	private static void addAccount(happyPathInterface stub) throws RemoteException, ClassNotFoundException, SQLException{
		
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
		}
		catch(Exception e)
		{
			System.out.println("\nSeems like the Server is Down. Please try after some time. ");
		}
		
	}
	
	private static int validateLogin(happyPathInterface stub, boolean db){
		String username, password;
		int loginToken;
		System.out.println("\n--Exixting User Login--");
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
			System.out.println("\nSeems like the Server is Down. Please try after some time. ");
			e.printStackTrace();
			return 0;
		}
	}
	
	private static boolean chooseDb() {
		// TODO Auto-generated method stub
		
		int choiceDB;
		System.out.println("");
		System.out.println("Please choose a DataBase that you want to access:");
		System.out.println("1. MySql");
		System.out.println("2. MongoDB");
		
		choiceDB = input.nextInt();
		//use switch case here
		if(choiceDB == 1)
			return false;
		else if(choiceDB == 2)
			return true;
		else
		{
			System.out.println("Please enter 1 or 2 as choice");
		    choiceDB = input.nextInt();
		    if(choiceDB == 1)
				return false;
			else if(choiceDB == 2)
				return true;
		}
		return false;
		
	}

	private static void welcome() {
		String address = null;
		
		try{  
			address = (InetAddress.getLocalHost()).toString();
		}
		catch(Exception e){
			System.out.println("can't get inet address.");
		}
		
		System.out.println("Welcome to IGo");
		System.out.println("This address is " + address);
		
	}

}
