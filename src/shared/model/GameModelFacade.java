package shared.model;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.bank.PlayerBank;
import shared.model.player.Player;

public class GameModelFacade {

	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player's canAcceptTrade returns true and the player is being offered trade.  Otherwise returns false
	 */
	public boolean canAcceptTrade(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns whatever the player's canDiscard returns
	 */
	public boolean canDiscardCards(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player is the active player and the client status is rolling otherwise false
	 */
	public boolean canRollDice(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if both the location is valid and the player can build the road otherwise false
	 */
	public boolean canBuildRoad(Player player,EdgeLocation location){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if both the location is valid and the player can build the settlement otherwise false
	 */
	public boolean canBuildSettlement(Player player,VertexLocation location){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if the player can build the city otherwise false
	 */
	public boolean canBuildCity(Player player,VertexLocation location){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @param ResourceHand
	 * @pre none
	 * @post returns true if the player is the active player and has the resource cards they want to trade otherwise false
	 */
	public boolean canOfferTrade(Player player,PlayerBank ResourceHand){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can trade the resources at the desired rate and it is their turn otherwise false
	 */
	public boolean canMaritimeTrade(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if the player has cards to rob and the location is a new one for the robber otherwise false
	 */
	public boolean canRobPlayer(Player player,HexLocation location){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player is the active player otherwise false
	 */
	public boolean canFinishTurn(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can buy a development card and the bank still has them otherwise false
	 */
	public boolean canBuyDevCard(Player player){
		return false;
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can play a development card otherwise false
	 */
	public boolean canPlayDevCard(Player player){
		return false;
	}
}
