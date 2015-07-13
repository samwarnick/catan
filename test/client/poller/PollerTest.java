package client.poller;

import static org.junit.Assert.*;

import org.junit.Test;

import server.IServer;
import shared.model.GameModel;
import client.controller.ModelController;
import client.proxy.MockProxyServer;

public class PollerTest {
	boolean changed = false;
	IServer proxy;
	ModelController controller;
	
	@Test
	public void testPost() {
		controller = new ModelController(0, new MockProxyServer());
		GameModel game = new GameModel(3);//version3
		controller.getGameModelFacade().setGameModel(game);
		try {
			Thread.sleep(2000);
			GameModel game2 = controller.getGameModelFacade().getGameModel();
			assertEquals(4, game2.getGameVersion());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//change gameModel
		//wait 2 seconds
		//check if the change happened here too
	}

}

