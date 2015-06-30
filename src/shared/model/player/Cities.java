package shared.model.player;

import java.util.ArrayList;


import shared.model.board.City;

/**
 * 
 * @author Spencer Krieger
 *
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