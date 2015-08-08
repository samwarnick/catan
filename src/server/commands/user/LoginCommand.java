package server.commands.user;

import java.io.Serializable;

import com.google.gson.Gson;

import server.GameHub;
import server.commands.ICommand;
import shared.communication.input.UserLoginInput;

@SuppressWarnings("serial")
public class LoginCommand implements ICommand,Serializable{

	@Override
	public Object execute(String input) {
		UserLoginInput loginInput = new Gson().fromJson(input, UserLoginInput.class);
		return GameHub.getInstance().getUserByNameAndPassword(loginInput.getUsername(), loginInput.getPassword());
	}
}