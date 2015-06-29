package shared.model.bank;

import shared.definitions.ResourceType;

public class ResourceCard {
	
	private ResourceType type;

	public ResourceCard(ResourceType type) {
		this.type = type;
	}

	public ResourceType getType() {
		return type;
	}	

}
