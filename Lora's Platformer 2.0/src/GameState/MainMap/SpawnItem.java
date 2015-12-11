package GameState.MainMap;

import Entity.Item.Coin;
import Entity.Item.ItemData;
import Entity.Item.Herb;
import Entity.Item.Item;
import Entity.Item.TemporaryItemData;
import Entity.Item.Key;
import Entity.Item.Potion;

public class SpawnItem {

	protected MainMap mainMap;
	
	public SpawnItem(MainMap mainMap)
	{
		this.mainMap = mainMap;
	}
	
	public Item spawn(TemporaryItemData itemData)
	{		
		for(int i = 0; i < ItemData.Coins.values().length; i++)
		{
			if(itemData.getItemType().equals(ItemData.Coins.values()[i].toString()))
			{
				return spawnCoin(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		for(int i = 0; i < ItemData.Keys.values().length; i++)
		{
			if(itemData.getItemType().equals(ItemData.Keys.values()[i].toString()))
			{
				return spawnKey(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		for(int i = 0; i < ItemData.Potions.values().length; i++)
		{
			if(itemData.getItemType().equals(ItemData.Potions.values()[i].toString()))
			{
				return spawnPotion(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		for(int i = 0; i < ItemData.Herbs.values().length; i++)
		{
			if(itemData.getItemType().equals(ItemData.Herbs.values()[i].toString()))
			{
				return spawnHerb(itemData.getSpawnLocationX(), itemData.getSpawnLocationY(), itemData.getItemType(), itemData.getStacks());
			}
		}
		
		return null;
	}
	
	public Key spawnKey(double locationX, double locationY, String keyType, int stacks)
	{
		Key key = new Key(mainMap.tileMap, mainMap, true, locationX, locationY, null, stacks, keyType);
		mainMap.items.add(key);
		return key;
	}
	
	public Potion spawnPotion(double locationX, double locationY, String potionType, int stacks)
	{
		Potion potion = new Potion(mainMap.tileMap, mainMap, true, locationX, locationY, null, stacks, potionType);
		mainMap.items.add(potion);
		return potion;
	}
	
	public Coin spawnCoin(double locationX, double locationY, String coinType, int stacks)
	{
		Coin coin = new Coin(mainMap.tileMap, mainMap, true, locationX, locationY, null, stacks, coinType);
		mainMap.items.add(coin);
		return coin;
	}
	
	public Herb spawnHerb(double locationX, double locationY, String herbType, int stacks)
	{
		Herb herb = new Herb(mainMap.tileMap, mainMap, true, locationX, locationY, null, stacks, herbType);
		mainMap.items.add(herb);
		return herb;
	}
	
}
