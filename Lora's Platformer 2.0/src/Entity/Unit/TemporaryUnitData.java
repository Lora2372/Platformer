package Entity.Unit;


import java.util.ArrayList;

import Entity.Item.Item;

public class TemporaryUnitData 
{
	boolean facingRight;
	boolean friendly;
	boolean untouchable;
	boolean invulnerable;
	boolean unkillable;
	String name;
	double health;
	double spawnLocationX;
	double spawnLocationY;
	String unitType;
	int gold;
	int silver;
	ArrayList<Item> items;
	
	
	public TemporaryUnitData
	(
		boolean facingRight,
		boolean friendly,
		boolean untouchable,
		boolean invulnerable,
		boolean unkillable,
		String name,
		double health,
		double spawnLocationX,
		double spawnLocationY,
		String unitType,
		int silver,
		int gold,
		ArrayList<Item> items
	)
	{
		this.facingRight = facingRight;
		this.friendly = friendly;
		this.untouchable = untouchable;
		this.invulnerable = invulnerable;
		this.unkillable = unkillable;
		this.name = name;
		this.health = health;
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
		this.unitType = unitType;
		this.gold = gold;
		this.silver = silver;
		this.items = items;
		
		
	}
	
	public boolean getFacingRight()
	{
		return facingRight;
	}
	
	public boolean getFriendly()
	{
		return friendly;
	}
	
	public boolean getUntouchable()
	{
		return untouchable;
	}
	
	public boolean getInvulnerable()
	{
		return invulnerable;
	}
	
	public boolean getUnkillable()
	{
		return unkillable;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getHealth()
	{
		return health;
	}
	
	public double getSpawnLocationX()
	{
		return spawnLocationX;
	}
	
	public double getSpawnLocationY()
	{
		return spawnLocationY;
	}
	
	public String getUnitType()
	{
		return unitType;
	}
	
	public int getSilver()
	{
		return silver;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
}
