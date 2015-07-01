package shared.model.player;

import java.util.ArrayList;


import shared.model.board.City;

/**
 * 
 * @author Spencer Krieger
 * Contains a list of City and keeps track of how many cities the player has left.
 */
public class Cities {
	
	private ArrayList<City> cities;
	private int citiesLeft;
	
	public Cities(){
		cities = new ArrayList<City>();
		citiesLeft = 4;
	}

	public ArrayList<City> getCities() {
		return cities;
	}
	
/**
 * @pre none
 * @param city
 * @throws NoCitiesLeftException
 * @post adds a city, or throws NoCitiesLeftException.
 */
	public void buildCity(City city) throws NoCitiesLeftException {
		if (citiesLeft > 0)
		{
			cities.add(city);
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