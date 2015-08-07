package client.proxy;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.UserLoginInput;
import shared.definitions.CatanColor;
import client.proxy.ProxyServer;

public class GameTests {

	private static ProxyServer ps;
	static String user = "Sam";
	static String pass = "sam";
	
	@BeforeClass
	public static void init(){
		ps = ProxyServer.getInstance();
		try {
			//test login
			ps.loginUser(new UserLoginInput(user, pass));
			ps.joinGame(new GamesJoinInput(0,CatanColor.RED));
		} catch (ServerException e) {
		}
	}
	
	@Test
	public void testGetGameModelVersion(){

		try {
			ps.getGameModelVersion(new GameModelVersionInput(1));
		} catch (ServerException e) {
		}
	}
}
