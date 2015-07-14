package client.communication;

import shared.communication.input.move.SendChatInput;
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
		MC.sendChat(new SendChatInput(MC.getPlayerID(), message));
	}

	@Override
	public void ModelChanged() {
		getView().setEntries(MC.getGameModelFacade().getGameModel().getChats());
	}

}

