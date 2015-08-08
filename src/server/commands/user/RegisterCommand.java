package server.commands.user;

import java.io.Serializable;

import com.google.gson.Gson;

import server.GameHub;
import server.ServerException;
import server.commands.ICommand;
import shared.communication.input.UserRegisterInput;
import shared.model.user.User;

@SuppressWarnings("serial")
public class RegisterCommand implements ICommand,Serializable {

	@Override
	public Object execute(String input) throws ServerException {
		UserRegisterInput registerInput = new Gson().fromJson(input, UserRegisterInput.class);
		User newUser = GameHub.getInstance().registerUser(registerInput.getUsername(), registerInput.getPassword());
		return newUser;
	}

}
