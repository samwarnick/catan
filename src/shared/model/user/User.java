package shared.model.user;

public class User {
	private String username;
	private String password;
	private int id;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		id = -1;
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
}
