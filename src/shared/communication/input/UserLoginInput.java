package shared.communication.input;

/**
 * 
 * @author Matt
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
