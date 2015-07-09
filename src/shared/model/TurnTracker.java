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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTurn;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnTracker other = (TurnTracker) obj;
		if (currentTurn != other.currentTurn)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}

@SuppressWarnings("serial")
class NoPlayerFoundException extends Exception{
	
}
