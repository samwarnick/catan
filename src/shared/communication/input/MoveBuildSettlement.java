package shared.communication.input;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * @author isaachartung
 *
 */
public class MoveBuildSettlement extends MoveInput{
	private boolean free;
	private VertexLocation location;
	
	public MoveBuildSettlement(int playerID, boolean free, VertexLocation location){
		super("/moves/buildSettlement", playerID);
		this.free = free;
		this.location = location;
	}
	
	public boolean isFree() {
		return free;
	}

	public VertexLocation getLocation() {
		return location;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public void setLocation(VertexLocation location) {
		this.location = location;
	}
}
