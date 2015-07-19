package shared.model.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.controller.ModelController;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.player.Player;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class BoardFacade {

	private Board board;
	
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
		if (board.getRobber().getLocation().equals(location.getHexLoc())) locIsRes = true;
		if (board.getRobber().getLocation().equals(adj)) adjIsRes = true;
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
		if (board.getRobber().getLocation().equals(location.getHexLoc())) locIsRes = true;
		if (board.getRobber().getLocation().equals(adj)) adjIsRes = true;
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
	
	public ArrayList<PlayerID> getPlayersOnHex(HexLocation location) {
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
				}
			}
		}
		return players;
	}
}
