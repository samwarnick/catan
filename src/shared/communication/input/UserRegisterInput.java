package shared.communication.input;

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
