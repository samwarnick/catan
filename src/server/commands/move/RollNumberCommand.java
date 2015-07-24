package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class RollNumberCommand implements ICommand{
	
	private GameModel model;

	
	/**
	 * @pre none
	 * @post changes the resources the players have based on the number of the roll.  It adds one resource for each settlement touching the hexes that have
	 * the number, and 2 of each resource for the cities on the hexes.
	 * @return returns the game model after the modifications have been made
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setModel(GameModel model){
		
	}
}
