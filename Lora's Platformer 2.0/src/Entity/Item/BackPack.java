package Entity.Item;

import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class BackPack extends Item
{

	protected ItemData.BackPacks backpackType;
	
	public BackPack
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			ItemData.BackPacks backpackType
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
				backpackType.toString(),
				ItemData.getDescriptionName(backpackType.toString()),
				ItemData.getDescription(backpackType.toString())
			);
		
		this.backpackType = backpackType;

	}
	
	public void setItem()
	{
		if(itemType.equals(ItemData.BackPacks.Small.toString()))
		{
			sprites = Content.PotionHealth[0];
			portrait = Content.PortraitPotionHealing[0];
		}

	}
	
	public void use(Unit user)
	{
		super.use(user);

		user.getInventory().addInventorySlots(backpackType);
	}
	
	public void playSound()
	{
		// Pick up sound
	}
}