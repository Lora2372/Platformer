package Entity.Item;

import Entity.MapObject;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Key extends Item
{

	public Key
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			String keyType
		) 
	{
		super
			(
				tileMap,
				mainMap,
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
				true, 
				stacks,
				false,
				true, 
				keyType,
				ItemData.getDescriptionName(keyType.toString()),
				ItemData.getDescription(keyType.toString())
			);
	}
	
	public void setItem()
	{
		if(itemType.equals(ItemData.Keys.Uncommon.toString()))
		{
			sprites = Content.KeyUncommon[0];
			portrait = Content.PortraitKeyUncommon[0];
		}
		else if(itemType.equals(ItemData.Keys.Rare.toString()))
		{
			sprites = Content.KeyRare[0];
			portrait = Content.PortraitKeyRare[0];
		}
		else if(itemType.equals(ItemData.Keys.Boss.toString()))
		{
			sprites = Content.KeyBoss[0];
			portrait = Content.PortraitKeyBoss[0];
		}
	
	}	
}
