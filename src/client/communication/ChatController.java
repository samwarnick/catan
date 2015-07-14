package client.communication;

import client.base.*;
import client.controller.ModelController.ModelControllerListener;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, ModelControllerListener {

	public ChatController(IChatView view) {
		
		super(view);
		
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		
	}

	@Override
	public void ModelChanged() {
		// TODO Auto-generated method stub
		
	}

}

