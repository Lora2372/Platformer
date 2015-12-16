package GameState.Maps;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.Chest;
import Entity.Doodad.Activatable.DoodadData;
import Entity.Item.ItemData;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class DeepWoods extends MainMap
{

	public static int startLocationX = 200;
	public static int startLocationY = 1200;
	
	
	protected enum doodadIDs
	{
		InnDoor,
		AlchemyDoor
	};
	
	public DeepWoods
		(
			GameStateManager gameStatemanager,
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
		
		spawnDoodad.spawnTorch(2070, 1670);
		spawnDoodad.spawnTorch(2250, 1670);
		
		
		
		if(!player.getLoading(index))
		{
			
			Chest chest = spawnDoodad.spawnChest(2170, 1700, false, 0, DoodadData.Chests.Uncommon.toString());
			dropPotion(ItemData.Potions.Healing.toString(), 100, 1, chest);
			dropPotion(ItemData.Potions.Mana.toString(), 100, 2, chest);
			dropCoin(ItemData.Coins.Silver.toString(), 100, 5, chest);
			
			player.setPosition(startLocationX, startLocationY);
			player.setSpawnPoint(startLocationX, startLocationY);
			
			spawnUnit.spawnWolf(2940, 1680, true);
			
			spawnDoodad.spawnCampFire(5100,  1800);
			
			spawnDoodad.spawnDoor(5340,  1800, false, doodadIDs.InnDoor.toString(), 0, "Village");
			spawnDoodad.spawnDoor(7260,  1140, false, doodadIDs.AlchemyDoor.toString(), 0, "Village");
			
			spawnUnit.spawnBunny(2200, 200, true);
			
		}
		else
		{
			player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
		}
		
		if(player.getAnimation().getFrames() == null)
		{
			player.setAnimationState(0);
		}
		
		spawnDoodad.spawnStatueSave(2200, 1100);
		

		
		
		player.spawn();
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
	
	public void useDoodad(Doodad doodad)
	{
		if(doodad.getUniqueID().equals(doodadIDs.InnDoor.toString()))
		{
//			player.setSpawnPoint(FionasSanctum.startLocationX, FionasSanctum.startLocationY);
			
			gameStateManager.setState(GameStateManager.Inn);
		}
	}
	
	public void update()
	{
		super.update();
		
	}
	
}