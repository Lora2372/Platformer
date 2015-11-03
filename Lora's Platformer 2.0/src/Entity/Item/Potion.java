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
				potionType
				
				);

	}
	
	public void setItem()
	{
		if(itemType.equals(CreateItem.Potions.PotionHealing.toString()))
		{
			sprites = Content.PotionHealth[0];
		}
		else if(itemType.equals(CreateItem.Potions.PotionMana.toString()))
		{
			sprites = Content.PotionMana[0];
		}
		else if(itemType.equals(CreateItem.Potions.PotionStamina.toString()))
		{
			sprites = Content.PotionStamina[0];
		}
	}
	
	public void use(Unit user)
	{
		super.use(user);
		
		JukeBox.play("PotionDrink");
		
		if(itemType.equals(CreateItem.Potions.PotionHealing.toString()))
		{
			user.restoreHealth(50);
		}
		else if(itemType.equals(CreateItem.Potions.PotionMana.toString()))
		{
			user.restoreMana(50);
		}
		else if(itemType.equals(CreateItem.Potions.PotionStamina.toString()))
		{
			user.restoreStamina(50);
		}
	}
	
	public void playSound()
	{
		// Pick up sound
	}
}