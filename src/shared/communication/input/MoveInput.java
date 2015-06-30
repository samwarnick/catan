package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveInput extends Input{
	
	private int playerId;
	
	public MoveInput(String method, int ID){
		super(method);
		playerId = ID;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}
