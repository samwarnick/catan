package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MoveHandler implements HttpHandler {

	/**
	 * creates a new command based on which /move/ method is called and executes that command one the correct game.
	 * @post the command is executed correctly. If invalid params, returns 400. If error in server, returns 500.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In MoveHandler");
	}
}
