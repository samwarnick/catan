package server.commands.move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.communication.input.move.PlayYearOfPlentyInput;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;

public class PlayYearOfPlentyCommandTest {

	private GameModel model;
	@Before
	public void setUp() throws Exception {
		model = GameModel.getDefaultModel();	
	}
	
	@Test
	public void test() {
		//test no dev card
		PlayYearOfPlentyInput inputClass = new PlayYearOfPlentyInput(0, ResourceType.BRICK, ResourceType.BRICK);
		Gson gson = new Gson();
		String input = gson.toJson(inputClass);
		PlayYearOfPlentyCommand command = new PlayYearOfPlentyCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		
		
		//test normal two of same resource
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.YEAR_OF_PLENTY);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity(), 8);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 4);
		
		
		
		//test two different resources
		inputClass = new PlayYearOfPlentyInput(0, ResourceType.WOOD, ResourceType.SHEEP);
		input = gson.toJson(inputClass);
		command = new PlayYearOfPlentyCommand();
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.YEAR_OF_PLENTY);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity(), 8);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity(), 5);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity(), 5);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 4);
		
		
		
		//test inactive player
		inputClass = new PlayYearOfPlentyInput(1, ResourceType.WOOD, ResourceType.SHEEP);
		input = gson.toJson(inputClass);
		command = new PlayYearOfPlentyCommand();
		try {
			model.getPlayer(new PlayerID(1)).getPlayerBank().addDC(DevCardType.YEAR_OF_PLENTY);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getBrick().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getWood().getQuantity(), 1);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getSheep().getQuantity(), 1);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getWheat().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getOre().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getNumDevCards(), 0);
	}

}
