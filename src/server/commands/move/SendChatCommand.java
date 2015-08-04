package server.commands.move;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.communication.LogEntry;
import shared.communication.input.move.SendChatInput;
import shared.definitions.CatanColor;


public class SendChatCommand extends MoveCommand{

	
	
	/**
	 * @pre none
	 * @post Puts the chat dialog in the chat log.
	 * @return returns the game model after it has been updated.
	 */
	
	public Object execute(String input) {
		SendChatInput in;
		try {
			in = new ObjectMapper().readValue(input, SendChatInput.class);
			int pi = in.getPlayerIndex();
			CatanColor cc = model.getPlayers().get(pi).getColor();
			LogEntry le = new LogEntry(cc, in.getContent());
			model.getChats().add(le);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	


}
