package shared.communication.input.move;

import shared.locations.HexLocation;

/**
 * This class contains the playSoldier method name, the new location for the robber and the playerIndexes for the player stealing and being stolen from.
 * @author Matt
 * 
 */
public class PlaySoldierInput extends MoveInput {

	private int victimIndex;
	private SoldierSpot location;
	

	public PlaySoldierInput(int playerIndex, HexLocation location, int victimIndex) {
		super("/Soldier", playerIndex);
		setType("Soldier");
		this.victimIndex = victimIndex;
		this.location = new SoldierSpot(Integer.toString(location.getX()), Integer.toString(location.getY()));
	}

	public SoldierSpot getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}
		
}
