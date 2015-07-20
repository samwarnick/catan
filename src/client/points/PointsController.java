package client.points;

import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import shared.model.player.Player;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, ModelControllerListener {

	private IGameFinishedView finishedView;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		ModelController.getInstance().addListener(this);
		
		setFinishedView(finishedView);
		
		initFromModel();
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {
		getPointsView().setPoints(0);
	}

	@Override
	public void ModelChanged() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if ( clientPlayer != null) {
			getPointsView().setPoints(clientPlayer.getVictoryPoints().getTotalVictoryPoints());
		}
		Player winner = ModelController.getInstance().getGameModelFacade().getWinner();
		if (winner != null) {
			boolean isLocalPlayer = false;
			if (clientPlayer.getName().equals(winner.getName())) {
				isLocalPlayer = true;
			}
			getFinishedView().setWinner(winner.getName(), isLocalPlayer);
			if (!getFinishedView().isModalShowing()) {
				getFinishedView().showModal();
			}
		}
	}
	
}

