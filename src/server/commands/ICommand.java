package server.commands;

import server.ServerException;

/**
 * Interface for the command objects
 * @author Matt
 *
 */
public interface ICommand {
	
	/**
	 * This method will be called by the appropriate handler on the server.
	 * It should perform all necessary operations for the command on the server data.
	 */
	Object execute(String input) throws ServerException;
	
}
