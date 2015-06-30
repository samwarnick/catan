package shared.communication.input;

import java.util.ArrayList;

import shared.model.bank.ResourceCard;
import shared.model.ratios.TradeRatio;

/**
 * @author isaachartung
 *
 */
public class MoveMaritimeTrade extends MoveInput {
	
	private TradeRatio ratio;
	private ArrayList<ResourceCard> give;
	private ArrayList<ResourceCard> get;
	
	
	public MoveMaritimeTrade(int playerID, TradeRatio ratio, ArrayList<ResourceCard> give, ArrayList<ResourceCard> get){
		super("/moves/maritimeTrade", playerID);
		this.ratio = ratio;
		this.give = give;
		this.get = get;
	}


	public TradeRatio getRatio() {
		return ratio;
	}


	public ArrayList<ResourceCard> getGive() {
		return give;
	}


	public ArrayList<ResourceCard> getGet() {
		return get;
	}


	public void setRatio(TradeRatio ratio) {
		this.ratio = ratio;
	}


	public void setGive(ArrayList<ResourceCard> give) {
		this.give = give;
	}


	public void setGet(ArrayList<ResourceCard> get) {
		this.get = get;
	}
	




}
