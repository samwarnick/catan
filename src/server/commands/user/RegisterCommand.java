package server.commands.user;

import com.google.gson.Gson;

import server.GameHub;
import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.UserRegisterInput;

public class RegisterCommand implements ICommand {

	@Override
	public Object execute(String input) throws ServerException {
		UserRegisterInput registerInput = new Gson().fromJson(input, UserRegisterInput.class);
		return GameHub.getInstance().registerUser(registerInput.getUsername(), registerInput.getPassword());
	}

}
