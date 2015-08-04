package shared.communication.input.move;

/**
 * This class contains the playMonopoly method name, the desired resource and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayMonopolyInput extends MoveInput {

	private String resource = "wool";
	
	public PlayMonopolyInput() {
		super("/Monopoly", -1);
		this.resource = null;
		super.setType("Monopoly");
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public PlayMonopolyInput(int playerIndex, String resource) {
		super("/Monopoly", playerIndex);
		this.resource = resource;
		super.setType("Monopoly");
	}

	
}
