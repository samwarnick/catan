package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.FinishTurnInput;
import shared.communication.input.move.OfferTradeInput;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.DevelopmentHand;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.InactivePlayerFacade;
import shared.model.player.Player;
import client.domestic.Trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class FinishTurnTest {
	
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
		FinishTurnInput input = new FinishTurnInput(0);
		Player testPlayer = model.getPlayer(new PlayerID(0));
		try {
			//(int soldier, int monument, int monopoly, int yearOfPlenty, int roadBuild)
			testPlayer.getPlayerBank().setDC(new DevelopmentHand(0, 0, 0, 0, 0));
			testPlayer.getPlayerBank().addNewDC(new DevelopmentHand(1, 1, 1, 1, 1));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FinishTurnCommand command = new FinishTurnCommand();
			command.setGameModel(model);
			try {
				model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		
		assertEquals(testPlayer.getPlayerBank().getMonopoly().getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getMonument().getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getRoadBuild().getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getSoldier().getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getYearOfPlenty().getQuantity(),1);
		assertEquals(testPlayer.getPlayerBank().getNewMonopoly().getQuantity(),0);
		assertEquals(testPlayer.getPlayerBank().getNewMonument().getQuantity(),0);
		assertEquals(testPlayer.getPlayerBank().getNewRoadBuild().getQuantity(),0);
		assertEquals(testPlayer.getPlayerBank().getNewSoldier().getQuantity(),0);
		assertEquals(testPlayer.getPlayerBank().getNewYearOfPlenty().getQuantity(),0);

		assertEquals(testPlayer.getPlayerFacade().canBeRobbed(), true);
		
		
	}

}
