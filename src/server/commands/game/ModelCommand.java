package server.commands.game;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.Input;

public class ModelCommand implements ICommand {
	
	/**
	 * @pre player is logged in and has joined a game
	 * @post returns a the most current version of the game in progress
	 */

	@Override
	public Object execute(String input) {
		int GameID = 0;
		return GameHub.getInstance().getModel(GameID);
	}

}
