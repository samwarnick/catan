package shared.model.player;

import org.junit.Test;

import shared.definitions.ResourceType;
import shared.model.ratios.TradeRatio;
import static org.junit.Assert.*;


public class TradeRatioTest {
	
	@Test
	public void testSetRatio() {
		TradeRatio trade = new TradeRatio(ResourceType.BRICK);
		try {
			trade.setRatio(2);
			assertEquals(trade.getRatio(), 2);
			trade.setRatio(1);
			assertTrue(false);
		} catch (Exception e) {
			
		}
		try {
			trade.setRatio(5);
			assertTrue(false);
		} catch (Exception e) {

		}
		
	}

}
