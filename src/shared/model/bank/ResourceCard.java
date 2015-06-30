package shared.model.bank;

import shared.definitions.ResourceType;

/**
 * @author isaachartung
 *
 *The ResourceCard card holds only a ResourceType.  It contains a method to
 *query this type.
 *
 */
public class ResourceCard {
	
	private ResourceType type;
	
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
