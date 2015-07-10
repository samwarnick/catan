package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;

public class InactivePlayerFacadeTest {

	private Player inactivePlayer;
	
	@Before
	public void setUp() throws Exception {
		inactivePlayer = new Player(CatanColor.BLUE, "Tester McTester", 0);
		inactivePlayer.setPlayerFacade(new InactivePlayerFacade(inactivePlayer));
	}

	@Test
	public void testIsActivePlayer() {
		assertFalse(inactivePlayer.getPlayerFacade().isActivePlayer());
	}
	
	@Test
	public void testCanFinishTurn() {
		assertFalse(inactivePlayer.getPlayerFacade().canFinishTurn());
	}
	
	@Test
	public void testCanBeRobbed() {
		// no resource cards
		assertFalse(inactivePlayer.getPlayerFacade().canBeRobbed());
		
		// has resource cards
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(1, 1, 0, 0, 0));
		} catch (BankException e) {
			return;
		}
		
		assertTrue(inactivePlayer.getPlayerFacade().canBeRobbed());
	}

	@Test
	public void testCanDiscard() {
		// less than or equal to 7
		assertFalse(inactivePlayer.getPlayerFacade().canDiscard());
		
		// more than 7
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(6, 1, 0, 0, 0));
		} catch (BankException e) {
			return;
		}
		
		assertTrue(inactivePlayer.getPlayerFacade().canBeRobbed());
	}
	
	@Test
	public void testCanAcceptTrade() {
		
		// does not have resource
		assertFalse(inactivePlayer.getPlayerFacade().canAcceptTrade(new ResourceHand(0, 0, 0, 0, 1)));
		
		// has resource
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(1, 1, 0, 0, 0));
		} catch (BankException e) {
			return;
		}
		assertTrue(inactivePlayer.getPlayerFacade().canAcceptTrade(new ResourceHand(1, 0, 0, 0, 0)));
	}
	
	@Test
	public void testCanMaritimeTrade() {
		assertFalse(inactivePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.WOOD, ResourceType.SHEEP));
	}
	
	@Test
	public void testCanTrade() {
		assertFalse(inactivePlayer.getPlayerFacade().canTrade(new ResourceHand(1, 0, 0, 0, 0)));
	}
	
	@Test
	public void testCanPlayCard() {
		assertFalse(inactivePlayer.getPlayerFacade().canPlayCard());
	}
	
	@Test
	public void testCanBuyDevelopmentCard() {
		// without resources
		assertFalse(inactivePlayer.getPlayerFacade().canBuyDevelopmentCard());
		
		// has resource cards
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(0, 0, 1, 1, 1));
		} catch (BankException e) {
			return;
		}
		
		assertFalse(inactivePlayer.getPlayerFacade().canBuyDevelopmentCard());
	}
	
	@Test
	public void testCanBuildRoad() {
		// without resources
		assertFalse(inactivePlayer.getPlayerFacade().canBuyDevelopmentCard());
		
		// has resources
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(1, 1, 0, 0, 0));
		} catch (BankException e) {
			return;
		}
		
		assertFalse(inactivePlayer.getPlayerFacade().canBuyDevelopmentCard());
	}
	
	@Test
	public void testCanBuildSettlement() {
		// without resources
		assertFalse(inactivePlayer.getPlayerFacade().canBuildSettlement());
		
		// has resource cards
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 0));
		} catch (BankException e) {
			return;
		}
		
		assertFalse(inactivePlayer.getPlayerFacade().canBuildSettlement());
	}
	
	@Test
	public void testCanBuildCity() {
		// without resources
		assertFalse(inactivePlayer.getPlayerFacade().canBuildCity());
		
		// has resource cards
		try {
			inactivePlayer.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, 2, 3));
		} catch (BankException e) {
			return;
		}
		
		assertFalse(inactivePlayer.getPlayerFacade().canBuildCity());
	}
	

}
