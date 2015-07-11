package shared.communication.input.move;

import shared.communication.input.Input;

/**
 * This class is the base class for all Move server methods and contains the base method name and playerIndex.
 * @author Matt
 * 
 */
public class MoveInput extends Input {

	private int playerIndex;
	
	public MoveInput(String method, int playerIndex) {
		super("/moves" + method);
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
}
