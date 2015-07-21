package client.turntracker;

import shared.definitions.CatanColor;
import shared.model.GameModelFacade;
import shared.model.board.PlayerID;
import shared.model.player.Player;

import java.util.List;

import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, ModelControllerListener {
	
	public enum Phase {first, second, playing, discarding, trading};

	int currentPlayerIndex = 0;
	int numberOfPlayers = 0;
	Phase phase = Phase.first;
	boolean initialized = false;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		ModelController.getInstance().addListener(this);
		
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		ModelController.getInstance().finishTurn();
		// currentPlayerIndex++;
//		if(currentPlayerIndex == 4)
//		{
//			currentPlayerIndex = 0;
//			if(phase == Phase.first)
//			{
//				phase = Phase.second;
//			}
//			else if(phase == Phase.second)
//			{
//				phase = Phase.playing;
//			}
//		}
//		initFromModel();
		// getView().updateGameState("Waiting for other players", false);
	}
	
	public void changePhase(Phase newPhase)
	{
		phase = newPhase;
	}
	
	private void initFromModel() {
       // if(Facade.getInstance().getPregameState().equals(PreGameState.PLAYING)) //phase == Phase.first / Phase.second
      //  {
        	//int currentTurnIndex = ProxyServer.getInstance().getGamemodel().getTurnTracker().getCurrentTurn();
        	getView().updatePlayer(currentPlayerIndex,0,true,false,false);
        	
            getView().updateGameState("First Round",false);
            int playerId = GameModelFacade.getInstance().getGameModel().getTurnTracker().getCurrentTurn();
            Player currentPlayer = GameModelFacade.getInstance().getGameModel().getPlayer(new PlayerID(playerId));
            CatanColor color = currentPlayer.getColor();
            getView().setLocalPlayerColor(color);
     //   }
	}

	@Override
	public void ModelChanged() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if (clientPlayer != null) {
			currentPlayerIndex = ModelController.getInstance().getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn();
			// set title bar color
			getView().setLocalPlayerColor(clientPlayer.getColor());
			
			String status = ModelController.getInstance().getGameModelFacade().getGameModel().getTurnTracker().getStatus();
			if (status.equals("Playing")) {
				changePhase(Phase.playing);
			}
			if (status.equals("Discarding")) {
				changePhase(Phase.discarding);
			}
			if (status.equals("FirstRound")) {
				changePhase(Phase.first);
			}
			if (status.equals("SecondRound")) {
				changePhase(Phase.second);
			}
			if (status.equals("Trading")) {
				changePhase(Phase.trading);
			}
			
			// set finish 
			if (status.equals("Rolling")) {
				getView().updateGameState("Roll the Dice", false);
			}
			else if (clientPlayer.getPlayerFacade().canFinishTurn() && phase != Phase.first && phase != Phase.second) {
				getView().updateGameState("Finish Turn", true);
			}
			else {
				getView().updateGameState("Waiting for other Players", false);
			}
			
			List<Player> players = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayers();
			int numPlayers = 0;
			for (Player p: players) {
				if (p != null) {
					numPlayers++;
				}
			}
			
			if(numPlayers == 4 && !initialized)
			{
				initializePlayers(players);
				initialized = true;
			}
			if (initialized) {
				updatePlayers(players, clientPlayer, currentPlayerIndex);
			}
		}
	}
	
	private void initializePlayers(List<Player> players) {
		
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if(p != null)
			{
				getView().initializePlayer(i, p.getName(), p.getColor());
			}
		}
	}
	
	private void updatePlayers(List<Player> players, Player clientPlayer, int currentPlayerIndex) {
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if(p != null)
			{
				int victoryPoints = p.getVictoryPoints().getPublicVictoryPoints();
				if (clientPlayer.getName().equals(p.getName())) {
					victoryPoints = p.getVictoryPoints().getTotalVictoryPoints();
				}
				boolean highlight = false;
				if (i == currentPlayerIndex) {
					highlight = true;
				}
				getView().updatePlayer(i, victoryPoints, highlight, p.hasLargestArmy(), p.hasLongestRoad());
			}
		}
	}
}
