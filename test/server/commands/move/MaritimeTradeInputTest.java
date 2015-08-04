package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.MaritimeTradeInput;
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

public class MaritimeTradeInputTest {
	
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
		//MaritimeTradeInput(int playerIndex, int ratio, ResourceType inputResource, ResourceType outpurResource)
		MaritimeTradeInput input = new MaritimeTradeInput(0, 3, ResourceType.BRICK, ResourceType.ORE);
		Player testPlayer = model.getPlayer(new PlayerID(0));
		try {
			testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(5);
			
			model.getBank().setBrick(new ResourceCard(1, ResourceType.BRICK));
			model.getBank().setSheep(new ResourceCard(1, ResourceType.SHEEP));
			model.getBank().setWheat(new ResourceCard(1, ResourceType.WHEAT));
			model.getBank().setWood(new ResourceCard(1, ResourceType.WOOD));
			model.getBank().setOre(new ResourceCard(1, ResourceType.ORE));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaritimeTradeCommand command = new MaritimeTradeCommand();
			command.setGameModel(model);
			try {
				model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),2);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),6);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).getQuantity(),5);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity(),5);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity(),5);
			
			assertEquals(model.getBank().getBrick().getQuantity(),4);
			assertEquals(model.getBank().getOre().getQuantity(),0);
			assertEquals(model.getBank().getSheep().getQuantity(),1);
			assertEquals(model.getBank().getWheat().getQuantity(),1);
			assertEquals(model.getBank().getWood().getQuantity(),1);
		
		
	}
	
	@Test
	public void testgoodInputTwo() {
		//MaritimeTradeInput(int playerIndex, int ratio, ResourceType inputResource, ResourceType outpurResource)
		MaritimeTradeInput input = new MaritimeTradeInput(0, 4, ResourceType.BRICK, ResourceType.ORE);
		Player testPlayer = model.getPlayer(new PlayerID(0));
		try {
			testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).setQuantity(5);
			testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(5);
			
			model.getBank().setBrick(new ResourceCard(1, ResourceType.BRICK));
			model.getBank().setSheep(new ResourceCard(1, ResourceType.SHEEP));
			model.getBank().setWheat(new ResourceCard(1, ResourceType.WHEAT));
			model.getBank().setWood(new ResourceCard(1, ResourceType.WOOD));
			model.getBank().setOre(new ResourceCard(1, ResourceType.ORE));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaritimeTradeCommand command = new MaritimeTradeCommand();
			command.setGameModel(model);
			try {
				model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).getQuantity(),1);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.ORE).getQuantity(),6);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.SHEEP).getQuantity(),5);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WHEAT).getQuantity(),5);
			assertEquals(testPlayer.getPlayerBank().getResourceStack(ResourceType.WOOD).getQuantity(),5);
			
			assertEquals(model.getBank().getBrick().getQuantity(),5);
			assertEquals(model.getBank().getOre().getQuantity(),0);
			assertEquals(model.getBank().getSheep().getQuantity(),1);
			assertEquals(model.getBank().getWheat().getQuantity(),1);
			assertEquals(model.getBank().getWood().getQuantity(),1);
		
		
	}

}
