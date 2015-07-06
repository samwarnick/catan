package shared.model.bank;

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
	 * @param quantity is an int
	 * @throws Exception 
	 * @pre quantity is a non-negative int <= 19;
	 * @post sets the quantity to the given parameter.
	 */
	
	public ResourceCard(int quantity) throws BankException {
		if(quantity<0||quantity>19) throw new BankException("quantity out of bounds");
		else this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws BankException {
		if(quantity < 0 || quantity > 19 ) throw new BankException("set quantity parameter is out of bounds");
		this.quantity = quantity;
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


}
