package shared.communication.input.move;

import shared.locations.VertexLocation;

/**
 * This class contains the buildSettlement method name, the location for the settlement and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildCityInput extends MoveInput {

	VertexLocation location;
	private String type = "buildCity";

	public BuildCityInput(int playerIndex, VertexLocation location) {
		super("/buildCity", playerIndex);
		this.location = location;
	}

	public VertexLocation getVertexLocation() {
		return location;
	}

	public String getType() {
		return type;
	}

	public void setVertexLocation(VertexLocation location) {
		this.location = location;
	}

	public void setType(String type) {
		this.type = type;
	}


//	private String abbreviate(VertexDirection e){
//		switch(e){
//		case East: return "E";
//		case NorthEast: return "NE";
//		case West: return "W";
//		case SouthEast: return "SE";
//		case NorthWest: return "NW";
//		case SouthWest: return "SW";
//		default: return "";
//		}
//	}
//	
}
