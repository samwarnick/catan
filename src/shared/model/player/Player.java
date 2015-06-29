package shared.model.player;

import shared.model.bank.PlayerBank;
import shared.model.ratios.*;

public class Player {
	
	private PlayerBank playerBank;
	private LongestRoad longestRoad;
	private LargestArmy largestArmy;
	private Color color;
	private Roads roads;
	private Settlements settlements;
	private Cities cities;
	private VictoryPoints victoryPoints;
	private Name name;
	private TradeRatios tradeRatios;
	private PlayerID playerID;
	
	public Player(Color color) {
		this.playerBank = new PlayerBank();
		this.longestRoad = new LongestRoad();
		this.largestArmy = new LargestArmy();
		this.color = color;
		this.roads = new Roads();
		this.settlements = new Settlements();
		this.cities = new Cities();
		this.victoryPoints = new VictoryPoints();
		this.name = new Name();
		this.tradeRatios = new TradeRatios();
		this.playerID = new PlayerID();
	}
	
	
	public boolean hasLongestRoad(){
		return longestRoad.isHasLongestRoad();
	}
	
	public boolean hasLargestArmy(){
		return largestArmy.getHasLargestArmy();
	}
	
	public int getArmySize(){
		return largestArmy.getNumSoldiers();
	}
	
	public int getLongestRoad(){
		return longestRoad.getNumRoads();
	}

	public PlayerBank getPlayerBank() {
		return playerBank;
	}

	public void setPlayerBank(PlayerBank playerBank) {
		this.playerBank = playerBank;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public VictoryPoints getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(VictoryPoints victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
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

	public void setPlayerID(PlayerID playerID) {
		this.playerID = playerID;
	}

	public Roads getRoads() {
		return roads;
	}

	public void setRoads(Roads roads) {
		this.roads = roads;
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
	
	
	

}
