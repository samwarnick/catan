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

	private Spot spot1;
	private Spot spot2;
	private String type = "Road_Building";
	
	public PlayRoadBuildingInput(int playerIndex, EdgeLocation location1, EdgeLocation location2) {
		super("/Road_Building", playerIndex);
		spot1 = new Spot(location1.getHexLoc().getX(), location1.getHexLoc().getY(),
				location1.getDir().toString().toLowerCase());
		spot2 =  new Spot(location2.getHexLoc().getX(), location2.getHexLoc().getY(),
				location2.getDir().toString().toLowerCase());
	}

	public Spot getSpot1() {
		return spot1;
	}

	public Spot getSpot2() {
		return spot2;
	}

	public String getType() {
		return type;
	}

	public void setSpot1(Spot spot1) {
		this.spot1 = spot1;
	}

	public void setSpot2(Spot spot2) {
		this.spot2 = spot2;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	
	
}
