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
	
	public PlayYearOfPlentyInput(int playerIndex, ResourceType resource1, ResourceType resource2) {
		super("/Year_of_Plenty", playerIndex);
		super.setType("Year_of_Plenty");
		this.resource1 = resource1.toString().toLowerCase();
		this.resource2 = resource2.toString().toLowerCase();
	}
	
	public PlayYearOfPlentyInput() {
		super("/Year_of_Plenty", -1);
		this.resource1 = null;
		this.resource2 = null;
	}

	public String getResource1() {
		return resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}
}
