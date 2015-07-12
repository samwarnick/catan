package shared.communication.input.move;

import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * This class contains the buildSettlement method name, whether the Settlement is free or not, the location for the Settlement and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildSettlementInput extends MoveInput {

	private boolean free;
	private Spot vertexLocation;
	private String type = "buildSettlement";
	
	public BuildSettlementInput(int playerIndex, boolean isFree, VertexLocation location) {
		super("/buildSettlement", playerIndex);
		this.free = isFree;
		this.vertexLocation = new Spot(location.getHexLoc().getX(), location.getHexLoc().getY(),
				abbreviate(location.getDir()));
	}
	
	public boolean isFree() {
		return free;
	}


	public String getType() {
		return type;
	}

	public void setFree(boolean free) {
		this.free = free;
	}


	public void setType(String type) {
		this.type = type;
	}

	public Spot getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(Spot vertexLocation) {
		this.vertexLocation = vertexLocation;
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
