package client.poller;

import static org.junit.Assert.*;

import org.junit.Test;

import server.IServerFacade;
import shared.model.GameModel;
import client.controller.ModelController;

public class PollerTest {
	boolean changed = false;
	IServerFacade proxy;
	ModelController controller;
	
	@Test
	public void testPost() {
		controller = ModelController.getInstance();
		controller.setTesting(true);
		GameModel game = new GameModel(3);//version3
		controller.getGameModelFacade().setGameModel(game);
		try {
			Thread.sleep(2000);
			GameModel game2 = controller.getGameModelFacade().getGameModel();
			assertEquals(4, game2.getGameVersion());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controller.setTesting(false);
		
		//change gameModel
		//wait 2 seconds
		//check if the change happened here too
	}

}

