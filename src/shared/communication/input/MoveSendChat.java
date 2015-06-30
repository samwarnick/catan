package shared.communication.input;

/**
 * @author isaachartung
 *
 */
public class MoveSendChat extends MoveInput {
	
	private String message;
	
	public MoveSendChat(int playerID, String message){
		super("/moves/sendChat", playerID);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
