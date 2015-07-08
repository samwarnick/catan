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
import shared.model.ratios.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

	public static GameModel gameModelFromJson(File file) {
		GameModel gameModel = new GameModel(gameID);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(new File("model.json"));
			
			Board board = parseBoard(rootNode.path("map"));
			Bank bank = parseBank(rootNode.path("deck"), rootNode.path("bank"));
			// players
			List<Player> players = parsePlayers(rootNode.path("players"));
			// log
			// chat
			// turn tracker
			// winner
			// version
			gameModel.setBoard(board);
			gameModel.setBank(bank);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gameModel;
	}
	
	private static Bank parseBank(JsonNode deckNode, JsonNode bankNode) {
		Bank bank = new Bank();
		
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
		
		board.setDesertHex(resourceHexes.get(0));
		board.setResourceHexes(resourceHexes);
		board.setRoads(roads);
		board.setBuildings(buildings);
		board.setRobber(robber);
		// TODO: radius?
		return board;
	}
	
	private static List<ResourceHex> parseHexes(JsonNode hexesNode) {
		List<ResourceHex> resourceHexes = new ArrayList<ResourceHex>();
		
		if (hexesNode != null) {
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
		
		if (roadsNode != null) {
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
		
		if (citiesNode != null) {
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
		
		if (settlementsNode != null) {
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
		
		if (portsNode != null) {
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
						validVertex1, 
						validVertex2, 
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
}
