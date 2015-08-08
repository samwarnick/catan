package server.commands.games;

import java.io.Serializable;

import server.GameHub;
import server.commands.ICommand;

@SuppressWarnings("serial")
public class ListCommand implements ICommand, Serializable{
	
	/**
	 * @pre no preconditions
	 * @post returns the list of active games on this server.
	 */

	@Override
	public Object execute(String input) {
		return GameHub.getInstance().getGameInfos();
	}
}
