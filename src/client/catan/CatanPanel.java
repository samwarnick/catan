package client.catan;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import shared.definitions.ResourceType;
import shared.model.bank.BankException;
import shared.model.bank.PlayerBank;
import shared.model.bank.ResourceHand;
import shared.model.player.Player;
import client.controller.ModelController;
import client.discard.DiscardController;
import client.discard.DiscardView;
import client.misc.WaitView;
import client.roll.RollController;
import client.roll.RollResultView;
import client.roll.RollView;

@SuppressWarnings("serial")
public class CatanPanel extends JPanel
{
	private TitlePanel titlePanel;
	private LeftPanel leftPanel;
	private MidPanel midPanel;
	private RightPanel rightPanel;
	
	private DiscardView discardView;
	private WaitView discardWaitView;
	private DiscardController discardController;
	
	private RollView rollView;
	private RollResultView rollResultView;
	private RollController rollController;
	
	public CatanPanel()
	{
		this.setLayout(new BorderLayout());
		
		titlePanel = new TitlePanel();
		midPanel = new MidPanel();
		leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel());
		rightPanel = new RightPanel(midPanel.getMapController());
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
		discardView = new DiscardView();
		discardWaitView = new WaitView();
		discardWaitView.setMessage("Waiting for other Players to Discard");
		discardController = new DiscardController(discardView, discardWaitView);
		discardView.setController(discardController);
		discardWaitView.setController(discardController);
		
		rollView = new RollView();
		rollResultView = new RollResultView();
		rollController = new RollController(rollView, rollResultView);
		rollView.setController(rollController);
		rollResultView.setController(rollController);
		
//		JButton testButton = new JButton("Test");
//		testButton.addActionListener(new ActionListener() {
			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 new client.points.GameFinishedView().showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 rollView.showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(java.awt.event.ActionEvent
//			 e) {
//			
//			 midPanel.getMapController().startMove(PieceType.ROBBER,
//			 false, false);
//			 }
			
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
////				rollView.showModal();
//				
//				Player clientPlayer = ModelController.getInstance().getClientPlayer();
//				if ( clientPlayer != null && clientPlayer.getPlayerFacade().canDiscard()) {
//					
//					PlayerBank bank = clientPlayer.getPlayerBank();
//					int brick = bank.getResourceStack(ResourceType.BRICK).getQuantity();
//					int wood = bank.getResourceStack(ResourceType.WOOD).getQuantity();
//					int sheep = bank.getResourceStack(ResourceType.SHEEP).getQuantity();
//					int wheat = bank.getResourceStack(ResourceType.WHEAT).getQuantity();
//					int ore = bank.getResourceStack(ResourceType.ORE).getQuantity();
//					
//					discardView = new DiscardView();
//					discardView.setController(new DiscardController(discardView, discardWaitView));
//					
//					discardView.setResourceMaxAmount(ResourceType.WOOD, wood);
//					discardView.setResourceMaxAmount(ResourceType.BRICK, brick);
//					discardView.setResourceMaxAmount(ResourceType.SHEEP, sheep);
//					discardView.setResourceMaxAmount(ResourceType.WHEAT, wheat);
//					discardView.setResourceMaxAmount(ResourceType.ORE, ore);
//
//					discardView.setStateMessage(String.format("%d/%d", 0, ((brick+wood+sheep+wheat+ore)/2)));
//					
//					discardView.showModal();
//				}
//				else 
//				{
//					discardWaitView.showModal();
//				}
//			}
//		});
//		this.add(testButton, BorderLayout.SOUTH);
	}
	
}

