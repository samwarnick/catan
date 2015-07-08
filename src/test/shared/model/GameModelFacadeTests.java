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
			BoardFacade bf = new BoardFacade();
			bf.setBoard(board);
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
		boolean passed = GMF.canAcceptTrade(p0, new ResourceHand());
		assert(passed);
		passed = GMF.canAcceptTrade(p0, new ResourceHand(5,5,5,5,5));
		assert(!passed);
		passed = GMF.canAcceptTrade(p0, new ResourceHand(1,1,5,1,1));
		assert(!passed);
	}
	
	@Test
	public void testCanDiscardCards(){
		boolean passed = GMF.canDiscardCards(p0);
		assert(passed);
		passed = GMF.canDiscardCards(p1);
		assert(!passed);
		passed = GMF.canDiscardCards(p2);
		assert(!passed);
		passed = GMF.canDiscardCards(p3);
		assert(passed);
	}
	
	@Test
	public void testCanRollDice(){
		try {
			GMF.getGameModel().getTurnTracker().setStatus("Rolling");
			GMF.getGameModel().getTurnTracker().setCurrentTurn(0);
			boolean passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(0));
			assert(passed);
			passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(1));
			assert(!passed);
			GMF.getGameModel().getTurnTracker().setStatus("NotRolling");
			passed = GMF.canRollDice(GMF.getGameModel().getPlayers().get(0));
			assert(!passed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCanBuildRoad(){
		boolean passed = GMF.canBuildRoad(p3, new EdgeLocation(new HexLocation(0,2), EdgeDirection.North));
		assert(!passed);
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthWest));
		assert(passed);
		passed = GMF.canBuildRoad(p0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthWest));
		assert(!passed);
	}
	
	@Test
	public void testCanBuildSettlement(){
		boolean passed = GMF.canBuildSettlement(p1, new VertexLocation(new HexLocation(-1,-1), VertexDirection.East));
		assert(!passed);
		// no roads near this vertex
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(-1,-1), VertexDirection.East));
		assert(!passed);
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assert(passed);
		// settlement adjacent
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast));
		assert(!passed);
		// already settlement
		passed = GMF.canBuildSettlement(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.East));
		assert(!passed);
	}
	
	@Test
	public void testCanBuildCity(){
		boolean passed = GMF.canBuildCity(p1, new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast));
		assert(!passed);
		passed = GMF.canBuildCity(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.East));
		assert(passed);
		passed = GMF.canBuildCity(p0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assert(!passed);
	}
	
	@Test
	public void testCanOfferTrade(){
		boolean passed = GMF.canOfferTrade(p0, new ResourceHand(3,3,3,3,3));
		assert(passed);
		passed = GMF.canOfferTrade(p0, new ResourceHand(3,5,3,3,3));
		assert(!passed);
		passed = GMF.canOfferTrade(p1, new ResourceHand(0,0,0,0,1));
		assert(!passed);
		passed = GMF.canOfferTrade(p1, new ResourceHand(0,0,0,0,0));
		assert(!passed);
		
	}
	
	@Test
	public void testCanMaritimeTrade(){
		boolean passed = GMF.canMaritimeTrade(p3, 1, ResourceType.BRICK, ResourceType.ORE);
		assert(!passed);
		passed = GMF.canMaritimeTrade(p0, 1, ResourceType.BRICK, ResourceType.ORE);
		assert(passed);
		passed = GMF.canMaritimeTrade(p0, 2, ResourceType.BRICK, ResourceType.ORE);
		assert(!passed);
	}
	
	@Test
	public void testCanRobPlayer(){
		GMF.getGameModel().getBoard().setRobber(new Robber(new HexLocation(1,0)));
		GMF.getGameModel().getTurnTracker().setStatus("Robbing");
		boolean passed = GMF.canRobPlayer(p0, new HexLocation(1,0));
		assert(!passed);
		passed = GMF.canRobPlayer(p1, new HexLocation(1,0));
		assert(!passed);
		passed = GMF.canRobPlayer(p2, new HexLocation(1,0));
		assert(passed);
		passed = GMF.canRobPlayer(p3, new HexLocation(1,0));
		assert(passed);
		passed = GMF.canRobPlayer(p2, new HexLocation(2,0));
		assert(!passed);
		passed = GMF.canRobPlayer(p2, new HexLocation(1,1));
		assert(!passed);
		GMF.getGameModel().getTurnTracker().setStatus("NotRobbing");
		passed = GMF.canRobPlayer(p2, new HexLocation(1,0));
		assert(!passed);
		passed = GMF.canRobPlayer(p3, new HexLocation(1,0));
		assert(!passed);
		
		

	}
	
	@Test
	public void testCanFinishTurn(){
		boolean passed = GMF.canFinishTurn(GMF.getGameModel().getPlayers().get(0));
		assert(passed);
		passed = GMF.canFinishTurn(GMF.getGameModel().getPlayers().get(1));
		assert(!passed);
	}
	
	@Test
	public void testCanBuyDevCard(){
		boolean passed = GMF.canBuyDevCard(p0);
		assert(passed);
		passed = GMF.canBuyDevCard(p1);
		assert(!passed);
		passed = GMF.canBuyDevCard(p2);
		assert(!passed);
		
	}
	
	@Test
	public void testCanPlayDevCard(){
		p0.setHasPlayedCard(false);
		p1.setHasPlayedCard(false);
		boolean passed = GMF.canPlayDevCard(p0);
		assert(passed);
		passed = GMF.canPlayDevCard(p1);
		assert(!passed);
		passed = GMF.canPlayDevCard(p2);
		assert(!passed);
	}

}
