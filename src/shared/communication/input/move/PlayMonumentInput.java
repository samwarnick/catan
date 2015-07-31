package shared.communication.input.move;

/**
 * This class contains the playMonument method name and the index of the player doing the action.
 * @author Matt
 * 
 */
public class PlayMonumentInput extends MoveInput {
	
	private String type = "Monument";
	
	public PlayMonumentInput() {
		super("/Monument", -1);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlayMonumentInput(int playerIndex) {
		super("/Monument", playerIndex);
	}

}
