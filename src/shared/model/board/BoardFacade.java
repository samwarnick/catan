package shared.model.board;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class BoardFacade {

	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no road at that location already.  false otherwise
	 */
	public boolean canBuildRoad(EdgeLocation location){
		return false;
	}
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if there is no settlement at that location or any of the locations one away from it.  Returns false otherwise
	 */
	public boolean canBuildSettlement(VertexLocation location){
		return false;
	}
	
}
