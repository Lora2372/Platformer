package Entity.Doodad.Activatable;

import java.io.IOException;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Conversation;
import Entity.Player.ConversationBox;
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
	protected boolean used = false;
	protected boolean touched = false;
	
	protected Fiona fiona;
	
	protected Conversation conversation;
	
	protected ConversationBox conversationBox;
	
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
		
		conversation = player.getConversation();
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Shrine[0];
	}
	
	public boolean getStartConversation() { return startConversation; }
	
	public void startConversation(Player player)
	{
		
		if(!touched)
		{
			player.getConversationBox().startConversation(player, null, null, player.getConversation().interactWithFionasShrine(), player.getConversation().interactWithFinonasShrineWhoTalks());
		}
		else if(!used)
		{
			used = true;
			player.getConversationBox().startConversation(player, fiona, null, conversation.interactWithFionasShrine(), conversation.interactWithFinonasShrineWhoTalks());
		}
	}
	
	public void interact(Player player)
	{
		// If the player is null for whatever reason, update it with the player interacting with it:
		if(this.player == null) 
		{
			this.player = player;
			conversationBox = player.getConversationBox();
		}
		
		
		// If the player is not yet in a conversation and has not yet used the shrine, start the first conversation:
		if(!player.getInConversation() && !used)
		{
			startConversation(player);
		}
		
		// If the player currently is in a conversation but has not yet made the choice to touch the shrine:
		if(player.getInConversation() && !touched)
		{
			if(conversationBox.getConversationTracker() >= player.getConversation().interactWithFionasShrine().length)
			{
				if(conversationBox.getChoiceMade() == 1)
				{
					touched = true;
					startConversation(player);
				}
				else
				{
					conversationBox.endConversation();
				}
			}
		}
		
		// If the player has decided to start the encounter and therefore used the shrine:
		if(used)
		{
			if(conversationBox.getConversationTracker() == 1)
			{
				// Play humming sound
				System.out.println("tracker is at 1");
				

			}
			else if(conversationBox.getConversationTracker() == 2)
			{
				// Play laugh
				System.out.println("tracker is at 2");
				
			}
			else if(conversationBox.getConversationTracker() == 3)
			{
				// Close the door
				System.out.println("tracker is at 3");
				
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
								System.out.println("tempX:" + tempX);
//								character.setPosition(locationX - (GamePanel.WIDTH / 30 - 20), locationY);
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
					
					JukeBox.stop("MysteriousDungeon");
					
					tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
					tileMap.loadMap("/Maps/MysteriousDungeonB.map");
					tileMap.setPosition(0, 0);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			else if(conversationBox.getConversationTracker() == 6)
			{
				// Fiona reveals herself
				fiona.setPosition(player.getLocationX() + 200, player.getLocationY() - 300);
				fiona.setSpawning(true);
				fiona.setHidden(false);
				fiona.inControl(false);
				
				JukeBox.loop("MysteriousConversation");	
			}
			else if(conversationBox.getConversationTracker() >= player.getConversation().interactWithFinonasShrinChoiceYesWhoTalks().length)
			{
				player.getConversationBox().endConversation();
				startConversation = true;
				active = false;
				used = true;
				fiona.inControl(true);
				player.getHUD().addBoss(fiona);
			}
		}
	}	
}