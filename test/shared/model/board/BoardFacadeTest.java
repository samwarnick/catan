package shared.model.board;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.board.Board;
import shared.model.board.BoardFacade;
import shared.model.board.Road;
import shared.model.board.Settlement;
import shared.model.board.Vertex;
import shared.model.player.Player;
import shared.model.player.Roads;

public class BoardFacadeTest {
	
	private Board board;
	
	@Before
	public void setUp()
	{
		board = new Board(false,false,false);
		board.setBoardFacade(new BoardFacade(board));
	}

	//if it's connected to one of the players pieces and no one else has built on that edge location

	@Test
	public void testCanBuildRoad() {
		Player player1 = new Player(CatanColor.ORANGE, "cat", 0);
		Player player2 = new Player(CatanColor.BLUE, "bird", 1);
		//add a road to player1
		List<Road> roads = new ArrayList<Road>();
		HexLocation hexLocation = new HexLocation(0,0);
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.North);
		Road road = new Road(player1.getPlayerID(), edgeLocation);
		roads.add(road);
		//add a settlement to player1
		List<Vertex> buildings = new ArrayList<Vertex>();
		VertexLocation vertexLocation = new VertexLocation(hexLocation, VertexDirection.NorthWest);
		Settlement settlement = new Settlement(player1.getPlayerID(), vertexLocation);
		buildings.add(settlement);
		//add a road to player2
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);
		road = new Road(player2.getPlayerID(), edgeLocation);
		roads.add(road);
		
		board.setBuildings(buildings);
		board.setRoads(roads);
		
		EdgeLocation locationValid = new EdgeLocation(hexLocation, EdgeDirection.NorthWest); //attached to city
		EdgeLocation locationValid2 = new EdgeLocation(hexLocation, EdgeDirection.SouthWest); // attached to road
		hexLocation = new HexLocation(0,1);
		EdgeLocation locationInvalid = new EdgeLocation(hexLocation, EdgeDirection.South); // not attached to anything
		
		assertTrue(board.getBoardFacade().canBuildRoad(player1, locationValid, false, false, null));
		road = new Road(player1.getPlayerID(), locationValid);
		roads.add(road);
		board.setRoads(roads);
		assertFalse(board.getBoardFacade().canBuildRoad(player1, locationValid, false, false, null));
		assertFalse(board.getBoardFacade().canBuildRoad(player2, locationValid, false, false, null));
		assertTrue(board.getBoardFacade().canBuildRoad(player1, locationValid2, false, false, null));
		assertFalse(board.getBoardFacade().canBuildRoad(player2, locationInvalid, false, false, null));
	}
	
	//if it doesn't boarder any other settlements or cities and is empty
	
	@Test
	public void testCanBuildSettlement() {
		Player player1 = new Player(CatanColor.ORANGE, "cat", 0);
		Player player2 = new Player(CatanColor.BLUE, "bird", 1);
		
		HexLocation hexLocation = new HexLocation(0,0);
		List<Vertex> buildings = new ArrayList<Vertex>();
		List<Road> roads = new ArrayList<Road>();
		//add 3 roads to player1
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.North);
		Road road = new Road(player1.getPlayerID(), edgeLocation);
		roads.add(road);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthWest);
		road = new Road(player1.getPlayerID(), edgeLocation);
		roads.add(road);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.SouthWest);
		road = new Road(player1.getPlayerID(), edgeLocation);
		roads.add(road);
		//add a settlement to player1
		VertexLocation vertexLocation = new VertexLocation(hexLocation, VertexDirection.NorthWest);
		Settlement settlement = new Settlement(player1.getPlayerID(), vertexLocation);
		buildings.add(settlement);
		//add 2 roads to player2
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);
		road = new Road(player2.getPlayerID(), edgeLocation);
		roads.add(road);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.SouthEast);
		road = new Road(player2.getPlayerID(), edgeLocation);
		roads.add(road);
		//add a settlement to player2
		vertexLocation = new VertexLocation(hexLocation, VertexDirection.East);
		settlement = new Settlement(player2.getPlayerID(), vertexLocation);
		buildings.add(settlement);
		
		board.setBuildings(buildings);
		board.setRoads(roads);
		
		VertexLocation locationValid = new VertexLocation(hexLocation, VertexDirection.SouthWest); // empty vertex
		VertexLocation locationInvalid = new VertexLocation(hexLocation, VertexDirection.West); // boarders a city
		VertexLocation locationInvalid2 = new VertexLocation(hexLocation, VertexDirection.NorthWest); // has a  city on it

		assertTrue(board.getBoardFacade().canBuildSettlement(player1, locationValid, false));
		assertFalse(board.getBoardFacade().canBuildSettlement(player2, locationValid, false));
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.South);
		road = new Road(player2.getPlayerID(), edgeLocation);
		roads.add(road);
		board.setRoads(roads);
		assertTrue(board.getBoardFacade().canBuildSettlement(player2, locationValid, false));
		settlement = new Settlement(player1.getPlayerID(), locationValid);
		buildings.add(settlement);
		board.setBuildings(buildings);
		assertFalse(board.getBoardFacade().canBuildSettlement(player1, locationValid, false));
		assertFalse(board.getBoardFacade().canBuildSettlement(player2, locationValid, false));
		assertFalse(board.getBoardFacade().canBuildSettlement(player2, locationInvalid, false));
		assertFalse(board.getBoardFacade().canBuildSettlement(player1, locationInvalid, false));
		assertFalse(board.getBoardFacade().canBuildSettlement(player1, locationInvalid2, false));
	}
	
	//if they have a settlement on that vertex location then build
	
	@Test
	public void testCanBuildCity() {
		Player player1 = new Player(CatanColor.ORANGE, "cat", 0);
		Player player2 = new Player(CatanColor.BLUE, "bird", 1);
		
		HexLocation hexLocation = new HexLocation(0,0);
		List<Vertex> buildings = new ArrayList<Vertex>();
		//add a settlement to player1
		VertexLocation vertexLocation = new VertexLocation(hexLocation, VertexDirection.NorthWest);
		Settlement settlement = new Settlement(player1.getPlayerID(), vertexLocation);
		buildings.add(settlement);
		//add a settlement to player2
		VertexLocation vertexLocation2 = new VertexLocation(hexLocation, VertexDirection.East);
		settlement = new Settlement(player2.getPlayerID(), vertexLocation2);
		buildings.add(settlement);
		
		board.setBuildings(buildings);		
		
		VertexLocation locationValid = vertexLocation;
		VertexLocation locationValid2 = vertexLocation2;
		VertexLocation locationInvalid = new VertexLocation(hexLocation, VertexDirection.SouthWest);
		//add a settlement to player1 and player2		
		assertTrue(board.getBoardFacade().canBuildCity(player1, locationValid, false));
		assertFalse(board.getBoardFacade().canBuildCity(player2, locationValid, false));
		assertTrue(board.getBoardFacade().canBuildCity(player2, locationValid2, false));
		assertFalse(board.getBoardFacade().canBuildCity(player2, locationInvalid, false));
	}
}








