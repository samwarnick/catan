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
}
