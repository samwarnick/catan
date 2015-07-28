package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import server.GameHub;
import server.ServerException;
import server.commands.move.*;
import shared.communication.input.Input;
import shared.model.GameModel;

public class MoveHandler extends Handler {
	
	/**
	 * creates a new command based on which /move/ method is called and executes that command one the correct game.
	 * @post the command is executed correctly. If invalid params, returns 400. If error in server, returns 500.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		switch (input.getMethod()) {
		case "/moves/sendChat":
			command = new SendChatCommand();
			break;
		case "/moves/rollNumber":
			command = new RollNumberCommand();
			break;
		case "/moves/robPlayer":
			command = new RobPlayerCommand();
			break;
		case "/moves/finishTurn":
			command = new FinishTurnCommand();
			break;
		case "/moves/buyDevCard":
			command = new BuyDevCardCommand();
			break;
		case "/moves/Year_of_Plenty":
			command = new PlayYearOfPlentyCommand();
			break;
		case "/moves/Road_Building":
			command = new PlayRoadBuildingCommand();
			break;
		case "/moves/Soldier":
			command = new PlaySoldierCommand();
			break;
		case "/moves/Monument":
			command = new PlayMonumentCommand();
			break;
		case "/moves/Monopoly":
			command = new PlayMonopolyCommand();
			break;
		case "/moves/buildRoad":
			command = new BuildRoadCommand();
			break;
		case "/moves/buildCity":
			command = new BuildCityCommand();
			break;
		case "/moves/buildSettlement":
			command = new BuildSettlementCommand();
			break;
		case "/moves/offerTrade":
			command = new OfferTradeCommand();
			break;
		case "/moves/acceptTrade":
			command = new AcceptTradeCommand();
			break;
		case "/moves/maritimeTrade":
			command = new MaritimeTradeCommand();
			break;
		case "/moves/discardCards":
			command = new DiscardCardsCommand();
			break;
		}
		
		String cookie = exchange.getRequestHeaders().getFirst("Cookie");
		StringBuilder temp = new StringBuilder(URLDecoder.decode(cookie, "UTF-8"));
		int index = temp.lastIndexOf("catan.game=") + 11;
		int gameId = Integer.parseInt(temp.substring(index, temp.length()));
		
		if (command != null && cookie != null) {
			try {
				GameModel model = GameHub.getInstance().getModel(gameId);
				GameModel updatedModel = (GameModel) command.execute(json);
				GameHub.getInstance().updateModel(updatedModel);
				
				exchange.getResponseHeaders().set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

				// write to response body
				Writer writer = new OutputStreamWriter(exchange.getResponseBody());
				String toWrite = new Gson().toJson(updatedModel);
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
