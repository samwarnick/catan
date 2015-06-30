package shared.communication.input.move;

import shared.locations.HexLocation;

/**
 * 
 * @author Matt
 * This class contains the playSoldier method name, the new location for the robber and the playerIndexes for the player stealing and being stolen from.
 * 
 */
public class PlaySoldierInput extends MoveInput {

	private HexLocation location;
	private int victimIndex;
	
	public PlaySoldierInput(int playerIndex, HexLocation location, int victimIndex) {
		super("playSoldier", playerIndex);
		this.location = location;
		this.victimIndex = victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}
		
}
