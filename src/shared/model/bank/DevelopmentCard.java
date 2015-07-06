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

	private int quantity;
	private int limit;
	
	/**
	 * 
	 * @param quantity
	 * @param type
	 * @throws Exception
	 */
	
	public DevelopmentCard(int quantity, DevCardType type) throws Exception{
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
			throw new Exception("bad type parameter");
		}
		if(quantity > limit || quantity < 0) throw new Exception("quantity out of range");
		this.quantity = quantity;
	}
	
	/**
	 * 
	 * @param inc is an int
	 * @throws Exception 
	 * @pre when inc is added to quantity, it does not go over limit or under 0
	 * @post inc is added to the quantity.
	 */
	public void modify(int inc) throws Exception{
		if((quantity + inc) < 0 || (quantity + inc) > limit ) throw new Exception
																	("modify parameter takes quantity out of bounds");
		quantity += inc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws Exception {
		if((quantity) < 0 || (quantity) > limit ) throw new Exception
													("set quantity parameter is out of bounds");
		this.quantity = quantity;
	}
	
	
	
}
