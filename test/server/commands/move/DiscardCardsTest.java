package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.DiscardCardsInput;
import shared.communication.input.move.OfferTradeInput;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceCard;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.domestic.Trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class DiscardCardsTest {
	
	private GameModel model;
	
	@Before
	public void setUp(){
		model = GameModel.getDefaultModel();
	}

	@Test
	public void testBadInput() {
		//?
		BuyDevCardInput input = new BuyDevCardInput(0);
	}
	
	@Test
	public void testgoodInput() {
		//DiscardCardsInput(int playerIndex, ResourceHand discardedCards)
		DiscardCardsInput input = new DiscardCardsInput(0, new ResourceHand(-1, -1, -1, -1, -1));
		Player testPlayer = model.getPlayer(new PlayerID(0));
		try {
			testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(2);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).setQuantity(2);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).setQuantity(2);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).setQuantity(2);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(2);
			model.getBank().setBrick(new ResourceCard(1, ResourceType.BRICK));
			model.getBank().setSheep(new ResourceCard(1, ResourceType.SHEEP));
			model.getBank().setWheat(new ResourceCard(1, ResourceType.WHEAT));
			model.getBank().setWood(new ResourceCard(1, ResourceType.WOOD));
			model.getBank().setOre(new ResourceCard(1, ResourceType.ORE));
			System.out.println(model.getBank().getBrick().getQuantity());
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DiscardCardsCommand command = new DiscardCardsCommand();
			command.setGameModel(model);
			try {
				model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("player");
			System.out.println(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity());
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),1);

		System.out.println("bank");
		System.out.println(model.getBank().getBrick().getQuantity());
		assertEquals(model.getBank().getBrick().getQuantity(),2);
		assertEquals(model.getBank().getOre().getQuantity(),2);
		assertEquals(model.getBank().getSheep().getQuantity(),2);
		assertEquals(model.getBank().getWheat().getQuantity(),2);
		assertEquals(model.getBank().getWood().getQuantity(),2);
		
		
		
		
	}

}
