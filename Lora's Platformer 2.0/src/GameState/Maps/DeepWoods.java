package GameState.Maps;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Doodad.Activatable.Door;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MainMap;
import GameState.Conversation.ConversationState;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class DeepWoods extends MainMap
{
	protected Door door;
	
	protected double startLocationX = 400;
	protected double startLocationY = 200;
	
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
		

		
		player.setTileMap(tileMap);
		

		if(!player.getLoaded())
		{
			player.setPosition(startLocationX, startLocationY);
			player.setSpawnPoint(startLocationX, startLocationY);
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		
		if(player.getAnimation().getFrames() == null)
		{
			player.setAnimationState(0);
		}
		
		spawnStatueSave(600, 780);
		
		spawnCampFire(2830,  910);
		
		door = spawnDoor(3030,  890, false, 0, "Village");
		
		
		player.setSpawning(true);
		player.setUnkillable(false);
		
	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap("DeepWoods");
		
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
		
		doneInitializing = true;
	}
	
	public double getStartingLocationX() { return startLocationX; }
	public double getStartingLocationY() { return startLocationY; }
	
	
	public void update()
	{
		super.update();
		
	}
	
}