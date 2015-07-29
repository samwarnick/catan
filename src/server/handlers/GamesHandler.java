package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import server.ServerException;
import server.commands.ICommand;
import server.commands.games.CreateCommand;
import server.commands.games.JoinCommand;
import server.commands.games.ListCommand;
import shared.communication.input.Input;

public class GamesHandler extends Handler {

	ICommand command = null;
	
	/**
	 * creates a new command based on which /games/ method is called and executes that command one the correct game.
	 * @post the command is executed correctly. If invalid params, returns 400. If error in server, returns 500.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		switch (input.getMethod()) {
		case "/games/list":
			command = new ListCommand();
			break;
		case "/games/create":
			command = new CreateCommand();
			break;
		case "/games/join":
			command = new JoinCommand();
			break;
		}
		
		String userCookie = exchange.getRequestHeaders().getFirst("Cookie");
		
		if (command != null && userCookie != null) {
			
			Object result;
			try {
				result = command.execute(json);
				
				if (command.getClass().equals(JoinCommand.class)) {
					// add to cookie with game 
					
					int id = (int) result;
					String gameCookie = "catan.game=" + id;
					
					exchange.getResponseHeaders().add("Set-Cookie", gameCookie);
				}
				
				exchange.getResponseHeaders().set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

				// write to response body
				Writer writer = new OutputStreamWriter(exchange.getResponseBody());
				String toWrite = new Gson().toJson(result);
				writer.write(toWrite);
				writer.close();
				
				exchange.getResponseBody().close();
			} catch (ServerException e) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
			}
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
		}
	}
}
