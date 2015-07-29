package server.commands.move;

import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

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
		Gson parser = new Gson();
		SendChatInput in = parser.fromJson(input, SendChatInput.class);
		int pi = in.getPlayerIndex();
		CatanColor cc = model.getPlayers().get(pi).getColor();
		LogEntry le = new LogEntry(cc, in.getContent());
		model.getChats().add(le);
		return model;
	}
	


}
