package Entity.Item;

import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Bag extends Item
{

	protected ItemData.Bags BagType;
	
	public Bag
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			ItemData.Bags BagType
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
				false, 
				stacks, 
				true,
				true,
				BagType.toString(),
				ItemData.getDescriptionName(BagType.toString()),
				ItemData.getDescription(BagType.toString())
			);
		
		this.BagType = BagType;

	}
	
	public void setItem()
	{
		if(itemType.equals(ItemData.Bags.Small.toString()))
		{
			sprites = Content.BagSmall[0];
			portrait = Content.PortraitBagSmall[0];
		}
		else if(itemType.equals(ItemData.Bags.Medium.toString()))
		{
			sprites = Content.BagMedium[0];
			portrait = Content.PortraitBagMedium[0];
		}

	}
	
	public void use(Unit user)
	{
		super.use(user);

		user.getInventory().addInventorySlots(BagType);
	}
	
	public void playSound()
	{
		// Pick up sound
	}
}