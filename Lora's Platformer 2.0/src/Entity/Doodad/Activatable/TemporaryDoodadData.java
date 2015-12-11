package Entity.Doodad.Activatable;

import java.util.ArrayList;

import Entity.Item.Item;

public class TemporaryDoodadData 
{
	
	boolean untouchable;
	boolean invulnerable;
	boolean active;
	int currentAction;
	boolean locked;
	double spawnLocationX;
	double spawnLocationY;
	String doodadType;
	String uniqueID;
	ArrayList<Item> items;
	
	
	public TemporaryDoodadData
		(
			boolean untouchable,
			boolean invulnerable,
			boolean active,
			int currentAction,
			boolean locked,
			double spawnLocationX,
			double spawnLocationY,
			String doodadType,
			String uniqueID,
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
		this.uniqueID = uniqueID;
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
	
	public String getUniqueID()
	{
		return uniqueID;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
}
