package test;

import static org.junit.Assert.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RmiServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import remote.HappyPathServer;

public class ConnectionTest {

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

	@Test
	public void testValidationSQL() {
		 Registry registry = LocateRegistry.getRegistry(12345);
         remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
		int loginToken;
		loginToken = stub.validateLogin("shouvik", "igo", false);
		assertNotNull(loginToken);
	}
	
	@Test
	public void testValidationnoSQL() {
		 Registry registry = LocateRegistry.getRegistry(12345);
         remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
		int loginToken;
		loginToken = stub.validateLogin("shouvik", "igo", true);
		assertNotNull(loginToken);
	}
	
	@Test
	public void testSetLoc(){
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
	}
	
	@Test
	public void testJSON(){
		Registry registry = LocateRegistry.getRegistry(12345);
        remote.happyPathInterface stub = (remote.happyPathInterface) registry.lookup("rmiServer");
        int locid;
		locid = stub.setLocation("Chicago", "IL", false);
		assertNotNull(locid);
		String response1, response2;
		response1 = stub.seeRestauraunts(locid, false);
		response2 = stub.seeHospitals(locid, false);
		assertNotNull(response1);
		assertNotNull(response2);
		
	}

}
