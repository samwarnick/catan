package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class BuyDevCardCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @param input is a valid BuyDevCardInput object
	 * @pre The player has the required resources (1 ore, 1 wheat, 1 sheep) and there is at least one development card left in the game bank
	 * @post The player has a new development card. If it is a monument card, it has been added to the player's old development card hand. If it is a non-monument card, it has been added to the player's new development card hand. 
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
