package GameState.Maps;

import java.io.IOException;
import javax.imageio.ImageIO;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class DeepWoods extends MainMap
{
	
	public static int startLocationX = 400;
	public static int startLocationY = 200;
	
	public DeepWoods(GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			) 
	{
		super
			(
				gameStatemanager, 
				tileMap,
				player,
				conversationState,
				"DeepWoods"
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
		
		int index = 0;
		for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
		{
			if(currentMap.equals(GameStateManager.GameMaps.values()[i].toString()))
			{
				index = i;
			}
		}
		if(!player.getLoading(index))
		{
			player.setPosition(startLocationX, startLocationY);
			player.setSpawnPoint(startLocationX, startLocationY);
			
			spawnDoodad.spawnCampFire(2830,  910);
			
			spawnDoodad.spawnDoor(3030,  890, false, 0, "Village");
			spawnDoodad.spawnDoor(4970,  230, false, 0, "Village");
			
			spawnUnit.spawnBunny(200, 200, true);
			
		}
		else
		{
			player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
		}
		
		if(player.getAnimation().getFrames() == null)
		{
			player.setAnimationState(0);
		}
		
		spawnDoodad.spawnStatueSave(600, 780);
		

		
		
		player.setSpawning(true);
		player.setUnkillable(false);
		
	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap("DeepWoods");
		
		doneInitializing = true;
	}
	
	public double getStartingLocationX() { return startLocationX; }
	public double getStartingLocationY() { return startLocationY; }
	
	
	public void update()
	{
		super.update();
		
	}
	
}