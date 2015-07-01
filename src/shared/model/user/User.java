package shared.model.user;

public class User {
	private Username username;
	private Password password;
	private String cookie;
	
	public User(Username username, Password password) {
		this.username = username;
		this.password = password;
		cookie = "";
	}

	public String getCookie() {
		return cookie;
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public Username getUsername() {
		return username;
	}
	
	public Password getPassword() {
		return password;
	}
	
	
}
