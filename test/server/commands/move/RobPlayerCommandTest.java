package server.commands.move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.communication.input.move.RobPlayerInput;
import shared.locations.HexLocation;
import shared.model.GameModel;
import shared.model.board.PlayerID;

public class RobPlayerCommandTest {
	
	private GameModel model;
	@Before
	public void setUp() throws Exception {
		model = GameModel.getDefaultModel();	
	}

	@Test
	public void testExecute() {
		// test for change of location and change of resources
		HexLocation loc = new HexLocation(1,1);
		RobPlayerInput inputClass = new RobPlayerInput(0, loc, 3);
		Gson gson = new Gson();
		String input = gson.toJson(inputClass);
		RobPlayerCommand command = new RobPlayerCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 21);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 7);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		
		
		// test for not robbing, but new location for robber
		loc = new HexLocation(-1,-1);
		inputClass = new RobPlayerInput(3, loc, -1);
		input = gson.toJson(inputClass);
		command = new RobPlayerCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(model.getPlayer(new PlayerID(0)).getPlayerBank().getNumResourceCards(), 21);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 7);
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
		
		loc = new HexLocation(1,1);
		inputClass = new RobPlayerInput(0, loc, 3);
		input = gson.toJson(inputClass);
		command = new RobPlayerCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
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
		loc = new HexLocation(0,0);
		inputClass = new RobPlayerInput(3, loc, 1);
		input = gson.toJson(inputClass);
		command = new RobPlayerCommand();
		command.setGameModel(model);
		try {
			model = (GameModel)command.execute(input);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertEquals(model.getPlayer(new PlayerID(1)).getPlayerBank().getNumResourceCards(), 0);
		assertEquals(model.getPlayer(new PlayerID(3)).getPlayerBank().getNumResourceCards(), 6);
		loc = new HexLocation(1,1);
		assertTrue(model.getBoard().getRobber().getLocation().equals(loc));
		
		
		
		//test with same location as current robber
		inputClass = new RobPlayerInput(0, loc, 3);
		input = gson.toJson(inputClass);
		command = new RobPlayerCommand();
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
	}

}
