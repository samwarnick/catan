package server.commands.games;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesListInput;
import shared.communication.input.move.BuyDevCardInput;
import client.data.*;

public class ListCommandTest {
	
	
	@Before
	public void setUp(){
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
		ListCommand listCommand = new ListCommand();
		List<GameInfo> output = (List<GameInfo>) listCommand.execute(input);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testgoodInput() {
		GamesCreateInput createInput = new GamesCreateInput("Bob", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		createCommand.execute(createInput);
		GamesListInput listInput = new GamesListInput();
		ListCommand listCommand = new ListCommand();
		List<GameInfo> output = (List<GameInfo>) listCommand.execute(listInput);
		assertEquals(output.get(0).getTitle(),"Bob");
		assertEquals(output.get(0).getId(),0);
		assertEquals(output.get(0).getPlayers().size(),1);

		
	}
}

