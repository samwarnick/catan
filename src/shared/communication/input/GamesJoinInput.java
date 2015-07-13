package shared.communication.input;

import shared.definitions.CatanColor;

/**
 * This class contains the gamesJoin method name, the gameID and the Color for the new player.
 * @author Matt
 * 
 */
public class GamesJoinInput extends Input {

	private int id;
	private CatanColor color;
	
	public GamesJoinInput(int gameID, CatanColor color2) {
		super("/games/join");
		this.id = gameID;
		this.color = color2;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return "red";
	}

}
