package client.data;

import java.io.Serializable;

import shared.definitions.*;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
@SuppressWarnings("serial")
public class PlayerInfo implements Serializable
{
	
	private int id;
	private int playerIndex;
	private String name;
	private CatanColor color;
	
	public PlayerInfo()
	{
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColor(CatanColor.WHITE);
	}
	
	public PlayerInfo(String nameIn, String colorIn, int playerid)
	{
		setName(nameIn);
		setId(playerid);
		//RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
		if(colorIn.equals("red"))
			color = CatanColor.RED;
		if(colorIn.equals("orange"))
			color = CatanColor.ORANGE;
		if(colorIn.equals("yellow"))
			color = CatanColor.YELLOW;
		if(colorIn.equals("blue"))
			color = CatanColor.BLUE;
		if(colorIn.equals("green"))
			color = CatanColor.GREEN;
		if(colorIn.equals("purple"))
			color = CatanColor.PURPLE;
		if(colorIn.equals("puce"))
			color = CatanColor.PUCE;
		if(colorIn.equals("white"))
			color = CatanColor.WHITE;
		if(colorIn.equals("brown"))
			color = CatanColor.BROWN;
	}
	
	public PlayerInfo(String nameIn, CatanColor colorIn, int playerid)
	{
		setName(nameIn);
		setId(playerid);
		setColor(colorIn);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public CatanColor getColor()
	{
		return color;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color;
	}

	@Override
	public int hashCode()
	{
		return 31 * this.id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final PlayerInfo other = (PlayerInfo) obj;
		
		return this.id == other.id;
	}
}

