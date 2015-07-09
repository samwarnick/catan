package shared.communication.input;

import shared.model.user.*;

/**
 * This class contains the userLogin method name, the username and the password for the user.
 * @author Matt
 * 
 * 
 */
public class UserLoginInput extends Input{
	
	private String username;
	private String password;

	public UserLoginInput(String username, String password) {
		super("/user/login");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

<<<<<<< HEAD
=======

>>>>>>> implement_MOCK
	public String getPassword() {
		return password;
	}

<<<<<<< HEAD
=======

>>>>>>> implement_MOCK
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
<<<<<<< HEAD
=======

//	public Username getUsername() {
//		return username;
//	}
//
//	public Password getPassword() {
//		return password;
//	}
	
>>>>>>> implement_MOCK
}
