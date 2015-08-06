package server.commands.move;

import com.fasterxml.jackson.annotation.JsonIgnore;

import server.ServerException;
import server.commands.ICommand;
import shared.model.GameModel;

public abstract class MoveCommand implements ICommand {
	@JsonIgnore protected transient GameModel model;
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
