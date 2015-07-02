package shared.communication.input.move;

import shared.locations.EdgeLocation;

/**
 * This class contains the buildRoad method name, whether the Road is free or not, the location for the Road and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildRoadInput extends MoveInput {

	private boolean isFree;
	private EdgeLocation location;
	
	public BuildRoadInput(int playerIndex, boolean isFree, EdgeLocation location) {
		super("buildRoad", playerIndex);
		this.isFree = isFree;
		this.location = location;
	}
	
	public boolean isFree() {
		return isFree;
	}
	
	public EdgeLocation getLocation() {
		return location;
	}
		
}
