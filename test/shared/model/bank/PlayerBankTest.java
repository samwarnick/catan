package shared.model.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

public class PlayerBankTest {

	@Test
	public void testConstructor() {
		PlayerBank bank = new PlayerBank();
		assertEquals(0, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		assertEquals(0, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
		assertFalse(bank.hasLargestArmyCard());
		assertFalse(bank.hasLongestRoadCard());
	}
	
	@Test
	public void testNewDevCardHand() {
		PlayerBank bank = new PlayerBank();
		boolean error = false;
		DevelopmentHand hand = new DevelopmentHand(1, 0, 0, 0, 0);
		try {
			bank.addNewDC(hand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(0, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
		assertEquals(1, bank.getNewDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
		try {
			bank.transfer();
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(1, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(0, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
		assertEquals(0, bank.getNewDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(0, bank.getNewDevStack(DevCardType.ROAD_BUILD).getQuantity());
	}

}
