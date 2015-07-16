package client.discard;

import shared.definitions.*;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	private ResourceHand toDiscard;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		view.setDiscardButtonEnabled(false);
		toDiscard = new ResourceHand(0, 0, 0, 0, 0);
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		switch (resource) {
		case BRICK:
			int brick = toDiscard.getBrick() - 1;
			toDiscard.setBrick(brick);
			getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, -brick);
			break;
		case WOOD:
			int wood = toDiscard.getWood() - 1;
			toDiscard.setWood(wood);
			getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, -wood);
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() - 1;
			toDiscard.setSheep(sheep);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, -sheep);
			break;
		case ORE:
			int ore = toDiscard.getOre() - 1;
			toDiscard.setOre(ore);
			getDiscardView().setResourceDiscardAmount(ResourceType.ORE, -ore);
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() - 1;
			toDiscard.setWheat(wheat);
			getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, -wheat);
		default:
			break;
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		switch (resource) {
		case BRICK:
			int brick = toDiscard.getBrick() + 1;
			toDiscard.setBrick(brick);
			getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, -brick);
			break;
		case WOOD:
			int wood = toDiscard.getWood() + 1;
			toDiscard.setWood(wood);
			getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, -wood);
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() + 1;
			toDiscard.setSheep(sheep);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, -sheep);
			break;
		case ORE:
			int ore = toDiscard.getOre() + 1;
			toDiscard.setOre(ore);
			getDiscardView().setResourceDiscardAmount(ResourceType.ORE, -ore);
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() + 1;
			toDiscard.setWheat(wheat);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, -wheat);
			break;
		default:
			break;
		}
	}

	@Override
	public void discard() {
		ModelController.getInstance().discard(toDiscard);
	}
}

