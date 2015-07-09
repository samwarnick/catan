package shared.communication.input;

import shared.model.player.Color;

/**
 * This class contains the gamesJoin method name, the gameID and the Color for the new player.
 * @author Matt
 * 
 */
public class GamesJoinInput extends Input {

	private int id;
	private Color color;
	
	public GamesJoinInput(int gameID, Color color) {
		super("/games/join");
		this.id = gameID;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return "red";
	}

}
