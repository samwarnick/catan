package client.discard;

import shared.communication.input.move.DiscardCardsInput;
import shared.definitions.*;
import shared.model.bank.ResourceHand;
import client.base.*;
import client.misc.*;
import client.proxy.ProxyServer;
import server.ServerException;


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
			break;
		case WOOD:
			int wood = toDiscard.getWood() - 1;
			toDiscard.setWood(wood);
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() - 1;
			toDiscard.setSheep(sheep);
			break;
		case ORE:
			int ore = toDiscard.getOre() - 1;
			toDiscard.setOre(ore);
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() - 1;
			toDiscard.setWheat(wheat);
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
			break;
		case WOOD:
			int wood = toDiscard.getWood() + 1;
			toDiscard.setWood(wood);
			break;
		case SHEEP:
			int sheep = toDiscard.getSheep() + 1;
			toDiscard.setSheep(sheep);
			break;
		case ORE:
			int ore = toDiscard.getOre() + 1;
			toDiscard.setOre(ore);
			break;
		case WHEAT:
			int wheat = toDiscard.getWheat() + 1;
			toDiscard.setWheat(wheat);
		default:
			break;
		}
	}

	@Override
	public void discard() {
		// TODO
		DiscardCardsInput input = new DiscardCardsInput(0, toDiscard);
		try {
			ProxyServer.getInstance().discardCards(input);
			getDiscardView().closeModal();
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
}

