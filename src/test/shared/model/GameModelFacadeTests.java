package test.shared.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.TurnTracker;
import shared.model.bank.Bank;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.BoardFacade;
import shared.model.board.City;
import shared.model.board.PlayerID;
import shared.model.board.Road;
import shared.model.board.Robber;
import shared.model.board.Settlement;
import shared.model.board.Vertex;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Color;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;
import shared.model.player.Roads;
import shared.model.ratios.TradeRatio;

public class GameModelFacadeTests {
	
	private static GameModelFacade GMF;
	private static Player p0;
	private static Player p1;
	private static Player p2;
	private static Player p3;
	
	@BeforeClass 
	public static void prep(){
		GMF = new GameModelFacade(0);
		try {
			Player harold = new Player(CatanColor.BROWN, "Harold", 0);
			harold.getPlayerBank().setRC(4);
			harold.setPlayerFacade(new ActivePlayerFacade(harold));
			Player gretchen = new Player(CatanColor.GREEN, "Gretchen", 1);
			gretchen.getPlayerBank().setLargestArmyCard(true);
			gretchen.getPlayerBank().setLongestRoadCard(true);
			gretchen.setPlayerFacade(new InactivePlayerFacade(gretchen));
			Player ingrid  = new Player(CatanColor.ORANGE, "Ingrid", 2);
			ingrid.getPlayerBank().modifyRC(new ResourceHand(4,0,2,1,0));
			ingrid.setPlayerFacade(new InactivePlayerFacade(ingrid));
			Player jerry = new Player(CatanColor.BLUE, "Jerry", 3);
			jerry.getPlayerBank().modifyRC(new ResourceHand(2,2,2,1,1));
			jerry.setPlayerFacade(new InactivePlayerFacade(jerry));
			GMF.getGameModel().addPlayer(harold);
			GMF.getGameModel().addPlayer(gretchen);
			GMF.getGameModel().addPlayer(ingrid);
			GMF.getGameModel().addPlayer(jerry);
			p0 = GMF.getGameModel().getPlayers().get(0);
			p1 = GMF.getGameModel().getPlayers().get(1);
			p2 = GMF.getGameModel().getPlayers().get(2);
			p3 = GMF.getGameModel().getPlayers().get(3);
			Board board = new Board(false, false, false);
			List<Vertex> vertices = new ArrayList<Vertex>();
			vertices.add(new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(0,0), VertexDirection.East)));
			vertices.add(new City(new PlayerID(0), new VertexLocation(new HexLocation(-1,-1), VertexDirection.SouthWest)));
			vertices.add(new Settlement(new PlayerID(3), new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast)));
			board.setBuildings(vertices);
			List<Road> roads = new ArrayList<Road>();
			roads.add(new Road(new PlayerID(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast)));
			roads.add(new Road(new PlayerID(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.South)));
			roads.add(new Road(new PlayerID(3), new EdgeLocation(new HexLocation(1,1), EdgeDirection.North)));
			roads.add(new Road(new PlayerID(3), new EdgeLocation(new HexLocation(0,2), EdgeDirection.NorthEast)));
			board.setRoads(roads);
			BoardFacade bf = new BoardFacade(board);
