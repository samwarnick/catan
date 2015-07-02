package shared.model.board;

import java.util.ArrayList;
import java.util.List;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
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
	 * @post returns true if there is no road at that location already.  false otherwise
	 */
	public boolean canBuildRoad(EdgeLocation location){
		EdgeLocation ambiguity = location.getAmbiguousEdge();
		
		List<Road> roads = board.getRoads();
		for(Road road : roads) {
			if (road.getLocation().equals(location)
					|| road.getLocation().equals(ambiguity)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no settlement at that location or any of the locations one away from it.  Returns false otherwise
	 */
	public boolean canBuildSettlement(VertexLocation location){
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
		
		List<Settlement> settlements = board.getSettlements();
		for(Settlement settlement : settlements) {
			for(VertexLocation loc : vertices) {
				if (settlement.getLocation().equals(loc)) {
					return false;	
				}
			}
		}
		
		return true;
	}
	
}
