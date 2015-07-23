package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class PlayMonopolyCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @pre None
	 * @post All other players have given all of the specified resource to 
	 * @return The GameModel after executing the changes
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
}
