package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gamesCreate method name, the name for the game and booleans for whether the tiles, numbers and ports are random or not.
 * 
 */
public class GamesCreateInput extends Input {

	private String name;
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;

	public GamesCreateInput(String name, boolean randomTiles, boolean randomNumbers,
			boolean randomPorts) {
		super("/games/create");
		this.name = name;
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
	}

	public String getName() {
		return name;
	}

	public boolean isRandomTiles() {
		return randomTiles;
	}

	public boolean isRandomNumbers() {
		return randomNumbers;
	}

	public boolean isRandomPorts() {
		return randomPorts;
	}
	
	
}
