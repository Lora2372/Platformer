package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.ActivatableCave;
import Entity.Doodad.Activatable.ActivatableShrineMysteriousDungeon;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import Entity.Unit.Fiona;
import GameState.GameStateManager;
import GameState.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class MysteriousDungeon extends MainMap
{
	protected boolean pathBlocked;
	
	protected ActivatableShrineMysteriousDungeon activatableShrine;
	
	protected Fiona fiona;
	
	protected boolean bossEngaged;
	protected boolean bossDefeated;
	
	protected boolean dungeonIntroduction;
	
	protected Conversation conversation;
	
	
	public MysteriousDungeon(GameStateManager gameStatemanager,
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
			tileMap.loadMap("/Maps/MysteriousDungeonA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/UndergroundBackground.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		conversation = player.getConversation();
		
		spawnTorch(109, 200);
		
		
		spawnTorch(3508, 410);
		
		spawnTorch(3928, 410);
		
		spawnStatueSave(250, 530);
		
		spawnChest(800, 630, false, 3, 0, "Common");

		spawnSign(400, 575, conversation.mysteriousDungeonDirectionMessage, conversation.mysteriousDungeonDirectionMessageWhoTalks);
		
		player.setCurrentMap("MysteriousDungeon");

		if(!player.getLoaded())
		{
			player.setPosition(109, 200);
			player.setSpawnPoint(109, 200);
		}
		else
		{
			dungeonIntroduction = true;
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		player.setSpawning(true);
				
		spawnSuccubus(937, 190, false);
		spawnSuccubus(2320, 1030, false);
		spawnSuccubus(2833, 550, false);
		
		spawnStatueSave(3045, 530);

		
		fiona = new Fiona(tileMap,false,false,false,false, true, "Fiona", 400, 200, this, player);
		enemies.add(fiona);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new ActivatableShrineMysteriousDungeon(tileMap, gameStatemanager, this, 3730, 530, fiona);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		
		fiona.inControl(false);
		
		doneInitializing = true;
		bossEngaged = false;
		bossDefeated = false;
	}
	
	
	
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
		
		if(!dungeonIntroduction)
		{
			if(player.getDirectionY() == 0 && player.getLocationY() > 300)
			{
				if(!player.getConversationState().inConversation())
				{
					int[] whoTalks = new int[]{0};		
					player.getConversationState().startConversation(player, null, null, conversation.mysteriousDungeonTorchMessage, whoTalks);
				}
				else if(player.getConversationState().getConversationTracker() >= player.getConversationState().getConversationLength())
				{
					player.getConversationState().endConversation();
					dungeonIntroduction = true;
					JukeBox.play("Female01EnterDungeon");
				}
			}
		}
		
		if(bossEngaged)
		{
			if(bossDefeated)
			{
				bossEngaged = false;
				JukeBox.stop("MysteriousBattle");
				JukeBox.loop("MysteriousDungeon");
				
				ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 1468, 550);
				stuff.add(activatableCave);
				activatables.add(activatableCave);
				
				spawnTorch(1348, 410);
				spawnTorch(1588, 410);
				
			}
		}
	}
}