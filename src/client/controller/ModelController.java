package client.controller;

import java.util.List;

import client.login.LoginController;
import client.poller.Poller;
import client.proxy.ProxyServer;
import server.IServer;
import server.ServerException;
import shared.communication.input.move.DiscardCardsInput;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.bank.ResourceHand;
import shared.model.board.Board;
import shared.model.player.Player;

public class ModelController {

	private GameModelFacade gameModelFacade;
	private Poller poller;
	private int PlayerID;
	private ModelController instance = null;
	
	public ModelController getInstance() {
		if (instance == null) {
			// TODO
			instance = new ModelController(0);
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

	public void updateGame(GameModel gameModel){
		gameModelFacade.setGameModel(gameModel);
	}

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	
}
