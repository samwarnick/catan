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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gameID;
		result = prime * result
				+ ((gameName == null) ? 0 : gameName.hashCode());
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (gameID != other.gameID)
			return false;
		if (gameName == null) {
			if (other.gameName != null)
				return false;
		} else if (!gameName.equals(other.gameName))
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		return true;
	}
	
	
	
	
}
