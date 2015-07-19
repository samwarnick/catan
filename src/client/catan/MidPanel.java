package client.catan;

import java.awt.*;
import javax.swing.*;

import client.base.IAction;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.map.*;
import client.proxy.ProxyServer;
import server.ServerException;
import shared.communication.input.move.FinishTurnInput;
import shared.model.player.Player;

@SuppressWarnings("serial")
public class MidPanel extends JPanel implements ModelControllerListener
{
	
	private TradePanel tradePanel;
	private MapView mapView;
	private RobView robView;
	private MapController mapController;
	private GameStatePanel gameStatePanel;
	
	public MidPanel()
	{
		this.setLayout(new BorderLayout());
		
		tradePanel = new TradePanel();
		
		mapView = new MapView();
		robView = new RobView();
		mapController = new MapController(mapView, robView);
		mapView.setController(mapController);
		robView.setController(mapController);
		
		gameStatePanel = new GameStatePanel();
		gameStatePanel.setButtonAction(new IAction() {
			
			@Override
			public void execute() {
				ModelController.getInstance().finishTurn();
			}
		});
		
		this.add(tradePanel, BorderLayout.NORTH);
		this.add(mapView, BorderLayout.CENTER);
		this.add(gameStatePanel, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(800, 700));
		
		ModelController.getInstance().addListener(this);
	}
	
	public GameStatePanel getGameStatePanel()
	{
		return gameStatePanel;
	}
	
	public IMapController getMapController()
	{
		return mapController;
	}

	@Override
	public void ModelChanged() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if ( clientPlayer != null && clientPlayer.getPlayerFacade().canFinishTurn()) {
			gameStatePanel.updateGameState("Finish Turn", true);
		}
		else {
			gameStatePanel.updateGameState("Waiting for other Players", false);
		}
	}
	
}

