package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class BuildCityCommand implements ICommand{
	
	private GameModel model;

	
	/**
	 * @pre the location is a valid location for the player to build a city, the player has enough resources to build a city.
	 * @post the city will be placed at the location and the resources will be removed from the player
	 * @return the updated GameModel
	 */
	@Override
	public Object execute(Input input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}

}
