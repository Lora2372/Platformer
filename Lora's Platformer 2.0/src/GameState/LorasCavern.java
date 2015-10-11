package GameState;


import java.io.IOException;
import javax.imageio.ImageIO;
import Entity.Player.*;
import Entity.Doodad.Activatable.*;
import Entity.NPC.Activatable.*;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class LorasCavern extends MainMap
{

	
	
	public LorasCavern(
			GameStateManager gameStateManager,
			TileMap tileMap,
			Player player
			) 
	{
		super(gameStateManager, 
				tileMap,
				player
				);
	
		try
		{						
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/LorasMap01018.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/ForestMountainBackgroud.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnTorch(800, 1600);
		spawnTorch(1850,1400);

		spawnEnemies();
		this.player = player;

		player.setCurrentMap("LorasCavern");
		if(!player.getLoaded())
		{
			player.setPosition(720, 2200);
			player.setSpawnPoint(720, 2200);
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}

		player.setSpawning(true);
						
		doneInitializing = true;
	}
	
	public void spawnEnemies()
	{
		spawnSlug(1690, 1600, false, "Steve");
		
		spawnSuccubus(2700, 1400, false);
		spawnSuccubus(1339,1900, true);
		spawnSuccubus(2252, 2170, true);
		spawnSuccubus(1423, 650, true);
		spawnSuccubus(3689, 1430, false);
		
		spawnWolf(853, 1680, true);
		
		spawnChest(989, 2250, "Common");
		spawnChest(989 + 120, 2250, "Uncommon");
		spawnChest(989 + 240, 2250, "Rare");
		
		spawnChest(1923, 1170, "Rare");
		
		ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 3614, 2340);
		stuff.add(activatableCave);
		activatables.add(activatableCave);
		
		spawnChest(1712, 2610, "Uncommon");
		
		spawnStatueSave(3200, 2150);
		
		LiadrinFirstEncounter liadrinFirstEncounter = new LiadrinFirstEncounter(tileMap, false, true, false, true, true, "Liadrin", 2680, 1800, this);
		characterList.add(liadrinFirstEncounter);
		allies.add(liadrinFirstEncounter);
		
		spawnSign(
				1357, 
				2250, 
				new String[] 
					{ 
						"Greetings mortal, I am a welcome sign intended to make you feel,\n well... Welcome!",
						"What an odd sign..."
					}, 
					new int[]
					{
						2,
						0
					}
				);
		
		
		spawnSign(
				3805, 
				1480, 
				new String[] 
					{ 
						"World's end!",
						"The world ends here? How is that even possible?",
						"It is entirely possible.",
						"Did you just talk?",
						"...",
						"No?",
						"..."
					}, 
					new int[]
					{
						2,
						0,
						2,
						0,
						2,
						2,
						0
					}
				);
	

	}

}
