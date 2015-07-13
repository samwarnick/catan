package client.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.UserLoginInput;
import client.data.GameInfo;
import client.proxy.ProxyServer;

public class ClientCommunicatorTest {
	
	@Test
	public void testLogin() {
		boolean error = false;
		ProxyServer proxy = ProxyServer.getInstance();
		UserLoginInput input = new UserLoginInput("Sam", "sam");
		try {
			proxy.loginUser(input);
		} catch (ServerException e) {
			e.printStackTrace();
			error = true;
		}
		assertFalse(error);
	}
	
	@Test
	public void testPost() {
		ProxyServer proxy = ProxyServer.getInstance();
		GamesCreateInput input = new GamesCreateInput("game1", false, false, false);
		try {
			GameInfo game = proxy.createGame(input);
			//ping server to see if game was created
			GameInfo game2 = new GameInfo();
			game2.setId(game.getId());
			game2.setTitle("game1");
			game2.addPlayer(null);
			game2.addPlayer(null);
			game2.addPlayer(null);
			game2.addPlayer(null);
			assertEquals(game2.getId(), game.getId());
			assertEquals(game2.getTitle(), game.getTitle());
			
		} catch (ServerException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	
	}

}
