package test.client;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.Input;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;
import shared.model.GameModel;
import shared.model.JsonParser;
import shared.model.player.Color;
import shared.model.user.Password;
import shared.model.user.Username;
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
			GameModel game = proxy.createGame(input);
			//ping server to see if game was created
			
			GameModel game2 = new GameModel(0);
			assertEquals(game2, game);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
