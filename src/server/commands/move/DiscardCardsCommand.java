package server.commands.move;

import server.commands.ICommand;
import shared.communication.input.Input;
import shared.communication.input.move.DiscardCardsInput;
import shared.model.GameModel;
import shared.model.bank.BankException;
import shared.model.bank.ResourceHand;
import shared.model.board.PlayerID;

public class DiscardCardsCommand implements ICommand{
	
	private GameModel model;

	/**
	 * @pre The player has the discarded resources
	 * @post the resource hand will be discarded from the player's bank and added to the bank.
	 * @return the updated GameModel
	 */
	@Override
	public Object execute(Input input) {
		DiscardCardsInput discardCardsinput = (DiscardCardsInput) input;
		ResourceHand rh = discardCardsinput.getDiscardedCards();
		ResourceHand bankRH = discardCardsinput.getDiscardedCards();
		bankRH.setBrick(rh.getBrick() * -1);
		bankRH.setWood(rh.getWood() * -1);
		bankRH.setSheep(rh.getSheep() * -1);
		bankRH.setWheat(rh.getWheat() * -1);
		bankRH.setOre(rh.getOre() * -1);
		try {
			model.getPlayer(new PlayerID(discardCardsinput.getPlayerIndex())).getPlayerBank().modifyRC(rh);
			model.getBank().modifyRC(bankRH);
			
		} catch (BankException e) {
			e.printStackTrace();
		}
		model.getTurnTracker().setStatus("Playing");
		return model;
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}

}