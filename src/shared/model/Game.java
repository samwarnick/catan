package shared.model;

import java.util.List;

public class Game {

	private int gameID;
	private String gameName;
	private List<DisplayPlayer> players;
	public Game(int gameID, String gameName, List<DisplayPlayer> players) {
		super();
		this.gameID = gameID;
		this.gameName = gameName;
		this.players = players;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public List<DisplayPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<DisplayPlayer> players) {
		this.players = players;
	}
	
	
	
	
}
