package server.commands.move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.communication.input.move.PlayMonopolyInput;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;

public class PlayMonopolyCommandTest {

	private GameModel model;
	@Before
	public void setUp() throws Exception {
		model = GameModel.getDefaultModel();	
	}

	@Test
	public void test() {
		//test no monopoly card
		PlayMonopolyInput inputClass = new PlayMonopolyInput(0, "Brick");
		Gson gson = new Gson();
		String input = gson.toJson(inputClass);
		PlayMonumentCommand command = new PlayMonumentCommand();
		command.setModel(model);
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		
		
		// test normal
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.MONOPOLY);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity(), 10);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 0);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getBrick().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(2)).getPlayerBank().getBrick().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getBrick().getQuantity(), 0);
		
		
		
		//test for other players dont have any of resource
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.MONOPOLY);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity(), 10);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity(), 4);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 0);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getBrick().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(2)).getPlayerBank().getBrick().getQuantity(), 0);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getBrick().getQuantity(), 0);
	}

}
