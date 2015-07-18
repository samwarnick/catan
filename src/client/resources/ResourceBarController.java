package client.resources;

import java.util.*;

import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import shared.definitions.ResourceType;
import shared.model.bank.PlayerBank;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Player;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, ModelControllerListener {

	private Map<ResourceBarElement, IAction> elementActions;
	int count = 0;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		ModelController.getInstance().addListener(this);
		elementActions = new HashMap<ResourceBarElement, IAction>();
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

	@Override
	public void ModelChanged() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if ( clientPlayer != null) {
			
			// get each resource and set value and enable buttons

			PlayerBank bank = clientPlayer.getPlayerBank();

			int brick = bank.getResourceStack(ResourceType.BRICK).getQuantity();
			int wood = bank.getResourceStack(ResourceType.WOOD).getQuantity();
			int sheep = bank.getResourceStack(ResourceType.SHEEP).getQuantity();
			int wheat = bank.getResourceStack(ResourceType.WHEAT).getQuantity();
			int ore = bank.getResourceStack(ResourceType.ORE).getQuantity();
			
			getView().setElementAmount(ResourceBarElement.BRICK, brick);
			getView().setElementAmount(ResourceBarElement.WOOD, wood);
			getView().setElementAmount(ResourceBarElement.SHEEP, sheep);
			getView().setElementAmount(ResourceBarElement.WHEAT, wheat);
			getView().setElementAmount(ResourceBarElement.ORE, ore);
			
			// get pieces left
			int roads = clientPlayer.getRoads().getRoadsLeft();
			int settlements = clientPlayer.getSettlements().getSettlementsLeft();
			int cities = clientPlayer.getCities().getCitiesLeft();
			int soldiers = clientPlayer.getArmySize();
			
			getView().setElementAmount(ResourceBarElement.ROAD, roads);
			getView().setElementAmount(ResourceBarElement.SETTLEMENT, settlements);
			getView().setElementAmount(ResourceBarElement.CITY, cities);
			getView().setElementAmount(ResourceBarElement.SOLDIERS, soldiers);
			
			// enable only if canDo are true
			if (clientPlayer.getPlayerFacade().canBuildRoad(false)) {
				getView().setElementEnabled(ResourceBarElement.ROAD, true);
			} else {
				getView().setElementEnabled(ResourceBarElement.ROAD, false);
			}
			if (clientPlayer.getPlayerFacade().canBuildSettlement(false)) {
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
			} else {
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
			}
			if (clientPlayer.getPlayerFacade().canBuildCity(false)) {
				getView().setElementEnabled(ResourceBarElement.CITY, true);
			} else {
				getView().setElementEnabled(ResourceBarElement.CITY, false);
			}
			if (ModelController.getInstance().getGameModelFacade().canBuyDevCard(clientPlayer)) {
				getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
			} else {
				getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			}
			if (clientPlayer.getPlayerFacade().canPlayCard()) {
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD, true);
			} else {
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
			}
		}
	}

}

