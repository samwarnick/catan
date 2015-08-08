package server.commands.game;

import java.io.Serializable;

import server.commands.ICommand;

@SuppressWarnings("serial")
public class ModelCommand implements ICommand, Serializable{
	
	/**
	 * @pre player is logged in and has joined a game
	 * @post returns a the most current version of the game in progress
	 */

	@Override
	public Object execute(String input) {
		return null;
	}

}
