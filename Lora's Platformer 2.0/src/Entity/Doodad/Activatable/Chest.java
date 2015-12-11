package Entity.Doodad.Activatable;

import Main.Content;
import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.ItemData;
import Entity.Item.Item;
import Entity.Player.Player;
import GameState.Conversation.ConversationData;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;

public class Chest extends Doodad
{
	protected boolean used;
	
	protected int silver = 0;
	protected int gold = 0;
	
	protected boolean successfullyOpened;
	
	protected int choiceMade;
	
	protected String chestName;
	
	protected boolean openingChest;
	
	protected ConversationData conversationData;
	protected ConversationState conversationDataBox;
	
	protected Player player;
	
	public Chest
		(
			TileMap tileMap, 
			MainMap mainMap,
			double spawnLocationX,
			double spawnLocationY,
			boolean locked,
			int currentAction,
			String chestType
		) 
	{
		super
			(
				tileMap,
				mainMap,
				spawnLocationX, 
				spawnLocationY, 
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
				currentAction,
				chestType,
				DoodadData.doodadName.get(chestType)
			);
		
		this.locked = locked;
		this.chestName = DoodadData.doodadName.get(chestType);
	}
	
	public void setDoodad(int currentAction)
	{
		if(doodadType.equals(DoodadData.Chests.Common.toString()))
		{
			portrait = Content.PortraitChestCommon[0];
			if(currentAction == 0)
			{
				sprites = Content.ChestCommonClosed[0];
				delay = 1000;
			}
			else if(currentAction == 1)
			{
				sprites = Content.ChestCommonOpening[0];
				delay = 70;
			}
			else if(currentAction == 2)
			{
				sprites = Content.ChestCommonOpened[0];
				delay = 1000;
			}
		}
		else if(doodadType.equals(DoodadData.Chests.Uncommon.toString()))
		{
			portrait = Content.PortraitChestUncommon[0];
			if(currentAction == 0)
			{
				sprites = Content.ChestUncommonClosed[0];
				delay = 1000;
			}
			else if(currentAction == 1)
			{
				sprites = Content.ChestUncommonOpening[0];
				delay = 70;
			}
			else if(currentAction == 2)
			{
				sprites = Content.ChestUncommonOpened[0];
				delay = 1000;
			}
		}
		else if(doodadType.equals(DoodadData.Chests.Rare.toString()))
		{
			portrait = Content.PortraitChestRare[0];
			if(currentAction == 0)
			{
				sprites = Content.ChestRareClosed[0];
				delay = 1000;
			}
			else if(currentAction == 1)
			{
				sprites = Content.ChestRareOpening[0];
				delay = 70;
			}
			else if(currentAction == 2)
			{
				sprites = Content.ChestRareOpened[0];
				delay = 1000;
			}
		}
	}
	
	public void setUsed(boolean used)
	{
		this.used = used;
	}
	
	public void interact(Player player)
	{
		
		// If the chest is already open, we don't want to open it again
		if(used)
		{
			return;
		}
		
		// If the player is null for some reason, re-initialize the variables.
		if(this.player == null)
		{
			this.player = player;
			conversationDataBox = player.getConversationState();
			conversationData = new ConversationData(player);
		}
		
		
		// If it hasn't been opened yet...
		if(!successfullyOpened)
		{
			// If the chest is locked
			if(locked)
			{
				// If the player hasn't started the conversation yet, start it.
				if(!player.getInConversation() && choiceMade == 0)
				{
					conversationDataBox.startConversation(player, null, this, conversationData.unlockObject(chestName), conversationData.unlockObjectWhoTalks());
					return;
				}
				
				// If the player is still in the conversation but we haven't gotten a decision yet
				if(player.getInConversation() && choiceMade == 0)
				{
					// If the conversationData is over
					if(conversationDataBox.getConversationOver())
					{
						// get the decision made
						choiceMade = conversationDataBox.getChoiceMade();
						
						// If the player decided to try and open the chest
						if(choiceMade == 1)
						{
							// Checking if the player has the required key for the chest
							Item item = player.getInventory().hasItem(doodadType);
							if(item != null)
							{
								// Let's remove that key shall we
								item.use(player);
								successfullyOpened = true;
								openingChest = true;
								JukeBox.play("Unlock");
								conversationDataBox.startConversation(player, null, this, conversationData.unlockObjectChoiceYesSuccess(chestName), conversationData.unlockObjectChoiceYesSuccessWhoTalks());
							}
							else
							{
								// No key. =(
								player.playCannotOpenSound();
								conversationDataBox.startConversation(player, null, this, conversationData.unlockObjectChoiceYesFailure(chestName), conversationData.unlockObjectChoiceYesFailureWhoTalks());
							}
						}
						// If the player decides not to open the chest
						else
						{
							conversationDataBox.startConversation(player, null, this, conversationData.unlockObjectChoiceNo(chestName), conversationData.unlockObjectChoiceNoWhoTalks());
						}	
					}
				}
			}
			// If the chest wasn't locked to begin with
			else
			{
				successfullyOpened = true;
				openingChest = true;
			}
		}
		
		// If the player is in a conversationData but has not yet looted the chest
		if(player.getInConversation() && !used)
		{
			// If the conversationData is over
			if(conversationDataBox.getConversationOver())
			{
				// End the conversationData and reset the choice
				conversationDataBox.endConversation();
				choiceMade = 0;
			}
		}
	}
	
	public void update()
	{
		super.update();
		if(used)
		{
			return;
		}
		
		if(successfullyOpened)
		{
			if(openingChest)
			{
				if(!active)
				{
					if(conversationDataBox.getConversationOver())
					{
						hasPlayedOnce = false;
						active = true;
						playSound();
					}
				}

				if(hasPlayedOnce && active)
				{
					player.playLootSound();
					String conversationDataPiece = "You found ";
							
					int tempRows = inventory.getNumberOfRows();
					int tempColumns = inventory.getNumberOfColumns();
					Item[][] items = inventory.getItems();
					for(int i = 0; i < tempRows; i++)
					{
						for(int j = 0; j < tempColumns; j++)
						{
							Item item = items[i][j];
							
							if(item != null)
							{
								conversationDataPiece += item.getStacks() + " " + ItemData.itemDescriptionName.get(item.getItemType()) + (item.getStacks() > 1 ? "s, " : ", ");
								
								if(item.getItemType().equals(ItemData.Coins.Silver.toString()))
								{
									silver = item.getStacks();
								}
								else if(item.getItemType().equals(ItemData.Coins.Gold.toString()))
								{
									gold = item.getStacks();
								}
								else
								{
									player.getInventory().addItem(item);
								}
							}
						}
					}
					conversationDataPiece = conversationDataPiece.substring(0, conversationDataPiece.length() - 2);
					
					conversationDataPiece += ".";
					
					String[] conversation = new String[]
					{
						conversationDataPiece		
					};
					conversationDataBox.startConversation(player, null, this, conversation, new int[] { 2 });
					openingChest = false;
				}
				
			}
			else if(hasPlayedOnce)
			{
				if(conversationDataBox.getConversationOver())
				{
					if(silver > 0 || gold > 0)
					{
						JukeBox.play("Coin");
					}
							
					conversationDataBox.endConversation();
					player.addSilver(silver);
					player.addGold(gold);
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