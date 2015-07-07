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
import shared.model.GameModel;
import shared.model.user.Password;
import shared.model.user.Username;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;

public class ProxyServerTest {
	
	private ProxyServer ps;
	private ClientCommunicator cc;
	Username user = new Username("Sam");
	Password pass = new Password("sam");
	
	@BeforeClass
	public void init(){
		cc = new ClientCommunicator();
		ps = new ProxyServer(cc);
	}
	
	
	@Test
	public void testRegisterUser(UserRegisterInput input){
		Username newUN = new Username("xxx");
		Password newP = new Password("xxx");
		
		boolean passed = false;
		
		try {
			ps.registerUser(new UserRegisterInput(user, pass));
		} catch (ServerException e) {
			passed = true;
		}
		
		assert(passed);
		
		passed = true;
		
		try {
			ps.registerUser(new UserRegisterInput(newUN, newP));
		} catch (ServerException e) {
			passed = false;
		}
		
		assert(passed);
		
	}
	
	
	
	@Test
	public void testLoginUser(UserLoginInput input){
		try {
			ps.loginUser(new UserLoginInput(user, pass));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

	
	@Test
	public void testListGames(GamesListInput input){
		try {
			//ps.loginUser(new UserLoginInput(user, pass));
			ps.listGames(new GamesListInput());
		} catch (ServerException e) {
			assert(false);
		}
		
	}

	
	@Test
	public void testCreateGame(GamesCreateInput input){
		
		ps.createGame(new GamesCreateInput());
		
	}

	
	@Test
	public void testJoinGame(GamesJoinInput input){
		ps.joinGame(new GamesJoinInput());
		
	}

	
	@Test
	public void testSaveGame(GamesSaveInput input){
		ps.saveGame(new GamesSaveInput());
		
	}

	
	@Test
	public void testLoadGame(GamesLoadInput input) {
		ps.loadGame(new GamesLoadInput());
		
	}

	
	@Test
	public void testGetGameModelVersion(GameModelVersionInput input){

		ps.getGameModelVersion(new GameModelVersionInput());
	}

	
	@Test
	public void testResetGame(GameResetInput input) {
		ps.resetGame(new GameResetInput());
		
	}

	
	@Test
	public void testGetGameCommands(GameCommandsGetInput input){
		ps.getGameCommands(new GamesCommandsGetInput());
		
	}

	
	@Test
	public void testPostGameCommands(GameCommandsPostInput input){
		ps.postGameCommands(new GameCommandsPostInput());
	}

	
//	@Test
//	public void testChangeLogLevel(UtilChangeLogLevelInput input){
//		ps.changeLogLevel(new UtilChangeLog)
//	}

	
	@Test
	public void testSendChat(SendChatInput input){
		ps.sendChat(SendChatInput());
	}

	
	@Test
	public void testAcceptTrade(AcceptTradeInput input){
		ps.acceptTrade(new AcceptTradeInput());
	}

	
	@Test
	public void testDiscardCards(DiscardCardsInput input){
		ps.discardCards(new DiscardCardsInput());
	}

	
	@Test
	public void testRollNumber(RollNumberInput input) {
		ps.rollNumber(new RollNumberInput());
	}

	
	@Test
	public void testBuildRoad(BuildRoadInput input){
		ps.buildRoad(new BuildRoadInput());
	}

	
	@Test
	public void testBuildSettlement(BuildSettlementInput input){
		ps.buildSettlement(new BuildSettlementInput());
	}

	
	@Test
	public void testBuildCity(BuildCityInput input){
		ps.buildCity(new BuildCityInput());
	}

	
	@Test
	public void testOfferTrade(OfferTradeInput input){
		ps.offerTrade(new OfferTradeInput());
	}

	
	@Test
	public void testMaritimeTrade(MaritimeTradeInput input){
		ps.maritimeTrade(new MaritimeTradeInput());
	}

	
	@Test
	public void testRobPlayer(RobPlayerInput input) {
		ps.robPlayer(new RobPlayerInput());
	}

	
	@Test
	public void testFinishTurn(FinishTurnInput input){
		ps.finishTurn(new FinishTurnInput());
	}

	
	@Test
	public void testBuyDevCard(BuyDevCardInput input){
		ps.buyDevCard(new BuyDevCardInput());
	}

	
	@Test
	public void testPlaySoldier(PlaySoldierInput input){
		ps.playSoldier(new PlaySoldierInput())
	}

	
	@Test
	public void testPlayYearOfPlenty(PlayYearOfPlentyInput input){
		ps.playYearOfPlenty(new PlayYearOfPlentyInput());
	}

	
	@Test
	public void testPlayRoadBuilding(){
		ps.playRoadBuilding(new PlayRoadBuildingInput());
	}

	
	@Test
	public void testPlayMonopoly(){
		ps.playMonopoly(new PlayMonopolyInput());
	}

	
	@Test
	public void testPlayMonument(){
		try {
			ps.playMonument(new PlayMonumentInput(0));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testErrors(){
		cc.post(new Input("Bad/Path"));
	}

}
