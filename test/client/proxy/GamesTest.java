package client.proxy;

import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesListInput;
import shared.communication.input.UserLoginInput;
import shared.definitions.CatanColor;
import client.proxy.ProxyServer;

public class GamesTest {

	private static ProxyServer ps;
	static String user = "Sam";
	static String pass = "sam";
	
	@BeforeClass
	public static void init(){
		ps = ProxyServer.getInstance();
		try {
			ps.loginUser(new UserLoginInput(user, pass));
		//	ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
		}
	}
	
	@Test
	public void testListGames(){
		try {
			ps.listGames(new GamesListInput());
		} catch (ServerException e) {
		}
		
	}

	
	@Test
	public void testCreateGame(){
		
		try {
			ps.createGame(new GamesCreateInput("x", true, true, true));
		} catch (ServerException e) {
		}
		
	}

	
	@Test
	public void testJoinGame(){
		try {
			ps.joinGame(new GamesJoinInput(0,CatanColor.RED));
		} catch (ServerException e) {
		}
		
	}
	
//	@Test
//	public void testSaveGame(){
//		try {
//			ps.saveGame(new GamesSaveInput(0, "file"));
//		} catch (ServerException e) {
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
//			assertTrue(false);
//		}
//		
//	}
	
}
