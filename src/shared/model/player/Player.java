package shared.model.player;

import shared.model.bank.PlayerBank;

import shared.model.ratios.TradeRatios;

/**
 * 
 * @author Spencer Krieger
 * Represents a player.  Has everything a player would have.
 */
public class Player {
	
	private PlayerBank playerBank;
	private LongestRoad longestRoad;
	private LargestArmy largestArmy;
	
	private Color color;
	private Roads roads;
	private Settlements settlements;
	private Cities cities;
	private VictoryPoints victoryPoints;
	private String name;
	private TradeRatios tradeRatios;
	private IPlayerFacade playerFacade;
	private int playerID;


	private boolean hasPlayedCard;

	
	public Player(Color color, String name, int playerID) {
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
		this.playerID = playerID;
	}
	
	
	public IPlayerFacade getPlayerFacade() {
		return playerFacade;
	}


	public void setPlayerFacade(IPlayerFacade playerFacade) {
		this.playerFacade = playerFacade;
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

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
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


	public boolean getHasPlayedCard() {
		return hasPlayedCard;
	}


	public void setHasPlayedCard(boolean hasPlayedCard) {
		this.hasPlayedCard = hasPlayedCard;
	}
	
	
	

}
