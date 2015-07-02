package shared.communication.input;

import shared.model.user.*;

/**
 * This class contains the userLogin method name, the username and the password for the user.
 * @author Matt
 * 
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
