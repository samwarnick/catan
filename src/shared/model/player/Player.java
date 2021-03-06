package shared.model.player;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import client.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.model.bank.PlayerBank;
import shared.model.board.PlayerID;
import shared.model.ratios.TradeRatios;

/**
 * 
 * @author Spencer Krieger
 * Represents a player.  Has everything a player would have.
 */
@SuppressWarnings("serial")
public class Player implements Serializable{
	
	private PlayerBank playerBank;
	private LongestRoad longestRoad;
	private LargestArmy largestArmy;
	
	private CatanColor color;
	private Roads roads;
	private Settlements settlements;
	private Cities cities;
	private VictoryPoints victoryPoints;
	private String name;
	private TradeRatios tradeRatios;
	@JsonIgnore private transient IPlayerFacade playerFacade;
	private PlayerID playerID;
	private int uniqueID;
	
	private boolean hasPlayedCard = false;
	private boolean hasDiscared = false;
	
	public Player(CatanColor color, String name, int playerID) {
		this.playerBank = new PlayerBank();
		this.longestRoad = new LongestRoad();
		this.largestArmy = new LargestArmy();
		this.color = color;
		this.roads = new Roads();
		this.settlements = new Settlements();
		this.cities = new Cities();
		this.victoryPoints = new VictoryPoints();
		this.name = name;
		this.tradeRatios = new TradeRatios();
		this.playerID = new PlayerID(playerID);
		this.playerFacade = new InactivePlayerFacade(this); // by default, they are inactive
	}
	
	public Player() {
	}


	@JsonIgnore public IPlayerFacade getPlayerFacade() {
		return playerFacade;
	}


	@JsonIgnore public void setPlayerFacade(IPlayerFacade playerFacade) {
		this.playerFacade = playerFacade;
	}


	@JsonIgnore public boolean hasLongestRoad(){
		return longestRoad.isHasLongestRoad();
	}
	
	@JsonIgnore public boolean hasLargestArmy(){
		return largestArmy.getHasLargestArmy();
	}
	
	@JsonIgnore public int getArmySize(){
		return largestArmy.getNumSoldiers();
	}
	
	public LongestRoad getLongestRoad(){
		return longestRoad;
	}

	public PlayerBank getPlayerBank() {
		return playerBank;
	}

	public void setPlayerBank(PlayerBank playerBank) {
		this.playerBank = playerBank;
	}

	public CatanColor getColor() {
		return color;
	}

	public void setColor(CatanColor color) {
		this.color = color;
	}

	public VictoryPoints getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(VictoryPoints victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TradeRatios getTradeRatios() {
		return tradeRatios;
	}

	public void setTradeRatios(TradeRatios tradeRatios) {
		this.tradeRatios = tradeRatios;
	}

	public PlayerID getPlayerID() {
		return playerID;
	}

	@JsonIgnore public void setPlayerID(int playerID) {
		this.playerID = new PlayerID(playerID);
	}

	public Roads getRoads() {
		return roads;
	}

	public void setRoads(Roads roads) {
		this.roads = roads;
	}
	
	public void buildRoad() throws Exception {
		roads.buildRoad();
		longestRoad.addRoad();
	}

	public Settlements getSettlements() {
		return settlements;
	}

	public void setSettlements(Settlements settlements) {
		this.settlements = settlements;
	}

	public Cities getCities() {
		return cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	public boolean getHasPlayedCard() {
		return hasPlayedCard;
	}

	public void setHasPlayedCard(boolean hasPlayedCard) {
		this.hasPlayedCard = hasPlayedCard;
	}

	public LargestArmy getLargestArmy() {
		return largestArmy;
	}

	public void setLargestArmy(LargestArmy largestArmy) {
		this.largestArmy = largestArmy;
	}

	public void setLongestRoad(LongestRoad longestRoad) {
		this.longestRoad = longestRoad;
	}
	

	public void setPlayerID(PlayerID playerID) {
		this.playerID = playerID;
	}

	public boolean isHasDiscared() {
		return hasDiscared;
	}

	public void setHasDiscared(boolean hasDiscared) {
		this.hasDiscared = hasDiscared;
	}


	@JsonIgnore public boolean getHasDiscarded() {
		return hasDiscared;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	@Override
	public String toString() {
		return "Player [color=" + color + ", name=" + name + ", playerID="
				+ playerID + "]";
	}
	
	@JsonIgnore public PlayerInfo getPlayerInfo() {
		return new PlayerInfo(name, color, playerID.getPlayerid());
	}

}
