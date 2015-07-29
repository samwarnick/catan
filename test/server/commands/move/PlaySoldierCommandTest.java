package server.commands.move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.communication.input.move.PlaySoldierInput;
import shared.definitions.DevCardType;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.InactivePlayerFacade;

public class PlaySoldierCommandTest {

	private GameModel model;
	@Before
	public void setUp() throws Exception {
		model = GameModel.getDefaultModel();	
	}

	@Test
	public void test() {
		// test for no soldier card
		HexLocation loc = new HexLocation(1,1);
		PlaySoldierInput inputClass = new PlaySoldierInput(0, loc, 3);
		Gson gson = new Gson();
		String input = gson.toJson(inputClass);
		PlaySoldierCommand command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//test change of location and change of resources
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 1);
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 21);
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 0);
		assertEquals(model.getPlayer(new PlayerID(0)).getArmySize(), 1);
		assertTrue(model.getBank().hasLargestArmyCard());
		assertFalse(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 7);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		
		
		
		// test for not robbing, but new location for robber
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(-1,-1);
		inputClass = new PlaySoldierInput(0, loc, -1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 21);
		assertEquals(model.getPlayer(new PlayerID(0)).getArmySize(), 2);
		assertTrue(model.getBank().hasLargestArmyCard());
		assertFalse(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		
		
		
		// test for resource robbed is resource added to player
		int brick = model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity();
		int wood = model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity();
		int sheep = model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity();
		int wheat = model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity();
		int ore = model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity();
		
		int brickRobbed = model.getPlayer(new PlayerID(3)).getPlayerBank().getBrick().getQuantity();
		int woodRobbed = model.getPlayer(new PlayerID(3)).getPlayerBank().getWood().getQuantity();
		int sheepRobbed = model.getPlayer(new PlayerID(3)).getPlayerBank().getSheep().getQuantity();
		int wheatRobbed = model.getPlayer(new PlayerID(3)).getPlayerBank().getWheat().getQuantity();
		int oreRobbed = model.getPlayer(new PlayerID(3)).getPlayerBank().getOre().getQuantity();
		
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(1,1);
		inputClass = new PlaySoldierInput(0, loc, 3);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		assertEquals(model.getPlayer(new PlayerID(0)).getArmySize(), 3);
		assertFalse(model.getBank().hasLargestArmyCard());
		assertTrue(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 22);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 6);
		boolean change = false;
		if (model.getPlayer(new PlayerID(3)).getPlayerBank().getBrick().getQuantity() < brickRobbed) {
			change = true;
			assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getBrick().getQuantity(), brick+1);
		}
		if (model.getPlayer(new PlayerID(3)).getPlayerBank().getWood().getQuantity() < woodRobbed) {
			change = true;
			assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWood().getQuantity(), wood+1);
		}
		if (model.getPlayer(new PlayerID(3)).getPlayerBank().getSheep().getQuantity() < sheepRobbed) {
			change = true;
			assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getSheep().getQuantity(), sheep+1);
		}
		if (model.getPlayer(new PlayerID(3)).getPlayerBank().getWheat().getQuantity() < wheatRobbed) {
			change = true;
			assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getWheat().getQuantity(), wheat+1);
		}
		if (model.getPlayer(new PlayerID(3)).getPlayerBank().getOre().getQuantity() < oreRobbed) {
			change = true;
			assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getOre().getQuantity(), ore+1);
		}
		assertTrue(change);
		
		
		
		//test rob from someone that has no resources
		try {
			model.getPlayer(new PlayerID(0)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(0,0);
		inputClass = new PlaySoldierInput(0, loc, 1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 22);
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getNumResourceCards(), 0);
		loc = new HexLocation(1,1);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 1);
		
		
		
		//test with same location as current robber
		inputClass = new PlaySoldierInput(0, loc, 3);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 22);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 6);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 1);
		
		
		//test inactive player play card
		loc = new HexLocation(-1,-1);
		inputClass = new PlaySoldierInput(3, loc, 0);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 22);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 6);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumDevCards(), 1);
		
		
		
		//test switch of largestArmy card
		try {
			model.getPlayer(new PlayerID(3)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		model.getPlayer(new PlayerID(3)).setPlayerFacade(new ActivePlayerFacade(model.getPlayer(new PlayerID(3))));
		model.getPlayer(new PlayerID(0)).setPlayerFacade(new InactivePlayerFacade(model.getPlayer(new PlayerID(0))));
		loc = new HexLocation(-1,-1);
		inputClass = new PlaySoldierInput(3, loc, 1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(3)).getArmySize(), 1);
		assertFalse(model.getBank().hasLargestArmyCard());
		assertFalse(model.getPlayer(new PlayerID(3)).hasLargestArmy());
		assertTrue(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		
		try {
			model.getPlayer(new PlayerID(3)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(0,0);
		inputClass = new PlaySoldierInput(3, loc, 1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(3)).getArmySize(), 2);
		assertFalse(model.getBank().hasLargestArmyCard());
		assertFalse(model.getPlayer(new PlayerID(3)).hasLargestArmy());
		assertTrue(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		
		try {
			model.getPlayer(new PlayerID(3)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(-1,-1);
		inputClass = new PlaySoldierInput(3, loc, 1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(3)).getArmySize(), 3);
		assertFalse(model.getBank().hasLargestArmyCard());
		assertFalse(model.getPlayer(new PlayerID(3)).hasLargestArmy());
		assertTrue(model.getPlayer(new PlayerID(0)).hasLargestArmy());
		
		try {
			model.getPlayer(new PlayerID(3)).getPlayerBank().addDC(DevCardType.SOLDIER);
		} catch (BankException e1) {
			e1.printStackTrace();
		}
		loc = new HexLocation(0,0);
		inputClass = new PlaySoldierInput(3, loc, 1);
		input = gson.toJson(inputClass);
		command = new PlaySoldierCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(3)).getArmySize(), 4);
		assertFalse(model.getBank().hasLargestArmyCard());
		assertTrue(model.getPlayer(new PlayerID(3)).hasLargestArmy());
		assertFalse(model.getPlayer(new PlayerID(0)).hasLargestArmy());
	}
}
