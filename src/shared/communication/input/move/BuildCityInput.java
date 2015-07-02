package shared.communication.input.move;

import shared.locations.VertexLocation;

/**
 * This class contains the buildSettlement method name, the location for the settlement and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildCityInput extends MoveInput {

	private VertexLocation location;

	public BuildCityInput(String method, int playerIndex, VertexLocation location) {
		super(method, playerIndex);
		this.location = location;
	}

	public VertexLocation getLocation() {
		return location;
	}
	
}
