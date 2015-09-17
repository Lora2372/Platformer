package Entity.Doodad.Activatable;

import java.io.IOException;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Enemies.Succubus;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MysteriousDungeon;

public class ActivatableShrineMysteriousDungeon extends Doodad
{
	protected GameStateManager gameStateManager;
	
	protected Player player;
	
	protected boolean startConveration;
	
	protected Succubus succubus;
	
	int whoTalks[] = new int[]
	{
		1, 0, 1, 1, 0, 1, 0				
	};
	
	protected String conversation[] = new String[]
			{
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
			Succubus succubus
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
		
		this.succubus = succubus;
		this.gameStateManager = gameStateManager;
	}
	
	public boolean getStartConversation() { return startConveration; }
	
	public void startConversation(Player player)
	{
		System.out.println("Engaging conversation");
		succubus.setPosition(player.getx() + 200, player.gety() - 10);
		player.getConversationBox().startConversation(player, succubus, null, conversation, whoTalks);
		
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/MysteriousDungeonB.map");
			tileMap.setPosition(0, 0);
			
			JukeBox.stop("Dungeon1");
			JukeBox.loop("MysteriousConversation");			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
	
	public void interact(Player player)
	{
		if(player == null) this.player = player;
		
		if(!player.getConversationBox().inConversation())
			startConversation(player);
		else
			player.getConversationBox().progressConversation();
		
		
		if(player.getConversationBox().getConversationTracker() >= conversation.length)
		{
			player.getConversationBox().endConversation();
			startConveration = true;
			active = false;
			removeMe = true;
		}
		

	}
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}