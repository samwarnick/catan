package shared.model.board;

import java.util.ArrayList;
import java.util.List;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.player.Player;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class BoardFacade {

	private Board board;
	
	
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no existing road at the location and if the player has an adjacent road.  false otherwise
	 */
	public boolean canBuildRoad(Player player, EdgeLocation location) {
		EdgeLocation ambiguity = location.getAmbiguousEdge();
		
		List<Road> roads = board.getRoads();
		for(Road road : roads) {
			if (road.getLocation().equals(location)
					|| road.getLocation().equals(ambiguity)) {
				return false;
			}
		}
		
		// check for adjacent road belonging to player
		List<EdgeLocation> edges = location.getAdjacentEdges();
		List<EdgeLocation> adjacents = location.getAdjacentEdges();
		for(EdgeLocation edge: edges) {
			adjacents.add(edge.getAmbiguousEdge());
		}
		
		for(Road road : roads) {
			for(EdgeLocation adjacentEdge : adjacents) {
				if (road.getLocation().equals(adjacentEdge) && road.getOwner().equals(player.getPlayerID())){
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
	 * @post returns true if there is no settlement at that location or any of the locations one away from it and if the player has an adjacent road.  Returns false otherwise
	 */
	public boolean canBuildSettlement(Player player, VertexLocation location) {
		// create list to hold all VertexLocations that need to be checked
		ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
		// add ambiguities of the given location
		vertices.addAll(location.getAbiguousVertices());
		
		// get list of adjacent VertexLocations
		ArrayList<VertexLocation> adjacentHexes = location.getAdjacentVertices();
		// for each adjacent vertex, add all ambiguities
		for(VertexLocation loc : adjacentHexes) {
			vertices.addAll(loc.getAbiguousVertices());
		}
		
		List<Vertex> buildings = board.getBuildings();
		for(Vertex building : buildings) {
			for(VertexLocation loc : vertices) {
				if (building.getLocation().equals(loc)) {
					return false;	
				}
			}
		}
		
		// check for adjacent road belonging to player
		List<Road> roads = board.getRoads();
		List<EdgeLocation> edges = location.getAdjacentEdges();
		List<EdgeLocation> adjacents = location.getAdjacentEdges();
		for(EdgeLocation edge: edges) {
			adjacents.add(edge.getAmbiguousEdge());
		}
		
		for(Road road : roads) {
			for(EdgeLocation adjacentEdge : adjacents) {
				if (road.getLocation().equals(adjacentEdge) && road.getOwner().equals(player.getPlayerID())){
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
	 * @post returns true if there is a settlement owned by the player at the given location. false otherwise
	 */
	public boolean canBuildCity(Player player, VertexLocation location) {
		// create list to hold all VertexLocations that need to be checked
		ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
		// add ambiguities of the given location
		vertices.addAll(location.getAbiguousVertices());
		
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
	
}
