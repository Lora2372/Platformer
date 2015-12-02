package Entity.Item;

import Entity.MapObject;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Herb extends Item
{
	public Herb
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld,
			double locationX,
			double locationY,
			MapObject owner,
			int stacks,
			String herbType
		)
	{
		super
			(
				tileMap,
				mainMap,
				inWorld,
				21,
				17,
				21,
				17,
				0.3,
				0.3,
				8,
				locationX,
				locationY,
				owner, 
				true,
				stacks,
				false,
				herbType,
				CreateItem.getDescriptionName(herbType), 
				CreateItem.getDescription(herbType)
			);
	}
	
	public void setItem()
	{
		if(itemType.equals(CreateItem.Herbs.Sun.toString()))
		{
			sprites = Content.HerbSun[0];
			portrait = sprites;
		}
	}
	
	
}
