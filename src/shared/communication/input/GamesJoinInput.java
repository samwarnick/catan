package shared.communication.input;

import shared.model.player.Color;

public class GamesJoinInput extends Input {

	private int gameID;
	private Color color;
	
	public GamesJoinInput(int gameID, Color color) {
		super("/games/join");
		this.gameID = gameID;
		this.color = color;
	}

	public int getGameID() {
		return gameID;
	}

	public Color getColor() {
		return color;
	}

}
