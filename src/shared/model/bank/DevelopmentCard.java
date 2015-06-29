package shared.model.bank;

import shared.definitions.DevCardType;

public class DevelopmentCard {

	private DevCardType type;
	
	public DevelopmentCard(DevCardType type){
		this.type = type;
	}
	
	/**
	 * retrieves the type of the Development Card.
	 * @return
	 * DevCardType type
	 */

	public DevCardType getType() {
		return type;
	}
	
	
}
