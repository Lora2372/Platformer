package GameState.Inventory;

import java.util.ArrayList;

import Entity.Item.Item;

public class Inventory 
{
	
	protected int maxColumns = 5;
	protected int maxRows = 5;
	
	protected int numberOfColumns;
	protected int numberOfRows;
	
	protected Item[][] items;
	
	public Inventory(
			int numberOfColumns,
			int numberOfRows,
			ArrayList<Item> items
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
		

		this.items = new Item[this.numberOfColumns][this.numberOfRows];
		if(items != null)
		{
			for(int i = 0; i < items.size(); i++)
			{
				addItem(items.get(i));
			}
		}

	}
	
	public int getColumns() { return numberOfColumns; }
	public int getRows() { return numberOfRows; }
	
	public boolean addItem(Item item)
	{
		item.setInWorld(false);
		
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
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				if(items[i][j] != null)
				{
					if(items[i][j].getItemType().equals(itemType))
					{
						System.out.println("Found the item: " + itemType);
						return items[i][j];
					}
				}
			}
		}
		
		
		System.out.println("Item wasn't found.");
		return null;
	}
	
	
	public Item[][] getItems()
	{
		return items;
	}
	
}
