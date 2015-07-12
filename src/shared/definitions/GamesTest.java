package shared.definitions;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesListInput;
import shared.communication.input.GamesLoadInput;
import shared.communication.input.GamesSaveInput;
import shared.communication.input.UserLoginInput;
import shared.model.player.Color;
import shared.model.user.Password;
import shared.model.user.Username;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class GamesTest {

	private static ProxyServer ps;
	static Username user = new Username("Sam");
	static Password pass = new Password("sam");
	
	@BeforeClass
	public static void init(){
		ps = ProxyServer.getInstance();
		try {
			ps.loginUser(new UserLoginInput(user.getUsername(), pass.getPassword()));
		//	ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testListGames(){
		try {
			ps.listGames(new GamesListInput());
		} catch (ServerException e) {
			assertTrue(false);
		}
		
	}

	
	@Test
	public void testCreateGame(){
		
		try {
			ps.createGame(new GamesCreateInput("x", true, true, true));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		
	}

	
	@Test
	public void testJoinGame(){
		try {
			ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
//	@Test
//	public void testSaveGame(){
//		try {
//			ps.saveGame(new GamesSaveInput(0, "file"));
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			assertTrue(false);
//		}
//		
//	}
//
//	
//	@Test
//	public void testLoadGame() {
//		try {
//			ps.loadGame(new GamesLoadInput("file"));
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			assertTrue(false);
//		}
//		
//	}
	
}
