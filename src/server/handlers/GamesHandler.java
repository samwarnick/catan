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
		boolean needCookie = false;
		boolean join = false;
		switch (input.getMethod()) {
		case "/games/list":
			command = new ListCommand();
			break;
		case "/games/create":
			command = new CreateCommand();
			needCookie = true;
			break;
		case "/games/join":
			command = new JoinCommand();
			needCookie = true;
			join = true;
			break;
		}
		
		// deal with the cookie
		String cookie = exchange.getRequestHeaders().getFirst("Cookie");
		String userCookie = cookie;
		boolean valid = true;
		if (cookie != null) {
			// check if it has more than just the user
			StringBuilder builder = new StringBuilder(cookie);
			boolean needsTobeSplit = false;
			for (int i = 0; i < builder.length(); i++) {
				if (builder.charAt(i) == ';') {
					needsTobeSplit = true;
				}
			}
			if (needsTobeSplit) {
				String[] array = cookie.split(";");
				userCookie = array[0];
			}
			if (userCookie == null) {
				valid = false;
			}
		}
		
		if (command != null && ((needCookie && valid) || (!needCookie))) {
			Object result;
			try {
				
				if (join) {
					StringBuilder temp = new StringBuilder(userCookie);
					int index = temp.lastIndexOf("catan.user=") + 11;
					int playerID = Integer.parseInt(temp.substring(index, temp.length()));
					JoinCommand joinCommand = (JoinCommand) command;
					joinCommand.setPlayerID(playerID);
				}
				
				result = command.execute(json);
				
				if (join) {
					// add to cookie with game 
					
					int id = (int) result;
					String gameCookie = "catan.game=" + id;
					
					exchange.getResponseHeaders().add("Set-Cookie", gameCookie);
					
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 7);

					// write to response body
					Writer writer = new OutputStreamWriter(exchange.getResponseBody());
					writer.write("Success");
					writer.close();
				} else {
					
					exchange.getResponseHeaders().set("Content-Type", "text/html");
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					
					// write to response body
					Writer writer = new OutputStreamWriter(exchange.getResponseBody());
					String toWrite = new Gson().toJson(result);
					writer.write(toWrite);
					writer.close();
				}
				
				exchange.getResponseBody().close();
			} catch (ServerException e) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
			}
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
		}
	}
}
