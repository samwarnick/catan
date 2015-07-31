package shared.model.ratios;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
			tradeRatios.put(type, new TradeRatio(type));
		}
	}

	public HashMap<ResourceType, TradeRatio> getTradeRatios() {
		return tradeRatios;
	}
	
	@JsonIgnore public TradeRatio getTradeRatio(ResourceType type) {
		return tradeRatios.get(type);
	}
	
	public void setTradeRatios(HashMap<ResourceType, TradeRatio> tradeRatios) {
		this.tradeRatios = tradeRatios;
	}

	public void setRatio(TradeRatio newRatio) {
		tradeRatios.put(newRatio.getType(), newRatio);
	}
}
