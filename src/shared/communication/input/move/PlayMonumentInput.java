package shared.communication.input.move;

/**
 * This class contains the playMonument method name and the index of the player doing the action.
 * @author Matt
 * 
 */
public class PlayMonumentInput extends MoveInput {

	public PlayMonumentInput(int playerIndex) {
		super("playMonument", playerIndex);
	}

}
