package server.commands.move;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import server.ServerException;
import server.commands.ICommand;
import shared.model.GameModel;
import shared.model.board.City;
import shared.model.board.Settlement;

@SuppressWarnings("serial")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value = AcceptTradeCommand.class, name = "accepttradecommand"),
	@Type(value = BuildCityCommand.class, name = "buildcitycommand"),
	@Type(value = BuildRoadCommand.class, name = "buildroadcommand"),
	@Type(value = BuildSettlementCommand.class, name = "buildsettlementcommand"),
	@Type(value = BuyDevCardCommand.class, name = "buydevcardcommand"),
	@Type(value = DiscardCardsCommand.class, name = "discardcardscommand"),
	@Type(value = FinishTurnCommand.class, name = "finishturncommand"),
	@Type(value = MaritimeTradeCommand.class, name = "maritimetradecommand"),
	@Type(value = OfferTradeCommand.class, name = "offertradecommand"),
	@Type(value = PlayMonopolyCommand.class, name = "playmonopolycommand"),
	@Type(value = PlayMonumentCommand.class, name = "playmonumentcommand"),
	@Type(value = PlayRoadBuildingCommand.class, name = "playroadbuildingcommand"),
	@Type(value = PlaySoldierCommand.class, name = "playsoldiercommand"),
	@Type(value = PlayYearOfPlentyCommand.class, name = "playyearofplentycommand"),
	@Type(value = RobPlayerCommand.class, name = "robplayercommand"),
	@Type(value = RollNumberCommand.class, name = "rollnumbercommand"),
	@Type(value = SendChatCommand.class, name = "sendchatcommand")
})
public abstract class MoveCommand implements ICommand,Serializable {
	@JsonIgnore protected transient GameModel model;
	protected String input;

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
