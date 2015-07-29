package server.commands.move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.communication.input.move.PlayMonumentInput;
import shared.definitions.DevCardType;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;

public class PlayMonumentCommandTest {

	private GameModel model;
	@Before
	public void setUp() throws Exception {
		model = GameModel.getDefaultModel();	
	}

	@Test
	public void test() {
		//test for no monument card
		PlayMonumentInput inputClass = new PlayMonumentInput(0);
		Gson gson = new Gson();
		String input = gson.toJson(inputClass);
		PlayMonumentCommand command = new PlayMonumentCommand();
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		
		
		//test for not enough victory points to win
		try {
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.MONUMENT);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getVictoryPoints().getTotalVictoryPoints(), 8);
		
		
		
		//test for enough to win
		model.getPlayer(new PlayerID(0)).getVictoryPoints().addPublicVictoryPoint();
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getVictoryPoints().getTotalVictoryPoints(), 10);
		
		
		
		// test for enough to win other player
		try {
			model.getPlayer(new PlayerID(1)).setPlayerFacade(new ActivePlayerFacade(model.getPlayer(new PlayerID(1))));
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getVictoryPoints().addPublicVictoryPoint();
			model.getPlayer(new PlayerID(1)).getPlayerBank().addDC(DevCardType.MONUMENT);
			model.getPlayer(new PlayerID(1)).getPlayerBank().addDC(DevCardType.MONUMENT);
		} catch (BankException e) {
			e.printStackTrace();
		}
		
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getVictoryPoints().getTotalVictoryPoints(), 9);
		
		command.setModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getVictoryPoints().getTotalVictoryPoints(), 10);
	}

}
