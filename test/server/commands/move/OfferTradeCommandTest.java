package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.OfferTradeInput;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.controller.ModelController;
import client.domestic.Trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class OfferTradeCommandTest {
		
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
			Player testPlayer = model.getPlayer(new PlayerID(0));
			Player reciver = model.getPlayer(new PlayerID(1));
			try {
				testPlayer.getPlayerBank().getResourceStack(ResourceType.BRICK).setQuantity(5);
				reciver.getPlayerBank().getResourceStack(ResourceType.ORE).setQuantity(5);
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				OfferTradeCommand command = new OfferTradeCommand();
				command.setGameModel(model);
				try {
					model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Trade trade = new Trade(input.getOffer().getBrick(), input.getOffer().getWood(), input.getOffer().getSheep(), input.getOffer().getWheat(), 
						input.getOffer().getOre(), input.getPlayerIndex(), input.getReceiver());
				
			
			assertEquals(model.getTrade().getBrickNum(),trade.getBrickNum());
			assertEquals(model.getTrade().getOreNum(),trade.getOreNum());
			assertEquals(model.getTrade().getReceiver(),trade.getReceiver());
			assertEquals(model.getTrade().getSender(),trade.getSender());
			assertEquals(model.getTrade().getSheepNum(),trade.getSheepNum());
			assertEquals(model.getTrade().getWheatNum(),trade.getWheatNum());
			assertEquals(model.getTrade().getWoodNum(),trade.getWoodNum());
			
			
		}
	}
