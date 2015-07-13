package client.roll;

import java.util.Random;

import server.ServerException;
import shared.communication.input.move.RollNumberInput;
import shared.model.GameModelFacade;
import client.base.*;
import client.proxy.ProxyServer;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	//
	@Override
	public void rollDice() {
		Random rand = new Random();
		int  diceTotal = rand.nextInt(6) + rand.nextInt(6) + 2;
		int currentPlayerIndex = GameModelFacade.getInstance().getGameModel().getTurnTracker().getCurrentTurn();
		RollNumberInput input = new RollNumberInput(currentPlayerIndex ,diceTotal);
		try {
			ProxyServer.getInstance().rollNumber(input);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getResultView().setRollValue(diceTotal);
		getResultView().showModal();
	}

}


