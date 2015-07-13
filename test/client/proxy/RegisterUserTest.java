package client.proxy;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;

public class RegisterUserTest {
	
	private static ProxyServer ps;
	
	@BeforeClass
	public static void init(){
		ps = ProxyServer.getInstance();	
	}
	
	@Test
	public void testRegisterUser(){
		
		boolean passed = true;
		
		try {
			ps.registerUser(new UserRegisterInput("ilovekittenswhotalk", "eros"));
		} catch (ServerException e) {
			passed = false;
		}
		
		assertTrue(passed);
		
	}
	
	//Test the login here, can't test it in the other because it is assumed by each test
	
	@Test
	public void testLoginUser(){
		
		boolean passed = true;
		
		try {
			ps.loginUser(new UserLoginInput("Sam", "sam"));
		} catch (ServerException e) {
			passed = false;
		}
		
		assertTrue(passed);
	}

}
