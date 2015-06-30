package shared.communication.input.move;

import shared.locations.EdgeLocation;

/**
 * 
 * @author Matt
 * This class contains the playRoadBuilding method name, the locations for two new roads and the playerIndex for the player doing the action.
 * 
 */
public class PlayRoadBuildingInput extends MoveInput {

	private EdgeLocation location1;
	private EdgeLocation location2;
	
	public PlayRoadBuildingInput(String method, int playerIndex, EdgeLocation location1, EdgeLocation location2) {
		super(method, playerIndex);
		this.location1 = location1;
		this.location2 = location2;
	}

	public EdgeLocation getLocation1() {
		return location1;
	}

	public EdgeLocation getLocation2() {
		return location2;
	}
	
}
