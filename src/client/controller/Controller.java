package client.controller;

import java.util.List;

import client.poller.Poller;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;
import server.IServer;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.board.Board;
import shared.model.player.Player;

public class Controller {

	private GameModelFacade gameModelFacade;
	private Poller poller;
	private IServer proxyServer;
	
	public Controller(int gameID){
		gameModelFacade = new GameModelFacade(gameID);
		poller = new Poller(this);
		proxyServer = new ProxyServer(new ClientCommunicator());
	}
	
	public Controller(int gameID, IServer server){
		gameModelFacade = new GameModelFacade(gameID);
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
}
