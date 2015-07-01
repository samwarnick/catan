package shared.communication.input;

import shared.model.user.*;

/**
 * 
 * @author Matt
 * This class contains the userLogin method name, the username and the password for the user.
 * 
 */
public class UserLoginInput extends Input{
	
	private Username username;
	private Password password;

	public UserLoginInput(Username username, Password password) {
		super("/user/login");
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
