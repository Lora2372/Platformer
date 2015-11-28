package Entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import Entity.Unit.Unit;
import GameState.GameStateManager;
import TileMap.TileMap;

public class CreateItem 
{
	protected TileMap tileMap;
	
	
	public static ArrayList<ArrayList<ItemData>> itemList;
	
	public CreateItem(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		items = new HashMap<String, ArrayList<ItemData>>();
		itemList = new ArrayList<ArrayList<ItemData>>();
		
		for(int i = 0; i <  GameStateManager.GameMaps.values().length - 1; i++)
		{
			ArrayList<ItemData> tempList = new ArrayList<ItemData>();
			itemList.add(tempList);
			items.put(GameStateManager.GameMaps.values()[i + 1].toString(), itemList.get(i));
		}
		
		
    	itemDescriptionName = new HashMap<String, String>();
		itemDescriptionName.put(Potions.Healing.toString(), "Healing Potion");
		itemDescriptionName.put(Potions.Mana.toString(), "Mana Potion");
		itemDescriptionName.put(Potions.Stamina.toString(), "Stamina Potion");
		
		itemDescriptionName.put(Keys.Uncommon.toString(), "Uncommon Key");
		itemDescriptionName.put(Keys.Rare.toString(), "Rare Key");
		itemDescriptionName.put(Keys.Boss.toString(), "Boss Key");
		
		itemDescriptionName.put(Coins.Silver.toString(), "Silver Coin");
		itemDescriptionName.put(Coins.Gold.toString(), "Gold Coin");
		
		itemDescriptionName.put(Herbs.Sun.toString(), "Sun Herb");
		
		itemDescription = new HashMap<String, String>();
		itemDescription.put(Potions.Healing.toString(), "A magical red potion which heals wounds when consumed.");
		itemDescription.put(Potions.Mana.toString(), "A magical blue potion which replenishes mana when consumed.");
		itemDescription.put(Potions.Stamina.toString(), "A magical yellow potion wich restores stamina when consumed.");
		
		itemDescription.put(Keys.Uncommon.toString(), "Unlocks chests of Uncommon quality.");
		itemDescription.put(Keys.Rare.toString(), "Unlocks chests of Rare quality.");
		itemDescription.put(Keys.Boss.toString(), "Unlocks boss doors.");
		
		itemDescription.put(Coins.Silver.toString(), "Common currency of the realm.");
		itemDescription.put(Coins.Gold.toString(), "Rare currency of the realm.");
		
		itemDescription.put(Herbs.Sun.toString(), "A common herb used in alchemy.");
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
	
	public static enum Herbs
	{
		Sun
	}
	
	public static HashMap<String, String> itemDescriptionName = new HashMap<String, String>();

	public static HashMap<String, String> itemDescription = new HashMap<String, String>();
	
	public static HashMap<String, ArrayList<ItemData>> items = new HashMap<String, ArrayList<ItemData>>();

	public static int getItemListSize() { return itemList.size(); }
	
	public static void addItem(String currentMap, ItemData itemData)
	{
		items.get(currentMap).add(itemData);
	}
	
	public static void resetItemList(String map)
	{
		items.get(map).clear();
	}
	
	public static ArrayList<ItemData> getItemDataList(String map)
	{
		return items.get(map);
	}
	
	
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
		
		if(lookForKey(itemType))
		{
			return new Key(tileMap, false, 0, 0, owner, stacks, itemType);
		}
		if(lookForPotion(itemType))
		{
			return new Potion(tileMap, false, 0, 0, owner, stacks, itemType);
		}
		
		if(lookForHerbs(itemType))
		{
			return new Herb(tileMap, false, 0, 0, owner, stacks, itemType);
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
	
	public boolean lookForHerbs(String itemType)
	{
		for(int i = 0; i < Herbs.values().length; i++)
		{
			if(Herbs.values()[i].toString().equals(itemType))
			{
				return true;
			}
		}
		return false;
	}
	
		
	
}
