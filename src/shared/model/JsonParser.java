package shared.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.definitions.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.bank.*;
import shared.model.board.*;
import shared.model.player.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

	public static JsonNode nodeFromFile(File file) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(file);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static GameModel gameModelFromJson(JsonNode rootNode) {
		GameModel gameModel = new GameModel(0);
		
		// board
		Board board = parseBoard(rootNode.path("map"));
		// bank
		Bank bank = parseBank(rootNode.path("deck"), rootNode.path("bank"));
		// players
		List<Player> players = parsePlayers(rootNode.path("players"));
		// turn tracker
		TurnTracker tracker = parseTracker(rootNode.path("turnTracker"), players);
		
		// go through players and find longest and largest
		// winner
		// int winner = rootNode.path("winner").intValue();
		// version
		int version = rootNode.path("version").intValue();
		
		// set up board
		gameModel.setBoard(board);
		gameModel.setBank(bank);
		gameModel.setPlayers(players);
		gameModel.setTurnTracker(tracker);
		gameModel.setGameVersion(version);
			
		return gameModel;
	}
	
	private static Bank parseBank(JsonNode deckNode, JsonNode bankNode) {
		Bank bank = new Bank();
		
		int brick = bankNode.path("brick").intValue();
		int wood = bankNode.path("wood").intValue();
		int sheep = bankNode.path("sheep").intValue();
		int wheat = bankNode.path("wheat").intValue();
		int ore = bankNode.path("ore").intValue();
		
		if (brick > 19) {
			brick = 19;
		}
		if (wood > 19) {
			wood = 19;
		}
		if (sheep > 19) {
			sheep = 19;
		}
		if (wheat > 19) {
			wheat = 19;
		}
		if (ore > 19) {
			ore = 19;
		}
		
		int soldier = deckNode.path("soldier").intValue();
		int monument = deckNode.path("monument").intValue();
		int monopoly = deckNode.path("monopoly").intValue();
		int yearOfPlenty = deckNode.path("yearOfPlenty").intValue();
		int roadBuilding = deckNode.path("roadBuilding").intValue();
		
		ResourceHand rh = new ResourceHand(brick, wood, sheep, wheat, ore);
		DevelopmentHand dh = new DevelopmentHand(soldier, monument, monopoly, yearOfPlenty, roadBuilding);
		
		try {
			bank.setRC(rh);
			bank.setDC(dh);
		} catch (BankException e) {
			e.printStackTrace();
		}
		
		return bank;
	}
	
	private static Board parseBoard(JsonNode mapNode) {
		Board board = new Board();
		List<ResourceHex> resourceHexes = parseHexes(mapNode.path("hexes"));
		List<Road> roads = parseRoads(mapNode.path("roads"));
		List<Vertex> buildings = parseCities(mapNode.path("cities"));
		buildings.addAll(parseSettlements(mapNode.path("settlements")));
		List<PortHex> ports = parsePorts(mapNode.path("ports"));
		Robber robber = parseRobber(mapNode.path("robber"));
		
		//board.setDesertHex(resourceHexes.get(0));
		board.setResourceHexes(resourceHexes);
		board.setRoads(roads);
		board.setBuildings(buildings);
		board.setPorts(ports);
		board.setRobber(robber);
		return board;
	}
	
	private static List<ResourceHex> parseHexes(JsonNode hexesNode) {
		List<ResourceHex> resourceHexes = new ArrayList<ResourceHex>();
		
		if (!hexesNode.isMissingNode()) {
			Iterator<JsonNode> iter = hexesNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				String resource = temp.path("resource").textValue();
				JsonNode location = temp.path("location");
				int x = location.path("x").intValue();
				int y = location.path("y").intValue();
				int num = temp.path("number").intValue();
				if (resource != null) {
					ResourceHex hex = new ResourceHex(getResourceType(resource), new HexLocation(x, y), num);
					resourceHexes.add(hex);
				}
				else {
					ResourceHex hex = new ResourceHex(HexType.DESERT, new HexLocation(x, y), 0);
					resourceHexes.add(0, hex);
				}
			}
		}
		
		return resourceHexes;
	}
	
	private static List<Road> parseRoads(JsonNode roadsNode) {
		List<Road> roads = new ArrayList<Road>();
		
		if (!roadsNode.isMissingNode()) {
			Iterator<JsonNode> iter = roadsNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				int ownerID = temp.path("owner").intValue();
				JsonNode locationNode = temp.path("location");
				int x = locationNode.path("x").intValue();
				int y = locationNode.path("y").intValue();
				String direction = locationNode.path("direction").textValue();
				Road road = new Road(new PlayerID(ownerID),
						new EdgeLocation(new HexLocation(x, y), getEdgeDirection(direction)));
				roads.add(road);
			}
		}
		
		return roads;
	}
	
	private static List<Vertex> parseCities(JsonNode citiesNode) {
		List<Vertex> buildings = new ArrayList<Vertex>();
		
		if (!citiesNode.isMissingNode()) {
			Iterator<JsonNode> iter = citiesNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				int ownerID = temp.path("owner").intValue();
				JsonNode locationNode = temp.path("location");
				int x = locationNode.path("x").intValue();
				int y = locationNode.path("y").intValue();
				String direction = locationNode.path("direction").textValue();
				City city = new City(new PlayerID(ownerID), 
						new VertexLocation(new HexLocation(x, y), getVertexDirection(direction)));
				buildings.add(city);
			}
		}
		
		return buildings;
	}
	
	private static List<Vertex> parseSettlements(JsonNode settlementsNode) {
		List<Vertex> buildings = new ArrayList<Vertex>();
		
		if (!settlementsNode.isMissingNode()) {
			Iterator<JsonNode> iter = settlementsNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				int ownerID = temp.path("owner").intValue();
				JsonNode locationNode = temp.path("location");
				int x = locationNode.path("x").intValue();
				int y = locationNode.path("y").intValue();
				String direction = locationNode.path("direction").textValue();
				Settlement settlement = new Settlement(new PlayerID(ownerID), 
						new VertexLocation(new HexLocation(x, y), getVertexDirection(direction)));
				buildings.add(settlement);
			}
		}
		
		return buildings;
	}
	
	private static List<PortHex> parsePorts(JsonNode portsNode) {
		List<PortHex> ports = new ArrayList<PortHex>();
		
		if (!portsNode.isMissingNode()) {
			Iterator<JsonNode> iter = portsNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				String resource = temp.path("resource").textValue();
				JsonNode location = temp.path("location");
				int x = location.path("x").intValue();
				int y = location.path("y").intValue();
				String direction = temp.path("direction").textValue();
				int ratio = temp.path("ratio").intValue();
				PortHex port = new PortHex(new HexLocation(x, y), 
						getPortType(resource), 
						ratio, 
						getEdgeDirection(direction));
				ports.add(port);
			}
		}
		
		return ports;
	}
	
	private static Robber parseRobber(JsonNode robberNode) {
		int x = robberNode.path("x").intValue();
		int y = robberNode.path("y").intValue();
		return new Robber(new HexLocation(x, y));
	}
	
	private static List<Player> parsePlayers(JsonNode playersNode) {
		List<Player> players = new ArrayList<Player>();
		
		if (playersNode != null) {
			Iterator<JsonNode> iter = playersNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				players.add(parsePlayer(temp));
			}
		}
		
		return players;
	}
	
	private static Player parsePlayer(JsonNode playerNode) {
		if (!playerNode.isMissingNode()) {
			Iterator<JsonNode> iter = playerNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				PlayerBank playerBank = parsePlayerBank(temp.path("resources"), 
						temp.path("oldDevCards"), 
						temp.path("newDevCards"));
				int roads = temp.path("roads").intValue();
				int cities = temp.path("cities").intValue();
				int settlements = temp.path("settlements").intValue();
				int soliders = temp.path("soldiers").intValue();
				int victoryPoints = temp.path("victoryPoints").intValue();
				int monuments = temp.path("monuments").intValue();
				boolean playedDevCard = temp.path("playedDevCard").booleanValue();
				boolean discared = temp.path("discared").booleanValue();
				// int playerID = temp.path("playerID").intValue();
				int playerIndex = temp.path("playerIndex").intValue();
				String name = temp.path("name").textValue();
				String color = temp.path("color").textValue();
				
				Player player = new Player(getColor(color), name, playerIndex);
				player.setPlayerBank(playerBank);
				player.setRoads(new Roads(roads));
				player.setCities(new Cities(cities));
				player.setSettlements(new Settlements(settlements));
				player.setLargestArmy(new LargestArmy(soliders));
				player.setLongestRoad(new LongestRoad(15-roads));
				player.setVictoryPoints(new VictoryPoints(monuments+victoryPoints, victoryPoints));
				player.setHasPlayedCard(playedDevCard);
				player.setHasDiscared(discared);
			}
		}
		return null;
	}
	
	private static PlayerBank parsePlayerBank(JsonNode resourcesNode, JsonNode oldDevCardsNode, JsonNode newDevCardsNode) {
		PlayerBank playerBank = new PlayerBank();
		
		int brick = resourcesNode.path("brick").intValue();
		int wood = resourcesNode.path("wood").intValue();
		int sheep = resourcesNode.path("sheep").intValue();
		int wheat = resourcesNode.path("wheat").intValue();
		int ore = resourcesNode.path("ore").intValue();
		ResourceHand rh = new ResourceHand(brick, wood, sheep, wheat, ore);
		
		int soldier = oldDevCardsNode.path("soldier").intValue();
		int monument = oldDevCardsNode.path("monument").intValue();
		int monopoly = oldDevCardsNode.path("monopoly").intValue();
		int yearOfPlenty = oldDevCardsNode.path("yearOfPlenty").intValue();
		int roadBuild = oldDevCardsNode.path("roadBuilding").intValue();
		DevelopmentHand odv = new DevelopmentHand(soldier, monument, monopoly, yearOfPlenty, roadBuild);
		
		soldier = newDevCardsNode.path("soldier").intValue();
		monument = newDevCardsNode.path("monument").intValue();
		monopoly = newDevCardsNode.path("monopoly").intValue();
		yearOfPlenty = newDevCardsNode.path("yearOfPlenty").intValue();
		roadBuild = newDevCardsNode.path("roadBuilding").intValue();
		DevelopmentHand ndv = new DevelopmentHand(soldier, monument, monopoly, yearOfPlenty, roadBuild);
		
		try {
			playerBank.setRC(rh);
			playerBank.setDC(odv);
			playerBank.addNewDC(ndv);
		} catch (BankException e) {
			e.printStackTrace();
		}
		
		return playerBank;
	}
	
	private static TurnTracker parseTracker(JsonNode trackerNode, List<Player> players) {
		TurnTracker tracker = new TurnTracker();
		
		if (!trackerNode.isMissingNode()) {
			String status = trackerNode.path("status").textValue();
			int currentTurn = trackerNode.path("currentTurn").intValue();
			int longestRoad = trackerNode.path("longestRoad").intValue();
			int largestArmy = trackerNode.path("largestArmy").intValue();
			
			try {
				tracker.setCurrentTurn(currentTurn);
			} catch (NoPlayerFoundException e) {
				e.printStackTrace();
			}
			tracker.setStatus(status);
			
			if (longestRoad != -1) {
				for (Player p: players) {
					if (p.getPlayerID().getPlayerid() == longestRoad) {
						p.getLongestRoad().setHasLongestRoad(true);
					}
				}
			}
			if (largestArmy != -1) {
				for (Player p: players) {
					if (p.getPlayerID().getPlayerid() == largestArmy) {
						p.getLargestArmy().setHasLargestArmy(true);
					}
				}
			}
		}
		
		return tracker;
	}
	
	// String to enum methods
	
	private static HexType getResourceType(String resource) {
		if (resource != null) {
			switch (resource) {
			case "brick": return HexType.BRICK;
			case "wood": return HexType.WOOD;
			case "sheep": return HexType.SHEEP;
			case "wheat": return HexType.WHEAT;
			case "ore": return HexType.ORE;
			default: return HexType.DESERT;
			}
		}
		else {
			return HexType.DESERT;
		}
	}
	
	private static PortType getPortType(String resource) {
		if (resource != null) {
			switch (resource) {
			case "brick": return PortType.BRICK;
			case "wood": return PortType.WOOD;
			case "sheep": return PortType.SHEEP;
			case "wheat": return PortType.WHEAT;
			case "ore": return PortType.ORE;
			default: return PortType.THREE;
			}
		}
		else {
			return PortType.THREE;
		}
	}
	
	private static EdgeDirection getEdgeDirection(String direction) {
		switch (direction) {
		case "N": return EdgeDirection.NorthWest;
		case "NE": return EdgeDirection.NorthEast;
		case "SE": return EdgeDirection.SouthEast;
		case "S": return EdgeDirection.South;
		case "SW": return EdgeDirection.SouthWest;
		case "NW": return EdgeDirection.NorthWest;
		default: return null;
		}
	}
	
	private static VertexDirection getVertexDirection(String direction) {
		switch (direction) {
		case "W": return VertexDirection.West;
		case "NW": return VertexDirection.NorthWest;
		case "NE": return VertexDirection.NorthEast;
		case "E": return VertexDirection.East;
		case "SE": return VertexDirection.SouthEast;
		case "SW": return VertexDirection.SouthWest;
		default: return null;
		}
	}
	
	private static CatanColor getColor(String color) {
		switch (color) {
		case "red": return CatanColor.RED;
		case "orange": return CatanColor.ORANGE;
		case "yellow": return CatanColor.YELLOW;
		case "blue": return CatanColor.BLUE;
		case "green": return CatanColor.GREEN;
		case "purple": return CatanColor.PURPLE;
		case "puce": return CatanColor.PUCE;
		case "white": return CatanColor.WHITE;
		case "brown": return CatanColor.BROWN;
		default: return null;
		}
	}
}
