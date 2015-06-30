package shared.model.player;

import shared.model.bank.PlayerBank;
import shared.model.ratios.TradeRatios;

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
	private int playerID;
	
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
	
	

}
