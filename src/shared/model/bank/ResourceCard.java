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
	private ResourceType type;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @param quantity is an int
	 * @throws Exception 
	 * @pre quantity is a non-negative int <= 19;
	 * @post sets the quantity to the given parameter.
	 */
	
	public ResourceCard(int quantity, ResourceType type) throws BankException {
		if(quantity<0||quantity>19) throw new BankException("quantity out of bounds");
		else {
			this.quantity = quantity;
			this.type = type;
		}
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws BankException {
		if(quantity < 0 || quantity > 19 ) throw new BankException("set quantity parameter is out of bounds");
		this.quantity = quantity;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * 
	 * @param inc is an int
	 * @throws Exception 
	 * @pre when inc is added to quantity, it does not go over 19 or under 0
	 * @post inc is added to the quantity.
	 */
	public void modify(int inc) throws BankException{
		if((quantity + inc) < 0 || (quantity + inc) > 19 ) throw new BankException("modify value takes quantity out of bounds");
		quantity += inc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantity;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceCard other = (ResourceCard) obj;
		if (quantity != other.quantity)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	

}
