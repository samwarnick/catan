package client.main;

import javax.swing.*;

import shared.communication.input.GameModelVersionInput;
import client.catan.*;
import client.controller.ModelController;
import client.login.*;
import client.join.*;
import client.misc.*;
import client.proxy.ProxyServer;
import client.base.*;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	
	private CatanPanel catanPanel;
	private static ProxyServer ps;
	
	public Catan()
	{
		
		client.base.OverlayView.setWindow(this);
		
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		catanPanel = new CatanPanel();
		this.setContentPane(catanPanel);
		
		display();
	}
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		System.setProperty("sun.zip.disableMemoryMapping", "true");
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				if(args.length==2){
					ps = ProxyServer.getInstance(args[0], Integer.parseInt(args[1]));
				}
				else{
					ps = ProxyServer.getInstance();
				}
				
				new Catan();
				
				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
																									playerWaitingView);
				playerWaitingView.setController(playerWaitingController);
				
				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(
																				 joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView);
				ModelController.getInstance().setJGC(joinController);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						ModelController.getInstance().getGameModelVersion(new GameModelVersionInput(-1));
						ModelController.getInstance().startPoller();
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);
				
				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(
																	  loginView,
																	  loginMessageView);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				
				loginController.start();
			}
		});
	}
	
}

