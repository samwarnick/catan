package client.domestic;

import shared.definitions.*;
import shared.model.board.PlayerID;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private Integer playerIndex;
	private Integer woodNum = 0;
	private Integer brickNum = 0;
	private Integer sheepNum=0;
	private Integer wheatNum = 0;
	private Integer oreNum = 0;
	private Integer playerWood;
	private Integer playerBrick;
	private Integer playerSheep;
	private Integer playerWheat;
	private Integer playerOre;
	private Integer woodStatus = 0;
	private Integer brickStatus = 0;
	private Integer sheepStatus = 0;
	private Integer wheatStatus = 0;
	private Integer oreStatus = 0;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		Player thisPlayer = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayer(new PlayerID(ModelController.getInstance().getPlayerID()));
		playerWood = thisPlayer.getPlayerBank().getWood().getQuantity();
		playerBrick = thisPlayer.getPlayerBank().getBrick().getQuantity();
		playerSheep = thisPlayer.getPlayerBank().getSheep().getQuantity();
		playerWheat = thisPlayer.getPlayerBank().getWheat().getQuantity();
		playerOre = thisPlayer.getPlayerBank().getOre().getQuantity();
		tradeOverlay.setResourceSelectionEnabled(true);
		//TODO
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		//check that it isn't at 0.  If it is, gray out the button.
		switch (resource){
		case BRICK:
			brickNum--;
			tradeOverlay.setResourceAmount(resource, brickNum.toString());
			if (brickNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			break;
		case ORE:
			oreNum--;
			tradeOverlay.setResourceAmount(resource, oreNum.toString());
			if (oreNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			break;
		case SHEEP:
			sheepNum--;
			tradeOverlay.setResourceAmount(resource, sheepNum.toString());
			if (sheepNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			break;
		case WHEAT:
			wheatNum--;
			tradeOverlay.setResourceAmount(resource,wheatNum.toString());
			if (wheatNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			break;
		case WOOD:
			woodNum--;
			tradeOverlay.setResourceAmount(resource, woodNum.toString());
			if (woodNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			break;
		default:
			break;
		
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		//check if it is set to receive or send
		//if it is to send, make sure it can't go above their num.
		switch (resource){
		case BRICK:
			if (brickStatus == 1)
			{
				if (playerBrick > brickNum + 1)
				{
					brickNum++;
					tradeOverlay.setResourceAmount(resource, brickNum.toString());
				}
				if (brickNum == playerBrick){
					tradeOverlay.setResourceAmountChangeEnabled(resource, false, true);
				}
				else
					tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			else
			{
				brickNum++;
				tradeOverlay.setResourceAmount(resource, brickNum.toString());
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			
			
			break;
		case ORE:
			if (oreStatus == 1)
			{
				if (playerOre > oreNum + 1)
				{
					oreNum++;
					tradeOverlay.setResourceAmount(resource, oreNum.toString());
				}
				if (oreNum == playerOre){
					tradeOverlay.setResourceAmountChangeEnabled(resource, false, true);
				}
				else
					tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			else
			{
				oreNum++;
				tradeOverlay.setResourceAmount(resource, oreNum.toString());
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			break;
		case SHEEP:
			if (sheepStatus == 1)
			{
				if (playerSheep > sheepNum + 1)
				{
					sheepNum++;
					tradeOverlay.setResourceAmount(resource, sheepNum.toString());
				}
				if (sheepNum == playerSheep){
					tradeOverlay.setResourceAmountChangeEnabled(resource, false, true);
				}
				else
					tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			else
			{
				sheepNum++;
				tradeOverlay.setResourceAmount(resource, sheepNum.toString());
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			break;
		case WHEAT:
			if (wheatStatus == 1)
			{
				if (playerWheat > wheatNum + 1)
				{
					wheatNum++;
					tradeOverlay.setResourceAmount(resource, wheatNum.toString());
				}
				if (wheatNum == playerWheat){
					tradeOverlay.setResourceAmountChangeEnabled(resource, false, true);
				}
				else
					tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			else
			{
				wheatNum++;
				tradeOverlay.setResourceAmount(resource, wheatNum.toString());
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			break;
		case WOOD:
			if (woodStatus == 1)
			{
				if (playerWood > woodNum + 1)
				{
					woodNum++;
					tradeOverlay.setResourceAmount(resource, woodNum.toString());
				}
				if (woodNum == playerWood){
					tradeOverlay.setResourceAmountChangeEnabled(resource, false, true);
				}
				else
					tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			else
			{
				woodNum++;
				tradeOverlay.setResourceAmount(resource, woodNum.toString());
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			}
			break;
		default:
			break;
		
		}
		//if it is at their num, gray out the button.
	}

	@Override
	public void sendTradeOffer() {

		getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {

		this.playerIndex = playerIndex;
		
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		//change the gui
		switch (resource){
		case BRICK:
			brickStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			//drop down the menu somehow
			break;
		case ORE:
			oreStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			break;
		case SHEEP:
			sheepStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			break;
		case WHEAT:
			wheatStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			break;
		case WOOD:
			woodStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		//change the gui
		switch (resource){
		case BRICK:
			brickStatus = -1;
			if (playerBrick > 0)
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			//drop down the menu somehow
			break;
		case ORE:
			oreStatus = -1;
			if (playerOre > 0)
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			break;
		case SHEEP:
			sheepStatus = -1;
			if (playerSheep > 0)
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			break;
		case WHEAT:
			wheatStatus = -1;
			if (playerWheat > 0)
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			break;
		case WOOD:
			woodStatus = -1;
			if (playerWood > 0)
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void unsetResource(ResourceType resource) {
		//change the gui, change the amount back to 0
		switch (resource){
		case BRICK:
			brickStatus = 0;
			brickNum = 0;
			tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			tradeOverlay.setResourceAmount(resource, "0");
			break;
		case ORE:
			oreStatus = 0;
			oreNum = 0;
			tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			tradeOverlay.setResourceAmount(resource, "0");
			break;
		case SHEEP:
			sheepStatus = 0;
			sheepNum = 0;
			tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			tradeOverlay.setResourceAmount(resource, "0");
			break;
		case WHEAT:
			wheatStatus = 0;
			wheatNum = 0;
			tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			tradeOverlay.setResourceAmount(resource, "0");
			break;
		case WOOD:
			woodStatus = 0;
			woodNum = 0;
			tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
			tradeOverlay.setResourceAmount(resource, "0");
			break;
		default:
			break;
		
		}
	}

	@Override
	public void cancelTrade() {
		//change things back to 0.  done by the start trade.
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		
		getAcceptOverlay().closeModal();
	}

}

