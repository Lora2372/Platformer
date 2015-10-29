package Entity.Item;

import Entity.MapObject;
import TileMap.TileMap;

public class Item extends MapObject
{
	
	
	protected boolean inWorld; 				// If false, it's in an inventory/chest/whatever, otherwise in the world.
	
	protected MapObject owner; 				// Whatever is carrying it, assuming something/someone is.
	
	
	public Item(
			TileMap tileMap,
			boolean inWorld,
			double locationX,
			double locationY,
			MapObject owner
			) 
	{
		super(tileMap);
		
		this.inWorld = inWorld;
		
		
		if(inWorld)
		{
			this.locationX = locationX;
			this.locationY = locationY;
		}
		else
		{
			this.owner = owner;
		}
		
	
	}

	
	public boolean inWorld() { return inWorld; }
	public void inWorld(boolean inWorld) { this.inWorld = inWorld; }
	
	public MapObject getOwner() { return owner; }
	public void setOwner(MapObject newOwner) { owner = newOwner; }
	
	
	
	
	
}
