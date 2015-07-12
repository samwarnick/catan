package shared.communication.input.move;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * This class contains the playRoadBuilding method name, the locations for two new roads and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayRoadBuildingInput extends MoveInput {

	private EdgeLocation spot1 = new EdgeLocation(new HexLocation(0,0), EdgeDirection.North);
	private EdgeLocation spot2 = new EdgeLocation(new HexLocation(1,1), EdgeDirection.South);
	private String type = "Road_Building";
	
	public PlayRoadBuildingInput(int playerIndex, EdgeLocation location1, EdgeLocation location2) {
		super("/Road_Building", playerIndex);
	}

	public EdgeLocation getSpot1() {
		return spot1;
	}

	public EdgeLocation getSpot2() {
		return spot2;
	}

	public String getType() {
		return type;
	}

	public void setSpot1(EdgeLocation spot1) {
		this.spot1 = spot1;
	}

	public void setSpot2(EdgeLocation spot2) {
		this.spot2 = spot2;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
