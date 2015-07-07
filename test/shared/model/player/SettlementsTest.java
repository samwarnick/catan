package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class SettlementsTest {

	@Test
	public void testConstructor() {
		Settlements settlements = new Settlements();
		int left = settlements.getSettlementsLeft();
		assertEquals(5, left);
	}
	
	@Test
	public void testChanging() {
		Settlements settlements = new Settlements();
		boolean noLeft = false;
		
		// build settlements
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(4, settlements.getSettlementsLeft());
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(3, settlements.getSettlementsLeft());
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(2, settlements.getSettlementsLeft());
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(1, settlements.getSettlementsLeft());
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertFalse(noLeft);
		assertEquals(0, settlements.getSettlementsLeft());
		try {
			settlements.buildSettlement();
		} catch (NoSettlementsLeftException e) {
			noLeft = true;
		}
		assertTrue(noLeft);
		assertEquals(0, settlements.getSettlementsLeft());
		
		// put back settlements
		boolean tooMany = false;
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertFalse(tooMany);
		assertEquals(1, settlements.getSettlementsLeft());
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertFalse(tooMany);
		assertEquals(2, settlements.getSettlementsLeft());
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertFalse(tooMany);
		assertEquals(3, settlements.getSettlementsLeft());
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertFalse(tooMany);
		assertEquals(4, settlements.getSettlementsLeft());
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertFalse(tooMany);
		assertEquals(5, settlements.getSettlementsLeft());
		try {
			settlements.subtractSettlement();
		} catch (TooManySettlementsException e) {
			tooMany = true;
		}
		assertTrue(tooMany);
		assertEquals(5, settlements.getSettlementsLeft());
	}
}
