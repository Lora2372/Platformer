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
				8, 
				locationX, 
				locationY,
				owner, 
				false, 
				stacks, 
				true, 
				potionType
				
				);

	}
	
	public void setItem()
	{
		if(itemType.equals("Health"))
			sprites = Content.PotionHealth[0];
		else if(itemType.equals("Mana"))
			sprites = Content.PotionMana[0];
		else if(itemType.equals("Stamina"))
			sprites = Content.PotionStamina[0];
	}
	
	public void use(Unit user)
	{
		System.out.println("Using within potion");
		super.use(user);
		
		if(itemType.equals("Health"))
			user.restoreHealth(50);
		else if(itemType.equals("Mana"))
			user.restoreMana(50);
		else if(itemType.equals("Stamina"))
			user.restoreStamina(50);
	}
	
	public void playSound()
	{
		JukeBox.play("PotionDrink");
	}
	
	
}