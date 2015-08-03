package client.data;

import java.util.*;

import shared.definitions.CatanColor;
import shared.model.player.Player;

/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique game ID</li>
 * <li>Title: Game title (non-empty string)</li>
 * <li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfo()
	{
		setId(-1);
		setTitle("");
		players = new ArrayList<PlayerInfo>();
	}
	
	public GameInfo(int gameID, String gameName, List<PlayerInfo> players) {
		super();
		this.id = gameID;
		this.title = gameName;
		this.players = players;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer)
	{
		players.add(newPlayer);
	}
	
	public List<PlayerInfo> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}

	public void setPlayers(List<PlayerInfo> players) {
		this.players = players;
	}
	
	public void updatePlayers(List<Player> ps) {
		players = new ArrayList<PlayerInfo>();
		for(int i=0; i<ps.size(); i++){
			if(ps.get(i)==null){
				players.add(null);
				continue;
			}
			Player p = ps.get(i);
			PlayerInfo pi = new PlayerInfo();
			pi.setColor(p.getColor());
			pi.setId(p.getUniqueID());
			pi.setName(p.getName());
			pi.setPlayerIndex(i);
			players.add(pi);
		}
	}

	public boolean hasColor(CatanColor x) {
		for(int i = 0; i<players.size(); i++){
			if(players.get(i)==null) continue;
			if(players.get(i).getColor().equals(x)) return true;
		}
		
		return false;
	}
}

