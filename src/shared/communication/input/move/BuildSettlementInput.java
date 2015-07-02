package shared.communication.input.move;

import shared.locations.VertexLocation;

/**
 * This class contains the buildSettlement method name, whether the Settlement is free or not, the location for the Settlement and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildSettlementInput extends MoveInput {

	private boolean isFree;
	private VertexLocation location;
	
	public BuildSettlementInput(int playerIndex, boolean isFree, VertexLocation location) {
		super("buildSettlement", playerIndex);
		this.isFree = isFree;
		this.location = location;
	}

	public boolean isFree() {
		return isFree;
	}

	public VertexLocation getLocation() {
		return location;
	}
	
}
