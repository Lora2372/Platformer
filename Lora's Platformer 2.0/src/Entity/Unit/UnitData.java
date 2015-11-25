package Entity.Unit;

public class UnitData 
{
	boolean facingRight;
	boolean friendly;
	boolean untouchable;
	boolean invulnerable;
	boolean unkillable;
	String name;
	double spawnLocationX;
	double spawnLocationY;
	String currentMap;
	String unitType;
	
	public UnitData
	(
		boolean facingRight,
		boolean friendly,
		boolean untouchable,
		boolean invulnerable,
		boolean unkillable,
		String name,
		double spawnLocationX,
		double spawnLocationY,
		String currentMap,
		String unitType
	)
	{
		this.facingRight = facingRight;
		this.friendly = friendly;
		this.untouchable = untouchable;
		this.invulnerable = invulnerable;
		this.unkillable = unkillable;
		this.name = name;
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
		this.currentMap = currentMap;
		this.unitType = unitType;
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
	
	public double getSpawnLocationX()
	{
		return spawnLocationX;
	}
	
	public double getSpawnLocationY()
	{
		return spawnLocationY;
	}
	
	public String getCurrentMap()
	{
		return currentMap;
	}
	
	public String getUnitType()
	{
		return unitType;
	}
	
}
