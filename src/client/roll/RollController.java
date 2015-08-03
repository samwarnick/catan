package client.roll;

import java.util.Random;

import server.ServerException;
import shared.communication.input.move.RollNumberInput;
import shared.model.GameModelFacade;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
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
		ModelController.getInstance().addListener(modelListener);
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
		/*
		while (diceTotal == 7){
			diceTotal = rand.nextInt(6) + rand.nextInt(6) + 2;
		}
		*/
		int currentPlayerIndex = GameModelFacade.getInstance().getGameModel().getTurnTracker().getCurrentTurn();
		RollNumberInput input = new RollNumberInput(currentPlayerIndex, diceTotal);
		ModelController.getInstance().rollDice(input);
		getResultView().setRollValue(diceTotal);
		if (!getResultView().isModalShowing()) {
			getResultView().showModal();
		}
		
		
	}
	private void startTimer(){
		new RollTimer(this);
	}
	
	private ModelControllerListener modelListener = new ModelControllerListener() {

		@Override
		public void ModelChanged() {
			if (GameModelFacade.getInstance().getGameModel().getTurnTracker().getStatus().equals("Rolling") 
					&& ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid() == ModelController.getInstance().getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn()){
				if (!getRollView().isModalShowing()) {
					getRollView().showModal();
					startTimer();
				}
				
			} else if (getRollView().isModalShowing()) {
				getRollView().closeModal();
			} else if (getResultView().isModalShowing()) {
				getResultView().closeModal();
			}
			
		}
	};

}


