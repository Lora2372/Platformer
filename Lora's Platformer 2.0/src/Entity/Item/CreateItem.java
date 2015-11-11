package Entity.Item;

import java.util.HashMap;

import Entity.Unit.Unit;
import TileMap.TileMap;

public class CreateItem 
{
	protected TileMap tileMap;
	public CreateItem(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
    	itemDescriptionName = new HashMap<String, String>();
		itemDescriptionName.put(Potions.Healing.toString(), "Healing Potion");
		itemDescriptionName.put(Potions.Mana.toString(), "Stamina Potion");
		itemDescriptionName.put(Potions.Stamina.toString(), "Mana Potion");
		
		itemDescriptionName.put(Keys.Uncommon.toString(), "Uncommon Key");
		itemDescriptionName.put(Keys.Rare.toString(), "Rare Key");
		itemDescriptionName.put(Keys.Boss.toString(), "Boss Key");
		
		itemDescriptionName.put(Coins.Silver.toString(), "Silver Coin");
		itemDescriptionName.put(Coins.Gold.toString(), "Gold Coin");
		
		itemDescription = new HashMap<String, String>();
		itemDescription.put(Potions.Healing.toString(), "Restores health");
		itemDescription.put(Potions.Mana.toString(), "Restores stamina");
		itemDescription.put(Potions.Stamina.toString(), "Restores mana");
		
		itemDescription.put(Keys.Uncommon.toString(), "Unlocks chests of Uncommon quality.");
		itemDescription.put(Keys.Rare.toString(), "Unlocks chests of Rare quality.");
		itemDescription.put(Keys.Boss.toString(), "Unlocks boss doors.");
		
		itemDescription.put(Coins.Silver.toString(), "Common currency of the realm.");
		itemDescription.put(Coins.Gold.toString(), "Rare currency of the realm.");
		
	}
	
	public static enum Potions
	{
		Healing,
		Mana,
		Stamina
	}
	
	public static enum Keys
	{
		Uncommon,
		Rare,
		Boss
	}
	
	public static enum Coins
	{
		Silver,
		Gold
	}
	
	public static HashMap<String, String> itemDescriptionName = new HashMap<String, String>();

	public static HashMap<String, String> itemDescription = new HashMap<String, String>();

	
	public static String getDescriptionName(String key)
	{		
		return itemDescriptionName.get(key);
	}
	
	public static String getDescription(String key)
	{
		return itemDescription.get(key);
	}
	
	
	
	public Item createItem(String itemType, Unit owner, int stacks)
	{
		
		if(lookForCoin(itemType))
		{
			return new Coin(tileMap, false, 0, 0, owner, stacks, itemType);
		}
		
		else if(lookForKey(itemType))
		{
			return new Key(tileMap, false, 0, 0, owner, stacks, itemType);
		}
		else if(lookForPotion(itemType))
		{
			return new Potion(tileMap, false, 0, 0, owner, stacks, itemType);
		}
		
		return null;
	}
	
	public boolean lookForCoin(String itemType)
	{
		
		for(int i = 0; i < Coins.values().length; i++)
		{
			if(Coins.values()[i].toString().equals(itemType))
			{
				return true;
			}
		}
		return false;
	}
	public boolean lookForKey(String itemType)
	{
		for(int i = 0; i < Keys.values().length; i++)
		{
			if(Keys.values()[i].toString().equals(itemType))
			{
				return true;
			}
		}
		return false;
	}
		public boolean lookForPotion(String itemType)
		{
		
		for(int i = 0; i < Potions.values().length; i++)
		{
			if(Potions.values()[i].toString().equals(itemType))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
}
