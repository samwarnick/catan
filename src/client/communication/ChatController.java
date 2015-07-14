package client.communication;

import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, ModelControllerListener {
	
	private ModelController MC;

	public ChatController(IChatView view) {
		
		super(view);
		MC = ModelController.getInstance();
		MC.addListener(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		//
	}

	@Override
	public void ModelChanged() {
		// TODO Auto-generated method stub
		
	}

}

