package GameState.Maps;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Player.ConversationState;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class DeepWoods extends MainMap
{
	
	public DeepWoods(GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			) 
	{
		super(gameStatemanager, 
				tileMap,
				player,
				conversationState
				
				);
		
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
		
		player.setTileMap(tileMap);
		player.setCurrentMap("DeepWoods");

		if(!player.getLoaded())
		{
			player.setPosition(400, 200);
			player.setSpawnPoint(400, 200);
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		player.setSpawning(true);
				
		doneInitializing = true;
	}
}