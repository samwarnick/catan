package shared.communication.input.move;

/**
 * This class contains the sendChat method name, the message to be sent and the playerIndex of the player sending the message.
 * @author Matt
 * 
 */
public class SendChatInput extends MoveInput {

	private String content;
	private String type;
	
	public SendChatInput(int playerIndex, String message) {
		super("/sendChat", playerIndex);
		this.content = message;
		type = "sendChat";
	}
	
	public SendChatInput() {
		super("/sendChat", -1);
		type = "sendChat";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
