package shared.model;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import shared.model.bank.ResourceHand;

import shared.model.player.Player;

/**
 * 
 * @author Spencer Krieger
 *
 */
public class GameModelFacade {

	private GameModel gameModel;
	public GameModelFacade(int gameid){
		gameModel = new GameModel(gameid);
	}
	
	
	public GameModel getGameModel() {
		return gameModel;
	}


	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}


	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player's canAcceptTrade returns true and the player is being offered trade.  Otherwise returns false
	 */
	public boolean canAcceptTrade(Player player,ResourceHand rh){
		int wood = 0;
		int brick = 0;
		int sheep = 0;
		int wheat = 0;
		int ore = 0;
		if(rh.getWood() >=0) wood = rh.getWood();
		if(rh.getBrick() >= 0) brick = rh.getBrick();
		if(rh.getSheep() >=0) sheep = rh.getSheep();
		if(rh.getWheat() >=0) wheat = rh.getWheat();
		if(rh.getOre() >=0) ore = rh.getOre();
		ResourceHand neededResources = new ResourceHand(wood, brick, sheep, wheat, ore);
		return player.getPlayerFacade().canAcceptTrade(neededResources);
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns whatever the player's canDiscard returns
	 */
	public boolean canDiscardCards(Player player){
		return player.getPlayerFacade().canDiscard();
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player is the active player and the client status is rolling otherwise false
	 */
	public boolean canRollDice(Player player){
		return player.getPlayerFacade().isActivePlayer()
				&& gameModel.getTurnTracker().getStatus().equals("Rolling");
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if both the location is valid and the player can build the road otherwise false
	 */
	public boolean canBuildRoad(Player player,EdgeLocation location,boolean isFree,boolean allowDisconnected){
		return player.getPlayerFacade().canBuildRoad(isFree)
				&& gameModel.getBoard().getBoardFacade().canBuildRoad(player, location, allowDisconnected);
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if both the location is valid and the player can build the settlement otherwise false
	 */
	public boolean canBuildSettlement(Player player,VertexLocation location,boolean isFree,boolean allowDisconnected){
		return player.getPlayerFacade().canBuildSettlement(isFree)
				&& gameModel.getBoard().getBoardFacade().canBuildSettlement(player, location, allowDisconnected);
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if the player can build the city otherwise false
	 */
	public boolean canBuildCity(Player player,VertexLocation location,boolean isFree,boolean allowDisconnected){
		return player.getPlayerFacade().canBuildCity(isFree)
				&& gameModel.getBoard().getBoardFacade().canBuildCity(player, location, allowDisconnected);
	}
	/**
	 * 
	 * @param player
	 * @param ResourceHand
	 * @pre none
	 * @post returns true if the player is the active player and has the resource cards they want to trade otherwise false
	 */
	public boolean canOfferTrade(Player player, ResourceHand rh){
		int wood = 0;
		int brick = 0;
		int sheep = 0;
		int wheat = 0;
		int ore = 0;
		if(rh.getWood() >=0) wood = rh.getWood();
		if(rh.getBrick() >= 0) brick = rh.getBrick();
		if(rh.getSheep() >=0) sheep = rh.getSheep();
		if(rh.getWheat() >=0) wheat = rh.getWheat();
		if(rh.getOre() >=0) ore = rh.getOre();
		ResourceHand neededResources = new ResourceHand(wood, brick, sheep, wheat, ore);
		return player.getPlayerFacade().canTrade(neededResources);
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can trade the resources at the desired rate and it is their turn otherwise false
	 */
	public boolean canMaritimeTrade(Player player, int amtOutput, ResourceType input, ResourceType output){
		return player.getPlayerFacade().canMaritimeTrade(amtOutput, input, output);
	}
	/**
	 * 
	 * @param player
	 * @param location
	 * @pre none
	 * @post returns true if the player has cards to rob and the location is a new one for the robber otherwise false
	 */
	public boolean canRobPlayer(Player player,HexLocation location){
		return player.getPlayerFacade().canBeRobbed()
				&& canPlaceRobber(location);
	}
	/**
	 * 
	 * @param location
	 * @pre none
	 * @post returns true if the location is a new one for the robber otherwise false and current turn status is Robbing
	 */
	public boolean canPlaceRobber(HexLocation location) {
		return gameModel.getBoard().getRobber().getLocation().equals(location)
				&& gameModel.getTurnTracker().getStatus().equals("Robbing");
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player is the active player otherwise false
	 */
	public boolean canFinishTurn(Player player){
		return player.getPlayerFacade().canFinishTurn();
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can buy a development card and the bank still has them otherwise false
	 */
	public boolean canBuyDevCard(Player player){
		return player.getPlayerFacade().canBuyDevelopmentCard()
				&& gameModel.getBank().hasAnyDC();
	}
	/**
	 * 
	 * @param player
	 * @pre none
	 * @post returns true if the player can play a development card otherwise false
	 */
	public boolean canPlayDevCard(Player player){
		return player.getPlayerFacade().canPlayCard();
	}
}
