package shared.communication.input.move;

import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * This class contains the buildSettlement method name, the location for the settlement and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildCityInput extends MoveInput {

	private Spot vertexLocation;
	private String type = "buildCity";

	public BuildCityInput(int playerIndex, VertexLocation location) {
		super("/buildCity", playerIndex);
		this.vertexLocation = new Spot(location.getHexLoc().getX(), location.getHexLoc().getY(),
											abbreviate(location.getDir()));
	}

	public Spot getVertexLocation() {
		return vertexLocation;
	}

	public String getType() {
		return type;
	}

	public void setVertexLocation(Spot vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public void setType(String type) {
		this.type = type;
	}


	private String abbreviate(VertexDirection e){
		switch(e){
		case East: return "E";
		case NorthEast: return "NE";
		case West: return "W";
		case SouthEast: return "SE";
		case NorthWest: return "NW";
		case SouthWest: return "SW";
		default: return "";
		}
	}
	
}
