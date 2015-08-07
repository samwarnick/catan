package shared.model.player;

import java.io.Serializable;

import shared.definitions.ResourceType;
import shared.model.bank.ResourceHand;
/**
 * 
 * @author Spencer Krieger
 *
 */
@SuppressWarnings("serial")
public class ActivePlayerFacade implements IPlayerFacade,Serializable{

	private Player player;
	
	public ActivePlayerFacade(Player player){
		this.player = player;
	}
	
	@Override
	public boolean canBuildCity(boolean isFree) {
		if (player.getCities().getCitiesLeft() > 0) {
			if (player.getPlayerBank().hasRC(new ResourceHand(0,0,0,2,3)) || isFree)
				return true;
		}
		return false;
	}

	@Override
	public boolean canBuildSettlement(boolean isFree) {
		if (player.getSettlements().getSettlementsLeft() > 0) {
			if (isFree || player.getPlayerBank().hasRC(new ResourceHand(1,1,1,1,0))) {
				return true;				
			}
		}
		return false;
	}

	@Override
	public boolean canBuildRoad(boolean isFree) {
		if (player.getRoads().getRoadsLeft() > 0) {
			if (player.getPlayerBank().hasRC(new ResourceHand(1,1,0,0,0)) || isFree) {
				return true;	
			}
		}
		return false;
	}

	@Override
	public boolean canPlayCard() {
		return !player.getHasPlayedCard();
	}

	@Override
	public boolean canTrade(ResourceHand rh) {
		return player.getPlayerBank().hasRC(rh);
	}

	@Override
	public boolean isActivePlayer() {
		return true;
	}

	@Override
	public boolean canBuyDevelopmentCard() {
		if (player.getPlayerBank().hasRC(new ResourceHand(0,0,1,1,1)))
			return true;
		else
			return false;
	}

	@Override
	public boolean canAcceptTrade(ResourceHand rh) {
		if (player.getPlayerBank().hasRC(rh))
			return true;
		else
			return false;
	}

	@Override
	public boolean canDiscard() {
		return (player.getPlayerBank().getNumResourceCards() > 7);
	}

	@Override
	public boolean canFinishTurn() {
		return true;
	}

	@Override
	public boolean canBeRobbed() {
		return false;
	}

	@Override
	public boolean canMaritimeTrade(int amtOutput, ResourceType input, ResourceType output) {
		int ratio = player.getTradeRatios().getTradeRatio(input).getRatio();
		ResourceHand rh = null;
		switch(input){
		case BRICK:	rh = new ResourceHand(ratio*amtOutput,0,0,0,0);
		break;
		case WOOD:	rh = new ResourceHand(0,ratio*amtOutput,0,0,0);
		break;
		case SHEEP:	rh = new ResourceHand(0,0,ratio*amtOutput,0,0);
		break;
		case WHEAT:	rh = new ResourceHand(0,0,0,ratio*amtOutput,0);
		break;
		case ORE:	rh = new ResourceHand(0,0,0,0,ratio*amtOutput);
		break;
		default: 	assert false;
		}
		
		return player.getPlayerBank().hasRC(rh);
	}


}
