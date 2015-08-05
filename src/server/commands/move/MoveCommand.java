package server.commands.move;

import server.ServerException;
import server.commands.ICommand;
import shared.model.GameModel;

public abstract class MoveCommand implements ICommand {
	protected GameModel model;
	protected String input;

	@Override
	public abstract Object execute(String input) throws ServerException;
	
	public void setGameModel(GameModel model) {
		this.model = model;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}
	
	
	
}
