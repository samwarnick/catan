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
		System.out.println("GamesHandler");
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		System.out.println(input.getMethod());
		boolean needCookie = false;
		boolean join = false;
		switch (input.getMethod()) {
		case "/games/list":
			System.out.println("Listing");
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
		
		String userCookie = exchange.getRequestHeaders().getFirst("Cookie");
		System.out.println(userCookie);
		boolean valid = true;
		if (userCookie == null) {
			valid = false;
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
				
				if (command.getClass().equals(JoinCommand.class)) {
					// add to cookie with game 
					
					int id = (int) result;
					String gameCookie = "catan.game=" + id;
					
					exchange.getResponseHeaders().add("Set-Cookie", gameCookie);
				}
				
				exchange.getResponseHeaders().set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

				System.out.println("writing");
				
				// write to response body
				Writer writer = new OutputStreamWriter(exchange.getResponseBody());
				String toWrite = new Gson().toJson(result);
				System.out.println(toWrite);
				writer.write(toWrite);
				writer.close();
				
				System.out.println("written");
				
				exchange.getResponseBody().close();
			} catch (ServerException e) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
			}
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
		}
	}
}
