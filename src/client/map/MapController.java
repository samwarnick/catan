package client.map;

import java.util.*;

import shared.communication.input.move.*;
import shared.definitions.*;
import shared.locations.*;
import shared.model.board.*;
import client.base.*;
import client.data.*;
import server.ServerException;
import client.controller.ModelController;


/**
 * Implementation for the map controller
 */
public class MapController extends client.base.Controller implements IMapController {
	
	private IRobView robView;
	//private ModelController controller;
	private boolean isFree = false;
	private boolean allowDisconnected = false;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
		
		initFromModel();
		
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
	
	protected void initFromModel() {
	
		Board board = ModelController.getGameModelFacade().getGameModel().getBoard();
		
		//resource hexes
		for(ResourceHex hex : board.getResourceHexes()) {
			getView().addHex(hex.getLocation(), hex.getLandType());
			getView().addNumber(hex.getLocation(), hex.getNumberToken());
		}
		
		//ports
		for(PortHex port : board.getPorts()) {
			getView().addPort(new EdgeLocation(port.getLocation(), port.getOrientation()), port.getPortType());
		}
		
		//water hexes
		for(WaterHex hex : board.getWaterHexes()) {
			getView().addHex(hex.getLocation(), hex.getLandType());
		}
		
		//roads
		for(Road road : board.getRoads()) {
			CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(road.getOwner()).getColor();
			getView().placeRoad(road.getLocation(), color);
		}
		
		//buildings
		for(Vertex building : board.getBuildings()) {
			CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(building.getOwner()).getColor();
			if(building.getClass() == Settlement.class) {
				getView().placeSettlement(building.getLocation(), color);
			}
			else if(building.getClass() == City.class) {
				getView().placeCity(building.getLocation(), color);
			}
		}
		
		//desert and robber
		getView().addHex(board.getDesertHex().getLocation(), board.getDesertHex().getLandType());
		getView().placeRobber(board.getRobber().getLocation());
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		// can this only be called from the active player???
		PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
		return ModelController.getGameModelFacade().canBuildRoad(ModelController.getGameModelFacade().getGameModel().getPlayer(id), edgeLoc, isFree, allowDisconnected);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		// can this only be called from the active player???
		PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
		return ModelController.getGameModelFacade().canBuildSettlement(ModelController.getGameModelFacade().getGameModel().getPlayer(id), vertLoc, isFree, allowDisconnected);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		// can this only be called from the active player???
		PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
		return ModelController.getGameModelFacade().canBuildCity(ModelController.getGameModelFacade().getGameModel().getPlayer(id), vertLoc, isFree, allowDisconnected);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return ModelController.getGameModelFacade().canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		try {			
			//need to contact server?? what order with ResourceBar, which contacts server?
			PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
			CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(id).getColor();
			
			BuildRoadInput input = new BuildRoadInput(id.getPlayerid(), isFree, edgeLoc);
			ModelController.updateGame(ModelController.getProxyServer().buildRoad(input));
			
			// if updateGame will redraw whole map, then dont need this
			getView().placeRoad(edgeLoc, color);
		} 
		catch (ServerException e) {
			e.printStackTrace();
		}
		finally {
			isFree = false;
			allowDisconnected = false;			
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		try {
			PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
			CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(id).getColor();
			
			BuildSettlementInput input = new BuildSettlementInput(id.getPlayerid(), isFree, vertLoc);
			ModelController.updateGame(ModelController.getProxyServer().buildSettlement(input));
			
			getView().placeSettlement(vertLoc, color);
		}
		catch (ServerException e) {
			e.printStackTrace();
		}
		finally {
			isFree = false;
			allowDisconnected = false;			
		}
	}

	public void placeCity(VertexLocation vertLoc) {
		try {
			PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
			CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(id).getColor();
			
			BuildCityInput input = new BuildCityInput(id.getPlayerid(), vertLoc);
			ModelController.updateGame(ModelController.getProxyServer().buildCity(input));
			
			getView().placeCity(vertLoc, color);
		}
		catch (ServerException e) {
			e.printStackTrace();
		}
		finally {
			isFree = false;
			allowDisconnected = false;			
		}
	}

	public void placeRobber(HexLocation hexLoc) {
		ModelController.getGameModelFacade().getGameModel().getBoard().getRobber().setLocation(hexLoc);
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
		
		isFree = false;
		allowDisconnected = false;
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		this.isFree = isFree;
		this.allowDisconnected = allowDisconnected;
		
		
		PlayerID id = new PlayerID(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn());
		CatanColor color = ModelController.getGameModelFacade().getGameModel().getPlayer(id).getColor();
		
		boolean canCancel = true;
		String status = ModelController.getGameModelFacade().getGameModel().getTurnTracker().getStatus();
		if (status.equals("First Round") || status.equals("Second Round")) {
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
	}
	
	public void robPlayer(RobPlayerInfo victim) {
		
		HexLocation loc = ModelController.getGameModelFacade().getGameModel().getBoard().getRobber().getLocation();
		RobPlayerInput input = new RobPlayerInput(ModelController.getGameModelFacade().getGameModel().getTurnTracker().getCurrentTurn(), loc, victim.getPlayerIndex());
		try {
			ModelController.updateGame(ModelController.getProxyServer().robPlayer(input));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
}

