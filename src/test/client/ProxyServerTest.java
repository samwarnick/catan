package test.client;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.communication.input.GameCommandsGetInput;
import shared.communication.input.GameCommandsPostInput;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GameResetInput;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesListInput;
import shared.communication.input.GamesLoadInput;
import shared.communication.input.GamesSaveInput;
import shared.communication.input.Input;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;
import shared.communication.input.UtilChangeLogLevelInput;
import shared.communication.input.move.AcceptTradeInput;
import shared.communication.input.move.BuildCityInput;
import shared.communication.input.move.BuildRoadInput;
import shared.communication.input.move.BuildSettlementInput;
import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.DiscardCardsInput;
import shared.communication.input.move.FinishTurnInput;
import shared.communication.input.move.MaritimeTradeInput;
import shared.communication.input.move.OfferTradeInput;
import shared.communication.input.move.PlayMonopolyInput;
import shared.communication.input.move.PlayMonumentInput;
import shared.communication.input.move.PlayRoadBuildingInput;
import shared.communication.input.move.PlaySoldierInput;
import shared.communication.input.move.PlayYearOfPlentyInput;
import shared.communication.input.move.RobPlayerInput;
import shared.communication.input.move.RollNumberInput;
import shared.communication.input.move.SendChatInput;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.GameModel;
import shared.model.bank.ResourceHand;
import shared.model.player.Color;
import shared.model.user.Password;
import shared.model.user.Username;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class ProxyServerTest {
	
	private static ProxyServer ps;
	private static ClientCommunicator cc;
	static Username user = new Username("Sam");
	static Password pass = new Password("sam");
	
	@BeforeClass
	public static void init(){
		cc = new ClientCommunicator();
		ps = new ProxyServer(cc);
		try {
			ps.loginUser(new UserLoginInput(user, pass));
		} catch (ServerException e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	
	@Test
	public void testRegisterUser(){
		Username newUN = new Username("xxx");
		Password newP = new Password("xxx");
		
		boolean passed = false;
		
		try {
			ps.registerUser(new UserRegisterInput(newUN, newP));
		} catch (ServerException e) {
			passed = false;
		}
		
		assert(passed);
		
	}
	
	
	
//	@Test
//	public void testLoginUser(){
//		try {
//			ps.loginUser(new UserLoginInput(user, pass));
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			assert(false);
//		}
//		
//	}

	
	

	
	@Test
	public void testListGames(){
		try {
			//ps.loginUser(new UserLoginInput(user, pass));
			ps.listGames(new GamesListInput());
		} catch (ServerException e) {
			assert(false);
		}
		
	}

	
	@Test
	public void testCreateGame(){
		
		try {
			ps.createGame(new GamesCreateInput("x", true, true, true));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}

	
	@Test
	public void testJoinGame(){
		try {
			ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}

	
	@Test
	public void testSaveGame(){
		try {
			ps.saveGame(new GamesSaveInput(0, "file.txt"));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
		
	}

	
	@Test
	public void testLoadGame() {
		try {
			ps.loadGame(new GamesLoadInput("x"));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
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

	
	@Test
	public void testPostGameCommands(){
		try {
			ps.postGameCommands(new GameCommandsPostInput(null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
//	@Test
//	public void testChangeLogLevel(UtilChangeLogLevelInput input){
//		ps.changeLogLevel(new UtilChangeLog)
//	}

	
//	@Test
//	public void testSendChat(SendChatInput input){
//		ps.sendChat(SendChatInput());
//	}

	
	@Test
	public void testAcceptTrade(){
		try {
			ps.acceptTrade(new AcceptTradeInput(0,false));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testDiscardCards(){
		try {
			ps.discardCards(new DiscardCardsInput(0, new ResourceHand()));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testRollNumber() {
		try {
			ps.rollNumber(new RollNumberInput(2,2));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assert(false);
		}
	}

	
	@Test
	public void testBuildRoad(){
		try {
			ps.buildRoad(new BuildRoadInput(0,false,
					new EdgeLocation(new HexLocation(1,1), EdgeDirection.North)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testBuildSettlement(){
		try {
			ps.buildSettlement(new BuildSettlementInput(0,false,
					new VertexLocation(new HexLocation(1,1), VertexDirection.East)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testBuildCity(){
		try {
			ps.buildCity(new BuildCityInput("Joy", 0,
					new VertexLocation(new HexLocation(1,1), VertexDirection.East)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testOfferTrade(){
		try {
			ps.offerTrade(new OfferTradeInput(0, new ResourceHand(), 1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testMaritimeTrade(){
		try {
			ps.maritimeTrade(new MaritimeTradeInput(0, 1, ResourceType.BRICK, ResourceType.BRICK));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testRobPlayer() {
		try {
			ps.robPlayer(new RobPlayerInput(0, new HexLocation(1,1), 1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testFinishTurn(){
		try {
			ps.finishTurn(new FinishTurnInput(1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testBuyDevCard(){
		try {
			ps.buyDevCard(new BuyDevCardInput(1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testPlaySoldier(){
		try {
			ps.playSoldier(new PlaySoldierInput(0, null, 0));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testPlayYearOfPlenty(){
		try {
			ps.playYearOfPlenty(new PlayYearOfPlentyInput(0, null, null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testPlayRoadBuilding(){
		try {
			ps.playRoadBuilding(new PlayRoadBuildingInput(null, 0, null, null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testPlayMonopoly(){
		try {
			ps.playMonopoly(new PlayMonopolyInput(0, null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	
	@Test
	public void testPlayMonument(){
		try {
			ps.playMonument(new PlayMonumentInput(0));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assert(false);
		}
	}

	@Test
	public void testErrors(){

		boolean passed = false;

		try {
			cc.post(new Input("Bad/Path"));
		} catch (ServerException e) {

			passed = true;
		}
		
		assert(passed);
	

	}
}
