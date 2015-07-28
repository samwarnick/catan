package shared.communication.input;

/**
 * This class is the base class for all Input classes
 * @author Matt
 * 
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
