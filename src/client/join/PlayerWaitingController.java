package client.join;

import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.communication.input.GameModelVersionInput;
import shared.communication.input.GamesListInput;
import shared.model.GameModel;
import shared.model.GameModelFacade;
import shared.model.player.Player;
import client.base.*;
import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import client.proxy.ProxyServer;
import client.data.PlayerInfo;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController,
																	ModelControllerListener{
	
	private ModelController MC;
	private int numberOfCurrentPlayers = 0;

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		MC = ModelController.getInstance();
		MC.addListener(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		GamesListInput gameIn = new GamesListInput();
		List<client.data.GameInfo> gameList;
		int count = 0;
		try {
			int gameIndex =  ProxyServer.getInstance().getGameId();
			
			//need to get current players in game right now, not just in game
			
			/*GameModelVersionInput input = new GameModelVersionInput(gameIndex);
			GameModel model = ProxyServer.getInstance().getGameModelVersion(input);
			List<Player> players = model.getPlayers();
			for(Player p: players)
			{
				System.out.println("9999999999999999999999999");
				if(p != null)
				{
					System.out.println(p.getName());
				}
			}
			PlayerInfo[] players2 = (PlayerInfo[]) players.toArray();
	        getView().setPlayers(players2);*/
	        
			
			gameList = ProxyServer.getInstance().listGames(gameIn);
	        List<PlayerInfo> playerInfos = new ArrayList<PlayerInfo>();
	        for(PlayerInfo p: gameList.get(gameIndex).getPlayers())
	        {
	        	if(p != null)
	        	{
	        		count++;
	        		playerInfos.add(p);
	        	}
	        }
	        numberOfCurrentPlayers = count;
	        if(count != 4)
	        {
		        PlayerInfo[] players = new PlayerInfo[count];
		        players = playerInfos.toArray(players);
		        getView().setPlayers(players);
	        }
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count != 4 && !getView().isModalShowing())
        {
			getView().showModal();
        }
	}
	
	private void closeView(){
		if(getView().isModalShowing())
		{
			getView().closeModal();
		}
	}

	@Override
	public void ModelChanged() {
		int count = 0;
		for(Player p: MC.getGameModelFacade().getGameModel().getPlayers())
		{
			if(p != null)
			{
				count++;
			}
		}
		if(count != numberOfCurrentPlayers)
		{
			closeView();
			start();
		}
		if(MC.getGameModelFacade().isGameFull()){
			closeView();
		}
	}

	@Override
	public void addAI() {
		// TODO Auto-generated method stub
		
	}

}




