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
	
}
