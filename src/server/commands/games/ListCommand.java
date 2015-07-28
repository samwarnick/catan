package server.commands.games;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.Input;

public class ListCommand implements ICommand{
	
	/**
	 * @pre no preconditions
	 * @post returns a the list of active games on this server.
	 */

	@Override
	public Object execute(String input) {
		return GameHub.getInstance().getGameInfos();
	}
}
