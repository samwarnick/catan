package shared.communication.input;

/**
 * @author isaachartung
 *
 *This is the parent of all Move inputs.  It is given a playerindex and 
 *a method name by the constructors of its children.
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
