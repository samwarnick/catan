package shared.communication.input.move;

/**
 * This class contains the acceptTrade method name, playerIndex and a boolean indicating whether the player accepts the trade or not.
 * @author Matt
 * 
 */
public class AcceptTradeInput extends MoveInput {

	private boolean willAccept;
	private String type = "acceptTrade";

	public AcceptTradeInput(int playerIndex, boolean willAccept) {
		super("/acceptTrade", playerIndex);
		this.willAccept = willAccept;
	}
	
	public AcceptTradeInput() {
		super("/acceptTrade", -1);
		this.willAccept = false;
	}

	public boolean isWillAccept() {
		return willAccept;
	}

	public String getType() {
		return type;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
