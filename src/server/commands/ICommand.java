package server.commands;

import server.ServerException;
import shared.communication.input.Input;

/**
 * Interface for the command objects
 * @author Matt
 *
 */
public interface ICommand {

	/**
	 * This method will be called by the appropriate handler on the server.
	 * It should perform all necessary operations for the command on the server data.
	 * @throws ServerException 
	 */
	Object execute(Input input) throws ServerException;
	
}
