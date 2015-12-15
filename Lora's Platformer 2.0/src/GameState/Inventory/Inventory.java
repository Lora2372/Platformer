package GameState.Inventory;

import java.util.ArrayList;

import Entity.MapObject;
import Entity.Item.Item;
import Entity.Item.ItemData;

public class Inventory 
{
	
	protected int maxColumns = 10;
	protected int maxRows = 10;
	
	protected int numberOfColumns;
	protected int numberOfRows;
	
	protected Item[][] items;
	
	protected MapObject owner;
	
	public Inventory
		(
			int numberOfColumns,
			int numberOfRows,
			ArrayList<Item> items,
			MapObject owner
		)
	{
		
		
		if(numberOfColumns > maxColumns)
			this.numberOfColumns = maxColumns;
		else
			this.numberOfColumns = numberOfColumns;	
		
		if(numberOfRows > maxRows)
			this.numberOfRows = maxRows;
		else
			this.numberOfRows = numberOfRows;
		
		this.owner = owner;
		
		this.items = new Item[maxRows][maxColumns];
		if(items != null)
		{
			for(int i = 0; i < items.size(); i++)
			{
				addItem(items.get(i));
			}
		}
	}
	
	public void addInventorySlots(ItemData.BackPacks backpack)
	{
		int slotsToAdd = 0;
		if(backpack.equals(ItemData.BackPacks.Small))
		{
			slotsToAdd = 5;
		}
		
		if(backpack.equals(ItemData.BackPacks.Medium))
		{
			slotsToAdd = 10;
		}
		
		if(backpack.equals(ItemData.BackPacks.Large))
		{
			slotsToAdd = 15;
		}
		
		while(slotsToAdd > 0)
		{
			if(numberOfRows < maxRows)
			{
				numberOfRows++;
				slotsToAdd -= numberOfColumns;
			}
			else if(numberOfColumns < maxColumns)
			{
				numberOfColumns++;
				slotsToAdd -= numberOfRows;
			}
			else
			{
				System.out.println("-------Inventory.java 86-------");
				System.out.println("numberOfRows: " + numberOfRows + "\nmaxRows: " + maxRows + "\nnumberOfColumns: " + numberOfColumns + "\nmaxColumns: " + maxColumns);
				System.out.println("-------------------------------");
				slotsToAdd = 0;
				Exception exception = new Exception();
				exception.printStackTrace();
			}
		}
		
	}
	
	
	public boolean addItem(Item item)
	{
		item.setInWorld(false);
		
		item.setOwner(owner);
		
		Item tempItem = hasItem(item.getItemType());
		if(tempItem != null && tempItem.getStackable())
		{
			tempItem.setStacks(tempItem.getStacks() + item.getStacks());
			return true;
		}
		
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				if(items[i][j] == null)
				{
					items[i][j] = item;
					return true;
				}
			}
		}
		item.drop();
		if(owner != null)
		{
			owner.emoteExclamation();
			owner.playCannotCarry();
		}
		System.out.println("Item could not be added.");
		return false;
	}
	
	public boolean removeItem(Item item)
	{
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				if(items[i][j] != null)
				{
					if(items[i][j].equals(item))
					{
						items[i][j] = null;
						return true;
					}
				}
			}
		}
		

		
		System.out.println("Item could not be removed.");
		return false;
	}
	
	public Item hasItem(String itemType)
	{
		try
		{
			for(int i = 0; i < numberOfRows; i++)
			{
				for(int j = 0; j < numberOfColumns; j++)
				{
					if(items[i][j] != null)
					{
						if(items[i][j].getItemType().equals(itemType))
						{
							return items[i][j];
						}
					}
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}

		return null;
	}
	
	
	public Item[][] getItems()
	{
		return items;
	}
	
	public Item getItem(int x, int y)
	{
		return items[y][x];
	}
	
	public int getNumberOfColumns()
	{
		return numberOfColumns;
	}
	
	public int getNumberOfRows()
	{
		return numberOfRows;
	}
	
}
