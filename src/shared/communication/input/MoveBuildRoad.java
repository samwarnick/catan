package shared.communication.input;

import shared.locations.EdgeLocation;

/**
 * @author isaachartung
 *this is the input object for an BuildRoad command
 *
 */
public class MoveBuildRoad extends MoveInput {
	
	private boolean free;
	private EdgeLocation location;
	
	public MoveBuildRoad(int playerID, boolean free, EdgeLocation location){
		super("/moves/buildRoad", playerID);
		this.free = free;
		this.location = location;
	}

	public boolean isFree() {
		return free;
	}

	public EdgeLocation getLocation() {
		return location;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	

}
