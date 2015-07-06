package shared.communication.input;

import shared.model.user.*;

/**
 * This class contains the userRegister method name, the username and the password for the new user.
 * @author Matt
 * 
 * 
 */
public class UserRegisterInput extends Input {

	private Username username;
	private Password password;

	public UserRegisterInput(Username username, Password password) {
		super("/user/register");
		this.username = username;
		this.password = password;
	}

	public Username getUsername() {
		return username;
	}

	public Password getPassword() {
		return password;
	}

}
