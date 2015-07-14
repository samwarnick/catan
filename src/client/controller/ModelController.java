package client.controller;

import java.util.ArrayList;
import java.util.List;

import client.login.LoginController;
import client.maritime.MaritimeTradeController;
import client.maritime.MaritimeTradeView;
import client.poller.Poller;
import client.proxy.ProxyServer;
import server.IServer;
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
	private GameModelFacade gameModelFacade;
	private Poller poller;

	private int PlayerID;
	private static ModelController instance = null;
	private boolean testing = false;
	
	private List<ModelControllerListener> listeners = new ArrayList<ModelControllerListener>();


	
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
		maritimeController.getTradeView().enableMaritimeTrade(gameModelFacade.canFinishTurn(gameModelFacade.getGameModel().getPlayer(new PlayerID(PlayerID))));
		notifyListeners();
		
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
	
	public void maritimeTrade(MaritimeTradeInput input){
		try {
			gameModelFacade.setGameModel(ProxyServer.getInstance().maritimeTrade(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public void notifyListeners(){
		for(ModelControllerListener M: listeners){
			M.ModelChanged();
		}
	}
	
	public void addListener(ModelControllerListener e){
		listeners.add(e);
	}

	public interface ModelControllerListener {
		
		public void ModelChanged();

	}
	
}
