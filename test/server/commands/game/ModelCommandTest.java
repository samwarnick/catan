package server.commands.games;

import static org.junit.Assert.*;

import org.junit.*;

import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.move.BuyDevCardInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;
import client.data.*;

public class ModelCommandTest {
	
	
	@Before
	public void setUp(){
	}

	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
		ModelCommand modelCommand = new ModelCommand();
		GameInfo output = (GameInfo) modelCommand.execute(input);
	}
	
	@Test
	public void testModelGetGame() {
		GameModelVersionInput modelInput = new GameModelVersionInput(0);
		ModelCommand modelCommand = new ModelCommand();
		GameModel output = (GameModel) modelCommand.execute(modelInput);
		
		//insert good tests here.

		
	}
}