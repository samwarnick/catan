package shared.communication.input.move;

import shared.locations.EdgeLocation;

/**
 * This class contains the playRoadBuilding method name, the locations for two new roads and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayRoadBuildingInput extends MoveInput {

	private EdgeLocation location1;
	private EdgeLocation location2;
	private String type = "Road_Building";
	
	public PlayRoadBuildingInput(int playerIndex, EdgeLocation location1, EdgeLocation location2) {
		super("/Road_Building", playerIndex);
		this.location1 = location1;
		this.location2 = location2;
	}
	

	public EdgeLocation getLocation1() {
		return location1;
	}

	public void setLocation1(EdgeLocation location1) {
		this.location1 = location1;
	}

	public EdgeLocation getLocation2() {
		return location2;
	}

	public void setLocation2(EdgeLocation location2) {
		this.location2 = location2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	
	
}
