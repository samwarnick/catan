package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class SendChatCommand implements ICommand{
	
	private GameModel model;

	
	/**
	 * @pre none
	 * @post Puts the chat dialog in the chat log.
	 * @return returns the game model after it has been updated.
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setModel(GameModel game){
		
	}

}
