package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestRoadTest {

	@Test
	public void test() {
		LongestRoad roads = new LongestRoad();
		assertEquals(0, roads.getNumRoads());
		assertFalse(roads.isHasLongestRoad());
		roads.addRoad();
		roads.addRoad();
		roads.addRoad();
		roads.addRoad();
		roads.addRoad();
		assertEquals(5, roads.getNumRoads());
		roads.setHasLongestRoad(true);
		assertTrue(roads.isHasLongestRoad());
		roads.setHasLongestRoad(false);
		assertFalse(roads.isHasLongestRoad());
		roads.addRoad();
		assertEquals(6, roads.getNumRoads());
		assertFalse(roads.isHasLongestRoad());
		roads.setHasLongestRoad(true);
		assertTrue(roads.isHasLongestRoad());
	}

}
