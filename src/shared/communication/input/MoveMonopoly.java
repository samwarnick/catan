package shared.communication.input;

import shared.definitions.ResourceType;

/**
 * @author isaachartung
 *this is the input object for an Monopoly command
 */
public class MoveMonopoly extends MoveInput{
	
	private ResourceType receiveType;

	public MoveMonopoly(int ID, ResourceType receiveType) {
		super("/moves/Monopoly", ID);
		this.receiveType = receiveType;
	}

	public ResourceType getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(ResourceType receiveType) {
		this.receiveType = receiveType;
	}
	
	

}