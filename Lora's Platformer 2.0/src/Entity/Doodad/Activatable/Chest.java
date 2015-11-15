package Entity.Doodad.Activatable;

import Main.Content;
import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;

public class Chest extends Doodad
{
	protected boolean used;
	
	protected int silver = 0;
	protected int gold = 0;
	
	protected boolean locked;
	protected boolean successfullyOpened;
	
	protected int choiceMade;
	
	protected String chestName;
	
	protected Conversation conversation;
	protected ConversationState conversationBox;
	
	protected Player player;
	
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
		this.chestName = CreateDoodad.doodadName.get(chestType);
	}
	
	public void setDoodad(int currentAction)
	{
		if(doodadType.equals(CreateDoodad.Chests.Common.toString()))
		{
			if(currentAction == 0)
				sprites = Content.ChestCommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestCommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestCommonOpened[0];
		}
		else if(doodadType.equals(CreateDoodad.Chests.Uncommon.toString()))
		{
			if(currentAction == 0)
				sprites = Content.ChestUncommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestUncommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestUncommonOpened[0];
		}
		else if(doodadType.equals(CreateDoodad.Chests.Rare.toString()))
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
		// If the chest is already open, we don't want to open it again
		if(used)
		{
			return;
		}
		
		// If the player is null for some reason, re-initialize the variables.
		if(this.player == null)
		{
			this.player = player;
			conversationBox = player.getConversationState();
			conversation = player.getConversation();
		}
		
		String conversationPiece = "";
		
		// If it hasn't been opened yet...
		if(!successfullyOpened)
		{
			// If the chest is locked
			if(locked)
			{
				// If the player hasn't started the conversation yet, start it.
				if(!player.getInConversation() && choiceMade == 0)
				{
					conversationBox.startConversation(player, null, null, conversation.unlockObject(chestName), conversation.unlockObjectWhoTalks());
					return;
				}
				
				// If the player is still in the conversation but we haven't gotten a decision yet
				if(player.getInConversation() && choiceMade == 0)
				{
					// If the conversation is over
					if(conversationBox.getConversationOver())
					{
						// get the decision made
						choiceMade = conversationBox.getChoiceMade();
						
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
								JukeBox.play("Unlock");
								conversationBox.startConversation(player, null, null, conversation.unlockObjectChoiceYesSuccess(chestName), conversation.unlockObjectChoiceYesSuccessWhoTalks());
							}
							else
							{
								// No key. =(
								player.playCannotOpenSound();
								conversationBox.startConversation(player, null, null, conversation.unlockObjectChoiceYesFailure(chestName), conversation.unlockObjectChoiceYesFailureWhoTalks());
							}
						}
						// If the player decides not to open the chest
						else
						{
							conversationBox.startConversation(player, null, null, conversation.unlockObjectChoiceNo(chestName), conversation.unlockObjectChoiceNoWhoTalks());
						}	
					}
				}
			}
			// If the chest wasn't locked to begin with
			else
			{
				successfullyOpened = true;
			}
		}

		
		// If the player is in a conversation but has not yet looted the chest
		if(player.getInConversation() && !used)
		{
			// If the conversation is over
			if(conversationBox.getConversationOver())
			{
				// End the conversation and reset the choice
				conversationBox.endConversation();
				choiceMade = 0;
				
				// If the player managed to open the chest
				if(successfullyOpened)
				{
					// If the chest is not yet opened
					if(!active)
					{
						playSound();
						player.playLootSound();
						
						active = true;
						conversationPiece = "You found ";
						
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
							char[] tempCharArray = conversationPiece.toCharArray();
							conversationPiece = "";
							for(int z = 0; z < (tempCharArray.length - 2); z++)
							{
								conversationPiece += tempCharArray[z];
							}
							conversationPiece += ".";
						}
						
						String[] conversation = new String[]
						{
							conversationPiece		
						};
						player.getConversationState().startConversation(player, null, null, conversation, new int[] { 3 });
						
						
					}
					else
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
	}
	
	public void playSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
}