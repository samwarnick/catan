package shared.communication.input;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is the base class for all Input classes
 * @author Matt
 * 
 */
public class Input {

	@JsonIgnore
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
