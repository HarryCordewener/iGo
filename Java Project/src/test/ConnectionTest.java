package test;

import static org.junit.Assert.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import remote.HappyPathServer;

public class ConnectionTest {

	//set up RMI server on local host
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//HappyPathServer testServer = new HappyPathServer();
		HappyPathServer.createServer();
		HappyPathServer.connectRMI(12345);
		//testServer.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//test login via SQL database
	@Test
	public void testValidationSQL() throws RemoteException, ClassNotFoundException, SQLException, NotBoundException {
		 Registry registry = LocateRegistry.getRegistry(12345);
         remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
		int loginToken;
		loginToken = stub.validateLogin("shouvik", "igo", false);
		assertNotNull(loginToken);
	}
	
	//test login via mongodb database
	@Test
	public void testValidationnoSQL() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		 Registry registry = LocateRegistry.getRegistry(12345);
         remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
		int loginToken;
		loginToken = stub.validateLogin("shouvik", "igo", true);
		assertNotNull(loginToken);
	}
	
	//testing set location id using default (Chicago IL)
	@Test
	public void testSetLoc() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException{
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
	}
	
	//Searches for hospitals and restraunts in Chicago, IL and assures the server returns atleast some dummy data.
	@Test
	public void testJSONmethods() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException{
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
		String response1, response2, response3, response4;
		response1 = stub.seeRestauraunts(locid, false);
		response2 = stub.seeHospitals(locid, false);
		response3 = stub.seeRestauraunts(locid, true);
		response4 = stub.seeHospitals(locid, true);
		assertNotNull(response1);
		assertNotNull(response2);
		assertNotNull(response3);
		assertNotNull(response4);
		
	}
	
	@Test
	public void testJSONmethods2() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException{
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
		String response1, response2, response3, response4;
		response1 = stub.seeAirports(locid, true);
		response2 = stub.seeBanks(locid, true);
		response3 = stub.seeBars(locid, true);
		response4 = stub.seeHotels(locid, true);
		assertNull(response1);
		assertNull(response2);
		assertNull(response3);
		assertNull(response4);
		
	}
	
	@Test
	public void testJSONmethods3() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException{
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
		String response1, response2, response3, response4;
		response1 = stub.seeTheaters(locid, true);
		response2 = stub.seePolice(locid, true);
		response3 = stub.seeStore(locid, true);
		response4 = stub.seeSchool(locid, true);
		assertNull(response1);
		assertNull(response2);
		assertNull(response3);
		assertNull(response4);
		
	}
	
	//test login/logout via SQL database
	@Test
	public void testExit() throws RemoteException, ClassNotFoundException, SQLException, NotBoundException {
		 Registry registry = LocateRegistry.getRegistry(12345);
         remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
		int loginToken;
		loginToken = stub.validateLogin("shouvik", "igo", false);
		assertNotNull(loginToken);
		stub.Logout(loginToken);
		
	}

}
