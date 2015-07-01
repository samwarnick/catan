package shared.communication.input;

import shared.locations.HexLocation;

/**
 * @author isaachartung
 *this is the input object for an Soldier command
 */
public class MoveSoldier extends MoveInput {

	private int victimIndex;
	private HexLocation location;
	
	public MoveSoldier(int playerIndex, int victimIndex, HexLocation location) {
		super("/moves/Soldier", playerIndex);
		this.victimIndex = victimIndex;
		this.location = location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}
}
