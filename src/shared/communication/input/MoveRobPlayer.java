package shared.communication.input;

import shared.locations.HexLocation;

/**
 * @author isaachartung
 *
 */
public class MoveRobPlayer extends MoveInput {
	
	private int victimIndex;
	private HexLocation location;
	
	public MoveRobPlayer(int playerIndex, int victimIndex, HexLocation location){
		super("/moves/robPlayer", playerIndex);
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
