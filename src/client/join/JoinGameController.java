package client.join;

import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.communication.input.GamesCreateInput;
import shared.communication.input.GamesJoinInput;
import shared.communication.input.GamesListInput;
import shared.definitions.CatanColor;
import shared.model.GameModelFacade;
import shared.model.board.PlayerID;
import client.base.*;
import client.controller.ModelController;
import client.data.*;
import client.misc.*;
import client.proxy.ProxyServer;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private GameInfo gameInfo;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
		PlayerInfo localPlayer = new PlayerInfo();
		//localPlayer.setColor(GameModelFacade.getInstance().getGameModel().getPlayer(playerId).getColor());
		//localPlayer.setId(GameModelFacade.getInstance().getGameModel().getPlayer(playerId).get);  userId
		//localPlayer.setName(GameModelFacade.getInstance().getGameModel().getPlayer(playerId).getName());
		//localPlayer.setPlayerIndex(playerId.getPlayerid());
		GamesListInput input = new GamesListInput();
		List<client.data.GameInfo> games;
		try {
			games = ProxyServer.getInstance().listGames(input);
			GameInfo[] gameArray = new GameInfo[games.size()];
			for (int i = 0;i < games.size();i++){
				gameArray[i] = games.get(i);
			}
			getJoinGameView().setGames(gameArray, localPlayer);
		} catch (ServerException e) {
			e.printStackTrace();
		}
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		boolean randomTiles = getNewGameView().getRandomlyPlaceHexes();
        boolean randomNumbers = getNewGameView().getRandomlyPlaceNumbers();
        boolean randomPorts = getNewGameView().getUseRandomPorts();
        String title = getNewGameView().getTitle();
		GamesCreateInput input = new GamesCreateInput(title, randomTiles, randomNumbers, randomPorts);
		try {
			GameInfo newGame = ProxyServer.getInstance().createGame(input);
			if(newGame != null)
			{
				GamesJoinInput joinInput = new GamesJoinInput(newGame.getId(), CatanColor.RED);
				ProxyServer.getInstance().joinGame(joinInput);
	    		GamesListInput gamesListInput = new GamesListInput();
	    		List<GameInfo> gamesList = ProxyServer.getInstance().listGames(gamesListInput);
	    		GameInfo[] games = new GameInfo[gamesList.size()];
	    		for (int i = 0; i < gamesList.size(); i++) {
	    			games[i] = gamesList.get(i);
	    		}
	    		getJoinGameView().setGames(games, new PlayerInfo());
	    		getNewGameView().closeModal();
			}
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startJoinGame(GameInfo game) {
		gameInfo = game;
		GamesListInput gameIn = new GamesListInput();
		List<client.data.GameInfo> gameList;
		try {
			gameList = ProxyServer.getInstance().listGames(gameIn);
			int gameIndex =  game.getId();
	        List<PlayerInfo> players = new ArrayList<PlayerInfo>();
	        players = gameList.get(gameIndex).getPlayers();
			for(PlayerInfo p: players)
			{
				if(p != null)
				{
					if(!p.getName().equals(ModelController.getInstance().getPlayerName())) 
					//always returns the same persons ID/ how do i get the current player's id
					{
						CatanColor color = p.getColor();
						getSelectColorView().setColorEnabled(color, false);
					}
				}
			}
		} catch (ServerException e) {
			e.printStackTrace();
		}
       
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {

		int gameID = GameModelFacade.getInstance().getGameModel().getGameID();

		GamesJoinInput input = new GamesJoinInput(gameInfo.getId(), color);

		try {
			if(ProxyServer.getInstance().joinGame(input))
			{
				// for each player give them an index
				// and set the current players color
				// If join succeeded
				//getSelectColorView().setColorEnabled(color, false);
				getSelectColorView().closeModal();
				getJoinGameView().closeModal();
				ModelController.getInstance().startPoller();
				joinAction.execute();
			}
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
}


