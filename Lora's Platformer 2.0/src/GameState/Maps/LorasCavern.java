package GameState.Maps;


import java.io.IOException;
import javax.imageio.ImageIO;
import Entity.Doodad.Activatable.Chest;
import Entity.Item.CreateItem;
import Entity.Player.*;
import Entity.Unit.*;
import GameState.GameStateManager;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class LorasCavern extends MainMap
{
	
	public static int startLocationX = 700;
	public static int startLocationY = 2200;
	
	public LorasCavern
	(
		GameStateManager gameStateManager,
		TileMap tileMap,
		Player player,
		ConversationState conversationState
	) 
	{
		super
		(
			gameStateManager, 
			tileMap,
			player,
			conversationState,
			GameStateManager.GameMaps.LorasCavern.toString()
		);
	
		try
		{						
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/LorasCavernA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/ForestMountainBackgroud.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnDoodad.spawnTorch(810, 1610);
		spawnDoodad.spawnTorch(1230, 1610);
		spawnDoodad.spawnTorch(1710, 1610);
		
		spawnDoodad.spawnTorch(810, 1910);
		spawnDoodad.spawnTorch(1230, 1910);
		spawnDoodad.spawnTorch(1710, 1910);
		
		spawnDoodad.spawnTorch(1830, 1310);
		spawnDoodad.spawnTorch(2610, 2030);
		
		this.player = player;
		
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
			
			Slug slug = spawnUnit.spawnSlug(1690, 1600, false, "Steve");
			dropPotion(CreateItem.Potions.Healing.toString(), 100, 1, slug);
			
			Succubus succubus;
			succubus = spawnUnit.spawnSuccubus(2700, 1400, false);
			dropCoin(CreateItem.Coins.Silver.toString(), 100, 2, succubus);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(1339,1900, true);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(2700, 2100, true);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(1423, 650, true);
			dropPotion("Any", 25, 1, succubus);
			
			succubus = spawnUnit.spawnSuccubus(3689, 1430, false);
			dropPotion("Any", 25, 1, succubus);
		
			LiadrinFirstEncounter liadrinFirstEncounter = new LiadrinFirstEncounter(tileMap, false, true, false, true, true, "Liadrin", 2680, 1800, this);
			characterList.add(liadrinFirstEncounter);
			
			Chest chest;
			chest = spawnDoodad.spawnChest(1923, 1170, true, 0, "Uncommon");
			dropPotion(CreateItem.Potions.Healing.toString(), 100, 1, chest);
			dropPotion(CreateItem.Potions.Mana.toString(), 100, 2, chest);
			dropCoin(CreateItem.Coins.Silver.toString(), 100, 3, chest);
			
			spawnItem.spawnKey(1712, 		2610, CreateItem.Keys.Uncommon.toString(), 1);
			spawnItem.spawnHerb(2276, 1450, CreateItem.Herbs.Sun.toString(), 1);
			spawnItem.spawnHerb(3004, 1270, CreateItem.Herbs.Sun.toString(), 1);
		}
		
		player.setCurrentMap(GameStateManager.GameMaps.LorasCavern.toString());
		
		spawnDoodad.spawnStatueSave(3300, 1700);
		

		
		spawnDoodad.spawnSign(
				3805, 
				1480, 
				new String[] 
					{ 
						"World's end!",
						"The world ends here? How is that even possible?",
						"It is entirely possible.",
						"Did you just talk?",
						"...",
						"No?",
						"..."
					}, 
					new int[]
					{
						2,
						0,
						2,
						0,
						2,
						2,
						0
					}
				);

		player.setSpawning(true);
		player.setUnkillable(false);	
		doneInitializing = true;
	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap(GameStateManager.GameMaps.LorasCavern.toString());
	}
	
	public void update()
	{
		super.update();
		if(player.getLocationX() < 3750 && player.getLocationY() > 2640)
		{
			player.setSpawnPoint(MysteriousDungeon.startLocationX, MysteriousDungeon.startLocationY);
			gameStateManager.setState(GameStateManager.MysteriousDungeon);
		}
	}
}
