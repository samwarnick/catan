package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.List;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import server.GameHub;
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
			System.out.println("Listing");
			command = new ListCommand();
			break;
		case "/games/create":
			command = new CreateCommand();
			break;
		case "/games/join":
			command = new JoinCommand();
			break;
		}
		
		if (command != null) {
			List<GameInfo> games = GameHub.getInstance().getGameInfos();
			
			exchange.getResponseHeaders().set("Content-Type", "text/html");
			exchange.getResponseHeaders().add("Set-Cookie", "stuff");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

			// write to response body
			Writer writer = new OutputStreamWriter(exchange.getResponseBody());
			String toWrite = new Gson().toJson(games);
			writer.write(toWrite);
			writer.close();
			
//			Headers headers = exchange.getResponseHeaders();
//			for (String s: headers.keySet()) {
//				System.out.println(s);
//			}
			
			exchange.getResponseBody().close();
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
			exchange.getResponseBody().close();
		}
	}
}
