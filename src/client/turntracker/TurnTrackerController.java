package client.turntracker;

import shared.definitions.CatanColor;
import shared.model.GameModelFacade;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, ModelControllerListener {
	
	public enum Phase {first, second, playing, discarding, trading};

	int currentPlayerIndex = 0;
	Phase phase = Phase.first;
	

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
		currentPlayerIndex++;
		if(currentPlayerIndex == 4)
		{
			currentPlayerIndex = 0;
			if(phase == Phase.first)
			{
				phase = Phase.second;
			}
			else if(phase == Phase.second)
			{
				phase = Phase.playing;
			}
		}
		initFromModel();
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
			
			// set finish 
			if (clientPlayer.getPlayerFacade().canFinishTurn()) {
				getView().updateGameState("Finish Turn", true);
			}
			else {
				getView().updateGameState("Waiting for other Players", false);
			}
		}
	}
}
