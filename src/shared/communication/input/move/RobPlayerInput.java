package shared.communication.input.move;

import shared.locations.HexLocation;

/**
 * This class contains the robPlayer method name, the new location for the robber and the playerIndexes for the player stealing and being stolen from.
 * @author Matt
 * 
 */
public class RobPlayerInput extends MoveInput {

	private int victimIndex;
	private SoldierSpot location;
	private HexLocation hexLocation;

	public RobPlayerInput(int playerIndex, HexLocation location, int victimIndex) {
		super("/robPlayer", playerIndex);
		setType("robPlayer");
		this.location = new SoldierSpot(Integer.toString(location.getX()), Integer.toString(location.getY()));
		this.victimIndex = victimIndex;
		hexLocation = location;
	}
	
	public RobPlayerInput() {
		super("/robPlayer", -1);
		setType("robPlayer");
		this.location = null;
		this.victimIndex = -1;
		hexLocation = null;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	public void setLocation(SoldierSpot location) {
		this.location = location;
	}

	public void setHexLocation(HexLocation hexLocation) {
		this.hexLocation = hexLocation;
	}

	public SoldierSpot getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}
	
	public HexLocation getHexLocation() {
		return hexLocation;
	}
		
}
