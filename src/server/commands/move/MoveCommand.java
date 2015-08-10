package server.commands.move;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import server.ServerException;
import server.commands.ICommand;
import shared.model.GameModel;

@SuppressWarnings("serial")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type", defaultImpl = Default.class)
@JsonSubTypes({
	@JsonSubTypes.Type(value = MoveCommand.class, name = "MoveCommand"),
	@JsonSubTypes.Type(value = AcceptTradeCommand.class, name = "AcceptTradeCommand"),
	@JsonSubTypes.Type(value = BuildCityCommand.class, name = "BuildCityCommand"),
	@JsonSubTypes.Type(value = BuildRoadCommand.class, name = "BuildRoadCommand"),
	@JsonSubTypes.Type(value = BuildSettlementCommand.class, name = "BuildSettlementCommand"),
	@JsonSubTypes.Type(value = BuyDevCardCommand.class, name = "BuyDevCardCommand"),
	@JsonSubTypes.Type(value = DiscardCardsCommand.class, name = "DiscardCardsCommand"),
	@JsonSubTypes.Type(value = FinishTurnCommand.class, name = "FinishTurnCommand"),
	@JsonSubTypes.Type(value = MaritimeTradeCommand.class, name = "MaritimeTradeCommand"),
	@JsonSubTypes.Type(value = OfferTradeCommand.class, name = "OfferTradeCommand"),
	@JsonSubTypes.Type(value = PlayMonopolyCommand.class, name = "PlayMonopolyCommand"),
	@JsonSubTypes.Type(value = PlayMonumentCommand.class, name = "PlayMonumentCommand"),
	@JsonSubTypes.Type(value = PlayRoadBuildingCommand.class, name = "PlayRoadBuildingCommand"),
	@JsonSubTypes.Type(value = PlaySoldierCommand.class, name = "PlaySoldierCommand"),
	@JsonSubTypes.Type(value = PlayYearOfPlentyCommand.class, name = "PlayYearOfPlentyCommand"),
	@JsonSubTypes.Type(value = RobPlayerCommand.class, name = "RobPlayerCommand"),
	@JsonSubTypes.Type(value = RollNumberCommand.class, name = "RollNumberCommand"),
	@JsonSubTypes.Type(value = SendChatCommand.class, name = "SendChatCommand")
})

public abstract class MoveCommand implements ICommand,Serializable {
	@JsonIgnore protected transient GameModel model;
	protected String input;
	
	

	public MoveCommand() {
		model = null;
		input = "";
	}

	@Override
	public abstract Object execute(String input) throws ServerException;
	
	public void setGameModel(GameModel model) {
		this.model = model;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}
	
	
	
}
