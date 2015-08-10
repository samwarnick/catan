package shared.model.user;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private String username;
	private String password;
	private int id;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		id = -1;
	}
	
	public User() {
		username = "";
		password = "";
		id = -1;
	}
	
	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String createCookie() {
		return "catan.user=" + id;
	}
}
