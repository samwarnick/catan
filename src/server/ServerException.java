package server;

@SuppressWarnings("serial")
public class ServerException extends Exception{

	public ServerException(String message) {
		super(message);
	}
	
	public ServerException() {
		super("server error");
	}
}
