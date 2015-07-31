package shared.model.ratios;

import shared.definitions.ResourceType;

/**
 * 
 * @author Matt
 * This class contains a ratio for maritime trade.
 * 
 */
public class TradeRatio {

	private int ratio;
	private ResourceType type;
	
	public TradeRatio(ResourceType type) {
		ratio = 4;
		this.type = type;
	}
	
	public TradeRatio(ResourceType type, int ratio) throws Exception {
		this.type = type;
		setRatio(ratio);
	}

	public ResourceType getType() {
		return type;
	}
	
	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) throws Exception {
		if (ratio <= 4 && ratio >= 2) {
			this.ratio = ratio;
		}
		else
			throw new Exception("Invalid trade ratio");
	}
	
	public String toString() {
		return String.format("%d:1", ratio);
	}
}
