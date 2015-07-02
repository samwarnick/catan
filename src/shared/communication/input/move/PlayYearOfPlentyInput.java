package shared.communication.input.move;

import shared.definitions.ResourceType;

/**
 * This class contains the playYearOfPlenty method name, the desired resource types and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class PlayYearOfPlentyInput extends MoveInput {

	private ResourceType resource1;
	private ResourceType resource2;
	
	public PlayYearOfPlentyInput(int playerIndex, ResourceType resource1, ResourceType resource2) {
		super("playYearOfPlenty", playerIndex);
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	public ResourceType getResource1() {
		return resource1;
	}

	public ResourceType getResource2() {
		return resource2;
	}
	
}
