package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class PlayRoadBuildingCommand implements ICommand {

	private GameModel model;
	
	
	/**
	 * @pre The first road location must connect to one of the player's existing roads. The second road location must connect to one of the player's existing roads or the first road. The locations must be on a valid land edge and not occupied by another player. The player must have two unused roads.
	 * @post The player has two less unused roads. The two new roads appear at the specified locations on the map. If the player qualifies, the player is awarded the 'longest road' card.
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
