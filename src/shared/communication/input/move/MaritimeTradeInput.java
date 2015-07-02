package shared.communication.input.move;

import shared.definitions.ResourceType;

/**
 * This class contains the maritimeTrade method name, the trade ratio, input and output ResourceTypes and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class MaritimeTradeInput extends MoveInput {

	private int ratio;
	private ResourceType inputResource;
	private ResourceType outpurResource;
	
	public MaritimeTradeInput(int playerIndex, int ratio, ResourceType inputResource,
			ResourceType outpurResource) {
		super("maritimeTrade", playerIndex);
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outpurResource = outpurResource;
	}

	public int getRatio() {
		return ratio;
	}

	public ResourceType getInputResource() {
		return inputResource;
	}

	public ResourceType getOutpurResource() {
		return outpurResource;
	}
	
}
