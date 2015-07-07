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
}
