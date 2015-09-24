package Entity.Doodad.Activatable;

import java.io.IOException;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Enemies.Fiona;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MysteriousDungeon;
import Main.GamePanel;

public class ActivatableShrineMysteriousDungeon extends Doodad
{
	protected GameStateManager gameStateManager;
	protected MysteriousDungeon mysteriousDungeon;
	
	protected Player player;
	
	protected boolean startConveration;
	
	protected Fiona fiona;
	
	int whoTalks[] = new int[]
	{
		3, 3, 3, 3, 1, 0, 1, 1, 0, 1, 0				
	};
	
	protected String conversation[] = new String[]
			{
			"The Shrine glows faintly, there's something written on it.",
			"Read what it says?\n- Yes\n- No",
			"...",
			"...",
			"Well look who I found, rummaging through my sanctum...",						// 1
			"Liadrin?!",																	// 0
			"Liadrin?\n"
			+ "...\n"
			+ "No that's not quite right, I'm Fiona.\n",									// 1		
			"But enough about that, since you're here, \nthat can only mean one thing.",		// 1
			"...?",																			// 0
			"Play ignorant if you wish, it shan't stop me!",								// 1
			"Wait!..."																		// 0
		};
	
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
				"/Sprites/Doodads/Shrine.png", 
				new int[] {0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {1, 2, 1},
				false, 
				true, 
				false,
				true,
				false
				);
		
		this.fiona = fiona;
		this.gameStateManager = gameStateManager;
		this.mysteriousDungeon = mysteriousDungeon;
	}
	
	public boolean getStartConversation() { return startConveration; }
	
	public void startConversation(Player player)
	{
		System.out.println("Engaging conversation");
		player.getConversationBox().startConversation(player, fiona, null, conversation, whoTalks);

		

	}
	
	public void interact(Player player)
	{
		if(player == null) this.player = player;
		
		if(!player.getConversationBox().inConversation())
			startConversation(player);
		else
			player.getConversationBox().progressConversation();
		
		if(player.getConversationBox().getConversationTracker() == 2)
		{
			JukeBox.play("Close");
			try
			{				
				for(int i = 0; i < mysteriousDungeon.getCharacterList().size(); i++)
				{
					Entity.Character character = mysteriousDungeon.getCharacterList().get(i);
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
							
							double tempX = character.getx() - (tileMap.getWidth() - 20 * tileSize);
							
							if(tempX < 0) tempX = 200;
							System.out.println("tempX:" + tempX);
//							character.setPosition(locationX - (GamePanel.WIDTH / 30 - 20), locationY);
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
					Doodad thing = mysteriousDungeon.getStuff().get(i);
					
					if(thing == null)
					{
						System.out.println("Removing a doodad");
						mysteriousDungeon.getStuff().remove(thing);
						i--;
					}
					else
					{
						if(thing == this)
						{
							thing.setPosition(this.locationX - (tileMap.getWidth() - 20 * tileSize), this.locationY);
						}
						else
						{
							mysteriousDungeon.getStuff().remove(thing);
							thing = null;
							i--;
						}
					}
				}
				
				JukeBox.stop("Dungeon1");
				
				tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
				tileMap.loadMap("/Maps/MysteriousDungeonB.map");
				tileMap.setPosition(0, 0);
				
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		if(player.getConversationBox().getConversationTracker() == 3)
		{
			fiona.setPosition(player.getx() + 200, player.gety() - 300);
			fiona.setSpawning(true);
			fiona.setHidden(false);
			fiona.inControl(false);
			

			JukeBox.loop("MysteriousConversation");			
		}
		
		if(player.getConversationBox().getConversationTracker() >= conversation.length)
		{
			player.getConversationBox().endConversation();
			startConveration = true;
			active = false;
			removeMe = true;
			fiona.inControl(true);
		}
		

	}
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}