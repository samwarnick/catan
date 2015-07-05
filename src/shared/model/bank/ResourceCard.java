package shared.model.bank;

import shared.definitions.ResourceType;

/**
 * @author isaachartung
 *
 *The ResourceCard card holds only its quantity, which cannot exceed 19.
 *
 */
public class ResourceCard {
	
	private int quantity;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @param type is a ResourceType
	 * @pre a valid ResourceType
	 * @post creates a DevelopmentCard Object with the given type.
	 */

	public ResourceCard(ResourceType type) {
		this.type = type;
	}

	public ResourceType getType() {
		return type;
	}	

}
