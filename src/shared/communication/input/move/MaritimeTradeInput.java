package shared.communication.input.move;

import shared.definitions.ResourceType;

/**
 * This class contains the maritimeTrade method name, the trade ratio, input and output ResourceTypes and the playerIndex for the player doing the action.
 * @author Matt
 * 
 */
public class MaritimeTradeInput extends MoveInput {

	private int ratio;
	private String inputResource;
	private String outputResource;
	private String type = "maritimeTrade";
	
	public MaritimeTradeInput(int playerIndex, int ratio, ResourceType inputResource,
			ResourceType outpurResource) {
		super("/maritimeTrade", playerIndex);
		this.ratio = ratio;
		this.inputResource = inputResource.toString().toLowerCase();
		this.outputResource = outpurResource.toString().toLowerCase();
	}
	
	public MaritimeTradeInput() {
		super("/maritimeTrade", -1);
		this.ratio = -1;
		this.inputResource = null;
		this.outputResource = null;
	}

	public int getRatio() {
		return ratio;
	}

	public String getInputResource() {
		return inputResource;
	}

	public String getOutputResource() {
		return outputResource;
	}

	public String getType() {
		return type;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}

	public void setType(String type) {
		this.type = type;
	}


	
}
