package Entity.Item;

import Entity.Unit.Unit;
import TileMap.TileMap;

public class CreateItem 
{
	protected TileMap tileMap;
	public CreateItem(TileMap tileMap)
	{
		this.tileMap = tileMap;
	}
	
	public static enum Potions
	{
		PotionHealing,
		PotionMana,
		PotionStamina
	}
	
	public static enum Keys
	{
		Uncommon,
		Rare,
		Boss
	}
	
	public static enum Coins
	{
		CoinSilver,
		CoinGold
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
