package server.commands.move;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.communication.input.move.BuyDevCardInput;
import shared.communication.input.move.SendChatInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendChatCommandTest {

private GameModel model;
	
	@Before
	public void setUp(){
		model = GameModel.getDefaultModel();
	}

	@Test
	public void testBadInput() {
		BuyDevCardInput input = new BuyDevCardInput(0);
	}
	
	@Test
	public void testgoodInput() {
		SendChatInput input = new SendChatInput(0, "YAY");
		SendChatCommand command = new SendChatCommand();
		command.setGameModel(model);
		try {
			model = (GameModel) command.execute(new ObjectMapper().writeValueAsString(input));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assertEquals(model.getChats().get(0).getMessage(),"YAY");
		assertEquals(model.getChats().get(0).getColor(),CatanColor.BROWN);

		
		
		
	}
	
}
