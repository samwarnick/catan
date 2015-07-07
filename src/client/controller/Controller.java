package client.controller;

import java.util.List;

import client.poller.Poller;
import client.proxy.ClientCommunicator;
import client.proxy.ProxyServer;
import shared.model.GameModelFacade;
import shared.model.TooManyPlayersException;
import shared.model.board.Board;
import shared.model.player.Player;

public class Controller {

	private GameModelFacade gameModelFacade;
	private Poller poller;
	private ProxyServer proxyServer;
	
	public Controller(int gameID){
		gameModelFacade = new GameModelFacade(gameID);
		poller = new Poller(this);
		proxyServer = new ProxyServer(new ClientCommunicator());
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



	public ProxyServer getProxyServer() {
		return proxyServer;
	}



	public void setProxyServer(ProxyServer proxyServer) {
		this.proxyServer = proxyServer;
	}

	public void updateGame(){
		
	}
}
