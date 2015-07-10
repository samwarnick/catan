package shared.model.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.*;
import shared.model.bank.Bank;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;

public class BankTest {
	
	@Test
	public void testConstructor() {
		Bank bank = new Bank();
		assertEquals(19, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		assertEquals(14, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(5, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
		assertTrue(bank.hasLargestArmyCard());
		assertTrue(bank.hasLongestRoadCard());
	}
	
	@Test
	public void testInvalidAdd() {
		Bank bank = new Bank();
		boolean error = false;
		
		// Resource Cards
		ResourceHand recHand = new ResourceHand(0, 0, 0, 0, 1);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertTrue(error);
		assertEquals(19, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(19, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		// Development cards
		error = false;
		DevelopmentHand devHand = new DevelopmentHand(1, 2, 4, 6, 7);
		try {
			bank.modifyDC(devHand);
		} catch (BankException e) {
			error = true;
		}
		assertTrue(error);
		assertEquals(14, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(5, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
	}
	
	@Test
	public void testValidRemove() {
		Bank bank = new Bank();
		boolean error = false;
		
		// Resource Cards
		ResourceHand recHand = new ResourceHand(0, 0, 0, 0, -1);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(18, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		recHand = new ResourceHand(-1, -1, -1, -1, 0);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(18, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		recHand = new ResourceHand(0, 0, 0, -2, -3);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(18, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(18, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(16, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(15, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		recHand = new ResourceHand(-18, -18, -18, -16, -15);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(0, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		// Development cards
		DevelopmentHand devHand = new DevelopmentHand(-1, 0, 0, 0, 0);
		try {
			bank.modifyDC(devHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(13, bank.getDevStack(DevCardType.SOLDIER).getQuantity());
		assertEquals(5, bank.getDevStack(DevCardType.MONUMENT).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.MONOPOLY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity());
		assertEquals(2, bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity());
		
	}
	
	@Test
	public void testInvalidRemove() {
		Bank bank = new Bank();
		boolean error = false;
		
		// Resource Cards
		ResourceHand recHand = new ResourceHand(-19, -19, -19, -19, -19);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(0, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		recHand = new ResourceHand(-19, -19, -19, 0, -19);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertTrue(error);
		assertEquals(0, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(0, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		// Development cards
		DevelopmentHand devHand = new DevelopmentHand(-14, -5, -2, -2, -2);
		try {
			bank.modifyDC(devHand);
		} catch (BankException e) {
			error = true;
		}
		assertTrue(error);
		assertEquals(bank.getDevStack(DevCardType.SOLDIER).getQuantity(), 0);
		assertEquals(bank.getDevStack(DevCardType.MONUMENT).getQuantity(), 0);
		assertEquals(bank.getDevStack(DevCardType.MONOPOLY).getQuantity(), 0);
		assertEquals(bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity(), 0);
		assertEquals(bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity(), 0);
	}
	
	@Test
	public void testValidAdd() {
		Bank bank = new Bank();
		boolean error = false;
		ResourceHand recHand = new ResourceHand(-19, -19, -19, -19, -19);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(bank.getResourceStack(ResourceType.BRICK).getQuantity(), 0);
		assertEquals(bank.getResourceStack(ResourceType.WOOD).getQuantity(), 0);
		assertEquals(bank.getResourceStack(ResourceType.SHEEP).getQuantity(), 0);
		assertEquals(bank.getResourceStack(ResourceType.WHEAT).getQuantity(), 0);
		assertEquals(bank.getResourceStack(ResourceType.ORE).getQuantity(), 0);
		
		recHand = new ResourceHand(19, 3, 2, 1, 10);
		try {
			bank.modifyRC(recHand);
		} catch (BankException e) {
			error = true;
		}
		assertFalse(error);
		assertEquals(19, bank.getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(3, bank.getResourceStack(ResourceType.WOOD).getQuantity());
		assertEquals(2, bank.getResourceStack(ResourceType.SHEEP).getQuantity());
		assertEquals(1, bank.getResourceStack(ResourceType.WHEAT).getQuantity());
		assertEquals(10, bank.getResourceStack(ResourceType.ORE).getQuantity());
		
		// Can't add development cards. Tested in invlaid add
	}
	
	@Test
	public void testValidLongestAndLargest() {
		Bank bank = new Bank();
		assertTrue(bank.hasLargestArmyCard());
		assertTrue(bank.hasLongestRoadCard());
		bank.setLargestArmyCard(false);
		assertFalse(bank.hasLargestArmyCard());
		assertTrue(bank.hasLongestRoadCard());
		bank.setLongestRoadCard(false);
		assertFalse(bank.hasLargestArmyCard());
		assertFalse(bank.hasLongestRoadCard());
	}

}
