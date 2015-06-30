package shared.communication.input;

import shared.model.player.Color;

/**
 * 
 * @author Matt
 * This class contains the gamesJoin method name, the gameID and the Color for the new player.
 * 
 */
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
