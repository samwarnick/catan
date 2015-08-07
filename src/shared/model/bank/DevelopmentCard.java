package shared.model.bank;

import java.io.Serializable;

import shared.definitions.DevCardType;

/**
 * @author isaachartung
 *
 *The DevolopmentCard merely holds a DevCardType and allows queries for
 *said type.
 *
 */
@SuppressWarnings("serial")
public class DevelopmentCard implements Serializable{

	private int limit;
	private int quantity;
	
	public DevelopmentCard() {
		limit = 0;
		quantity = 0;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 
	 * @param quantity
	 * @param type
	 * @throws Exception
	 */
	
	public DevelopmentCard(int quantity, DevCardType type) throws BankException{
		switch(type){
		case SOLDIER: limit = 14;
			break;
		case MONOPOLY: limit = 2;
			break;
		case YEAR_OF_PLENTY: limit = 2;
			break;
		case ROAD_BUILD: limit = 2;
			break;
		case MONUMENT: limit = 5;
			break;
		default:
			throw new BankException("bad type parameter");
		}
		if(quantity > limit || quantity < 0) throw new BankException("quantity out of range");
		this.quantity = quantity;
	}
	
	/**
	 * 
	 * @param inc is an int
	 * @throws Exception 
	 * @pre when inc is added to quantity, it does not go over limit or under 0
	 * @post inc is added to the quantity.
	 */
	public void modify(int inc) throws BankException{
		if((quantity + inc) < 0 || (quantity + inc) > limit ) throw new BankException
																	("modify parameter takes quantity out of bounds");
		quantity += inc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws BankException {
//		if((quantity) < 0 || (quantity) > limit ) throw new BankException
//													("set quantity parameter is out of bounds");
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
		result = prime * result + quantity;
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
		DevelopmentCard other = (DevelopmentCard) obj;
		if (limit != other.limit)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	
	
}
