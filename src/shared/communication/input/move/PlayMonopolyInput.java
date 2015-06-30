package shared.communication.input.move;

import shared.definitions.ResourceType;

/**
 * 
 * @author Matt
 * This class contains the playMonopoly method name, the desired resource and the playerIndex for the player doing the action.
 * 
 */
public class PlayMonopolyInput extends MoveInput {

	private ResourceType resource;

	public PlayMonopolyInput(int playerIndex, ResourceType resource) {
		super("playMonopoly", playerIndex);
		this.resource = resource;
	}

	public ResourceType getResource() {
		return resource;
	}
	
}
