package shared.communication.input.move;

import shared.locations.HexLocation;

/**
 * This class contains the playSoldier method name, the new location for the robber and the playerIndexes for the player stealing and being stolen from.
 * @author Matt
 * 
 */
public class PlaySoldierInput extends MoveInput {

	private HexLocation location = new HexLocation(0,0);
	private int victimIndex;
	private String type = "Soldier";
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlaySoldierInput(int playerIndex, HexLocation location, int victimIndex) {
		super("/Soldier", playerIndex);
		this.victimIndex = victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}
		
}
