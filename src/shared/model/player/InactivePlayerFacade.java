package shared.model.player;

import shared.definitions.ResourceType;
import shared.model.bank.ResourceHand;

/**
 * 
 * @author Spencer Krieger
 *
 */
public class InactivePlayerFacade implements IPlayerFacade{

	private Player player;
	public InactivePlayerFacade(Player player){
		this.player = player;
	}
	@Override
	public boolean canBuildCity() {
		return false;
	}

	@Override
	public boolean canBuildSettlement() {
		return false;
	}

	@Override
	public boolean canBuildRoad() {
		return false;
	}

	@Override
	public boolean canPlayCard() {
		return false;
	}

	@Override
	public boolean canTrade(ResourceHand rh) {
		return false;
	}

	@Override
	public boolean isActivePlayer() {
		return false;
	}

	@Override
	public boolean canBuyDevelopmentCard() {
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
		return false;
	}

	@Override
	public boolean canBeRobbed() {
		return player.getPlayerBank().getNumResourceCards() > 0;
	}
	@Override
	public boolean canMaritimeTrade(int amtOutput, ResourceType input, ResourceType output) {
		return false;
	}

}
