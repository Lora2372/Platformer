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
			MapObject owner, 
			int stacks,
			String keyType
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
				0.3, 
				8, 
				locationX, 
				locationY,
				owner, 
				false, 
				stacks, 
				true, 
				keyType,
				CreateItem.getDescriptionName(keyType.toString()),
				CreateItem.getDescription(keyType.toString())
				
				);

	}
	
	public void setItem()
	{
		if(itemType.equals(CreateItem.Keys.Uncommon.toString()))
		{
			sprites = Content.KeyUncommon[0];
		}
		else if(itemType.equals(CreateItem.Keys.Rare.toString()))
			sprites = Content.KeyRare[0];
		else if(itemType.equals(CreateItem.Keys.Boss.toString()))
			sprites = Content.KeyBoss[0];
	
	}	
}
