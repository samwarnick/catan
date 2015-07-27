package server.handlers;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.commands.ICommand;
import server.commands.user.UserLoginCommand;
import shared.communication.input.Input;

public class UserHandler implements HttpHandler {

	private ICommand command = null;
	
	/**
	 * creates a new command based on which /user/ method is called and executes that command.
	 * @post sets the cookie if the user is valid and returns 200, or returns 400 if bad input.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Input input = mapper.readValue(exchange.getRequestBody(), Input.class);
		switch (input.getMethod()) {
		case "/user/login":
			command = new UserLoginCommand();
		case "/user/register":
			// command = new UserRegisterCommand();
		default:
			command = null;
		}
		
		if (command != null) {
			command.execute(input);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			// set cookie
			
			// write to response body
			byte[] bytes = mapper.writeValueAsBytes("Success");
			exchange.getResponseBody().write(bytes);
			exchange.getResponseBody().close();
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
		}
	}
}
