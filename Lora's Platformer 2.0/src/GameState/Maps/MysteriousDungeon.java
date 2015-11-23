package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.Chest;
import Entity.Doodad.Activatable.Door;
import Entity.Item.CreateItem;
import Entity.Player.Player;
import Entity.Unit.Slug;
import Entity.Unit.Succubus;
import Entity.Unit.Wolf;
import GameState.GameStateManager;
import GameState.MainMap;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import TileMap.GameOver;
import TileMap.TileMap;

public class MysteriousDungeon extends MainMap
{
	protected boolean dungeonIntroduction;
	
	protected Conversation conversation;
	
	protected Door door;
	
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
		
		conversation = player.getConversation();
		
		spawnTorch(109, 440);
		
		
		spawnTorch(3508, 410 + 240);
		
		spawnTorch(3928, 410 + 240);
		
		spawnTorch(390, 1250);
		spawnTorch(390, 1730);
		
		spawnTorch(690, 1250);
		spawnTorch(690, 1730);
		
		spawnKey(380, 1550, "Boss");
		
		
		Wolf wolf = spawnWolf(537, 1760, true);
		dropPotion("Any", 25, 1, wolf);
		wolf = spawnWolf(537, 1760, false);
		dropPotion("Any", 25, 1, wolf);
		
		Slug slug = spawnSlug(1788, 1250, true, null);
		dropPotion("Any", 25, 1, slug);
		
		slug = spawnSlug(1788, 1250, false, null);
		dropPotion("Any", 25, 1, slug);
		
		spawnStatueSave(250, 780);
		
		Chest chest = spawnChest(800, 850, false, "Common");
		dropPotion(CreateItem.Potions.Mana.toString(), 100, 1, chest);
		
		spawnSign(400, 810, conversation.mysteriousDungeonDirectionMessage, conversation.mysteriousDungeonDirectionMessageWhoTalks);
		
		chest = spawnChest(3262, 1620, false, "Common");
		dropPotion(CreateItem.Potions.Stamina.toString(), 100, 1, chest);
		
		Succubus succubus = spawnSuccubus(500, 1550, true);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(937, 430, false);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(3150, 1620, false);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(2833, 780, false);
		dropPotion("Any", 25, 1, succubus);
		
		spawnStatueSave(2900, 780);

		
		door = spawnDoor(3045,  780, true, 0, CreateItem.Keys.Boss.toString());
		
		
		

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
		

	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap("MysteriousDungeon");
		
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