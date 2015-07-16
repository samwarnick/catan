package client.domestic;

import java.util.ArrayList;
import java.util.List;

import shared.communication.input.move.OfferTradeInput;
import shared.definitions.*;
import shared.model.GameModelFacade;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;
import shared.model.player.ActivePlayerFacade;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.data.PlayerInfo;
import client.misc.*;
import client.proxy.ProxyServer;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private Integer playerIndex = -1;
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
	private PlayerInfo[] playerinfos;

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
		ModelController.getInstance().addListener(modelListener);
		
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
		List<Player> players = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayers();
		playerinfos = new PlayerInfo[4];
		for (int i = 0;i < players.size();i++){
			Player player = players.get(i);
			playerinfos[i] = new PlayerInfo(player.getName(),player.getColor(),player.getPlayerID().getPlayerid());
		}
		tradeOverlay.setPlayers(playerinfos);
		Player thisPlayer = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayer(new PlayerID(ModelController.getInstance().getPlayerID()));
		playerWood = thisPlayer.getPlayerBank().getWood().getQuantity();
		playerBrick = thisPlayer.getPlayerBank().getBrick().getQuantity();
		playerSheep = thisPlayer.getPlayerBank().getSheep().getQuantity();
		playerWheat = thisPlayer.getPlayerBank().getWheat().getQuantity();
		playerOre = thisPlayer.getPlayerBank().getOre().getQuantity();
		tradeOverlay.setResourceSelectionEnabled(true);
		//TODO
		getTradeOverlay().showModal();
		//startTradeAnswer();
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
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			break;
		case ORE:
			oreNum--;
			tradeOverlay.setResourceAmount(resource, oreNum.toString());
			if (oreNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			break;
		case SHEEP:
			sheepNum--;
			tradeOverlay.setResourceAmount(resource, sheepNum.toString());
			if (sheepNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			break;
		case WHEAT:
			wheatNum--;
			tradeOverlay.setResourceAmount(resource,wheatNum.toString());
			if (wheatNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
			break;
		case WOOD:
			woodNum--;
			tradeOverlay.setResourceAmount(resource, woodNum.toString());
			if (woodNum == 0){
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
			}
			else
				tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
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
			if (brickStatus == -1)
			{
				if (playerBrick >= brickNum + 1)
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
			if (oreStatus == -1)
			{
				if (playerOre >= oreNum + 1)
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
			if (sheepStatus == -1)
			{
				if (playerSheep >= sheepNum + 1)
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
			if (wheatStatus == -1)
			{
				if (playerWheat >= wheatNum + 1)
				{
					wheatNum++;
					System.out.println("Player: " + playerWheat + "\nGame: " + wheatNum);
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
			if (woodStatus == -1)
			{
				if (playerWood >= woodNum + 1)
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
		//update the server to tell the person to show their overlay
		GameModelFacade.getInstance().getGameModel().getTurnTracker().setStatus("Trading");
		ModelController.getInstance().sendTrade(new OfferTradeInput(ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid(),new ResourceHand(brickNum * brickStatus,woodNum * woodStatus,sheepNum * sheepStatus,wheatNum * wheatStatus,oreNum * oreStatus),playerIndex));
		if (getTradeOverlay().isModalShowing())
			getTradeOverlay().closeModal();
		System.out.println(GameModelFacade.getInstance().getGameModel().getTrade().getReceiver() + " " + ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid());

		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {

		this.playerIndex = playerIndex + 1;
		if (this.playerIndex == 4)
			this.playerIndex = 0;
		if ((woodStatus == 1 || brickStatus == 1 || sheepStatus == 1 || wheatStatus == 1 || oreStatus == 1) && (woodStatus == -1 || brickStatus == -1 || sheepStatus == -1 || wheatStatus == -1 || oreStatus == -1))
		{
			if (playerIndex != -1)
			{
				tradeOverlay.setStateMessage("Trade!");
				tradeOverlay.setTradeEnabled(true);
			}
			else
			{
				tradeOverlay.setStateMessage("Pick a Player to Trade With!");
			}
		}
		
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		//change the gui
		switch (resource){
		case BRICK:
			brickStatus = 1;
			tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
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
		if ((woodStatus == 1 || brickStatus == 1 || sheepStatus == 1 || wheatStatus == 1 || oreStatus == 1) && (woodStatus == -1 || brickStatus == -1 || sheepStatus == -1 || wheatStatus == -1 || oreStatus == -1))
		{
			if (playerIndex != -1)
			{
				tradeOverlay.setStateMessage("Trade!");
				tradeOverlay.setTradeEnabled(true);
			}
			else
			{
				tradeOverlay.setStateMessage("Pick a Player to Trade With!");
			}
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
		if ((woodStatus == 1 || brickStatus == 1 || sheepStatus == 1 || wheatStatus == 1 || oreStatus == 1) && (woodStatus == -1 || brickStatus == -1 || sheepStatus == -1 || wheatStatus == -1 || oreStatus == -1))
		{
			if (playerIndex != -1)
			{
				tradeOverlay.setStateMessage("Trade!");
				tradeOverlay.setTradeEnabled(true);
			}
			else
			{
				tradeOverlay.setStateMessage("Pick a Player to Trade With!");
			}
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
		getTradeOverlay().reset();
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		
		getAcceptOverlay().closeModal();
	}
	
	public void startTradeAnswer(){
		acceptOverlay.setController(this);
		acceptOverlay.setPlayerName(GameModelFacade.getInstance().getGameModel().getCurrentPlayer().getName());
		Player thisPlayer = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayer(new PlayerID(ModelController.getInstance().getPlayerID()));
		playerWood = thisPlayer.getPlayerBank().getWood().getQuantity();
		playerBrick = thisPlayer.getPlayerBank().getBrick().getQuantity();
		playerSheep = thisPlayer.getPlayerBank().getSheep().getQuantity();
		playerWheat = thisPlayer.getPlayerBank().getWheat().getQuantity();
		playerOre = thisPlayer.getPlayerBank().getOre().getQuantity();
		ArrayList<ResourceType> Resources = new ArrayList<ResourceType>();
		Resources.add(ResourceType.BRICK);
		Resources.add(ResourceType.WOOD);
		Resources.add(ResourceType.SHEEP);
		Resources.add(ResourceType.WHEAT);
		Resources.add(ResourceType.ORE);
		ArrayList<Integer> statuses = new ArrayList<Integer>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		Trade trade = GameModelFacade.getInstance().getGameModel().getTrade();
		amounts.add(trade.getBrickNum());
		amounts.add(trade.getWoodNum());
		amounts.add(trade.getSheepNum());
		amounts.add(trade.getWheatNum());
		amounts.add(trade.getOreNum());
		statuses.add(trade.getBrickStatus());
		statuses.add(trade.getWoodStatus());
		statuses.add(trade.getSheepStatus());
		statuses.add(trade.getWheatStatus());
		statuses.add(trade.getOreStatus());
		for (int i = 0;i < statuses.size();i++){
			if (statuses.get(i) == 1){
				acceptOverlay.addGetResource(Resources.get(i), amounts.get(i));
			}
			else if (statuses.get(i) == -1){
				acceptOverlay.addGiveResource(Resources.get(i), amounts.get(i));
			}
		}
		/*if (trade.getBrickStatus() == 1)
			getResources.add(ResourceType.BRICK);
		if (trade.getWoodStatus() == 1)
			getResources.add(ResourceType.WOOD);
		if (trade.getSheepStatus() == 1)
			getResources.add(ResourceType.SHEEP);
		if (trade.getWheatStatus() == 1)
			getResources.add(ResourceType.WHEAT);
		if (trade.getOreStatus() == 1)
			getResources.add(ResourceType.ORE);
		if (trade.getBrickStatus() == -1)
			giveResources.add(ResourceType.BRICK);
		if (trade.getWoodStatus() == -1)
			giveResources.add(ResourceType.WOOD);
		if (trade.getSheepStatus() == -1)
			giveResources.add(ResourceType.SHEEP);
		if (trade.getWheatStatus() == -1)
			giveResources.add(ResourceType.WHEAT);
		if (trade.getOreStatus() == -1)
			giveResources.add(ResourceType.ORE);*/
		if (thisPlayer.getPlayerBank().hasRC(new ResourceHand(playerBrick,playerWood,playerSheep,playerWheat,playerOre))){
			acceptOverlay.setAcceptEnabled(true);
		}
		else
			acceptOverlay.setAcceptEnabled(false);
		
		getAcceptOverlay().showModal();
		
	}
	
	private ModelControllerListener modelListener = new ModelControllerListener() {

		@Override
		public void ModelChanged() {
			System.out.println("I'm in the domestic");

			if (ModelController.getInstance().getClientPlayer() != null){

				if (ModelController.getInstance().getClientPlayer().getPlayerFacade().getClass().equals(new ActivePlayerFacade(new Player()).getClass())){
					getTradeView().enableDomesticTrade(true);
				}
				else
					getTradeView().enableDomesticTrade(false);
				if (GameModelFacade.getInstance().getGameModel().getTrade() != null){
					if (ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid()==GameModelFacade.getInstance().getGameModel().getTrade().getReceiver()){
						System.out.println("I'm starting it!");
						startTradeAnswer();
						
					}
				}
				
			}
			
					
		}
		
	};

}

