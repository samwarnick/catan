package client.maritime;

import java.util.ArrayList;

import server.ServerException;
import shared.communication.input.move.MaritimeTradeInput;
import shared.definitions.*;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	private ResourceType getResource;
	private ResourceType giveResource;
	private int getAmount;
	private int giveAmount;
	private ModelController controller;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		ArrayList<ResourceType> selected = new ArrayList<ResourceType>();
		Player activePlayer = controller.getGameModelFacade().getGameModel().getPlayer(new PlayerID(controller.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn())); //need to change after find controller
		ResourceType toConvert = ResourceType.WOOD;
		if (controller.getGameModelFacade().getGameModel().getBank().hasRC(new ResourceHand(0,1,0,0,0))){
			
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.BRICK, toConvert))
				selected.add(ResourceType.BRICK);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.WOOD, toConvert))
				selected.add(ResourceType.WOOD);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.SHEEP, toConvert))
				selected.add(ResourceType.SHEEP);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, toConvert))
				selected.add(ResourceType.WHEAT);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.ORE, toConvert))
				selected.add(ResourceType.ORE);
		}
		getTradeOverlay().showGiveOptions((ResourceType[])selected.toArray());

		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		if (getResource != null && giveResource != null)
			try {
				controller.getProxyServer().maritimeTrade(new MaritimeTradeInput(getAmount, giveAmount, getResource, giveResource));
			} catch (ServerException e) {
				e.printStackTrace();
			}
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		unsetGetValue();
		unsetGiveValue();
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		
		getResource = resource;
	}

	@Override
	public void setGiveResource(ResourceType resource) {

		giveResource = resource;
	}

	@Override
	public void unsetGetValue() {

		getResource = null;
	}

	@Override
	public void unsetGiveValue() {

		giveResource = null;
	}

}

