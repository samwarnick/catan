package client.maritime;

import java.util.ArrayList;

import shared.communication.input.move.MaritimeTradeInput;
import shared.definitions.*;
import shared.model.bank.Bank;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import shared.model.ratios.TradeRatio;
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
		Player activePlayer = ModelController.getInstance().getGameModelFacade().getGameModel().getCurrentPlayer(); //need to change after find controller
		ResourceType toConvert = ResourceType.WOOD;
		if (activePlayer.getPlayerFacade() != null){
			
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.BRICK, toConvert))
			{
				selected.add(ResourceType.BRICK);
			}
				
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.WOOD, toConvert))
				selected.add(ResourceType.WOOD);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.SHEEP, toConvert))
				selected.add(ResourceType.SHEEP);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.WHEAT, toConvert))
				selected.add(ResourceType.WHEAT);
			if (activePlayer.getPlayerFacade().canMaritimeTrade(1, ResourceType.ORE, toConvert))
				selected.add(ResourceType.ORE);
		}
		ResourceType[] ResourceArray = new ResourceType[selected.size()];
		for (int i = 0;i < selected.size();i++){
			ResourceArray[i] = selected.get(i);
		}
		tradeOverlay.reset();
		getTradeOverlay().showGiveOptions(ResourceArray);

		getTradeOverlay().setTradeEnabled(false);
		
		getTradeOverlay().showModal();
		
	}

	@Override
	public void makeTrade() {
		Player player = null;
		if (getResource != null && giveResource != null)
			player = ModelController.getInstance().getClientPlayer();
		ModelController.getInstance().maritimeTrade(new MaritimeTradeInput(player.getPlayerID().getPlayerid(), player.getTradeRatios().getTradeRatio(giveResource).getRatio(), getResource, giveResource));
			
		if (getTradeOverlay().isModalShowing())
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
		ResourceHand rh = new ResourceHand(resource);
		if(ModelController.getInstance().getGameModelFacade().getGameModel().getBank().hasRC(rh))
		{
			getResource = resource;
			getTradeOverlay().selectGetOption(resource, 1);
		}
		if(giveResource != null && getResource != null)
		{
			this.getTradeOverlay().setTradeEnabled(true);
		}
		else
		{
			getTradeOverlay().setTradeEnabled(false);
		}
	}

	@Override
	public void setGiveResource(ResourceType resource) {

		giveResource = resource;
		Player Currentplayer = ModelController.getInstance().getGameModelFacade().getGameModel().getCurrentPlayer();
		
		TradeRatio ratio = Currentplayer.getTradeRatios().getTradeRatio(resource);
		if(Currentplayer.getPlayerBank().hasRC(new ResourceHand(resource)))
		{
			giveResource = resource;
		}

		showGetOp();

		getTradeOverlay().selectGiveOption(resource,ratio.getRatio());
		if(giveResource!= null && getResource!= null)
		{
			this.getTradeOverlay().setTradeEnabled(true);
		}
		else
		{
			getTradeOverlay().setTradeEnabled(false);
		}
	}
	
	public void showGetOp()
	{
		Bank bank = ModelController.getInstance().getGameModelFacade().getGameModel().getBank();
		ArrayList<ResourceType> resources = new ArrayList<ResourceType>();
		if (bank.hasRC(new ResourceHand(ResourceType.BRICK))&& !giveResource.equals(ResourceType.BRICK))
			resources.add(ResourceType.BRICK);
		if (bank.hasRC(new ResourceHand(ResourceType.WOOD))&& !giveResource.equals(ResourceType.WOOD))
			resources.add(ResourceType.WOOD);
		if (bank.hasRC(new ResourceHand(ResourceType.SHEEP))&& !giveResource.equals(ResourceType.SHEEP))
			resources.add(ResourceType.SHEEP);
		if (bank.hasRC(new ResourceHand(ResourceType.WHEAT))&& !giveResource.equals(ResourceType.WHEAT))
			resources.add(ResourceType.WHEAT);
		if (bank.hasRC(new ResourceHand(ResourceType.ORE)) && !giveResource.equals(ResourceType.ORE))
			resources.add(ResourceType.ORE);
		ResourceType[] resourceArray = new ResourceType[resources.size()];
		for (int i = 0;i < resources.size();i++){
			resourceArray[i] = resources.get(i);
		}
		getTradeOverlay().showGetOptions(resourceArray);
	}

	@Override
	public void unsetGetValue() {

		getResource = null;
		showGetOp();
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {

		giveResource = null;
		startTrade();
		getTradeOverlay().setTradeEnabled(false);
	}

}

