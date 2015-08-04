package server.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

import client.communication.LogEntry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import server.GameHub;
import server.ServerException;
import server.commands.move.*;
import shared.communication.input.Input;
import shared.communication.input.move.AcceptTradeInput;
import shared.communication.input.move.RollNumberInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;

public class MoveHandler extends Handler {
	
	/**
	 * creates a new command based on which /move/ method is called and executes that command one the correct game.
	 * @post the command is executed correctly. If invalid params, returns 400. If error in server, returns 500.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String extension = "";
		String json = jsonStringFromExchange(exchange.getRequestBody());
		Input input = new Gson().fromJson(json, Input.class);
		switch (input.getMethod()) {
		case "/moves/sendChat":
			extension = "sent a chat";
			command = new SendChatCommand();
			break;
		case "/moves/rollNumber":
			extension = "rolled a ";
			command = new RollNumberCommand();
			break;
		case "/moves/robPlayer":
			extension = "burgled a player";
			command = new RobPlayerCommand();
			break;
		case "/moves/finishTurn":
			extension = "finished his/her turn";
			command = new FinishTurnCommand();
			break;
		case "/moves/buyDevCard":
			extension = "bought a dev card";
			command = new BuyDevCardCommand();
			break;
		case "/moves/Year_of_Plenty":
			extension = "cast Year-of-Plenty";
			command = new PlayYearOfPlentyCommand();
			break;
		case "/moves/Road_Building":
			extension = "cast Road-Building";
			command = new PlayRoadBuildingCommand();
			break;
		case "/moves/Soldier":
			extension = "Sent a Chat";
			command = new PlaySoldierCommand();
			break;
		case "/moves/Monument":
			extension = "cast Monument";
			command = new PlayMonumentCommand();
			break;
		case "/moves/Monopoly":
			extension = "cast Monopoly";
			command = new PlayMonopolyCommand();
			break;
		case "/moves/buildRoad":
			extension = "built a road";
			command = new BuildRoadCommand();
			break;
		case "/moves/buildCity":
			extension = "built a city";
			command = new BuildCityCommand();
			break;
		case "/moves/buildSettlement":
			extension = "built a settlement";
			command = new BuildSettlementCommand();
			break;
		case "/moves/offerTrade":
			extension = "offered a trade";
			command = new OfferTradeCommand();
			break;
		case "/moves/acceptTrade":
			extension = "accepted a trade";
			command = new AcceptTradeCommand();
			break;
		case "/moves/maritimeTrade":
			extension = "traded with pirates";
			command = new MaritimeTradeCommand();
			break;
		case "/moves/discardCards":
			extension = "sent his cards to the graveyard";
			command = new DiscardCardsCommand();
			break;
		}
		
		String cookie = exchange.getRequestHeaders().getFirst("Cookie");
		String[] cookieArray = cookie.split(";");
		
		if (command != null && cookieArray.length == 2) {
			try {
				
				String gameCookie = cookieArray[1].trim();
				StringBuilder temp = new StringBuilder(gameCookie);
				int index = temp.lastIndexOf("catan.game=") + 11;
				int gameId = Integer.parseInt(temp.substring(index, temp.length()));
				
				GameModel model = GameHub.getInstance().getModel(gameId);

				

				MoveCommand moveCommand = (MoveCommand) command;
				moveCommand.setGameModel(model);
				GameModel updatedModel = (GameModel) moveCommand.execute(json);
				GameHub.getInstance().updateModel(updatedModel);
				
				//add log to GameHistory
				gameCookie = cookieArray[0].trim();
				temp = new StringBuilder(gameCookie);
				index = temp.lastIndexOf("catan.user=") + 11;
				int pId = Integer.parseInt(temp.substring(index, temp.length()));
				String name = GameHub.getInstance().getUser(pId).getUsername();
				CatanColor cc = model.getPlayer(name).getColor();
				if (extension.equals("rolled a ")){
					RollNumberInput rollInput = new ObjectMapper().readValue(json, RollNumberInput.class);
					extension = extension + rollInput.getNumber();
				}
				if (extension.equals("accepted a trade")){
					AcceptTradeInput atinput = new ObjectMapper().readValue(json, AcceptTradeInput.class);
					if (!atinput.isWillAccept())
						extension = "rejected a trade";
				}
				String message = name + " " + extension + ".";
				System.out.printf("message %s and id %d\n", message, pId);
				LogEntry le = new LogEntry(cc, message);
				GameHub.getInstance().getModel(gameId).getLogs().add(le);
				//finished with log
				
				exchange.getResponseHeaders().set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

				// write to response body
				Writer writer = new OutputStreamWriter(exchange.getResponseBody());
				String toWrite = new ObjectMapper().writeValueAsString(updatedModel);
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
