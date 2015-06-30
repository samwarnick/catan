package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class contains the playMonument method name and the index of the player doing the action.
 * 
 */
public class PlayMonumentInput extends MoveInput {

	public PlayMonumentInput(int playerIndex) {
		super("playMonument", playerIndex);
	}

}
