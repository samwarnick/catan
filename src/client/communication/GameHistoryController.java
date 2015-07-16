package client.communication;

import java.util.*;

import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, ModelControllerListener {

	private ModelController MC;
	
	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		MC = ModelController.getInstance();
		MC.addListener(this);
		//initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
//	private void initFromModel() {
//		
//		//<temp>
//		getView().setEntries(entries);
//	
//		//</temp>
//	}

	@Override
	public void ModelChanged() {
		getView().setEntries(MC.getGameModelFacade().getGameModel().getLogs());
		
	}
	
}

