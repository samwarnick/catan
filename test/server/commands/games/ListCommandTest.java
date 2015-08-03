package server.commands.games;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.google.gson.Gson;

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
		List<GameInfo> output = (List<GameInfo>) listCommand.execute(new Gson().toJson(input));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testgoodInput() {
		GamesCreateInput createInput = new GamesCreateInput("Bob", true, true, true);
		CreateCommand createCommand = new CreateCommand();
		GameInfo gi = (GameInfo)createCommand.execute(new Gson().toJson(createInput));
		GamesListInput listInput = new GamesListInput();
		ListCommand listCommand = new ListCommand();
		List<GameInfo> output = (List<GameInfo>) listCommand.execute(new Gson().toJson(listInput));
		assertEquals(output.get(gi.getId()).getTitle(),"Bob");
		assertEquals(output.get(0).getPlayers().size(),4);

		
	}
}

