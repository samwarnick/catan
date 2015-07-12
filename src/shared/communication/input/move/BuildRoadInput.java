package shared.communication.input.move;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

/**
 * This class contains the buildRoad method name, whether the Road is free or not, the location for the Road and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class BuildRoadInput extends MoveInput {

	private boolean free;
	private Spot roadLocation;
	private String type = "buildRoad";
	
	public BuildRoadInput(int playerIndex, boolean isFree, EdgeLocation location1) {
		super("/buildRoad", playerIndex);
		this.free = isFree;
		this.roadLocation = new Spot(location1.getHexLoc().getX(), location1.getHexLoc().getY(),
									abbreviate(location1.getDir()));
	}

	public boolean isFree() {
		return free;
	}

	public Spot getRoadLocation() {
		return roadLocation;
	}

	public String getType() {
		return type;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public void setRoadLocation(Spot roadLocation) {
		this.roadLocation = roadLocation;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private String abbreviate(EdgeDirection e){
		switch(e){
		case North: return "N";
		case NorthEast: return "NE";
		case South: return "S";
		case SouthEast: return "SE";
		case NorthWest: return "NW";
		case SouthWest: return "SW";
		default: return "";
		}
		
	}
	
	
		
}
