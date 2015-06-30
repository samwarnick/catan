package shared.model.bank;

import shared.definitions.DevCardType;

/**
 * @author isaachartung
 *
 *The DevolopmentCard merely holds a DevCardType and allows queries for
 *said type.
 *
 */
public class DevelopmentCard {

	private DevCardType type;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @param type is a DevCarType
	 * @pre a valid DevCardType
	 * @post creates a DevelopmentCard Object with the given type.
	 */
	
	public DevelopmentCard(DevCardType type){
		this.type = type;
	}


	public DevCardType getType() {
		return type;
	}
	
	
}
