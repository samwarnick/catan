package client.discard;

import shared.definitions.*;
import shared.model.GameModelFacade;
import shared.model.bank.PlayerBank;
import shared.model.bank.ResourceHand;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, ModelControllerListener {

	private IWaitView waitView;
	private ResourceHand toDiscard;
	private int numToDiscard;
	private int numCards;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		ModelController.getInstance().addListener(this);
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
			int brick = toDiscard.getBrick() + 1;
			toDiscard.setBrick(brick);
			getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brick);
			numCards++;
			break;
		case WOOD:
			int wood = toDiscard.getWood() + 1;
			toDiscard.setWood(wood);
			getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, wood);
			numCards++;
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() + 1;
			toDiscard.setSheep(sheep);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheep);
			numCards++;
			break;
		case ORE:
			int ore = toDiscard.getOre() + 1;
			toDiscard.setOre(ore);
			getDiscardView().setResourceDiscardAmount(ResourceType.ORE, ore);
			numCards++;
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() + 1;
			toDiscard.setWheat(wheat);
			getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheat);
			numCards++;
			break;
		default:
			break;
		}
		
		getDiscardView().setStateMessage(String.format("%d/%d", numCards, numToDiscard));
		if (numCards == numToDiscard) {
			getDiscardView().setDiscardButtonEnabled(true);
		}
		else
		{
			getDiscardView().setDiscardButtonEnabled(false);
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		switch (resource) {
		case BRICK:
			int brick = toDiscard.getBrick() - 1;
			toDiscard.setBrick(brick);
			getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brick);
			numCards--;
			break;
		case WOOD:
			int wood = toDiscard.getWood() - 1;
			toDiscard.setWood(wood);
			getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, wood);
			numCards--;
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() - 1;
			toDiscard.setSheep(sheep);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheep);
			numCards--;
			break;
		case ORE:
			int ore = toDiscard.getOre() - 1;
			toDiscard.setOre(ore);
			getDiscardView().setResourceDiscardAmount(ResourceType.ORE, ore);
			numCards--;
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() - 1;
			toDiscard.setWheat(wheat);
			getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheat);
			numCards--;
			break;
		default:
			break;
		}
		
		getDiscardView().setStateMessage(String.format("%d/%d", numCards, numToDiscard));
		if (numCards == numToDiscard) {
			getDiscardView().setDiscardButtonEnabled(true);
		}
		else
		{
			getDiscardView().setDiscardButtonEnabled(false);
		}
	}

	@Override
	public void discard() {
		getDiscardView().closeModal();
		ModelController.getInstance().discard(toDiscard);
	}

	@Override
	public void ModelChanged() {
		GameModelFacade facade = ModelController.getInstance().getGameModelFacade();
		if (facade != null) {
			String status = facade.getGameModel().getTurnTracker().getStatus();
			if (status.equals("Discarding")) {
				if (ModelController.getInstance().getClientPlayer().getPlayerFacade().canDiscard() && !getDiscardView().isModalShowing()) {
					toDiscard = new ResourceHand(0, 0, 0, 0, 0);
					setUpDiscardView();
					// show discard view if they can discard
					if (!getDiscardView().isModalShowing()) {
						getDiscardView().showModal();
					}
				} else {
					// show wait view if they cannot discard
					if (!getWaitView().isModalShowing()) {
						getWaitView().showModal();
					}
				}
			}
			else {
				if (getWaitView().isModalShowing()) {
					getWaitView().closeModal();
				}
			}
		}
	}
	
	private void setUpDiscardView() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		PlayerBank bank = clientPlayer.getPlayerBank();
		int brick = bank.getResourceStack(ResourceType.BRICK).getQuantity();
		int wood = bank.getResourceStack(ResourceType.WOOD).getQuantity();
		int sheep = bank.getResourceStack(ResourceType.SHEEP).getQuantity();
		int wheat = bank.getResourceStack(ResourceType.WHEAT).getQuantity();
		int ore = bank.getResourceStack(ResourceType.ORE).getQuantity();
		int total = brick+wood+sheep+wheat+ore;
		numToDiscard = total/2;
		numCards = 0;
		
		getDiscardView().setResourceMaxAmount(ResourceType.WOOD, wood);
		getDiscardView().setResourceMaxAmount(ResourceType.BRICK, brick);
		getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, sheep);
		getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, wheat);
		getDiscardView().setResourceMaxAmount(ResourceType.ORE, ore);
		
		getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, 0);
		getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, 0);
		getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, 0);
		getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, 0);
		getDiscardView().setResourceDiscardAmount(ResourceType.ORE, 0);

		getDiscardView().setStateMessage("");
		getDiscardView().setStateMessage(String.format("%d/%d", numCards, numToDiscard));
		getDiscardView().setDiscardButtonEnabled(false);
	}
}