//			bf.setBoard(board);
			board.setBoardFacade(bf);
			GMF.getGameModel().setBoard(board);
		} catch (TooManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testCanAcceptTrade(){
		//active player with enough resources
		boolean passed = GMF.canAcceptTrade(p0, new ResourceHand());
		assert(passed);
		//active player with not enough
		passed = GMF.canAcceptTrade(p0, new ResourceHand(5,5,5,5,5));
		assert(!passed);
		//active player with not enough of one element
		passed = GMF.canAcceptTrade(p0, new ResourceHand(1,1,5,1,1));
		assert(!passed);
		//inactive player with enough resources
		passed = GMF.canAcceptTrade(p3, new ResourceHand());
		assert(passed);
	}
	
	@Test
	public void testCanDiscardCards(){
		//active player with more than 7 cards
		boolean passed = GMF.canDiscardCards(p0);
		assert(passed);
		//inactive player with less than 7
		passed = GMF.canDiscardCards(p1);
		assert(!passed);
		//inactive player with 7
		passed = GMF.canDiscardCards(p2);
		assert(!passed);
		//inactive player with 8
		passed = GMF.canDiscardCards(p3);
		assert(passed);
	}
	
	@Test
	public void testCanRollDice(){
		try {
			GMF.getGameModel().getTurnTracker().setStatus("Rolling");
			GMF.getGameModel().getTurnTracker().setCurrentTurn(0);
			//active player, status rolling
			boolean passed = GMF.canRollDice(p0);
			assert(passed);
			//inactive player
			passed = GMF.canRollDice(p1);
			assert(!passed);
			GMF.getGameModel().getTurnTracker().setStatus("NotRolling");
			//active player, status not rolling
			passed = GMF.canRollDice(p0);
			assert(!passed);
			//inactive player, status not rolling
			passed = GMF.canRollDice(p1);
			assert(!passed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCanBuildRoad(){
		//test whether an inactive player can build a valid road
		boolean passed = GMF.canBuildRoad(p3, new EdgeLocation(new HexLocation(0,2), EdgeDirection.North));
		assert(!passed);
		//test whether an active player can build a valid road
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthWest));
		assert(passed);
		//test whether an active player can build an invalid road
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthWest));
		assert(!passed);
		//test whether an active player can build upon an existent road
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(1,1), EdgeDirection.North));
		assert(!passed);
		//test whether an active player can build upon his own existent road
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.South));
		assert(!passed);
	}
	
	@Test
	public void testCanBuildSettlement(){
		//inactive player, valid location
		boolean passed = GMF.canBuildSettlement(p1, new VertexLocation(new HexLocation(-1,-1), VertexDirection.East));
		assert(!passed);
		// active player, no roads near this vertex
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(-1,-1), VertexDirection.East));
		assert(!passed);
		// active player valid location
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assert(passed);
		// active player , settlement adjacent
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast));
		assert(!passed);
		// active player, location already has settlement
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.East));
		assert(!passed);
	}
	
	@Test
	public void testCanBuildCity(){
		//inactive player
		boolean passed = GMF.canBuildCity(p1, new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast));
		assert(!passed);
		//active player, valid location
		passed = GMF.canBuildCity(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.East));
		assert(passed);
		//active player, invalid location
		passed = GMF.canBuildCity(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assert(!passed);
		//active player, someone else's settlement
		passed = GMF.canBuildCity(p0, new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast));
		assert(!passed);
	}
	
	@Test
	public void testCanOfferTrade(){
		//active player, valid hand
		boolean passed = GMF.canOfferTrade(p0, new ResourceHand(3,3,3,3,3));
		assert(passed);
		//active player, invalid hand
		passed = GMF.canOfferTrade(p0, new ResourceHand(3,5,3,3,3));
		assert(!passed);
		//inactive player, invalid hand
		passed = GMF.canOfferTrade(p1, new ResourceHand(0,0,0,0,1));
		assert(!passed);
		//inactive player, invalid hand
		passed = GMF.canOfferTrade(p1, new ResourceHand(0,0,0,0,0));
		assert(!passed);
		
	}
	
	@Test
	public void testCanMaritimeTrade(){
		//inactive player
		boolean passed = GMF.canMaritimeTrade(p3, 1, ResourceType.BRICK, ResourceType.ORE);
		assert(!passed);
		//active player, valid trade
		passed = GMF.canMaritimeTrade(p0, 1, ResourceType.BRICK, ResourceType.ORE);
		assert(passed);
		//active player, invalid trade
		passed = GMF.canMaritimeTrade(p0, 2, ResourceType.BRICK, ResourceType.ORE);
		assert(!passed);
		try {
			p0.getTradeRatios().setRatio(new TradeRatio(ResourceType.BRICK, 1));
			//same trade with new Ratio
			passed = GMF.canMaritimeTrade(p0, 2, ResourceType.BRICK, ResourceType.ORE);
			assert(passed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCanRobPlayer(){
		GMF.getGameModel().getBoard().setRobber(new Robber(new HexLocation(1,0)));
		GMF.getGameModel().getTurnTracker().setStatus("Robbing");
		//active player, valid hex
		boolean passed = GMF.canRobPlayer(p0, new HexLocation(1,0));
		assert(!passed);
		//inactive player, valid hex * 3
		passed = GMF.canRobPlayer(p1, new HexLocation(1,0));
		assert(!passed); 
		passed = GMF.canRobPlayer(p2, new HexLocation(1,0));
		assert(passed);
		passed = GMF.canRobPlayer(p3, new HexLocation(1,0));
		assert(passed);
		//inactive player, invalid hex
		passed = GMF.canRobPlayer(p2, new HexLocation(2,0));
		assert(!passed);
		//inactive player, invalid hex
		passed = GMF.canRobPlayer(p2, new HexLocation(1,1));
		assert(!passed);
		//Change status of turntracker, disabling all robbing
		GMF.getGameModel().getTurnTracker().setStatus("NotRobbing");
		//valid players and hexes * 2
		passed = GMF.canRobPlayer(p2, new HexLocation(1,0));
		assert(!passed);
		passed = GMF.canRobPlayer(p3, new HexLocation(1,0));
		assert(!passed);
		
		

	}
	
	@Test
	public void testCanFinishTurn(){
		//can an active player finish turn
		boolean passed = GMF.canFinishTurn(p0);
		assert(passed);
		//inactive player
		passed = GMF.canFinishTurn(p1);
		assert(!passed);
	}
	
	@Test
	public void testCanBuyDevCard(){
		//active player with resources
		boolean passed = GMF.canBuyDevCard(p0);
		assert(passed);
		//inactive player without resources
		passed = GMF.canBuyDevCard(p1);
		assert(!passed);
		//inactive player without resources
		passed = GMF.canBuyDevCard(p2);
		assert(!passed);
		//active player with no resources
		Player h = new Player(CatanColor.BLUE, "H", 0);
		h.setPlayerFacade(new ActivePlayerFacade(h));
		passed = GMF.canBuyDevCard(h);
		assert(!passed);
		
		
		
	}
	
	@Test
	public void testCanPlayDevCard(){
		p0.setHasPlayedCard(false);
		p1.setHasPlayedCard(false);
		//active player has not already played card
		boolean passed = GMF.canPlayDevCard(p0);
		assert(passed);
		p0.setHasPlayedCard(true);
		//active player has already played card
		passed = GMF.canPlayDevCard(p0);
		assert(!passed);
		//inactive player has not played card
		passed = GMF.canPlayDevCard(p1);
		assert(!passed);
		p1.setHasPlayedCard(true);
		//inactive player has played card
		passed = GMF.canPlayDevCard(p1);
		assert(!passed);
	}

}
