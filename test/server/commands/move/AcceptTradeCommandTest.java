package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.AcceptTradeInput;
import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.OfferTradeInput;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.domestic.Trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class AcceptTradeCommandTest {
	
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
		//OfferTradeInput(int playerIndex, ResourceHand offer, int receiverIndex)
		//ResourceHand(int brick, int wood, int sheep, int wheat, int ore)
		
		OfferTradeInput input = new OfferTradeInput(0, new ResourceHand(1, 0, 0, 0,-1),1);
		model.setTrade(new Trade(input.getOffer().getBrick(), input.getOffer().getWood(), input.getOffer().getSheep(), input.getOffer().getWheat(), 
					input.getOffer().getOre(), input.getPlayerIndex(), input.getReceiver()));
		
		AcceptTradeInput acceptTrue = new AcceptTradeInput(1, true);
		AcceptTradeInput acceptFalse = new AcceptTradeInput(1, false);
		
		Player testPlayer = model.getPlayer(new PlayerID(0));
		Player reciver = model.getPlayer(new PlayerID(1));
		try {
			testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(5);
			reciver.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(5);
			reciver.getPlayerBank().getResourceStack(ResourceType.SHEEP).setQuantity(5);
			reciver.getPlayerBank().getResourceStack(ResourceType.WHEAT).setQuantity(5);
			reciver.getPlayerBank().getResourceStack(ResourceType.WOOD).setQuantity(5);
			reciver.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(5);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AcceptTradeCommand command = new AcceptTradeCommand();
			command.setGameModel(model);
			try {
				model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(acceptTrue));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),4);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),6);
			assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),6);
			assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),4);

		command = new AcceptTradeCommand();
		command.setGameModel(model);
		try {
			model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(acceptFalse));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),4);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),6);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).getQuantity(),5);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity(),5);
		assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity(),5);
		assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),4);
		assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),6);
		assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity(),5);
		assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity(),5);
		assertEquals(reciver.getPlayerBank().getResourceStack(ResourceType.SHEEP).getQuantity(),5);
		
		
	}

}
