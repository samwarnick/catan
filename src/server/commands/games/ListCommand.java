package server.commands.games;

import server.GameHub;
import server.commands.ICommand;

public class ListCommand implements ICommand{
	
	/**
	 * @pre no preconditions
	 * @post returns the list of active games on this server.
	 */

	@Override
	public Object execute(String input) {
		return GameHub.getInstance().getGameInfos();
	}
}
