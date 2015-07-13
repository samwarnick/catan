package client.turntracker;

import server.ServerException;
import shared.communication.input.move.FinishTurnInput;
import shared.definitions.CatanColor;
import shared.model.GameModelFacade;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.base.*;
import client.proxy.ProxyServer;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	
	public enum Phase {first, second, playing, discarding, trading};

	int currentPlayerIndex = 0;
	Phase phase = Phase.first;
	

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		FinishTurnInput input = new FinishTurnInput(currentPlayerIndex);
		try {
			ProxyServer.getInstance().finishTurn(input);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		getView().updateGameState("Waiting for other players", false);
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
}
