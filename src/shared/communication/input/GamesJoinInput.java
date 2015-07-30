package shared.communication.input;

import shared.definitions.CatanColor;

/**
 * This class contains the gamesJoin method name, the gameID and the Color for the new player.
 * @author Matt
 * 
 */
public class GamesJoinInput extends Input {

	private int id;
	private String color;
	
	public GamesJoinInput(int gameID, CatanColor color) {
		super("/games/join");
		this.id = gameID;
		this.color = color.toString().toLowerCase();
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

}
