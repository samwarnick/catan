package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class LargestArmyTest {

	@Test
	public void test() {
		LargestArmy army = new LargestArmy();
		assertEquals(0, army.getNumSoldiers());
		assertFalse(army.getHasLargestArmy());
		army.addSoldier();
		army.addSoldier();
		army.addSoldier();
		assertEquals(3, army.getNumSoldiers());
		army.setHasLargestArmy(true);
		assertTrue(army.getHasLargestArmy());
		army.setHasLargestArmy(false);
		assertFalse(army.getHasLargestArmy());
		army.addSoldier();
		assertEquals(4, army.getNumSoldiers());
		assertFalse(army.getHasLargestArmy());
		army.setHasLargestArmy(true);
		assertTrue(army.getHasLargestArmy());
	}
}
