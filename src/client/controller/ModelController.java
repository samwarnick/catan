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
	private IServer proxyServer;
	private int PlayerID;
	
	public ModelController(int gameID){
		gameModelFacade = GameModelFacade.getInstance(gameID);
		poller = new Poller(this);
		proxyServer = ProxyServer.getInstance();
	}
	
	public ModelController(int gameID, IServer server){
		gameModelFacade = GameModelFacade.getInstance(gameID);
		proxyServer = server;
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



	public IServer getProxyServer() {
		return proxyServer;
	}

	

	public void setProxyServer(IServer proxyServer) {
		this.proxyServer = proxyServer;
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
