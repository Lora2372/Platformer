package Entity.Item;

public class TemporaryItemData 
{
	
	String itemType;
	int stacks;
	double spawnLocationX;
	double spawnLocationY;
	
	public TemporaryItemData
		(
			String itemType,
			int stacks,
			double spawnLocationX,
			double spawnLocationY
		)
	{
		this.itemType = itemType;
		this.stacks = stacks;
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
	}
	
	public String getItemType()
	{
		return itemType;
	}
	
	public int getStacks()
	{
		return stacks;
	}
	
	public double getSpawnLocationX()
	{
		return spawnLocationX;
	}
	
	public double getSpawnLocationY()
	{
		return spawnLocationY;
	}
	
}
