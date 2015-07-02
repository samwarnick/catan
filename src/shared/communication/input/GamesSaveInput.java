package shared.communication.input;

/**
 * This class contains the gamesSave method name, the gameID and the fileName for the game.
 * @author Matt
 * 
 * 
 */
public class GamesSaveInput extends Input {

	private int gameID;
	private String fileName;
	
	public GamesSaveInput(int gameID, String fileName) {
		super("/games/save");
		this.gameID = gameID;
		this.fileName = fileName;
	}

	public int getGameID() {
		return gameID;
	}

	public String getFileName() {
		return fileName;
	}
	
}
