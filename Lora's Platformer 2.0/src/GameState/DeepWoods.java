package GameState;

import java.io.IOException;
import javax.imageio.ImageIO;
import TileMap.Background;
import TileMap.GameOver;

public class DeepWoods extends MainMap
{
	
	public DeepWoods(GameStateManager gameStatemanager) 
	{
		super(gameStatemanager);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/DeepWoodsA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/DeepWoods01.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		player.setPosition(400, 200);
		player.setSpawnPoint(400, 200);
		player.setSpawning(true);
		
		doneInitializing = true;
	}
}