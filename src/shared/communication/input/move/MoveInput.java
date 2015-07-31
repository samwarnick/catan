package shared.communication.input.move;

import shared.communication.input.Input;

/**
 * This class is the base class for all Move server methods and contains the base method name and playerIndex.
 * @author Matt
 * 
 */
public class MoveInput extends Input {

	private String type;
	private int playerIndex;
	
	public MoveInput(String method, int playerIndex) {
		super("/moves" + method);
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
