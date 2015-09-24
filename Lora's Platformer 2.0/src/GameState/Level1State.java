package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Doodad.Activatable.*;
import Entity.NPC.Activatable.*;
import TileMap.Background;
import TileMap.GameOver;

public class Level1State extends MainMap
{

	public Level1State(GameStateManager gameStatemanager) 
	{
		super(gameStatemanager);
	
		try
		{						
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/LorasMap01018.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/Mountains5.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnTorch(800, 1600);
		spawnTorch(1850,1400);
		
		

		spawnEnemies();


		
		
		player.setPosition(720, 2200);
		player.setSpawnPoint(720, 2200);
		player.setSpawning(true);
		
//		JukeBox.loop("Battle9");
		
		doneInitializing = true;
	
	}
	
	public void spawnEnemies()
	{
		spawnSlug(1690, 1600, false);
		
		spawnSuccubus(2700, 1400, false);
		spawnSuccubus(1339,1900, true);
		spawnSuccubus(2252, 2170, true);
		spawnSuccubus(1423, 650, true);
		spawnSuccubus(3689, 1430, false);
		
		spawnChestCommon(989, 2250);
		spawnChestUncommon(989 + 120, 2250);
		spawnChestRare(989 + 240, 2250);
		
		spawnChestRare(1923, 1170);
		
		ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 3614, 2300);
		stuff.add(activatableCave);
		activatables.add(activatableCave);
		
		spawnChestUncommon(1712, 2610);
		
		LiadrinFirstEncounter liadrinFirstEncounter = new LiadrinFirstEncounter(tileMap, false, true, false, true, "Liadrin", 2680, 1800, this);
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
