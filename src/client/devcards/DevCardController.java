package client.devcards;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.bank.DevelopmentHand;
import client.base.*;
import client.controller.ModelController;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		ModelController.getInstance().buyDevCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		
		System.out.println("start play dev card");
		
		int soldiers = ModelController.getInstance().getClientPlayer().getPlayerBank().getDevStack(DevCardType.SOLDIER).getQuantity();
		int monuments = ModelController.getInstance().getClientPlayer().getPlayerBank().getDevStack(DevCardType.MONUMENT).getQuantity();
		int monopoly = ModelController.getInstance().getClientPlayer().getPlayerBank().getDevStack(DevCardType.MONOPOLY).getQuantity();
		int yearOfPlenty = ModelController.getInstance().getClientPlayer().getPlayerBank().getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity();
		int roadBuild = ModelController.getInstance().getClientPlayer().getPlayerBank().getDevStack(DevCardType.ROAD_BUILD).getQuantity();
		
		getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldiers);
		getPlayCardView().setCardAmount(DevCardType.MONUMENT, monuments);
		getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopoly);
		getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
		getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadBuild);
		
		if (soldiers > 0) {
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER, true);
		} else {
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER, false);
		}
		if (monuments > 0) {
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT, true);
		} else {
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT, false);
		}
		if (monopoly > 0) {
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, true);
		} else {
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, false);
		}
		if (yearOfPlenty > 0) {
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, true);
		} else {
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
		}
		if (roadBuild > 0) {
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, true);
		} else {
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, false);
		}
		
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		ModelController.getInstance().playMonopoly(resource);
	}

	@Override
	public void playMonumentCard() {
		ModelController.getInstance().playMonument();
	}

	@Override
	public void playRoadBuildCard() {
		
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		ModelController.getInstance().playYearOfPlenty(resource1, resource2);
	}

}

