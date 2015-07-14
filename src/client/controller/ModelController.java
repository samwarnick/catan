package client.controller;

import java.util.List;

import client.discard.DiscardController;
import client.maritime.MaritimeTradeController;
import client.poller.Poller;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.move.DiscardCardsInput;
import shared.communication.input.move.MaritimeTradeInput;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.board.PlayerID;
import shared.model.player.Player;

public class ModelController {

	private MaritimeTradeController maritimeController;
	private DiscardController discardController;
	private GameModelFacade gameModelFacade;
	private Poller poller;

	private int PlayerID;
	private static ModelController instance = null;
	private boolean testing = false;

	// get instances
	
	public static ModelController getInstance() {
		if (instance == null) {
			// TODO
			instance = new ModelController(0);
		}
		return instance;
	}
	
	public static ModelController getInstance(int x) {
		if (instance == null) {
			// TODO
			instance = new ModelController(x);
		}
		return instance;
	}
	
	private ModelController(int gameID){
		gameModelFacade = GameModelFacade.getInstance(gameID);
		poller = new Poller(this);
	}
	
	public void startGame(List<Player> players, boolean randomHexes, boolean randomNumbers, boolean randomPorts){
		for (Player player : players)
			try {
				gameModelFacade.getGameModel().addPlayer(player);
			} catch (TooManyPlayersException e) {
				e.printStackTrace();
			}
		gameModelFacade.getGameModel().setBoard(new Board(randomHexes,randomNumbers,randomPorts));
	}
	
	// getters and setters
	
	public GameModelFacade getGameModelFacade() {
		return gameModelFacade;
	}

	public void setGameModelFacade(GameModelFacade gameModelFacade) {
		this.gameModelFacade = gameModelFacade;
	}

	public Poller getPoller() {
		return poller;
	}
	
	public void setPoller(Poller poller) {
		this.poller = poller;
	}
	
	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	
	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}
	
	// controller 

	public void updateGame(GameModel gameModel){
		gameModelFacade.setGameModel(gameModel);
		maritimeController.getTradeView().enableMaritimeTrade(gameModelFacade.canFinishTurn(gameModelFacade.getGameModel().getPlayer(new PlayerID(PlayerID))));
		// TODO add updates to GUI here
	}
	
	public void maritimeTrade(MaritimeTradeInput input){
		try {
			gameModelFacade.setGameModel(ProxyServer.getInstance().maritimeTrade(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void discard(ResourceHand toDiscard) {
		DiscardCardsInput input = new DiscardCardsInput(PlayerID, toDiscard);
		try {
			updateGame(ProxyServer.getInstance().discardCards(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
}
