package shared.model.player;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import shared.locations.*;
import shared.model.board.PlayerID;
import shared.model.board.Settlement;

public class SettlementsTest {

	@Test
	public void testConstructor() {
		Settlements settlements = new Settlements();
		int left = settlements.getSettlementsLeft();
		assertEquals(5, left);
	}
	
	@Test
	public void testAddSettlement() {
		boolean noneLeftThrown = false;
		boolean alreadyThereThrown = false;
		Settlements settlements = new Settlements();
		Settlement temp1 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		Settlement temp2 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-1, 1), VertexDirection.NorthEast));
		Settlement temp3 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-2, 2), VertexDirection.NorthEast));
		Settlement temp4 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-3, 3), VertexDirection.NorthEast));
		Settlement temp5 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-4, 4), VertexDirection.NorthEast));
		try {
			settlements.buildSettlement(temp1);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		} catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		int left = settlements.getSettlementsLeft();
		List<Settlement> list = settlements.getSettlements();
		assertEquals(4, left); // settlements left
		assertEquals(1, list.size()); // settlements in list
		
		try {
			settlements.buildSettlement(temp2);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		}catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(3, left); // settlements left
		assertEquals(2, list.size()); // settlements in list
		
		try {
			settlements.buildSettlement(temp3);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		}catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(2, left); // settlements left
		assertEquals(3, list.size()); // settlements in list
		
		try {
			settlements.buildSettlement(temp4);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		}catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(1, left); // settlements left
		assertEquals(4, list.size()); // settlements in list
		
		// add same settlement twice–shoudln't allow
		try {
			settlements.buildSettlement(temp4);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		}catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertTrue(alreadyThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(1, left); // settlements left
		assertEquals(4, list.size()); // settlements in list
		
		alreadyThereThrown = false;
		
		try {
			settlements.buildSettlement(temp5);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		} catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(0, left); // settlements left
		assertEquals(5, list.size()); // settlements in list
		
		try {
			settlements.buildSettlement(temp5);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		}catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertTrue(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		assertEquals(0, left); // settlements left
		assertEquals(5, list.size()); // settlements in list
	}
	
	@Test
	public void testSubtractSettlement() {
		// add settlements
		boolean noneLeftThrown = false;
		boolean alreadyThereThrown = false;
		boolean notThereThrown = false;
		Settlements settlements = new Settlements();
		Settlement temp1 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		Settlement temp2 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-1, 1), VertexDirection.NorthEast));
		Settlement temp3 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-2, 2), VertexDirection.NorthEast));
		Settlement temp4 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-3, 3), VertexDirection.NorthEast));
		Settlement temp5 = new Settlement(new PlayerID(0), new VertexLocation(new HexLocation(-4, 4), VertexDirection.NorthEast));
		try {
			settlements.buildSettlement(temp1);
			settlements.buildSettlement(temp2);
			settlements.buildSettlement(temp3);
			settlements.buildSettlement(temp4);
			settlements.buildSettlement(temp5);
		} catch (NoSettlementsLeftException e) {
			noneLeftThrown = true;
		} catch (SettlementAlreadyThereException e) {
			alreadyThereThrown = true;
		}
		assertFalse(noneLeftThrown);
		assertFalse(alreadyThereThrown);
		int left = settlements.getSettlementsLeft();
		List<Settlement> list = settlements.getSettlements();
		assertEquals(0, left); // settlements left
		assertEquals(5, list.size()); // settlements in list
		
		// subtract settlements
		try {
			settlements.subtractSettlement(temp1);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertFalse(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(4, list.size());
		assertEquals(1, left);
		assertEquals(-1, list.indexOf(temp1));
		
		try {
			settlements.subtractSettlement(temp2);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertFalse(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(3, list.size());
		assertEquals(2, left);
		assertEquals(-1, list.indexOf(temp2));
		
		try {
			settlements.subtractSettlement(temp3);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertFalse(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(2, list.size());
		assertEquals(3, left);
		assertEquals(-1, list.indexOf(temp3));
		
		try {
			settlements.subtractSettlement(temp4);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertFalse(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(1, list.size());
		assertEquals(4, left);
		assertEquals(-1, list.indexOf(temp4));
		
		// test remove same settlement twice
		try {
			settlements.subtractSettlement(temp4);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertTrue(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(1, list.size());
		assertEquals(4, left);
		assertEquals(-1, list.indexOf(temp4));
		
		notThereThrown = false;
		
		try {
			settlements.subtractSettlement(temp5);
		} catch (NoSettlementFoundException e) {
			notThereThrown = true;
		}
		assertFalse(notThereThrown);
		left = settlements.getSettlementsLeft();
		list = settlements.getSettlements();
		assertEquals(0, list.size());
		assertEquals(5, left);
		assertEquals(-1, list.indexOf(temp5));
	}
	

}
