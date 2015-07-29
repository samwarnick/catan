package server.commands.user;

import com.google.gson.Gson;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.UserLoginInput;

public class LoginCommand implements ICommand {

	@Override
	public Object execute(String input) {
		UserLoginInput loginInput = new Gson().fromJson(input, UserLoginInput.class);
		return GameHub.getInstance().getUserByNameAndPassword(loginInput.getUsername(), loginInput.getPassword());
	}
}