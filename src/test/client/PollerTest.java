package test.client;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

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
	ProxyServer proxy;
	Controller controller;
	
	@Test
	public void testPost() {
		controller = new Controller(0,new MockProxyServer());
		proxy = controller.getProxyServer();
		GamesCreateInput createGame = new GamesCreateInput("game1", false, false, false);
		GamesJoinInput joinGame = new GamesJoinInput(0, Color.BLUE);
		try {
			proxy.createGame(createGame);
			proxy.joinGame(joinGame);
		} catch (ServerException e) {
			assertTrue(false);
		}

		Timer timer = new Timer();
		timer.schedule(checkChange(), 2001);
		//change game via proxyServer
		//wait 60000
		//check if the change happened here too
	}
	
	private TimerTask checkChange()
	{
		GamesLoadInput loadGame = new GamesLoadInput("game1");
		try {
			GameModel game = proxy.loadGame(loadGame); // GameModel game = controller.getGameModelFacade().getGameModel();
			assertEquals(game.getPlayers().get(0).getColor(), CatanColor.BLUE);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
