package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import server.GameHub;
import server.commands.game.ModelCommand;
import shared.communication.input.Input;
import shared.model.GameModel;

public class GameHandler extends Handler {

	/**
	 * creates a new command based on which /game/ method is called and executes that command one the correct game.
	 * @post the command is executed correctly. If invalid params, returns 400. If error in server, returns 500.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		switch (input.getMethod()) {
		case "/game/model":
			command = new ModelCommand();
			break;
		}
		
		String cookie = exchange.getRequestHeaders().getFirst("Cookie");	
		if (command != null && cookie != null) {
				
			StringBuilder temp = new StringBuilder(cookie);
			int index = temp.lastIndexOf("catan.game=") + 11;
			int gameId = Integer.parseInt(temp.substring(index, temp.length()));
			GameModel model = GameHub.getInstance().getModel(gameId);
			exchange.getResponseHeaders().set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

			// write to response body
			Writer writer = new OutputStreamWriter(exchange.getResponseBody());

			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
			Gson gson = builder.create();
			String toWrite = gson.toJson(model);
			writer.write(toWrite);
			writer.close();
			
			exchange.getResponseBody().close();
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
		}
		
	}
}
