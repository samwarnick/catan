package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gamesLoad method name and the fileName for the game.
 * 
 */
public class GamesLoadInput extends Input {

	private String fileName;

	public GamesLoadInput(String fileName) {
		super("/games/load");
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
}
