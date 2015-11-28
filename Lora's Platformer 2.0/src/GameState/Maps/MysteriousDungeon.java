package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.Chest;
import Entity.Doodad.Activatable.CreateDoodad;
import Entity.Doodad.Activatable.Door;
import Entity.Item.CreateItem;
import Entity.Player.Player;
import Entity.Unit.Slug;
import Entity.Unit.Succubus;
import Entity.Unit.Wolf;
import GameState.GameStateManager;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.GameOver;
import TileMap.TileMap;

public class MysteriousDungeon extends MainMap
{
	protected boolean dungeonIntroduction;
	
	protected Conversation conversation;
	
	protected Door door;
	
	public static int startLocationX = 109;
	public static int startLocationY = 200;
	public static int doorLocationX = 3045;
	public static int doorLocationY = 780;
	
	public MysteriousDungeon(GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			) 
	{
		super(gameStatemanager, 
				tileMap,
				player,
				conversationState,
				GameStateManager.GameMaps.MysteriousDungeon.toString()
				
				);
		
		conversation = player.getConversation();
		
		spawnDoodad.spawnTorch(109, 440);		
		
		spawnDoodad.spawnTorch(3508, 410 + 240);
		
		spawnDoodad.spawnTorch(3928, 410 + 240);
		
		spawnDoodad.spawnTorch(390, 1250);
		spawnDoodad.spawnTorch(390, 1730);
		
		spawnDoodad.spawnTorch(690, 1250);
		spawnDoodad.spawnTorch(690, 1730);

		spawnDoodad.spawnStatueSave(250, 780);
		
		spawnDoodad.spawnStatueSave(2900, 780);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/MysteriousDungeonA.map");
			tileMap.setPosition(0, 0);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
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
			
			door = spawnDoodad.spawnDoor(doorLocationX,  doorLocationY, true, 0, CreateDoodad.Doors.Boss.toString());
			
			player.setPosition(startLocationX, startLocationY);
			player.setSpawnPoint(startLocationX, startLocationY);
			
			Chest chest = spawnDoodad.spawnChest(800, 850, false, 0, "Common");
			dropPotion(CreateItem.Potions.Mana.toString(), 100, 1, chest);
			
			spawnDoodad.spawnSign(400, 810, conversation.mysteriousDungeonDirectionMessage, conversation.mysteriousDungeonDirectionMessageWhoTalks);
			
			chest = spawnDoodad.spawnChest(3262, 1620, false, 0, "Common");
			dropPotion(CreateItem.Potions.Stamina.toString(), 100, 1, chest);
			
			Succubus succubus = spawnUnit.spawnSuccubus(500, 1550, true);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(937, 430, false);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(3150, 1620, false);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(2833, 780, false);
			dropPotion("Any", 25, 1, succubus);
			
			Wolf wolf = spawnUnit.spawnWolf(537, 1760, true);
			dropPotion("Any", 25, 1, wolf);
			wolf = spawnUnit.spawnWolf(537, 1760, false);
			dropPotion("Any", 25, 1, wolf);
			
			Slug slug = spawnUnit.spawnSlug(1788, 1250, true, null);
			dropPotion("Any", 25, 1, slug);
			
			slug = spawnUnit.spawnSlug(1788, 1250, false, null);
			dropPotion("Any", 25, 1, slug);
			
			spawnItem.spawnKey(380, 1550, "Boss", 1);
			
		}
		else
		{
			dungeonIntroduction = true;
			player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
		}


	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap(GameStateManager.GameMaps.MysteriousDungeon.toString());
		

		
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
	
	public void update()
	{
		super.update();
		
		// We don't want the player to be able to progress the conversation whilst Fiona is spawning

		if(!dungeonIntroduction)
		{
			if(player.getDirectionY() == 0 && player.getLocationY() > 300)
			{
				if(!player.getInConversation())
				{
					player.getConversationState().startConversation(player, null, null, conversation.mysteriousDungeonTorchMessage, new int[] { 0 });
				}
				else if(player.getConversationState().getConversationOver())
				{
					player.getConversationState().endConversation();
					dungeonIntroduction = true;
					JukeBox.play("PlayerEnterDungeon");
				}
			}
		}

	}
}