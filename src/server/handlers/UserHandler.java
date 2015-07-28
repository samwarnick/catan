package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.ServerException;
import server.commands.user.LoginCommand;
import server.commands.user.RegisterCommand;
import shared.communication.input.Input;
import shared.model.user.User;

public class UserHandler extends Handler implements HttpHandler {
	
	/**
	 * creates a new command based on which /user/ method is called and executes that command.
	 * @post sets the cookie if the user is valid and returns 200, or returns 400 if bad input.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
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
				
				// set cookie
				exchange.getResponseHeaders().add("Set-Cookie", "stuff");
				
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 7);
				
				// write to response body

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
