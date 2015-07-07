package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class CitiesTest {

	@Test
	public void testConstructor() {
		Cities cities = new Cities();
		int left = cities.getCitiesLeft();
		assertEquals(4, left);
	}
	
	@Test
	public void testBuilding() {
		Cities cities = new Cities();
		boolean noLeft = false;
		
		try {
			cities.buildCity();
		} catch (NoCitiesLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(3, cities.getCitiesLeft());
		
		try {
			cities.buildCity();
		} catch (NoCitiesLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(2, cities.getCitiesLeft());
		
		try {
			cities.buildCity();
		} catch (NoCitiesLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(1, cities.getCitiesLeft());
		
		try {
			cities.buildCity();
		} catch (NoCitiesLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(0, cities.getCitiesLeft());
		
		try {
			cities.buildCity();
		} catch (NoCitiesLeftException e) {
			noLeft = true;
		}
		assertTrue(noLeft);
		assertEquals(0, cities.getCitiesLeft());
	}
}
