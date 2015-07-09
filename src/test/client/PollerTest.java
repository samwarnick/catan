package test.client;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import server.IServer;
import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesLoadInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;
import shared.model.player.Color;
import client.controller.Controller;
import client.poller.Poller;
import client.poller.UpdateGame;
import client.proxy.MockProxyServer;
import client.proxy.ProxyServer;

public class PollerTest {
	boolean changed = false;
	IServer proxy;
	Controller controller;
	
	@Test
	public void testPost() {
		controller = new Controller(0, new MockProxyServer());
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

