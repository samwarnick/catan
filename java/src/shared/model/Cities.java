package shared.model;

import java.util.ArrayList;

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
			throw new Exception();
	}

	public int getCitiesLeft() {
		return citiesLeft;
	}

}
