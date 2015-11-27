package Entity.Doodad.Activatable;

import java.util.ArrayList;

import Entity.Item.Item;

public class DoodadData 
{
	
	boolean untouchable;
	boolean invulnerable;
	boolean active;
	int currentAction;
	boolean locked;
	double spawnLocationX;
	double spawnLocationY;
	String doodadType;
	ArrayList<Item> items;
	
	
	public DoodadData
		(
			boolean untouchable,
			boolean invulnerable,
			boolean active,
			int currentAction,
			boolean locked,
			double spawnLocationX,
			double spawnLocationY,
			String doodadType,
			ArrayList<Item> items
		)
	{
		this.untouchable = untouchable;
		this.invulnerable = invulnerable;
		this.active = active;
		this.currentAction = currentAction;
		this.locked = locked;
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
		this.doodadType = doodadType;
		this.items = items;
	}
	
	public boolean getUntouchable()
	{
		return untouchable;
	}
	
	public boolean getInvulnerable()
	{
		return invulnerable;
	}
	
	public boolean getActive()
	{
		return active;
	}
	
	public double getSpawnLocationX()
	{
		return spawnLocationX;
	}
	
	public double getSpawnLocationY()
	{
		return spawnLocationY;
	}
	
	public int getCurrentAction()
	{
		return currentAction;
	}
	
	public boolean getLocked()
	{
		return locked;
	}
	
	public String getDoodadType()
	{
		return doodadType;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
}
