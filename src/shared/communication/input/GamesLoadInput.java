package shared.communication.input;

/**
 * This class contains the gamesLoad method name and the fileName for the game.
 * @author Matt
 * 
 */
public class GamesLoadInput extends Input {

	private String name;
	private int id;

	public GamesLoadInput(String fileName) {
		super("/games/load");
		this.name = fileName;
	}

	public String getName() {
		return name;
	}
	
	public int getId(){
		return id;
	}
	
}
