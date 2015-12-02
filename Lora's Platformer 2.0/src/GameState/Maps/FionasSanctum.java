package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.ShrineFionasSanctum;
import Entity.Doodad.Activatable.Door;
import Entity.Doodad.Activatable.Portal;
import Entity.Player.Player;
import Entity.Unit.Fiona;
import GameState.GameStateManager;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.GameOver;
import TileMap.TileMap;

public class FionasSanctum extends MainMap
{
	protected boolean pathBlocked;
	
	protected ShrineFionasSanctum activatableShrine;
	
	protected Fiona fiona;
	
	protected boolean bossEngaged;
	protected boolean bossDefeated;
	
	protected int unlockOnce;
	
	protected Conversation conversation;
	
	protected Door bossDoor;
	
	public static int startLocationX = 120;
	public static int startLocationY = 780;
	
	public FionasSanctum(
			GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			) 
	{
		super(gameStatemanager, 
				tileMap,
				player,
				conversationState,
				"FionasSanctum"
				
				);
		

		
		conversation = player.getConversation();
		
		spawnDoodad.spawnTorch(390, 410);
		spawnDoodad.spawnTorch(810, 410);

		spawnDoodad.spawnTorch(390, 650);
		spawnDoodad.spawnTorch(810, 650);


		
		fiona = new Fiona(tileMap, false, false, false, false, true, "Fiona", 400, 200, this, player);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new ShrineFionasSanctum(tileMap, this, gameStatemanager, this, 600, 760, fiona);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		bossDoor = new Door(tileMap, this, gameStatemanager, startLocationX, startLocationY, false, 2, "Boss");
		activatables.add(bossDoor);
		stuff.add(bossDoor);
		
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
			player.setPosition(bossDoor.getLocationX(), bossDoor.getLocationY());
			player.setSpawnPoint(bossDoor.getLocationX(), bossDoor.getLocationY());
		}
		else
		{
			player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
		}
		
		fiona.inControl(false);
		player.setUnkillable(false);
		bossEngaged = false;
		bossDefeated = false;
		
		
	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap("FionasSanctum");
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/FionasSanctumA.map");
			tileMap.setPosition(0, 0);
			
//			background = new Background(getClass().getResource("/Art/Backgrounds/UndergroundBackground.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		doneInitializing = true;
	}
	
	public Door getDoor() { return bossDoor; }
	
	public ArrayList<Entity.Unit.Unit> getCharacterList()
	{
		return characterList;
	}
	
	public ArrayList<Doodad> getStuff()
	{
		return stuff;
	}
	
	public ArrayList<Doodad> getActivatables()
	{
		return activatables;
	}
	
	public void setDefeated(boolean b) { bossDefeated = b; }
	
	public void setEngaged(boolean b) { bossEngaged = b; }
	
	public void update()
	{
		super.update();
		
		// We don't want the player to be able to progress the conversation whilst Fiona is spawning
		
		if(unlockOnce != 2)
		{
			if(fiona.getSpawning())
			{
				unlockOnce = 1;
			}
		}

		
		if(unlockOnce == 1)
		{
			if(!fiona.getSpawning())
			{
				unlockOnce = 2;
				player.getConversationState().lockConversation(false);
			}
		}
		
		
		if(bossEngaged)
		{
			if(bossDefeated)
			{
				bossEngaged = false;
				JukeBox.stop("MysteriousBattle");
				JukeBox.loop(GameStateManager.GameMaps.MysteriousDungeon.toString());
				
				Portal portal = new Portal(tileMap, this, gameStateManager, 1468, 790);
				stuff.add(portal);
				activatables.add(portal);
				
				spawnDoodad.spawnTorch(1350, 410);
				spawnDoodad.spawnTorch(1590, 410);
				
				spawnDoodad.spawnTorch(1350, 650);
				spawnDoodad.spawnTorch(1590, 650);
				
			}
		}
	}
}