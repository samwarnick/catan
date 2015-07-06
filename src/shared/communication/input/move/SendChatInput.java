package shared.communication.input.move;

/**
 * This class contains the sendChat method name, the message to be sent and the playerIndex of the player sending the message.
 * @author Matt
 * 
 */
public class SendChatInput extends MoveInput {

	private String message;
	
	public SendChatInput(int playerIndex, String message) {
		super("sendChat", playerIndex);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
