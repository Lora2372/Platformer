package Entity.Doodad.Activatable;

import java.io.IOException;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import Entity.Unit.Fiona;
import GameState.GameStateManager;
import GameState.Maps.MysteriousDungeon;
import Main.Content;

public class ActivatableShrineMysteriousDungeon extends Doodad
{
	protected GameStateManager gameStateManager;
	protected MysteriousDungeon mysteriousDungeon;
	
	protected Player player;
	
	protected boolean startConversation;
	protected boolean active = true;
	protected boolean touched = false;
	
	protected int choiceMade;
	
	protected Fiona fiona;
	
	protected Conversation conversation;
	
	protected ConversationState conversationBox;
	
	public ActivatableShrineMysteriousDungeon(
			TileMap tileMap,
			GameStateManager gameStateManager,
			MysteriousDungeon mysteriousDungeon,
			double spawnX,
			double spawnY,
			Fiona fiona
			) 
	{
		super(
				tileMap, 
				spawnX, 
				spawnY, 
				180, 
				180,
				180,
				180,
				false, 
				true, 
				false,
				true,
				false,
				"ActivatableShrineMysteriousDungeon"
				);
		
		this.fiona = fiona;
		this.gameStateManager = gameStateManager;
		this.mysteriousDungeon = mysteriousDungeon;
		
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Shrine[0];
	}
	
	public boolean getStartConversation() { return startConversation; }
	
	
	public void interact(Player player)
	{
		// Don't want to activate it more than once.
		if(!active)
		{
			return;
		}
		
		// If the player is null for whatever reason, update it with the player interacting with it:
		if(this.player == null) 
		{
			this.player = player;
			conversationBox = player.getConversationState();
			conversation = player.getConversation();
		}
		
		
		// If the player is not yet in a conversation and has not yet used the shrine, start the first conversation:
		if(!player.getInConversation() && choiceMade == 0)
		{
			player.getConversationState().startConversation(player, null, null, player.getConversation().interactWithFionasShrine(), player.getConversation().interactWithFinonasShrineWhoTalks());
			return;
		}
		
		// If the player currently is in a conversation but has not yet made the choice to touch the shrine:
		if(player.getInConversation() && choiceMade == 0)
		{
			if(conversationBox.getConversationTracker() >= player.getConversation().interactWithFionasShrine().length)
			{
				choiceMade = conversationBox.getChoiceMade();
				if(choiceMade == 1)
				{
					player.getConversationState().startConversation(player, fiona, null, conversation.interactWithFionasShrineChoiceYes(), conversation.interactWithFinonasShrineChoiceYesWhoTalks());

				}
				else
				{
					player.getConversationState().startConversation(player, fiona, null, conversation.interactWithFionasShrineChoiceNo(), conversation.interactWithFinonasShrineChoiceNoWhoTalks());
				}
			}
		}
		
		// Player didn't want to start the encounter.
		if(choiceMade == 2 && conversationBox.getConversationTracker() >= player.getConversation().interactWithFionasShrineChoiceNo().length)
		{
			conversationBox.endConversation();
			choiceMade = 0;
			return;
		}
		// If the player has decided to start the encounter and therefore used the shrine:
		if(player.getInConversation() && choiceMade == 1)
		{
			if(conversationBox.getConversationTracker() == 0)
			{
				// Play humming sound
				System.out.println("tracker is at 0");
				JukeBox.loop("Darkness");
				JukeBox.stop("MysteriousDungeon");

			}
			else if(conversationBox.getConversationTracker() == 1)
			{
				// Play laugh
				System.out.println("tracker is at 1");
				fiona.playRecoverSound();
				
			}
			else if(conversationBox.getConversationTracker() == 2)
			{
				// Close the door
				System.out.println("tracker is at 2");
				
				JukeBox.play("Close");
				
				try
				{
					for(int i = 0; i < mysteriousDungeon.getCharacterList().size(); i++)
					{
						Entity.Unit.Unit character = mysteriousDungeon.getCharacterList().get(i);
						if(character == null)
						{
							System.out.println("removing a character");
							mysteriousDungeon.getCharacterList().remove(character);
							i--;
						}
						else
						{
							if(character == player || character == fiona)
							{
								
								double tempX = character.getLocationX() - (tileMap.getWidth() - 20 * tileSize);
								
								if(tempX < 0) tempX = 200;
								character.setPosition(tempX, 550);
								
							}
							else
							{
								mysteriousDungeon.getCharacterList().remove(character);
								character = null;
								i--;
							}
						}
					}
					for(int i = 0; i < mysteriousDungeon.getStuff().size(); i++)
					{
						Doodad currentDoodad = mysteriousDungeon.getStuff().get(i);
						
						if(currentDoodad == null)
						{
							mysteriousDungeon.getStuff().remove(currentDoodad);
							i--;
						}
						else
						{
							double tempX = currentDoodad.getLocationX() - (tileMap.getWidth() - 20 * tileSize);
							if(tempX > 0)
							{
								currentDoodad.setPosition(currentDoodad.getLocationX() - (tileMap.getWidth() - 20 * tileSize), currentDoodad.getLocationY());
							}
							else
							{
								mysteriousDungeon.getStuff().remove(currentDoodad);
								mysteriousDungeon.getActivatables().remove(currentDoodad);
								currentDoodad = null;
								i--;
							}
						}
					}
					
					tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
					tileMap.loadMap("/Maps/MysteriousDungeonB.map");
					tileMap.setPosition(0, 0);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			else if(conversationBox.getConversationTracker() == 5)
			{
				// Fiona reveals herself
				fiona.setPosition(player.getLocationX() + 200, player.getLocationY() - 300);
				fiona.setSpawning(true);
				fiona.setHidden(false);
				fiona.inControl(false);
				
				JukeBox.loop("MysteriousConversation");
				JukeBox.stop("Darkness");
			}
			else if(conversationBox.getConversationTracker() >= player.getConversation().interactWithFinonasShrineChoiceYesWhoTalks().length)
			{
				player.getConversationState().endConversation();
				startConversation = true;
				active = false;
				fiona.inControl(true);
				mysteriousDungeon.setEngaged(true);
				JukeBox.stop("MysteriousConversation");
				JukeBox.loop("MysteriousBattle");
				player.getHUD().addBoss(fiona);
			}
		}
	}	
}