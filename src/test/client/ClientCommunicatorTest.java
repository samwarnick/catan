package test.client;

import static org.junit.Assert.*;

import org.junit.Test;

import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.UserLoginInput;
import shared.model.Game;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class ClientCommunicatorTest {
	
	@Test
	public void testLogin() {
		boolean error = false;
		ClientCommunicator cc = new ClientCommunicator();
		ProxyServer proxy = new ProxyServer(cc);
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
		ClientCommunicator cc = new ClientCommunicator();
		ProxyServer proxy = new ProxyServer(cc);
		GamesCreateInput input = new GamesCreateInput("game1", false, false, false);
		try {
			Game game = proxy.createGame(input);
			//ping server to see if game was created
			Game game2 = new Game(game.getGameID(), "game1", game.getPlayers());
			assertEquals(game2, game);
		} catch (ServerException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	
	}

}
