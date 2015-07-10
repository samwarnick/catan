package shared.model.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.NoCitiesLeftException;
import shared.model.player.NoRoadsLeftException;
import shared.model.player.NoSettlementsLeftException;
import shared.model.player.Player;

public class ActivePlayerFacadeTest {

	private Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player(CatanColor.BLUE, "Tester McTester", 0);
		player.setPlayerFacade(new ActivePlayerFacade(player));
	}

	@Test
	public void testIsActivePlayer() {
		assertTrue(player.getPlayerFacade().isActivePlayer());
	}
	
	@Test
	public void testCanFinishTurn() {
		assertTrue(player.getPlayerFacade().canFinishTurn());
	}
	
	@Test
	public void testCanBeRobbed() {
		assertFalse(player.getPlayerFacade().canBeRobbed());
	}

	@Test
	public void testCanDiscard() {
		// less than 8 cards
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 1));
		} catch (BankException e) {
			return;
		}
		assertFalse(player.getPlayerFacade().canDiscard());
		
		// 8 or more cards
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 1));
		} catch (BankException e) {
			return;
		}
		assertTrue(player.getPlayerFacade().canDiscard());
	}
	
	@Test
	public void testCanAcceptTrade() {
		// does not have cards
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 0));
		} catch (BankException e) {
			return;
		}
		assertFalse(player.getPlayerFacade().canAcceptTrade(new ResourceHand(0, 0, 0, 0, 1)));
		
		// has cards
		assertEquals(1, player.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity());
		assertTrue(player.getPlayerFacade().canAcceptTrade(new ResourceHand(1, 0, 0, 0, 0)));
	}
	
	@Test
	public void testCanMaritimeTrade() {
		// wrong trade ratio
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, 3, 0));
		} catch (BankException e) {
			return;
		}
		assertFalse(player.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, ResourceType.WOOD));
		
		// does not have resource
		
		assertFalse(player.getPlayerFacade().canMaritimeTrade(1, ResourceType.ORE, ResourceType.WOOD));
		
		// 4:1
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, 1, 0));
		} catch (BankException e) {
			return;
		}
		assertEquals(4, player.getTradeRatios().getTradeRatio(ResourceType.WHEAT).getRatio());
		assertEquals(4, player.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity());
		assertTrue(player.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, ResourceType.WOOD));
		
		// 3:1
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, -1, 0));
			player.getTradeRatios().getTradeRatio(ResourceType.WHEAT).setRatio(3);
		} catch (Exception e) {
			return;
		}
		assertEquals(3, player.getTradeRatios().getTradeRatio(ResourceType.WHEAT).getRatio());
		assertEquals(3, player.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity());
		assertTrue(player.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, ResourceType.WOOD));
		
		// 2:1
		
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, -1, 0));
			player.getTradeRatios().getTradeRatio(ResourceType.WHEAT).setRatio(2);
		} catch (Exception e) {
			return;
		}
		assertEquals(2, player.getTradeRatios().getTradeRatio(ResourceType.WHEAT).getRatio());
		assertEquals(2, player.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity());
		assertTrue(player.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, ResourceType.WOOD));
	}
	
	@Test
	public void testCanTrade() {
		// does not have the correct resources
		
		assertFalse(player.getPlayerFacade().canTrade(new ResourceHand(1, 0, 0, 0, 0)));
		
		// has resources
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 0));
		} catch (BankException e) {
			return;
		}
		
		assertEquals(1, player.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(1, player.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity());
		assertTrue(player.getPlayerFacade().canTrade(new ResourceHand(1, 1, 0, 0, 0)));
	}
	
	@Test
	public void testCanPlayCard() {
		// hasn't played a card
		assertTrue(player.getPlayerFacade().canPlayCard());
		
		// has played card
		player.setHasPlayedCard(true);
		assertFalse(player.getPlayerFacade().canPlayCard());
	}
	
	@Test
	public void testCanBuyDevelopmentCard() {
		// does not have the resources
		assertFalse(player.getPlayerFacade().canBuyDevelopmentCard());
		
		// has the resources
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 1, 1, 1));
		} catch (BankException e) {
			return;
		}
		assertTrue(player.getPlayerFacade().canBuyDevelopmentCard());
	}
	
	@Test
	public void testCanBuildRoad() {
		// does not have the resources
		assertFalse(player.getPlayerFacade().canBuildRoad());
		
		// has the resources
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 0, 0, 0));
		} catch (BankException e) {
			return;
		}
		assertTrue(player.getPlayerFacade().canBuildRoad());
		
		// none left
		try {
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
			player.getRoads().buildRoad();
		} catch (NoRoadsLeftException e) {
			return;
		}
		
		assertFalse(player.getPlayerFacade().canBuildRoad());
	}
	
	@Test
	public void testCanBuildSettlement() {
		// does not have the resources
		assertFalse(player.getPlayerFacade().canBuildSettlement());
		
		// has the resources
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(1, 1, 1, 1, 0));
		} catch (BankException e) {
			return;
		}
		assertTrue(player.getPlayerFacade().canBuildSettlement());
		
		// none left
		try {
			player.getSettlements().buildSettlement();
			player.getSettlements().buildSettlement();
			player.getSettlements().buildSettlement();
			player.getSettlements().buildSettlement();
			player.getSettlements().buildSettlement();
		} catch (NoSettlementsLeftException e) {
			return;
		}
		
		assertFalse(player.getPlayerFacade().canBuildSettlement());
	}
	
	@Test
	public void testCanBuildCity() {
		// does not have the resources
		assertFalse(player.getPlayerFacade().canBuildCity());
		
		// has the resources
		try {
			player.getPlayerBank().modifyRC(new ResourceHand(0, 0, 0, 2, 3));
		} catch (BankException e) {
			return;
		}
		assertTrue(player.getPlayerFacade().canBuildCity());
		
		try {
			player.getCities().buildCity();
			player.getCities().buildCity();
			player.getCities().buildCity();
			player.getCities().buildCity();
		} catch (NoCitiesLeftException e) {
			return;
		}
		
		assertFalse(player.getPlayerFacade().canBuildCity());
	}

}
