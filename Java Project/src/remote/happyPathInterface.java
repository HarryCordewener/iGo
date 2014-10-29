

package remote;


import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * The ReceiveMessageInterface holds all methods to interact with the server stored accounts
 * 
 * NOTE receiveMessage, passArrayList and updateArrayList were for testing only and not implemented by the client
 */
public interface happyPathInterface extends Remote{

	void recieveMessage(String x)throws RemoteException;//test method
	
	void addAccount(String username, String password, String email, String mobileNumber)throws RemoteException, ClassNotFoundException, SQLException;
	void displayFriendMenu(int db) throws RemoteException, ClassNotFoundException, SQLException;//displays all users in database
	void displayMyFriends (int userid)throws RemoteException, ClassNotFoundException, SQLException;//displays user's friends
	void addFriend(int userid, int friendid) throws RemoteException, ClassNotFoundException, SQLException; //Must add friend and apply to BOTH databases!
	
	int validateLogin (String user, String pass, boolean db)throws RemoteException, ClassNotFoundException, SQLException;//checks username and password 
	//int getMongoUserID(String user);
	//Validate login only verifies using SQL
	//if validateLogin returns 0, login was unsuccessuful, do not show menu,
	//otherwise validatelogin will return the userID (int) which can be used to add other friends
	
	int setLocation (String city, String state, boolean nosql)throws RemoteException, ClassNotFoundException, SQLException;//input a city and state to lookup a location in the database
	
	int getUsernamefromEmail(String email) throws RemoteException, ClassNotFoundException, SQLException;
	
	String seeResteraunts(int locationid, boolean nosql)throws RemoteException, ClassNotFoundException, SQLException;
	//will return a lsit of restraunts with all attributes in JSON
	
	String seeHospitals(int locationid, boolean nosql)throws RemoteException, ClassNotFoundException, SQLException;
	//will return a lsit of hospitals with all attributes in JSON

}
