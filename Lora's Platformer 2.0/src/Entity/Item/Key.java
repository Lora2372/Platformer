package Entity.Item;

import Entity.MapObject;
import Main.Content;
import TileMap.TileMap;

public class Key extends Item
{

	public Key(
			TileMap tileMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			int inventorySlotX,
			int inventorySlotY, 
			MapObject owner, 
			int stacks
			) 
	{
		super(
				tileMap, 
				inWorld, 
				60, 
				60, 
				60, 
				60, 
				0.3, 
				8, 
				locationX, 
				locationY,
				inventorySlotX, 
				inventorySlotY, 
				owner, 
				false, 
				stacks, 
				true, 
				"Key"
				
				);

	}
	
	public void setItem()
	{
		sprites = Content.Key[0];
	}
	

}
