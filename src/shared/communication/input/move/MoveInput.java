package shared.communication.input.move;

import shared.communication.input.Input;

/**
 * 
 * @author Matt
 * This class is the base class for all Move server methods and contains the base method name and playerIndex.
 * 
 */
public class MoveInput extends Input {

	private int playerIndex;
	
	public MoveInput(String method, int playerIndex) {
		super("/move/" + method);
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
}
