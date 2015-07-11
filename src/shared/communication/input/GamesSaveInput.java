package shared.communication.input;

/**
 * This class contains the gamesSave method name, the gameID and the fileName for the game.
 * @author Matt
 * 
 * 
 */
public class GamesSaveInput extends Input {

	private int id;
	private String name;
	
	public GamesSaveInput(int gameID, String fileName) {
		super("/games/save");
		this.id = gameID;
		this.name = fileName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
