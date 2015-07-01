package shared.communication.input;

/**
 * 
 * @author Matt
 * This is the parent of all input classes, it is given method names by
 * the constructors of its children.
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
