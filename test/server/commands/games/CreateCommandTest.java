package server.commands.games;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.gson.Gson;

import shared.communication.input.GamesCreateInput;
import shared.communication.input.move.BuyDevCardInput;
import client.data.*;

public class CreateCommandTest {
	
	
	@Before
	public void setUp(){
	}

	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
		CreateCommand createCommand = new CreateCommand();
		GameInfo output = (GameInfo) createCommand.execute(new Gson().toJson(input));
	}
	
	@Test
	public void testgoodInput() {
		GamesCreateInput createInput = new GamesCreateInput("Bob", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		GameInfo output = (GameInfo) createCommand.execute(new Gson().toJson(createInput));
		
		assertEquals(output.getTitle(),"Bob");
		assertEquals(output.getId(),0);
		assertEquals(output.getPlayers().size(),1);

		
	}
}
