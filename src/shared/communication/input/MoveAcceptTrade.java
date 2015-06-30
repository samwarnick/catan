package shared.communication.input;

public class MoveAcceptTrade extends MoveInput{
	
	private boolean acceptTrade;
	
	public MoveAcceptTrade(int playerID, boolean decision){
		super("/moves/acceptTrade", playerID);
		this.acceptTrade = decision;
	}

	public boolean isAcceptTrade() {
		return acceptTrade;
	}

	public void setAcceptTrade(boolean acceptTrade) {
		this.acceptTrade = acceptTrade;
	}
	

}
