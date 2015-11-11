package Entity.Doodad.Activatable;

import Main.Content;
import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Player.Player;

public class Chest extends Doodad
{
	protected boolean used;
	
	protected int silver = 0;
	protected int gold = 0;
	
	protected boolean locked;
	protected boolean successfullyOpened;
	
	
	public Chest(
			TileMap tileMap, 
			double spawnX,
			double spawnY,
			boolean locked,
			String chestType
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				60,
				60,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				chestType
				);
		
		this.locked = locked;
	}
	
	public void setDoodad(int currentAction)
	{
		if(doodadType.equals("Common"))
		{
			if(currentAction == 0)
				sprites = Content.ChestCommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestCommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestCommonOpened[0];
		}
		else if(doodadType.equals("Uncommon"))
		{
			if(currentAction == 0)
				sprites = Content.ChestUncommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestUncommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestUncommonOpened[0];
		}
		else if(doodadType.equals("Rare"))
		{
			if(currentAction == 0)
				sprites = Content.ChestRareClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestRareOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestRareOpened[0];
		}
	}
	
	public void interact(Player player)
	{
		
		if(used)
			return;
		
		String conversationPiece = "";
		if(!active)
		{
			if(locked)
			{
				Item item = player.getInventory().hasItem(doodadType);
				
				if(item != null)
				{
					conversationPiece += "You unlocked the chest and found ";
					active = true;
					item.use(player);
				}
				else
				{
					conversationPiece += "You struggle to open the chest to no avail, perhaps if you had a key...";
				}
			}
			else
			{
				conversationPiece = "You found ";
				active = true;
			}
			
			if(active)
			{
				
				int tempRows = inventory.getRows();
				int tempColumns = inventory.getColumns();
				Item[][] items = inventory.getItems();
				for(int i = 0; i < tempRows; i++)
				{
					for(int j = 0; j < tempColumns; j++)
					{
						Item item = items[i][j];
						
						if(item != null)
						{
							if(item.getStacks() > 1)
							{
								conversationPiece += item.getStacks() + " " + item.getDescriptionName() + "s, ";
							}else
							{
								conversationPiece += item.getStacks() + " " + item.getDescriptionName() + ", ";	
							}
							
							
							if(item.getItemType().equals(CreateItem.Coins.Silver.toString()))
							{
								silver = item.getStacks();
							}
							else if(item.getItemType().equals(CreateItem.Coins.Gold.toString()))
							{
								silver = item.getStacks();
							}
							else
							{
								player.getInventory().addItem(item);
							}
						}
					}
					conversationPiece = conversationPiece.substring(0, conversationPiece.length() - 2);
					conversationPiece += ".";
				}
			}
		}

		
		String[] conversation = new String[]
		{
			conversationPiece		
		};
		
		if(!player.getInConversation())
		{
			player.getConversationState().startConversation(player, null, null, conversation, new int[] { 3 });
			
			if(active)
			{
				player.playLootSound();
			}
			else
			{
				player.playCannotOpenSound();
			}
		}
		else
		{
			player.getConversationState().progressConversation();
			
			if(player.getConversationState().getConversationOver())
			{
				player.getConversationState().endConversation();
				
				if(active)
				{
					if(silver > 0 || gold > 0)
					{
						JukeBox.play("Coin");
					}

					
					player.addSilver(silver);
					player.addGold(gold);
					used = true;
					
					System.out.println("Successful");
				}
			}
		}
	}
	
	public void playSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
}