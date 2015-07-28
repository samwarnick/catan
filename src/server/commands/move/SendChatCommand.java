package server.commands.move;

import java.util.ArrayList;
import java.util.Random;

import client.communication.LogEntry;
import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.SendChatInput;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.model.GameModel;

public class SendChatCommand implements ICommand{

	
	/**
	 * @pre none
	 * @post Puts the chat dialog in the chat log.
	 * @return returns the game model after it has been updated.
	 */
	
	@Override
	public Object execute(Input input) {
		int GameID = -1;
		GameModel model = GameHub.getInstance().getModel(GameID);
		SendChatInput in = (SendChatInput)input;
		int pi = in.getPlayerIndex();
		CatanColor cc = model.getPlayers().get(pi).getColor();
		LogEntry le = new LogEntry(cc, in.getContent());
		model.getChats().add(le);
		return model;
	}
	


}
