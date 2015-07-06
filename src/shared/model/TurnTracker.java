package shared.model;

public class TurnTracker {

	private int currentTurn;
	private String status;
	
	public TurnTracker(){
		currentTurn = 0;
		status = "First Round";
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) throws NoPlayerFoundException {
		if (currentTurn < 4)
			this.currentTurn = currentTurn;
		else 
			throw new NoPlayerFoundException();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void incrementTurn(){
		currentTurn++;
		if (currentTurn == 4)
			currentTurn = 0;
	}
}

@SuppressWarnings("serial")
class NoPlayerFoundException extends Exception{
	
}
