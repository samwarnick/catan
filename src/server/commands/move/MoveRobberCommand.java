package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class MoveRobberCommand implements ICommand{
	
	private GameModel model;

	/**
	 * @pre a 7 is rolled, or a soldier card is played
	 * @post The robber will be moved and one of the players will be robbed.  The resources will be updated accordingly.
	 * @return the updated game model.
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setModel(GameModel game){
		
	}

}
