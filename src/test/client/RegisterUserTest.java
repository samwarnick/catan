package test.client;

import org.junit.BeforeClass;
import org.junit.Test;

import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;
import shared.model.user.Password;
import shared.model.user.Username;

public class RegisterUserTest {
	
	private static ProxyServer ps;
	private static ClientCommunicator cc;
	
	@BeforeClass
	public static void init(){
		cc = new ClientCommunicator();
		ps = new ProxyServer(cc);
	}
	
	@Test
	public void testRegisterUser(){
		
		boolean passed = false;
		
		try {
			ps.registerUser(new UserRegisterInput("ilovekittenswhotalk", "eros"));
		} catch (ServerException e) {
			passed = false;
		}
		
		assert(passed);
		
	}
	
	//Test the login here, can't test it in the other because it is assumed by each test
	
	@Test
	public void testLoginUser(){
		try {
			ps.loginUser(new UserLoginInput("Sam", "sam"));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}

}
