package test.client;

import static org.junit.Assert.*;

import org.junit.Test;

import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.Input;
import shared.model.GameModel;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class ClientCommunicatorTest {
	
	@Test
	public void testPost() {
		ClientCommunicator cc = new ClientCommunicator();
		ProxyServer proxy = new ProxyServer(cc);
		GamesCreateInput input = new GamesCreateInput("game1", false, false, false);
		try {
			GameModel game = proxy.createGame(input);
			//ping server to see if game was created
			
			GameModel game2 = new GameModel(0);
			assertEquals(game, game2);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
