package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.Item;
import Entity.Player.Player;
import GameState.GameStateManager;
import Main.Content;
import TileMap.TileMap;

public class Door extends Doodad
{
	protected boolean used;
	
	protected int silver;
	protected int gold;
	
	protected boolean locked;
	protected boolean successfullyOpened;
	protected GameStateManager gameStateManager;
	
	public Door(
			TileMap tileMap, 
			GameStateManager gameStateManager,
			double spawnX,
			double spawnY,
			boolean locked,
			int currentAction,
			String doorType
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				120, 
				120,
				120,
				120,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				currentAction,
				doorType
				);
		
		this.locked = locked;
		if(currentAction == 2)
		{
			System.out.println("door spawns open");
			used = true;
			spent = true;
			this.locked = false;
		}

		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		this.currentAction = currentAction;
		
		if(doodadType.equals("Boss"))
		{
			if(currentAction == 0)
			{
				sprites = Content.DoorClosed[0];
			}
			else if(currentAction == 1)
			{
				sprites = Content.DoorOpening[0];
			}
			else if(currentAction == 2)
			{
				sprites = Content.DoorOpened[0];
			}
			
			animation.setFrames(sprites);
		}
	}
	
	public void interact(Player player)
	{
		
		if(used)
		{
			if(gameStateManager.getState() == GameStateManager.MysteriousDungeon)
			{
				gameStateManager.setState(GameStateManager.FionasSanctum);
			}

			return;
		}
		
		String conversationPiece = "";
		if(!active)
		{
			if(locked)
			{
				Item item = player.getInventory().hasItem(doodadType);
				
				if(item != null)
				{
					JukeBox.play("Unlock");
					conversationPiece += "... ";
					item.use(player);
					successfullyOpened = true;
				}
				else
				{
					conversationPiece += "You struggle to open the door to no avail, perhaps if you had a key...";
				}
			}
			else
			{
				successfullyOpened = true;
			}
		}

		
		String[] conversation = new String[]
		{
			conversationPiece		
		};
		
		if(!player.getInConversation())
		{
			player.getConversationState().startConversation(player, null, null, conversation, new int[] { 3 });
			
			if(!successfullyOpened)
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
				
				if(successfullyOpened)
				{
					JukeBox.play("DoorOpen");
				
					active = true;
					used = true;
				}
			}
		}
	}
	
	public void playSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
}