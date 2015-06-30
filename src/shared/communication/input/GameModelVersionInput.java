package shared.communication.input;

/**
 * 
 * @author Matt
 * This class contains the gameModelVersion method name and the version number.
 * 
 */
public class GameModelVersionInput extends Input {

	private int version;
	
	public GameModelVersionInput (int version) {
		super("/game/model?version=");
//		super(String.format("/game/model?version=%d", version));
		this.version = version;
	}

	public int getVersion() {
		return version;
	}
	
}
