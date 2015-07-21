package shared.communication.input.move;

import shared.definitions.ResourceType;

/**
 * This class contains the playYearOfPlenty method name, the desired resource types and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayYearOfPlentyInput extends MoveInput {

	private String resource1 = "wood";
	private String resource2 = "sheep";
	private String type = "Year_of_Plenty";
	
	public PlayYearOfPlentyInput(int playerIndex, ResourceType resource1, ResourceType resource2) {
		super("/Year_of_Plenty", playerIndex);
		this.resource1 = resource1.toString().toLowerCase();
		this.resource2 = resource2.toString().toLowerCase();
	}

	public String getResource1() {
		return resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public String getType() {
		return type;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
