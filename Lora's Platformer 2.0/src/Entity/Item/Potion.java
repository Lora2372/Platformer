package Entity.Item;

import Audio.JukeBox;
import Entity.MapObject;
import Entity.Unit.Unit;
import Main.Content;
import TileMap.TileMap;

public class Potion extends Item
{

	public Potion(
			TileMap tileMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			String potionType
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
				true, 
				stacks, 
				true, 
				potionType,
				CreateItem.getDescriptionName(potionType.toString()),
				CreateItem.getDescription(potionType.toString())
				);

	}
	
	public void setItem()
	{
		if(itemType.equals(CreateItem.Potions.Healing.toString()))
		{
			sprites = Content.PotionHealth[0];
			portrait = Content.PortraitPotionHealing[0];
		}
		else if(itemType.equals(CreateItem.Potions.Mana.toString()))
		{
			sprites = Content.PotionMana[0];
			portrait = Content.PortraitPotionMana[0];
		}
		else if(itemType.equals(CreateItem.Potions.Stamina.toString()))
		{
			sprites = Content.PotionStamina[0];
			portrait = Content.PortraitPotionStamina[0];
		}
	}
	
	public void use(Unit user)
	{
		super.use(user);
		
		JukeBox.play("PotionDrink");
		
		if(itemType.equals(CreateItem.Potions.Healing.toString()))
		{
			user.restoreHealth(50);
		}
		else if(itemType.equals(CreateItem.Potions.Mana.toString()))
		{
			user.restoreMana(50);
		}
		else if(itemType.equals(CreateItem.Potions.Stamina.toString()))
		{
			user.restoreStamina(50);
		}
	}
	
	public void playSound()
	{
		// Pick up sound
	}
}