package shared.model.player;

import java.util.ArrayList;
/**
 * 
 * @author Spencer Krieger
 *
 */
public class Cities {
	
	private List<City> cities;
	private int citiesLeft;
	
	public Cities(){
		cities = new ArrayList<City>();
		citiesLeft = 4;
	}

	public List<City> getCities() {
		return cities;
	}

	public void buildCity(City city) {
		if (citiesLeft > 0)
		{
			cities.Add(city);
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