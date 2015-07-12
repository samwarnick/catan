package shared.definitions;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import server.ServerException;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.UserLoginInput;
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
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.bank.ResourceHand;
import shared.model.player.Color;
import shared.model.user.Password;
import shared.model.user.Username;
import client.proxy.ProxyServer;

public class MoveTest {

	private static ProxyServer ps;
	static Username user = new Username("Sam");
	static Password pass = new Password("sam");
	
	@BeforeClass
	public static void init(){
		ps = ProxyServer.getInstance();
		
		try {
			ps.loginUser(new UserLoginInput(user.getUsername(), pass.getPassword()));
			ps.joinGame(new GamesJoinInput(0,Color.RED));
		} catch (ServerException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPlayYearOfPlenty(){
		try {
			ps.playYearOfPlenty(new PlayYearOfPlentyInput(0, null, null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testPlayRoadBuilding(){
		try {
			ps.playRoadBuilding(new PlayRoadBuildingInput(0, null, null));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testPlayMonopoly(){
		try {
			ps.playMonopoly(new PlayMonopolyInput(0, "brick"));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testPlayMonument(){
		try {
			ps.playMonument(new PlayMonumentInput(0));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@Test
	public void testMaritimeTrade(){
		try {
			ps.maritimeTrade(new MaritimeTradeInput(0, 1, ResourceType.BRICK, ResourceType.BRICK));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testRobPlayer() {
		try {
			ps.robPlayer(new RobPlayerInput(0, new HexLocation(1,1), 1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testFinishTurn(){
		try {
			ps.finishTurn(new FinishTurnInput(1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testBuyDevCard(){
		try {
			ps.buyDevCard(new BuyDevCardInput(1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testPlaySoldier(){
		try {
			ps.playSoldier(new PlaySoldierInput(0, null, 0));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@Test
	public void testAcceptTrade(){
		try {
			ps.acceptTrade(new AcceptTradeInput(0,false));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testDiscardCards(){
		try {
			ps.discardCards(new DiscardCardsInput(1, new ResourceHand()));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}


	
	@Test
	public void testBuildRoad(){
		try {
			ps.buildRoad(new BuildRoadInput(0,false,
					new EdgeLocation(new HexLocation(1,1), EdgeDirection.North)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testBuildSettlement(){
		try {
			ps.buildSettlement(new BuildSettlementInput(0,false,
					new VertexLocation(new HexLocation(1,1), VertexDirection.East)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testBuildCity(){
		try {
			ps.buildCity(new BuildCityInput(0, new VertexLocation(new HexLocation(1,1), VertexDirection.East)));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
	@Test
	public void testOfferTrade(){
		try {
			ps.offerTrade(new OfferTradeInput(0, new ResourceHand(), 1));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}

	
}
