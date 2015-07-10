package shared.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.model.GameModel;
import shared.model.JsonParser;
import shared.model.TurnTracker;
import shared.model.bank.Bank;
import shared.model.bank.DevelopmentCard;
import shared.model.bank.PlayerBank;
import shared.model.bank.ResourceCard;
import shared.model.board.Board;
import shared.model.board.Hex;
import shared.model.board.PlayerID;
import shared.model.board.PortHex;
import shared.model.board.ResourceHex;
import shared.model.board.Road;
import shared.model.board.Robber;
import shared.model.board.Vertex;
import shared.model.board.WaterHex;
import shared.model.player.Cities;
import shared.model.player.LargestArmy;
import shared.model.player.LongestRoad;
import shared.model.player.Player;
import shared.model.player.Roads;
import shared.model.player.Settlements;
import shared.model.player.VictoryPoints;
import shared.model.ratios.TradeRatios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParserTest {
	
	@Test
	public void testGameModelFromJson() {
		//give it a file, them make sure the model it sends back is correct.
		File file = new File("JsonExampleFile.txt");
		JsonNode rootNode = JsonParser.nodeFromFile(file);
		GameModel game = JsonParser.gameModelFromJson(rootNode);
		//version
		assertEquals(game.getGameVersion(), 0);
		//board
		Board board = game.getBoard();
		testBoard(board);
		//players
		List<Player> players = game.getPlayers();
		assertEquals(4, players.size());
		testPlayer(players);	
		//turnTracker
		TurnTracker turnTracker = game.getTurnTracker();
		assertEquals(turnTracker.getCurrentTurn(), 0);
		assertEquals(turnTracker.getStatus(), "Rolling");		
		//bank
		Bank bank = game.getBank();
		testBank(bank);
	}
	
	private void testBoard(Board board)
	{
		//resourceHexes
		List<ResourceHex> resourceHexes = board.getResourceHexes();
		testResourceHexes(resourceHexes);
		//portHexes
		List<PortHex> portHexes = board.getPorts();
		testPorts(portHexes);
		//Robber
		Robber robber = board.getRobber();
		HexLocation hexlocation = robber.getLocation();
		assertEquals(hexlocation.getX(), 0);
		assertEquals(hexlocation.getY(), -2);
		//Roads
		List<Road> roads = board.getRoads();
		testRoads(roads);
		//buildings
		List<Vertex> buildings = board.getBuildings();
		testBuildings(buildings);
	}
	
	private void testPorts(List<PortHex> ports)
	{
		int i = 0;
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.BRICK);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.NorthEast);
		assertEquals(ports.get(i).getLocation().getX(), -2);
		assertEquals(ports.get(i).getLocation().getY(), 3);
		i++;
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.SouthEast);
		assertEquals(ports.get(i).getLocation().getX(), -3);
		assertEquals(ports.get(i).getLocation().getY(), 0);
		i++;
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.SHEEP);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.NorthWest);
		assertEquals(ports.get(i).getLocation().getX(), 3);
		assertEquals(ports.get(i).getLocation().getY(), -1);
		i++;
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.NorthWest);
		assertEquals(ports.get(i).getLocation().getX(), 2);
		assertEquals(ports.get(i).getLocation().getY(), 1);
		i++;
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.North);
		assertEquals(ports.get(i).getLocation().getX(), 0);
		assertEquals(ports.get(i).getLocation().getY(), 3);
		i++;
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.ORE);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.South);
		assertEquals(ports.get(i).getLocation().getX(), 1);
		assertEquals(ports.get(i).getLocation().getY(), -3);
		i++;
		assertEquals(ports.get(i).getRatio(), 3);
		assertEquals(ports.get(i).getPortType(), PortType.THREE);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.SouthWest);
		assertEquals(ports.get(i).getLocation().getX(), 3);
		assertEquals(ports.get(i).getLocation().getY(), -3);
		i++;
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.WHEAT);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.South);
		assertEquals(ports.get(i).getLocation().getX(), -1);
		assertEquals(ports.get(i).getLocation().getY(), -2);
		i++;
		assertEquals(ports.get(i).getRatio(), 2);
		assertEquals(ports.get(i).getPortType(), PortType.WOOD);
		assertEquals(ports.get(i).getOrientation(), EdgeDirection.NorthEast);
		assertEquals(ports.get(i).getLocation().getX(), -3);
		assertEquals(ports.get(i).getLocation().getY(), 2);
		i++;
	}
	
	private void testBuildings(List<Vertex> buildings)
	{
		int i = 0;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 3);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), -1);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 3);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthEast);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), 1);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), -2);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 2);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), 0);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), 0);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 2);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), 1);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), -1);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 1);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), -2);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 0);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthEast);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), 0);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 1);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), -1);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), -1);
		i++;
		assertEquals(buildings.get(i).getOwner().getPlayerid(), 0);
		assertEquals(buildings.get(i).getLocation().getDir(), VertexDirection.SouthWest);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getX(), 2);
		assertEquals(buildings.get(i).getLocation().getHexLoc().getY(), 0);
		i++;
	}
	
	
	private void testRoads(List<Road> roads)
	{
		int i = 0;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 1);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.South);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), -1);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), -1);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 3);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.SouthWest);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), -1);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 3);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.SouthWest);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), 2);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), -2);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 2);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.South);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), 1);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), -1);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 0);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.South);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), 0);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 2);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.South);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), 0);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), 0);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 1);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.SouthWest);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), -2);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), 1);
		i++;
		assertEquals(roads.get(i).getOwner().getPlayerid(), 0);
		assertEquals(roads.get(i).getLocation().getDir(), EdgeDirection.SouthWest);
		assertEquals(roads.get(i).getLocation().getHexLoc().getX(), 2);
		assertEquals(roads.get(i).getLocation().getHexLoc().getY(), 0);
	}

	
	private void testResourceHexes(List<ResourceHex> resourceHexes)
	{
		int i = 0;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		assertEquals(resourceHexes.get(i).getNumberToken(), 4);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		assertEquals(resourceHexes.get(i).getNumberToken(), 11);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		assertEquals(resourceHexes.get(i).getNumberToken(), 8);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		assertEquals(resourceHexes.get(i).getNumberToken(), 3);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		assertEquals(resourceHexes.get(i).getNumberToken(), 9);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), -1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		assertEquals(resourceHexes.get(i).getNumberToken(), 12);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		assertEquals(resourceHexes.get(i).getNumberToken(), 5);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		assertEquals(resourceHexes.get(i).getNumberToken(), 10);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		assertEquals(resourceHexes.get(i).getNumberToken(), 11);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.BRICK);
		assertEquals(resourceHexes.get(i).getNumberToken(), 5);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 0);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		assertEquals(resourceHexes.get(i).getNumberToken(), 6);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		assertEquals(resourceHexes.get(i).getNumberToken(), 2);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		assertEquals(resourceHexes.get(i).getNumberToken(), 9);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		assertEquals(resourceHexes.get(i).getNumberToken(), 4);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 1);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.SHEEP);
		assertEquals(resourceHexes.get(i).getNumberToken(), 10);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -2);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WOOD);
		assertEquals(resourceHexes.get(i).getNumberToken(), 6);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), -1);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.ORE);
		assertEquals(resourceHexes.get(i).getNumberToken(), 3);
		i++;
		assertEquals(resourceHexes.get(i).getLocation().getX(), 0);
		assertEquals(resourceHexes.get(i).getLocation().getY(), 2);
		assertEquals(resourceHexes.get(i).getLandType(), HexType.WHEAT);
		assertEquals(resourceHexes.get(i).getNumberToken(), 8);
	}
	
	private void testPlayer(List<Player> player)
	{
		assertEquals(4, player.size());
		PlayerBank bank = player.get(0).getPlayerBank();
		//playerBank
		List<PlayerBank> playerbanks = new ArrayList<PlayerBank>();
		playerbanks.add(player.get(0).getPlayerBank());
		playerbanks.add(player.get(1).getPlayerBank());
		playerbanks.add(player.get(2).getPlayerBank());
		playerbanks.add(player.get(3).getPlayerBank());
		testPlayerBank(playerbanks);
		//longestRoad
		for(int i = 0; i < 4; i++)
		{
			LongestRoad longestRoad = player.get(i).getLongestRoad();
			assertEquals(longestRoad.getNumRoads(), 2);
			assertFalse(longestRoad.isHasLongestRoad());
		}		
		//LargestArmy
		for(int i = 0; i < 4; i++)
		{
			LargestArmy largestArmy = player.get(i).getLargestArmy();
			assertEquals(largestArmy.getNumSoldiers(), 0);
			assertFalse(largestArmy.getHasLargestArmy());
		}	
		//catanColor
		CatanColor color = player.get(0).getColor();
		assertEquals(color, CatanColor.BLUE);
		color = player.get(1).getColor();
		assertEquals(color, CatanColor.BLUE);
		color = player.get(2).getColor();
		assertEquals(color, CatanColor.RED);
		color = player.get(3).getColor();
		assertEquals(color, CatanColor.GREEN);		
		//roads
		for(int i = 0; i < 4; i++)
		{
			Roads roads = player.get(i).getRoads();
			assertEquals(roads.getRoadsLeft(), 13);
		}
		//settlemts
		for(int i = 0; i < 4; i++)
		{
			Settlements settlements = player.get(i).getSettlements();
			assertEquals(settlements.getSettlementsLeft(), 3);
		}		
		//cities
		for(int i = 0; i < 4; i++)
		{
			Cities cities = player.get(i).getCities();
			assertEquals(cities.getCitiesLeft(), 4);
		}	
		//victoryPoints
		for(int i = 0; i < 4; i++)
		{
			VictoryPoints victoryPoints = player.get(i).getVictoryPoints();
			assertEquals(victoryPoints.getTotalVictoryPoints(), 2);
			assertEquals(victoryPoints.getPublicVictoryPoints(), 2);
		}	
		//name
		assertEquals(player.get(0).getName(), "Sam");
		assertEquals(player.get(1).getName(), "Brooke");
		assertEquals(player.get(2).getName(), "Pete");
		assertEquals(player.get(3).getName(), "Mark");
		//tradeRatios
		TradeRatios tradeRatios = player.get(0).getTradeRatios();
		
		
		
		
		
		//playerID
		for(int i = 0; i < 4; i++)
		{
			assertEquals(player.get(i).getPlayerID().getPlayerid(), i);
		}		
		//hasPlayedCard
		for(int i = 0; i < 4; i++)
		{
			assertFalse(player.get(0).getHasPlayedCard());
		}	
		//hasDiscarded
		for(int i = 0; i < 4; i++)
		{
			assertFalse(player.get(0).getHasDiscarded());
		}	
	}
	
	private void testBank(Bank bank)
	{
		//brick
		ResourceCard brick = bank.getBrick();
		assertEquals(brick.getQuantity(), 19);
		//wood
		ResourceCard wood = bank.getWood();
		assertEquals(wood.getQuantity(), 19);
		//sheep
		ResourceCard sheep = bank.getSheep();
		assertEquals(sheep.getQuantity(), 19);
		//wheat
		ResourceCard wheat = bank.getWheat();
		assertEquals(wheat.getQuantity(), 19);
		//ore
		ResourceCard ore = bank.getOre();
		assertEquals(ore.getQuantity(), 19);
		//solider
		DevelopmentCard soldier = bank.getSoldier();
		assertEquals(soldier.getQuantity(), 14);
		//yearOfPlenty
		DevelopmentCard yearOfPlenty = bank.getYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 2);
		//roadBuild
		DevelopmentCard roadBuild = bank.getRoadBuild();
		assertEquals(roadBuild.getQuantity(), 2);
		//monopoly
		DevelopmentCard monopoly = bank.getMonopoly();
		assertEquals(monopoly.getQuantity(), 2);
		//monument
		DevelopmentCard monument = bank.getMonument();
		assertEquals(monument.getQuantity(), 5);
		//largestArmyCard
		assertTrue(bank.hasLargestArmyCard());
		//longestRoadCard
		assertTrue(bank.hasLongestRoadCard());
	}
	
	public void testPlayerBank(List<PlayerBank> bank)
	{
		//Sam
		//brick
		ResourceCard brick = bank.get(0).getBrick();
		assertEquals(brick.getQuantity(), 0);
		//wood
		ResourceCard wood = bank.get(0).getWood();
		assertEquals(wood.getQuantity(), 1);
		//sheep
		ResourceCard sheep = bank.get(0).getSheep();
		assertEquals(sheep.getQuantity(), 1);
		//wheat
		ResourceCard wheat = bank.get(0).getWheat();
		assertEquals(wheat.getQuantity(), 1);
		//ore
		ResourceCard ore = bank.get(0).getOre();
		assertEquals(ore.getQuantity(), 0);
		//solider
		DevelopmentCard soldier = bank.get(0).getSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//yearOfPlenty
		DevelopmentCard yearOfPlenty = bank.get(0).getYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//roadBuild
		DevelopmentCard roadBuild = bank.get(0).getRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//monopoly
		DevelopmentCard monopoly = bank.get(0).getMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//monument
		DevelopmentCard monument = bank.get(0).getMonument();
		assertEquals(monument.getQuantity(), 0);
		//newsolider
		soldier = bank.get(0).getNewSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//newyearOfPlenty
		yearOfPlenty = bank.get(0).getNewYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//newroadBuild
		roadBuild = bank.get(0).getNewRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//newmonopoly
		monopoly = bank.get(0).getNewMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//newmonument
		monument = bank.get(0).getNewMonument();
		assertEquals(monument.getQuantity(), 0);
		//largestArmyCard
		assertFalse(bank.get(0).hasLargestArmyCard());
		//longestRoadCard
		assertFalse(bank.get(0).hasLongestRoadCard());
		
		//Brooke
		//brick
		brick = bank.get(1).getBrick();
		assertEquals(brick.getQuantity(), 1);
		//wood
		wood = bank.get(1).getWood();
		assertEquals(wood.getQuantity(), 0);
		//sheep
		sheep = bank.get(1).getSheep();
		assertEquals(sheep.getQuantity(), 1);
		//wheat
		wheat = bank.get(1).getWheat();
		assertEquals(wheat.getQuantity(), 0);
		//ore
		ore = bank.get(1).getOre();
		assertEquals(ore.getQuantity(), 1);
		//solider
		soldier = bank.get(1).getSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//yearOfPlenty
		yearOfPlenty = bank.get(1).getYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//roadBuild
		roadBuild = bank.get(1).getRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//monopoly
		monopoly = bank.get(1).getMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//monument
		monument = bank.get(1).getMonument();
		assertEquals(monument.getQuantity(), 0);
		//newsolider
		soldier = bank.get(1).getNewSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//newyearOfPlenty
		yearOfPlenty = bank.get(1).getNewYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//newroadBuild
		roadBuild = bank.get(1).getNewRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//newmonopoly
		monopoly = bank.get(1).getNewMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//newmonument
		monument = bank.get(1).getNewMonument();
		assertEquals(monument.getQuantity(), 0);
		//largestArmyCard
		assertFalse(bank.get(1).hasLargestArmyCard());
		//longestRoadCard
		assertFalse(bank.get(1).hasLongestRoadCard());
		
		//Pete
		//brick
		brick = bank.get(2).getBrick();
		assertEquals(brick.getQuantity(), 0);
		//wood
		wood = bank.get(2).getWood();
		assertEquals(wood.getQuantity(), 1);
		//sheep
		sheep = bank.get(2).getSheep();
		assertEquals(sheep.getQuantity(), 1);
		//wheat
		wheat = bank.get(2).getWheat();
		assertEquals(wheat.getQuantity(), 1);
		//ore
		ore = bank.get(2).getOre();
		assertEquals(ore.getQuantity(), 0);
		//solider
		soldier = bank.get(2).getSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//yearOfPlenty
		yearOfPlenty = bank.get(2).getYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//roadBuild
		roadBuild = bank.get(2).getRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//monopoly
		monopoly = bank.get(2).getMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//monument
		monument = bank.get(2).getMonument();
		assertEquals(monument.getQuantity(), 0);
		//newsolider
		soldier = bank.get(2).getNewSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//newyearOfPlenty
		yearOfPlenty = bank.get(2).getNewYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//newroadBuild
		roadBuild = bank.get(2).getNewRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//newmonopoly
		monopoly = bank.get(2).getNewMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//newmonument
		monument = bank.get(2).getNewMonument();
		assertEquals(monument.getQuantity(), 0);
		//largestArmyCard
		assertFalse(bank.get(2).hasLargestArmyCard());
		//longestRoadCard
		assertFalse(bank.get(2).hasLongestRoadCard());
		
		//Mark
		//brick
		brick = bank.get(3).getBrick();
		assertEquals(brick.getQuantity(), 0);
		//wood
		wood = bank.get(3).getWood();
		assertEquals(wood.getQuantity(), 1);
		//sheep
		sheep = bank.get(3).getSheep();
		assertEquals(sheep.getQuantity(), 1);
		//wheat
		wheat = bank.get(3).getWheat();
		assertEquals(wheat.getQuantity(), 0);
		//ore
		ore = bank.get(3).getOre();
		assertEquals(ore.getQuantity(), 1);
		//solider
		soldier = bank.get(3).getSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//yearOfPlenty
		yearOfPlenty = bank.get(3).getYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//roadBuild
		roadBuild = bank.get(3).getRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//monopoly
		monopoly = bank.get(3).getMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//monument
		monument = bank.get(3).getMonument();
		assertEquals(monument.getQuantity(), 0);
		//newsolider
		soldier = bank.get(3).getNewSoldier();
		assertEquals(soldier.getQuantity(), 0);
		//newyearOfPlenty
		yearOfPlenty = bank.get(3).getNewYearOfPlenty();
		assertEquals(yearOfPlenty.getQuantity(), 0);
		//newroadBuild
		roadBuild = bank.get(3).getNewRoadBuild();
		assertEquals(roadBuild.getQuantity(), 0);
		//newmonopoly
		monopoly = bank.get(3).getNewMonopoly();
		assertEquals(monopoly.getQuantity(), 0);
		//newmonument
		monument = bank.get(3).getNewMonument();
		assertEquals(monument.getQuantity(), 0);
		//largestArmyCard
		assertFalse(bank.get(3).hasLargestArmyCard());
		//longestRoadCard
		assertFalse(bank.get(3).hasLongestRoadCard());
	}


}















