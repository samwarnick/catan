package shared.communication.input.move;

/**
 * 
 * @author Matt
 * This class contains the acceptTrade method name, playerIndex and a boolean indicating whether the player accepts the trade or not.
 * 
 */
public class AcceptTradeInput extends MoveInput {

	private boolean willAccept;

	public AcceptTradeInput(int playerIndex, boolean willAccept) {
		super("acceptTrade", playerIndex);
		this.willAccept = willAccept;
	}

	public boolean isWillAccept() {
		return willAccept;
	}
	
}
