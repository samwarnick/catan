package client.map;

import shared.communication.input.move.*;
import shared.definitions.*;
import shared.locations.*;
import shared.model.GameModelFacade;
import shared.model.board.*;
import shared.model.player.Player;
import client.data.*;
import client.proxy.ProxyServer;
import server.ServerException;

import java.util.ArrayList;
import java.util.List;

import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;


/**
 * Implementation for the map controller
 */
public class MapController extends client.base.Controller implements IMapController, ModelControllerListener {
	
	private IRobView robView;
	private boolean isFree = false;
	private boolean allowDisconnected = false;
	private boolean isSettingUp = false;
	private VertexLocation settlement = null;
	private boolean isRobbing = false;
	private HexLocation robber = null;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
		
		initFromModel();
		ModelController.getInstance().addListener(this);
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	public void initFromModel() {
		Board board = ModelController.getInstance().getGameModelFacade().getGameModel().getBoard();
		
		if (board != null) {
			//resource hexes
			for (ResourceHex hex : board.getResourceHexes()) {
				getView().addHex(hex.getLocation(), hex.getLandType());
				getView().addNumber(hex.getLocation(), hex.getNumberToken());
			}
			//ports
			for (PortHex port : board.getPorts()) {
				getView().addPort(new EdgeLocation(port.getLocation(), port.getOrientation()), port.getPortType());
			}
			//water hexes
			for (WaterHex hex : board.getWaterHexes()) {
				getView().addHex(hex.getLocation(), hex.getLandType());
			}
			if (board.getRoads() != null) {
				//roads
				for (Road road : board.getRoads()) {
					CatanColor color = ModelController.getInstance().getGameModelFacade().getGameModel()
							.getPlayer(road.getOwner()).getColor();
					getView().placeRoad(road.getLocation(), color);
				} 
			}
			if (board.getBuildings() != null) {
				//buildings
				for (Vertex building : board.getBuildings()) {
					CatanColor color = ModelController.getInstance().getGameModelFacade().getGameModel()
							.getPlayer(building.getOwner()).getColor();
					if (building.getClass() == Settlement.class) {
						getView().placeSettlement(building.getLocation(), color);
					} else if (building.getClass() == City.class) {
						getView().placeCity(building.getLocation(), color);
					}
				} 
			}
			//desert and robber
			if (board.getDesertHex() != null) {
				getView().addHex(board.getDesertHex().getLocation(), board.getDesertHex().getLandType());				
			}
			if (board.getRobber() != null) {
				getView().placeRobber(board.getRobber().getLocation());				
			}
		}
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return ModelController.getInstance().getGameModelFacade().canBuildRoad(ModelController.getInstance().getClientPlayer(), edgeLoc, isFree, allowDisconnected, isSettingUp, settlement);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return ModelController.getInstance().getGameModelFacade().canBuildSettlement(ModelController.getInstance().getClientPlayer(), vertLoc, isFree, allowDisconnected);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		return ModelController.getInstance().getGameModelFacade().canBuildCity(ModelController.getInstance().getClientPlayer(), vertLoc, isFree, allowDisconnected);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return ModelController.getInstance().getGameModelFacade().canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {			
		int id = ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid();
		BuildRoadInput input = new BuildRoadInput(id, isFree, edgeLoc);
		ModelController.getInstance().buildRoad(input);
		
		isFree = false;
		allowDisconnected = false;
		if (isSettingUp) {
			FinishTurnInput endInput = new FinishTurnInput(ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid());
			try {
				ProxyServer.getInstance().finishTurn(endInput);
			} catch (ServerException e) {
				e.printStackTrace();
			}
			isSettingUp = false;
			settlement = null;
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		int id = ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid();
		BuildSettlementInput input = new BuildSettlementInput(id, isFree, vertLoc);
		ModelController.getInstance().buildSettlement(input);
		
		isFree = false;
		allowDisconnected = false;
		if (isSettingUp) {
			settlement = vertLoc;
			startMove(PieceType.ROAD, true, false);
		}
	}

	public void placeCity(VertexLocation vertLoc) {
		int id = ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid();
		BuildCityInput input = new BuildCityInput(id, vertLoc);
		ModelController.getInstance().buildCity(input);
			
		isFree = false;
		allowDisconnected = false;			
	}

	public void placeRobber(HexLocation hexLoc) {
		robber = hexLoc;
		
		ArrayList<PlayerID> playerIDs = ModelController.getInstance().getGameModelFacade().getGameModel().getBoard().getBoardFacade().getPlayersOnHex(hexLoc);
		ArrayList<Player> players = new ArrayList<Player>();
		for (PlayerID id : playerIDs) {
			Player player = ModelController.getInstance().getGameModelFacade().getGameModel().getPlayer(id);
			if (!player.equals(ModelController.getInstance().getClientPlayer()) && ModelController.getInstance().getGameModelFacade().canRobPlayer(player, robber)){
				players.add(player);
			}
		}
		
		RobPlayerInfo[] robInfo = new RobPlayerInfo[players.size()];
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			RobPlayerInfo info = new RobPlayerInfo();
			info.setId(player.getPlayerID().getPlayerid());
			info.setPlayerIndex(player.getPlayerID().getPlayerid());
			info.setName(player.getName());
			info.setColor(player.getColor());
			info.setNumCards(player.getPlayerBank().getNumResourceCards());
			robInfo[i] = info;
		}
		getRobView().setPlayers(robInfo);
		getRobView().showModal();
		
		isFree = false;
		allowDisconnected = false;
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		this.isFree = isFree;
		this.allowDisconnected = allowDisconnected;
		
		CatanColor color = ModelController.getInstance().getClientPlayer().getColor();
		
		boolean canCancel = true;
		String status = ModelController.getInstance().getGameModelFacade().getGameModel().getTurnTracker().getStatus();
		if (status.equals("FirstRound") || status.equals("SecondRound")) {
			canCancel = false;
		}
		
		getView().startDrop(pieceType, color, canCancel);
	}
	
	public void cancelMove() {
		isFree = false;
		allowDisconnected = false;
	}
	
	public void playSoldierCard() {	
		startMove(PieceType.ROBBER, true, true);
	}
	
	public void playRoadBuildingCard() {	
		startMove(PieceType.ROAD, true, false);
//		startMove(PieceType.ROAD, true, false);
	}
	
	public void robPlayer(RobPlayerInfo victim) {
		RobPlayerInput input = new RobPlayerInput(ModelController.getInstance().getClientPlayer().getPlayerID().getPlayerid(), robber, victim.getPlayerIndex());
		try {
			ModelController.getInstance().updateGame(ProxyServer.getInstance().robPlayer(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
		isRobbing = false;
		robber = null;
	}

	@Override
	public void ModelChanged() {
		initFromModel();
		
		GameModelFacade facade = ModelController.getInstance().getGameModelFacade();
		if (facade != null) {
			String status = facade.getGameModel().getTurnTracker().getStatus();
			
			Player clientPlayer = ModelController.getInstance().getClientPlayer();
			int current = facade.getGameModel().getTurnTracker().getCurrentTurn();
			Player currentPlayer = facade.getGameModel().getPlayers().get(current);

			
			if ((status.equals("FirstRound") || status.equals("SecondRound")) && clientPlayer.getName().equals(currentPlayer.getName()) && !isSettingUp) {
				isSettingUp = true;
				startMove(PieceType.SETTLEMENT, true, true);
			}
			else if (status.equals("Robbing") && !isRobbing) {
				facade.getGameModel().getTurnTracker().setStatus("Robbing");
				isRobbing = true;
				startMove(PieceType.ROBBER, true, true);
//				getRobView().showModal();
			}
		}
	}
}
