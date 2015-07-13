package shared.model.player;

import org.junit.Test;

import shared.definitions.ResourceType;
import shared.model.ratios.TradeRatio;
import static org.junit.Assert.*;


public class TradeRatioTest {
	
	@Test
	public void testSetRatio() {
		TradeRatio trade = new TradeRatio(ResourceType.BRICK);
		boolean error = false;
		try {
			trade.setRatio(2);
			assertEquals(trade.getRatio(), 2);
		} catch (Exception e) {
			error = true;
		}
		
		assertFalse(error);
		
		try {
			trade.setRatio(1);
		} catch (Exception e) {
			error = true;
		}
		
		assertTrue(error);
		
		error = false;
		
		try {
			trade.setRatio(5);
		} catch (Exception e) {
			error = true;
		}
		
		assertTrue(error);
	}

}
