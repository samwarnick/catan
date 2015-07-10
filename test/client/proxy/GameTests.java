package client.proxy;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.GameCommandsGetInput;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GameResetInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.UserLoginInput;
import shared.model.player.Color;
import shared.model.user.Password;
import shared.model.user.Username;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class GameTests {

	private static ProxyServer ps;
	private static ClientCommunicator cc;
	static Username user = new Username("Sam");
	static Password pass = new Password("sam");
	
	@BeforeClass
	public static void init(){
		cc = new ClientCommunicator();
		ps = new ProxyServer(cc);
		try {
			//test login
			ps.loginUser(new UserLoginInput(user.getUsername(), pass.getPassword()));
			ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	@Test
	public void testGetGameModelVersion(){

		try {
			ps.getGameModelVersion(new GameModelVersionInput(1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testResetGame() {
		try {
			ps.resetGame(new GameResetInput(true));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}
	
	@Test
	public void testGetGameCommands(){
		try {
			ps.getGameCommands(new GameCommandsGetInput());
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}
}
