package GameState.MainMap;

import Entity.Item.Coin;
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Item.ItemData;
import Entity.Item.Key;
import Entity.Item.Potion;

public class SpawnItem {

	protected MainMap mainMap;
	
	public SpawnItem(MainMap mainMap)
	{
		this.mainMap = mainMap;
	}
	
	public Item spawn(ItemData itemData)
	{
		Item item = null;
		
		for(int i = 0; i < CreateItem.Coins.values().length; i++)
		{
			if(itemData.getItemType().equals(CreateItem.Coins.values()[i].toString()))
			{
				item = spawnCoin(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		for(int i = 0; i < CreateItem.Keys.values().length; i++)
		{
			if(itemData.getItemType().equals(CreateItem.Keys.values()[i].toString()))
			{
				item = spawnKey(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		for(int i = 0; i < CreateItem.Potions.values().length; i++)
		{
			if(itemData.getItemType().equals(CreateItem.Potions.values()[i].toString()))
			{
				item = spawnPotion(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		if(item != null)
		{
			return item;
		}
		
		
		return null;
	}
	
	public Key spawnKey(double locationX, double locationY, String keyType, int stacks)
	{
		Key key = new Key(mainMap.tileMap, true, locationX, locationY, null, stacks, keyType);
		mainMap.items.add(key);
		return key;
	}
	
	public Potion spawnPotion(double locationX, double locationY, String potionType, int stacks)
	{
		Potion potion = new Potion(mainMap.tileMap, true, locationX, locationY, null, stacks, potionType);
		mainMap.items.add(potion);
		return potion;
	}
	
	public Coin spawnCoin(double locationX, double locationY, String coinType, int stacks)
	{
		Coin coin = new Coin(mainMap.tileMap, true, locationX, locationY, null, stacks, coinType);
		mainMap.items.add(coin);
		return coin;
	}
	
}
