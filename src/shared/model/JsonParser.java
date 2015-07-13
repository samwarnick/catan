package shared.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import shared.definitions.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.bank.*;
import shared.model.board.*;
import shared.model.player.*;
import client.data.GameInfo;
import client.data.PlayerInfo;

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
	
	public static List<GameInfo> gamesFromJson(JsonNode rootNode){
		
		List<GameInfo> games = new ArrayList<GameInfo>();
		JsonNode gamesNode = rootNode.path("game");
		ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
		if (!gamesNode.isMissingNode()) {
			Iterator<JsonNode> iter = gamesNode.elements();
			while (iter.hasNext()) {
				JsonNode temp = iter.next();
				String title = temp.path("title").textValue();
				int id = temp.path("id").intValue();
				JsonNode playersNode = temp.path("player");
				Iterator<JsonNode> iter2 = playersNode.elements();
				
				while (iter2.hasNext()) {
					JsonNode temp2 = iter2.next();
					String color = temp2.path("color").textValue();
					String name = temp2.path("name").textValue();
					int playerid = temp2.path("id").intValue();
					PlayerInfo tempPlayer = new PlayerInfo(name, color, playerid);
					players.add(tempPlayer);
				}
				GameInfo tempGame = new GameInfo(id, title, players);
				players.clear();
				games.add(tempGame);
			}
		}
		else {
			String title = rootNode.path("title").textValue();
			int id = rootNode.path("id").intValue();
			JsonNode playersNode = rootNode.path("player");
			Iterator<JsonNode> iter2 = playersNode.elements();
			
			while (iter2.hasNext()) {
				JsonNode temp2 = iter2.next();
				String color = temp2.path("color").textValue();
				String name = temp2.path("name").textValue();
				int playerid = temp2.path("id").intValue();
				PlayerInfo tempPlayer = new PlayerInfo(name, color, playerid);
				players.add(tempPlayer);
			}
			GameInfo tempGame = new GameInfo(id, title, players);
			players.clear();
			games.add(tempGame);
		}
		return games;
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
			PlayerBank playerBank = parsePlayerBank(playerNode.path("resources"), 
					playerNode.path("oldDevCards"), 
					playerNode.path("newDevCards"));
			int roads = playerNode.path("roads").intValue();
			int cities = playerNode.path("cities").intValue();
			int settlements = playerNode.path("settlements").intValue();
			int soliders = playerNode.path("soldiers").intValue();
			int victoryPoints = playerNode.path("victoryPoints").intValue();
			int monuments = playerNode.path("monuments").intValue();
			boolean playedDevCard = playerNode.path("playedDevCard").booleanValue();
			boolean discared = playerNode.path("discared").booleanValue();
			// int playerID = playerNode.path("playerID").intValue();
			int playerIndex = playerNode.path("playerIndex").intValue();
			String name = playerNode.path("name").textValue();
			String color = playerNode.path("color").textValue();
			
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
			return player;
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
		case "N": return EdgeDirection.North;
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
