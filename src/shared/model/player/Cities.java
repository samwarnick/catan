package shared.model.player;



import shared.model.board.City;

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

	
/**
 * @pre none
 * @param city
 * @throws NoCitiesLeftException
 * @post adds a city, or throws NoCitiesLeftException.
 */
	public void buildCity() throws NoCitiesLeftException {
		if (citiesLeft > 0)
		{
			citiesLeft--;
		}
		else
			throw new NoCitiesLeftException();
	}

	public int getCitiesLeft() {
		return citiesLeft;
	}
	
	

}

@SuppressWarnings("serial")
class NoCitiesLeftException extends Exception{
	
}