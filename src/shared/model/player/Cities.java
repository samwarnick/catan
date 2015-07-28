package shared.model.player;


/**
 * 
 * @author Spencer Krieger
 * Contains a list of City and keeps track of how many cities the player has left.
 */
public class Cities {
	
	private int citiesLeft;
	
	public Cities(){
		citiesLeft = 4;
	}
	
	public Cities(int citiesLeft) {
		this.citiesLeft = citiesLeft;
	}

/**
 * @pre none
 * @param city
 * @throws NoCitiesLeftException
 * @post adds a city, or throws NoCitiesLeftException.
 */
	public void buildCity() throws Exception {
		if (citiesLeft > 0)
		{
			citiesLeft--;
		}
		else
			throw new Exception("NoCitiesLeft");
	}

	public int getCitiesLeft() {
		return citiesLeft;
	}
	
	

}