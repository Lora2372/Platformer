package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.ActivatableCave;
import Entity.Doodad.Activatable.ActivatableShrineFionasSanctum;
import Entity.Doodad.Activatable.Door;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import Entity.Unit.Fiona;
import GameState.GameStateManager;
import GameState.MainMap;
import TileMap.GameOver;
import TileMap.TileMap;

public class FionasSanctum extends MainMap
{
	protected boolean pathBlocked;
	
	protected ActivatableShrineFionasSanctum activatableShrine;
	
	protected Fiona fiona;
	
	protected boolean bossEngaged;
	protected boolean bossDefeated;
	
	protected Conversation conversation;
	
	protected Door door;
	
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
				conversationState
				
				);
		
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
		
		conversation = player.getConversation();
		
		spawnTorch(390, 410);
		spawnTorch(810, 410);

		spawnTorch(390, 650);
		spawnTorch(810, 650);


		
		fiona = new Fiona(tileMap, false, false, false, false, true, "Fiona", 400, 200, this, player);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new ActivatableShrineFionasSanctum(tileMap, gameStatemanager, this, 600, 760, fiona);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		door = new Door(tileMap, gameStatemanager, 120, 780, false, 2, "Boss");
		activatables.add(door);
		stuff.add(door);
		
		player.setCurrentMap("FionasSanctum");

		if(!player.getLoaded())
		{
			player.setPosition(door.getLocationX(), door.getLocationY());
			player.setSpawnPoint(door.getLocationX(), door.getLocationY());
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		
		fiona.inControl(false);
		player.setUnkillable(false);
		bossEngaged = false;
		bossDefeated = false;
		
		doneInitializing = true;
	}
	
	public Door getDoor() { return door; }
	
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
		player.getConversationState().lockConversation(fiona.getSpawning());
		
		if(bossEngaged)
		{
			if(bossDefeated)
			{
				bossEngaged = false;
				JukeBox.stop("MysteriousBattle");
				JukeBox.loop("MysteriousDungeon");
				
				ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 1468, 790);
				stuff.add(activatableCave);
				activatables.add(activatableCave);
				
				spawnTorch(1348, 410);
				spawnTorch(1588, 410);
				
				spawnTorch(1348, 650);
				spawnTorch(1588, 650);
				
			}
		}
	}
}