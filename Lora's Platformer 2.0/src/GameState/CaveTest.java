package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import TileMap.Background;
import TileMap.GameOver;

public class CaveTest extends MainMap
{

	public CaveTest(GameStateManager gameStatemanager) 
	{
		super(gameStatemanager);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/CaveThroughTheDepths.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/RockCave.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnTorch(109, 200);
		
		spawnTorch(109, 780);
		
		spawnTorch(500, 780);
		
		player.setPosition(109, 200);
		player.setSpawnPoint(109, 200);
		player.setSpawning(true);
		
		spawnSuccubus(1600, 780, false);
		spawnSuccubus(1883, 240, false);
		
		spawnSlug(2551, 780, false);
		
		spawnSign(2616, 670, new String[] 
				{ 
					"Sadly this is as far as this dungeon goes at the moment.",
					"Bummer..."
				}, 
				new int[] 
				{ 
					2,
					0
				});
		
//		JukeBox.loop("Dungeon1");
		
		doneInitializing = true;
	
	}

}
