package server.commands.move;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import server.ServerException;
import server.commands.ICommand;
import shared.model.GameModel;

@SuppressWarnings("serial")

public abstract class MoveCommand implements ICommand,Serializable {
	@JsonIgnore protected transient GameModel model;
	protected String input;
	
	

	public MoveCommand() {
		model = null;
		input = "";
	}

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
