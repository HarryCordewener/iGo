package remote;

import java.rmi.*;
import java.util.ArrayList;

/*
 * The ReceiveMessageInterface holds all methods to interact with the server stored accounts
 * 
 * NOTE receiveMessage, passArrayList and updateArrayList were for testing only and not implemented by the client
 */
public interface happyPathInterface extends Remote{

void recieveMessage(String x);
	

	void addAccount();
	
	void validateLogin (String user, String pass)throws RemoteException;
	void setDatabaseType(int selection)throws RemoteException;
	void setLocation (String loc)throws RemoteException;//input a city
	
	void seeResteraunts()throws RemoteException;
	void seeHospitals()throws RemoteException;

}
