package Entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import Entity.Unit.Unit;
import GameState.GameStateManager;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class ItemData 
{
	protected TileMap tileMap;
	
	
	public static ArrayList<ArrayList<TemporaryItemData>> itemList;
	
	public ItemData(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		items = new HashMap<String, ArrayList<TemporaryItemData>>();
		itemList = new ArrayList<ArrayList<TemporaryItemData>>();
		
		for(int i = 0; i <  GameStateManager.GameMaps.values().length - 1; i++)
		{
			ArrayList<TemporaryItemData> tempList = new ArrayList<TemporaryItemData>();
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
		
		itemDescriptionName.put(Bags.Small.toString(), "Small Bag");
		itemDescriptionName.put(Bags.Medium.toString(), "Medium Bag");
		
		
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
		

		itemDescription.put(Bags.Small.toString(), "A small sized bag increasing your inventory by five slots.");
		itemDescription.put(Bags.Medium.toString(), "A medium sized bag increasing your inventory by ten slots.");
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
	
	public static enum Bags
	{
		Small,
		Medium,
		Large
	}
	
	public static HashMap<String, String> itemDescriptionName = new HashMap<String, String>();

	public static HashMap<String, String> itemDescription = new HashMap<String, String>();
	
	public static HashMap<String, ArrayList<TemporaryItemData>> items = new HashMap<String, ArrayList<TemporaryItemData>>();

	public static int getItemListSize() { return itemList.size(); }
	
	public static void addItem(String currentMap, TemporaryItemData itemData)
	{
		items.get(currentMap).add(itemData);
	}
	
	public static void resetItemList(String map)
	{
		items.get(map).clear();
	}
	
	public static ArrayList<TemporaryItemData> getItemDataList(String map)
	{
		return items.get(map);
	}
	
	
	public static String getDescriptionName(String item)
	{		
		return itemDescriptionName.get(item);
	}
	
	public static String getDescription(String item)
	{
		return itemDescription.get(item);
	}
	
	
	
	public Item createItem(MainMap mainMap, String itemType, Unit owner, int stacks)
	{
		
		if(lookForCoin(itemType))
		{
			return new Coin(tileMap, mainMap, false, 0, 0, owner, stacks, itemType);
		}
		
		if(lookForKey(itemType))
		{
			return new Key(tileMap, mainMap, false, 0, 0, owner, stacks, itemType);
		}
		
		if(lookForPotion(itemType))
		{
			return new Potion(tileMap, mainMap, false, 0, 0, owner, stacks, itemType);
		}
		
		if(lookForHerb(itemType))
		{
			return new Herb(tileMap, mainMap, false, 0, 0, owner, stacks, itemType);
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
	
	public boolean lookForHerb(String itemType)
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
