package Entity.Doodad.Activatable;

import Main.Content;
import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.Item;
import Entity.Player.Player;

public class ActivatableChest extends Doodad
{
	protected boolean used;
	
	protected int silver;
	protected int gold;
	
	protected boolean locked;
	protected boolean successfullyOpened;
	
	
	public ActivatableChest(
			TileMap tileMap, 
			double spawnX,
			double spawnY,
			boolean locked,
			int silver,
			int gold,
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
				false, 
				true, 
				false,
				true,
				false,
				chestType
				);
		
		this.silver = silver;
		this.gold = gold;
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
					conversationPiece += "You unlocked the chest and found:";
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
//				active = true;
				if(silver > 0)
				{
					conversationPiece = conversationPiece + silver + " silver";
					if(gold > 0)
					{
						conversationPiece = conversationPiece + " and " + gold + " gold!";
					}
				}
				else if(gold > 0)
				{
					conversationPiece = conversationPiece + gold + " gold!";
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
			
			if(player.getConversationState().getConversationTracker() >= player.getConversationState().getConversationLength())
			{
				player.getConversationState().endConversation();
				
				if(active)
				{
					JukeBox.play("Coin");
					
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
