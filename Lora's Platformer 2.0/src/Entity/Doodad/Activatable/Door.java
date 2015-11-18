package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.Item;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import Main.Content;
import TileMap.TileMap;

public class Door extends Doodad
{
	protected boolean used;
		
	protected boolean locked;
	protected boolean successfullyOpened;
	protected GameStateManager gameStateManager;
	
	protected int choiceMade;
	
	protected String doorName;
	
	protected Conversation conversation;
	protected ConversationState conversationBox;
	
	protected Player player;
	
	
	public Door
		(
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
				doorType,
				DoodadData.doodadName.get(doorType)
				);
		
		this.locked = locked;
		this.doorName = DoodadData.doodadName.get(doodadType);
		
		if(currentAction == 2)
		{
			used = true;
			spent = true;
			this.locked = false;
		}

		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		this.currentAction = currentAction;
		
		if(doodadType.equals(DoodadData.Doors.Boss.toString()))
		{
			portrait = Content.PortraitDoorBoss[0];
			if(currentAction == 0)
			{
				sprites = Content.DoorBossClosed[0];
			}
			else if(currentAction == 1)
			{
				sprites = Content.DoorBossOpening[0];
			}
			else if(currentAction == 2)
			{
				sprites = Content.DoorBossOpened[0];
			}
		}
		
		if(doodadType.equals(DoodadData.Doors.Village.toString()))
		{
			portrait = Content.PortraitDoorVillage[0];
			if(currentAction == 0)
			{
				sprites = Content.DoorVillageSquareClosed[0];
			}
			else if(currentAction == 1)
			{
				sprites = Content.DoorVillageSquareOpening[0];
			}
			else if(currentAction == 2)
			{
				sprites = Content.DoorVillageSquareOpened[0];
			}
		}
		
		animation.setFrames(sprites);
	}
	
	public void interact(Player player)
	{
		// If the door is already open, we walk through it.
		if(used && !player.getInConversation())
		{
			if(gameStateManager.getState() == GameStateManager.MysteriousDungeon)
			{
				gameStateManager.setState(GameStateManager.FionasSanctum);
			}
			return;
		}
		
		if(this.player == null)
		{
			this.player = player;
			conversationBox = player.getConversationState();
			conversation = player.getConversation();
		}
		
		
		if(!active)
		{
			if(locked)
			{
				if(!player.getInConversation() && choiceMade == 0)
				{
					conversationBox.startConversation(player, null, this, player.getConversation().unlockObject(doorName), player.getConversation().unlockObjectWhoTalks());
					return;
				}
				
				if(player.getInConversation() && choiceMade == 0)
				{
					if(conversationBox.getConversationOver())
					{
						choiceMade = conversationBox.getChoiceMade();
						
						if(choiceMade == 1)
						{
							Item item = player.getInventory().hasItem(doodadType);
							if(item != null)
							{
								item.use(player);
								JukeBox.play("Unlock");
								successfullyOpened = true;
								conversationBox.startConversation(player, null, this, conversation.unlockObjectChoiceYesSuccess(doorName), conversation.unlockObjectChoiceYesSuccessWhoTalks());
							}
							else
							{
								player.playCannotOpenSound();
								conversationBox.startConversation(player, null, this, conversation.unlockObjectChoiceYesFailure(doorName), conversation.unlockObjectChoiceYesFailureWhoTalks());
							}
						}
						else
						{
							conversationBox.startConversation(player, null, this, conversation.unlockObjectChoiceNo(doorName), conversation.unlockObjectChoiceNoWhoTalks());
						}
					}
				}
			}
			else
			{
				successfullyOpened = true;
			}
		}
		
		
		if(player.getInConversation())
		{
			
			if(conversationBox.getConversationOver())
			{
				conversationBox.endConversation();
				choiceMade = 0;
			}
		}
		
		if(successfullyOpened && !used)
		{
			JukeBox.play("DoorOpen");
				
			active = true;
			used = true;
		}
	}
	
	public void playSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
}