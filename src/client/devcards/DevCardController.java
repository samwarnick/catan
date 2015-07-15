package client.devcards;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.bank.PlayerBank;
import shared.model.player.Player;
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
		getBuyCardView().closeModal();
		ModelController.getInstance().buyDevCard();
	}

	@Override
	public void startPlayCard() {
		
		// defaults
		getPlayCardView().setCardEnabled(DevCardType.SOLDIER, false);
		getPlayCardView().setCardAmount(DevCardType.SOLDIER, 0);
		getPlayCardView().setCardEnabled(DevCardType.MONUMENT, false);
		getPlayCardView().setCardAmount(DevCardType.MONUMENT, 0);
		getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, false);
		getPlayCardView().setCardAmount(DevCardType.MONOPOLY, 0);
		getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
		getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, 0);
		getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, false);
		getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, 0);
		
		// get info from player
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if ( clientPlayer != null) {
			PlayerBank bank = clientPlayer.getPlayerBank();
			int soldiers = bank.getDevStack(DevCardType.SOLDIER).getQuantity();
			int monuments = bank.getDevStack(DevCardType.MONUMENT).getQuantity();
			int monopoly = bank.getDevStack(DevCardType.MONOPOLY).getQuantity();
			int yearOfPlenty = bank.getDevStack(DevCardType.YEAR_OF_PLENTY).getQuantity();
			int roadBuilding = bank.getDevStack(DevCardType.ROAD_BUILD).getQuantity();
			
			if (soldiers > 0) {
				getPlayCardView().setCardEnabled(DevCardType.SOLDIER, true);
				getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldiers);
			}
			if (monuments > 0) {
				getPlayCardView().setCardEnabled(DevCardType.MONUMENT, true);
				getPlayCardView().setCardAmount(DevCardType.MONUMENT, monuments);
			}
			if (monopoly > 0) {
				getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, true);
				getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopoly);
			}
			if (yearOfPlenty > 0) {
				getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, true);
				getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
			}
			if (roadBuilding > 0) {
				getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, true);
				getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadBuilding);
			}
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

