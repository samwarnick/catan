package shared.communication.input;

public class GameModelVersionInput extends Input {

	private int version;
	
	public GameModelVersionInput (int version) {
		super("/game/model?version=");
		this.version = version;
	}

	public int getVersion() {
		return version;
	}
	
}
