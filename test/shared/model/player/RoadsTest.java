package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.model.player.Roads;

public class RoadsTest {

	@Test
	public void testBuilding() {
		Roads roads = new Roads();
		boolean noLeft = false;
		assertEquals(15, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(14, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(13, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(12, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(11, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(10, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(9, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(8, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(7, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(6, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(5, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(4, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(3, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(2, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(1, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(0, roads.getRoadsLeft());
		
		try {
			roads.buildRoad();
		} catch (Exception e) {
			noLeft = true;
		}
		assertTrue(noLeft);
		assertEquals(0, roads.getRoadsLeft());
	}

}
