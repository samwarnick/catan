package shared.communication.input;

/**
 * This class contains the gameModelVersion method name and the version number.
 * @author Matt
 * 
 */
public class GameModelVersionInput extends Input {

	private int version;
	
	public GameModelVersionInput (int version) {
		super(String.format("/game/model", version));
		this.version = version;
	}

	public int getVersion() {
		return version;
	}
	
}
