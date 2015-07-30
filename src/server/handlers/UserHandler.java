package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import server.ServerException;
import server.commands.user.LoginCommand;
import server.commands.user.RegisterCommand;
import shared.communication.input.Input;
import shared.model.user.User;

public class UserHandler extends Handler {
	
	/**
	 * creates a new command based on which /user/ method is called and executes that command.
	 * @post sets the cookie if the user is valid and returns 200, or returns 400 if bad input.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		switch (input.getMethod()) {
		case "/user/login":
			command = new LoginCommand();
			break;
		case "/user/register":
			command = new RegisterCommand();
			break;
		}
		
		if (command != null) {
			try {
				User user = (User) command.execute(json);
				exchange.getResponseHeaders().set("Content-Type", "text/html");
				
				// create cookie from user
				String cookie = user.createCookie();
				// set cookie
				exchange.getResponseHeaders().add("Set-Cookie", cookie);
				
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 7);

				// write to response body
				Writer writer = new OutputStreamWriter(exchange.getResponseBody());
				writer.write("Success");
				writer.close();
				
				exchange.getResponseBody().close();
			} catch (ServerException e) {
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
			}
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
		}
	}
}
