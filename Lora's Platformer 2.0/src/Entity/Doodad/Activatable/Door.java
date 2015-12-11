package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.Item;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.Conversation.ConversationData;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import GameState.Maps.FionasSanctum;
import GameState.Maps.MysteriousDungeon;
import Main.Content;
import TileMap.TileMap;

public class Door extends Doodad
{
	protected boolean used;
		
	protected boolean successfullyOpened;
	protected GameStateManager gameStateManager;
	
	protected int choiceMade;
	
	protected String doorName;
	
	protected ConversationData conversationData;
	protected ConversationState conversationBox;
	
	protected Player player;
	
	
	public Door
		(
			TileMap tileMap, 
			MainMap mainMap,
			GameStateManager gameStateManager,
			double spawnLocationX,
			double spawnLocationY,
			boolean locked,
			int currentAction,
			String doorType
		) 
	{
		super
		(
			tileMap, 
			mainMap,
			spawnLocationX, 
			spawnLocationY, 
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
		animation.setDelay(70);
	}
	
	
	public void setUsed(boolean used)
	{
		this.used = used;
	}
	
	public void interact(Player player)
	{

		// If the door is already open, we walk through it.
		if(used)
		{
			// If the door has been closed (programmatically), prevent the player from walking through it.
			if(currentAction != 2)
			{
				return;
			}
			if(!player.getInConversation())
			{
				if(gameStateManager.getCurrentState() == GameStateManager.MysteriousDungeon)
				{

					player.setSpawnPoint(FionasSanctum.startLocationX, FionasSanctum.startLocationY);
					
					gameStateManager.setState(GameStateManager.FionasSanctum);
				}
				else if(gameStateManager.getCurrentState() == GameStateManager.FionasSanctum)
				{
					player.setSpawnPoint(MysteriousDungeon.doorLocationX, MysteriousDungeon.doorLocationY);
					gameStateManager.setState(GameStateManager.MysteriousDungeon);
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
			return;
		}
		
		if(this.player == null)
		{
			this.player = player;
			conversationBox = player.getConversationState();
			conversationData = new ConversationData(player);
		}
		
		
		if(!active)
		{
			if(locked)
			{
				if(!player.getInConversation() && choiceMade == 0)
				{
					conversationBox.startConversation(player, null, this, conversationData.unlockObject(doorName), conversationData.unlockObjectWhoTalks());
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
								conversationBox.startConversation(player, null, this, conversationData.unlockObjectChoiceYesSuccess(doorName), conversationData.unlockObjectChoiceYesSuccessWhoTalks());
							}
							else
							{
								player.playCannotOpenSound();
								conversationBox.startConversation(player, null, this, conversationData.unlockObjectChoiceYesFailure(doorName), conversationData.unlockObjectChoiceYesFailureWhoTalks());
							}
						}
						else
						{
							conversationBox.startConversation(player, null, this, conversationData.unlockObjectChoiceNo(doorName), conversationData.unlockObjectChoiceNoWhoTalks());
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