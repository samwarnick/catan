package shared.model.board;

public class PlayerID {

	private int playerid;
	
	public PlayerID(int num) {
		if (num >= -1 && num <= 3)
			playerid = num;
	}

	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int num) {
		if (num >= -1 && num <= 3)
			this.playerid = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerid;
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
		PlayerID other = (PlayerID) obj;
		if (playerid != other.playerid)
			return false;
		return true;
	}
}
