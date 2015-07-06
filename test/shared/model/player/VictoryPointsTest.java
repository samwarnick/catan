package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VictoryPointsTest {

	private static VictoryPoints points;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		points = new VictoryPoints();
	}

	@Test
	public void testAdd() {
		points.addPublicVictoryPoint();
		assertEquals(1, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(2, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(3, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(4, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(5, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(6, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(7, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(8, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(9, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(10, points.getTotalVictoryPoints());
	}
	
	@Test
	public void testSubtract() {
		assertEquals(10, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(1);
		assertEquals(9, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(2);
		assertEquals(7, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(1);
		assertEquals(6, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(2);
		assertEquals(4, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(1);
		assertEquals(3, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(2);
		assertEquals(1, points.getTotalVictoryPoints());
		points.subtractPublicVictoryPoints(1);
		assertEquals(0, points.getTotalVictoryPoints());
	}
	
	@Test
	public void testPublicAndPrivate() {
		assertEquals(0, points.getTotalVictoryPoints());
		points.addPublicVictoryPoint();
		points.addPublicVictoryPoint();
		assertEquals(2, points.getTotalVictoryPoints());
		assertEquals(2, points.getPublicVictoryPoints());
		points.addPrivateVictoryPoint();
		assertEquals(3, points.getTotalVictoryPoints());
		assertEquals(2, points.getPublicVictoryPoints());
		points.addPublicVictoryPoint();
		assertEquals(4, points.getTotalVictoryPoints());
		assertEquals(3, points.getPublicVictoryPoints());
		points.addPublicVictoryPoint();
		points.addPublicVictoryPoint();
		assertEquals(6, points.getTotalVictoryPoints());
		assertEquals(5, points.getPublicVictoryPoints());
		points.addPrivateVictoryPoint();
		assertEquals(7, points.getTotalVictoryPoints());
		assertEquals(5, points.getPublicVictoryPoints());
		points.subtractPublicVictoryPoints(2);
		assertEquals(5, points.getTotalVictoryPoints());
		assertEquals(3, points.getPublicVictoryPoints());
	}

}
