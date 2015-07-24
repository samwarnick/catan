package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class PlayYearOfPlentyCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid PlayYearOfPlentyInput object
	 * @pre The two specified resources are available in the bank
	 * @post The player gained one of each of the two specified resources
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
