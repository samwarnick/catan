package shared.communication.input;

/**
 * 
 * @author Matt
 * This class is the base class for all Input classes
 */
public class Input {

	private String method;
	
	public Input(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
