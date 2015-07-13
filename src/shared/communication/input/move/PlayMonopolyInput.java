package shared.communication.input.move;

/**
 * This class contains the playMonopoly method name, the desired resource and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayMonopolyInput extends MoveInput {

	private String resource = "wool";
	private String type = "Monopoly";

	public String getResource() {
		return resource;
	}

	public String getType() {
		return type;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlayMonopolyInput(int playerIndex, String resource) {
		super("/Monopoly", playerIndex);
		this.resource = resource;
	}

	
}
