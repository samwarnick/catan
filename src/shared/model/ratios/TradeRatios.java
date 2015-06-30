package shared.model.ratios;

import java.util.HashMap;

import shared.definitions.ResourceType;

/**
 * 
 * @author Matt
 * This class contains a map of TradeRatio organized by ResourceType.
 * 
 */
public class TradeRatios {

	private HashMap<ResourceType, TradeRatio> tradeRatios;
	
	public TradeRatios() {
		tradeRatios = new HashMap<ResourceType, TradeRatio>();
		
		for (ResourceType type : ResourceType.values()) {
			tradeRatios.put(type, new TradeRatio());
		}
	}

	public HashMap<ResourceType, TradeRatio> getTradeRatios() {
		return tradeRatios;
	}
	
	public int getTradeRatio(ResourceType type) {
		return tradeRatios.get(type).getRatio();
	}
	
	public void setRatio(ResourceType type, int newRatio) throws Exception {
		tradeRatios.get(type).setRatio(newRatio);
	}
}
