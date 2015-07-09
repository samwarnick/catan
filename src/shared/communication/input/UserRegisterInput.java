package shared.communication.input;

import shared.model.user.*;

/**
 * This class contains the userRegister method name, the username and the password for the new user.
 * @author Matt
 * 
 * 
 */
public class UserRegisterInput extends Input {

	private String username;
	private String password;

	public UserRegisterInput(String username, String password) {
		super("/user/register");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

<<<<<<< HEAD
	public void setUsername(String username) {
		this.username = username;
	}

=======
>>>>>>> implement_MOCK
	public String getPassword() {
		return password;
	}

<<<<<<< HEAD
	public void setPassword(String password) {
		this.password = password;
	}
	
	
=======
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Username getUsername() {
//		return username;
//	}
//
//	public Password getPassword() {
//		return password;
//	}

>>>>>>> implement_MOCK
}
