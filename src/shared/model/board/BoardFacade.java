package shared.model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.player.Player;
import shared.model.ratios.*;
/**
 * 
 * @author Spencer Krieger
 *
 */
@SuppressWarnings("serial")
public class BoardFacade implements Serializable{

	@JsonIgnore private Board board;
	
	public BoardFacade(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no existing road at the location and if the player has an adjacent road.  false otherwise
	 */
	public boolean canBuildRoad(Player player, EdgeLocation location, boolean allowDisconnected, boolean isSettingUp, VertexLocation settlement) {
		EdgeLocation ambiguity = location.getAmbiguousEdge();
		
		List<Road> roads = board.getRoads();
		for(Road road : roads) {
			if (road.getLocation().equals(location)
					|| road.getLocation().equals(ambiguity)) {
				return false;
			}
		}
		
		if (isSettingUp) {
			boolean touchesSettlement = false;
			List<EdgeLocation> edges = settlement.getAdjacentEdges();
			List<EdgeLocation> adjacents = settlement.getAdjacentEdges();
			for (EdgeLocation edge : edges) {
				adjacents.add(edge.getAmbiguousEdge());
			}
			for (EdgeLocation adjacentEdge : adjacents) {
				if (adjacentEdge.equals(location)) {
					touchesSettlement = true;
				}
			}
			if (!touchesSettlement) {
				return false;				
			}
		}
		
		// check if this location is on the map
		List<ResourceHex> resources = board.getResourceHexes();
		List<WaterHex> waters = board.getWaterHexes();
		boolean locIsRes = false;
		boolean locIsWater = false;
		boolean adjIsRes = false;
		boolean adjIsWater = false;
		HexLocation adj = location.getHexLoc().getNeighborLoc(EdgeDirection.North);
		
		if (board.getDesertHex().getLocation().equals(location.getHexLoc())) locIsRes = true;
		if (board.getDesertHex().getLocation().equals(adj)) adjIsRes = true;
		
		for (ResourceHex resHex : resources) {
			if (resHex.getLocation().equals(location.getHexLoc())) {
				locIsRes = true;
			}
			if (resHex.getLocation().equals(adj)) {
				adjIsRes = true;
			}
		}
		for (WaterHex waterHex : waters) {
			if (waterHex.getLocation().equals(location.getHexLoc())) {
				locIsWater = true;
			}
			if (waterHex.getLocation().equals(adj)) {
				adjIsWater = true;
			}
		}
		if ((!locIsRes && !locIsWater) || (!adjIsRes && !adjIsWater)) {
			return false;
		}
		if (locIsWater && location.getHexLoc().getX() >= 0 && location.getDir().equals(EdgeDirection.NorthEast)) {
			return false;
		}
		if (locIsWater && location.getHexLoc().getX() <= 0 && location.getDir().equals(EdgeDirection.NorthWest)) {
			return false;
		}
		if (locIsWater && adjIsWater) {
			if (adj.getX() == -3) {
				if (!location.getDir().equals(EdgeDirection.NorthEast)) return false;
				if (location.getHexLoc().getY() == 0) return false;
			}
			if (adj.getX() == 3) {
				if (!location.getDir().equals(EdgeDirection.NorthWest)) return false;
				if (location.getHexLoc().getY() == -3) return false;
			}
		}
				
		if (!allowDisconnected) {
			// check for adjacent settlement or city belonging to player
			List<Vertex> buildings = board.getBuildings();
			for (Vertex building : buildings) {
				if (building.getOwner().equals(player.getPlayerID())) {
					List<EdgeLocation> edges = building.getLocation().getAdjacentEdges();
					List<EdgeLocation> adjacents = building.getLocation().getAdjacentEdges();
					for (EdgeLocation edge : edges) {
						adjacents.add(edge.getAmbiguousEdge());
					}
					for (EdgeLocation adjacentEdge : adjacents) {
						if (adjacentEdge.equals(location)) {
							return true;
						}
					}
				}
				
			}
			
			if (!isSettingUp) {
				// check for adjacent road belonging to player
				// check left vertex and edges
				List<VertexLocation> leftVertices = new ArrayList<VertexLocation>();
				leftVertices.add(location.getLeftVertex());
				leftVertices.addAll(location.getLeftVertex().getAmbiguousVertices());
				boolean hasOpponentBuilding = false;
				for (Vertex building : buildings) {
					for (VertexLocation left : leftVertices) {
						if (building.getLocation().equals(left) && !building.getOwner().equals(player.getPlayerID())) {
							hasOpponentBuilding = true;
						}
					}
				}
				
				List<EdgeLocation> edges = location.getLeftAdjacentEdges();
				List<EdgeLocation> adjacents = location.getLeftAdjacentEdges();
				for (EdgeLocation edge : edges) {
					adjacents.add(edge.getAmbiguousEdge());
				}
				for (Road road : roads) {
					for (EdgeLocation adjacentEdge : adjacents) {
						if (road.getLocation().equals(adjacentEdge) && road.getOwner().equals(player.getPlayerID())) {
							if (hasOpponentBuilding) {
								return false;
							}
							else {
								return true;								
							}
						}
					}
				}
				
				// check right vertex and edges
				List<VertexLocation> rightVertices = new ArrayList<VertexLocation>();
				rightVertices.add(location.getRightVertex());
				rightVertices.addAll(location.getRightVertex().getAmbiguousVertices());
				hasOpponentBuilding = false;
				for (Vertex building : buildings) {
					for (VertexLocation right : rightVertices) {
						if (building.getLocation().equals(right) && !building.getOwner().equals(player.getPlayerID())) {
							hasOpponentBuilding = true;
						}
					}
				}
				
				edges = location.getRightAdjacentEdges();
				adjacents = location.getRightAdjacentEdges();
				for (EdgeLocation edge : edges) {
					adjacents.add(edge.getAmbiguousEdge());
				}
				for (Road road : roads) {
					for (EdgeLocation adjacentEdge : adjacents) {
						if (road.getLocation().equals(adjacentEdge) && road.getOwner().equals(player.getPlayerID())) {
							if (hasOpponentBuilding) {
								return false;
							}
							else {
								return true;								
							}
						}
					}
				}
			}
			return false;
		}
		
		
		
		return true;
	}
	
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no settlement at that location or any of the locations one away from it and if the player has an adjacent road.  Returns false otherwise
	 */
	public boolean canBuildSettlement(Player player, VertexLocation location, boolean allowDisconnected) {
		// create list to hold all VertexLocations that need to be checked
		ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
		vertices.add(location);
		// add ambiguities of the given location
		vertices.addAll(location.getAmbiguousVertices());		
		
		// get list of adjacent VertexLocations
		ArrayList<VertexLocation> adjacentHexes = location.getAdjacentVertices();
		vertices.addAll(adjacentHexes);
		// for each adjacent vertex, add all ambiguities
		for (VertexLocation loc : adjacentHexes) {
			vertices.addAll(loc.getAmbiguousVertices());
		}

		// check if this location is on the map
		List<ResourceHex> resources = board.getResourceHexes();
		List<WaterHex> waters = board.getWaterHexes();
		boolean locIsRes = false;
		boolean locIsWater = false;
		boolean adjIsRes = false;
		boolean adjIsWater = false;
		HexLocation adj = location.getHexLoc().getNeighborLoc(EdgeDirection.North);
		if (board.getDesertHex().getLocation().equals(location.getHexLoc())) locIsRes = true;
		if (board.getDesertHex().getLocation().equals(adj)) adjIsRes = true;
		for (ResourceHex resHex : resources) {
			if (resHex.getLocation().equals(location.getHexLoc())) {
				locIsRes = true;
			}
			if (resHex.getLocation().equals(adj)) {
				adjIsRes = true;
			}
		}
		for (WaterHex waterHex : waters) {
			if (waterHex.getLocation().equals(location.getHexLoc())) {
				locIsWater = true;
			}
			if (waterHex.getLocation().equals(adj)) {
				adjIsWater = true;
			}
		}
		if ((!locIsRes && !locIsWater) || (!adjIsRes && !adjIsWater)) {
			return false;
		}
		if (locIsWater && adjIsWater) {
			if (adj.getX() == -3 && location.getDir().equals(VertexDirection.NorthWest)) {
				return false;
			}
			if (adj.getX() == 3 && location.getDir().equals(VertexDirection.NorthEast)) {
				return false;
			}
		}
		
		List<Vertex> buildings = board.getBuildings();
		for (Vertex building : buildings) {
			for (VertexLocation loc : vertices) {
				if (building.getLocation().equals(loc)) {
					return false;	
				}
			}
		}
		
		if (!allowDisconnected) {
			// check for adjacent road belonging to player
			List<Road> roads = board.getRoads();
			List<EdgeLocation> edges = location.getAdjacentEdges();
			List<EdgeLocation> adjacents = location.getAdjacentEdges();
			for (EdgeLocation edge : edges) {
				adjacents.add(edge.getAmbiguousEdge());
			}
			for (Road road : roads) {
				for (EdgeLocation adjacentEdge : adjacents) {
					if (road.getLocation().equals(adjacentEdge) && road.getOwner().equals(player.getPlayerID())) {
						return true;
					}
				}
			} 
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is a settlement owned by the player at the given location. false otherwise
	 */
	public boolean canBuildCity(Player player, VertexLocation location, boolean allowDisconnected) {
		// create list to hold all VertexLocations that need to be checked
		ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
		vertices.add(location);
		// add ambiguities of the given location
		vertices.addAll(location.getAmbiguousVertices());
		
		List<Vertex> buildings = board.getBuildings();
		for(Vertex building : buildings) {
			for(VertexLocation loc : vertices) {
				if (building.getLocation().equals(loc)
						&& building.getClass() == Settlement.class
						&& building.getOwner().equals(player.getPlayerID())) {
					return true;	
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if this location is a valid resourceHex
	 */
	public boolean canPlaceRobber(HexLocation location) {
		// if robber is already at this location return false
		if (board.getRobber().getLocation().equals(location)) {
			return false;
		}
		
		// if location is valid resource hex return true
		List<ResourceHex> resources = board.getResourceHexes();
		for (ResourceHex hex : resources) {
			if (hex.getLocation().equals(location)) {
				return true;
			}
		}
		return false;
	}
	
	public List<PlayerID> getPlayersOnHex(HexLocation location) {
		List<VertexLocation> vertices = location.getVertices();
		List<VertexLocation> allVertices = new ArrayList<VertexLocation>(vertices);
		for (VertexLocation vertex : vertices) {
			allVertices.addAll(vertex.getAmbiguousVertices());
		}
		
		ArrayList<PlayerID> players = new ArrayList<PlayerID>();
		
		List<Vertex> buildings = board.getBuildings();
		for (Vertex building : buildings) {
			for (VertexLocation vertex : allVertices) {
				if (building.getLocation().equals(vertex)){
					players.add(building.getOwner());
					if (building.getClass() == City.class) {
						players.add(building.getOwner());
					}
				}
			}
		}
		return players;
	}
	
	public TradeRatios getRatiosForPlayer(Player player) {
		List<PortHex> ports = board.getPorts();
		List<Vertex> buildings = board.getBuildings();
		
		TradeRatios ratios = new TradeRatios();
		TradeRatio brick = ratios.getTradeRatio(ResourceType.BRICK);
		TradeRatio ore = ratios.getTradeRatio(ResourceType.ORE);
		TradeRatio sheep = ratios.getTradeRatio(ResourceType.SHEEP);
		TradeRatio wheat = ratios.getTradeRatio(ResourceType.WHEAT);
		TradeRatio wood = ratios.getTradeRatio(ResourceType.WOOD);
		
		for (Vertex building : buildings) {
			if (building.getOwner().equals(player.getPlayerID())) {
				for (PortHex port : ports) {
					for (VertexLocation vertex : port.getVertices()) {
						if (vertex.equals(building.getLocation())) {
							try {
								switch (port.getPortType()) {
								case BRICK:
									brick.setRatio(2);
									break;
								case ORE:
									ore.setRatio(2);
									break;
								case SHEEP:
									sheep.setRatio(2);
									break;
								case WHEAT:
									wheat.setRatio(2);
									break;
								case WOOD:
									wood.setRatio(2);
									break;
								case THREE:
									if (brick.getRatio() > 3) brick.setRatio(3);
									if (wheat.getRatio() > 3) wheat.setRatio(3);
									if (ore.getRatio() > 3) ore.setRatio(3);
									if (wood.getRatio() > 3) wood.setRatio(3);
									if (sheep.getRatio() > 3) sheep.setRatio(3);
									break;
								default:
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		return ratios;
		
		// create TradeRatios object

		// for each building
			// if owned by player
				// for each port get vertices
				// if same loc as building set trade ratio for type
		
	}
}
