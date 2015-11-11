package GameState.Maps;


import java.io.IOException;
import javax.imageio.ImageIO;

import Entity.Doodad.Activatable.Chest;
import Entity.Item.CreateItem;
import Entity.Player.*;
import Entity.Unit.LiadrinFirstEncounter;
import Entity.Unit.*;
import GameState.GameStateManager;
import GameState.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class LorasCavern extends MainMap
{

	
	
	public LorasCavern(
			GameStateManager gameStateManager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			) 
	{
		super(gameStateManager, 
				tileMap,
				player,
				conversationState
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
		
		spawnTorch(810, 1610);
		spawnTorch(1230, 1610);
		spawnTorch(1710, 1610);
		
		spawnTorch(810, 1910);
		spawnTorch(1230, 1910);
		spawnTorch(1710, 1910);
		
		spawnTorch(1830, 1310);
		spawnTorch(2610, 2030);
		
		spawnEnemies();
		this.player = player;

		player.setCurrentMap("LorasCavern");
		if(!player.getLoaded())
		{
			player.setPosition(720, 2200);
			player.setSpawnPoint(720, 2200);
		}
		else
		{
			player.setLoaded(false);

		}

		player.setSpawning(true);
		player.setUnkillable(false);	
		doneInitializing = true;
	}
	
	public void spawnEnemies()
	{
		Slug slug = spawnSlug(1690, 1600, false, "Steve");
		dropPotion(CreateItem.Potions.Healing.toString(), 100, 1, slug);
		
		Succubus succubus;
		succubus = spawnSuccubus(2700, 1400, false);
		dropCoin(CreateItem.Coins.Silver.toString(), 100, 2, succubus);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(1339,1900, true);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(2700, 2100, true);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(1423, 650, true);
		dropPotion("Any", 25, 1, succubus);
		
		succubus = spawnSuccubus(3689, 1430, false);
		dropPotion("Any", 25, 1, succubus);
		
		Chest chest;
		chest = spawnChest(1923, 1170, true, "Uncommon");
		dropPotion(CreateItem.Potions.Healing.toString(), 100, 1, chest);
		dropPotion(CreateItem.Potions.Mana.toString(), 100, 2, chest);
		dropCoin(CreateItem.Coins.Silver.toString(), 100, 3, chest);
		
		spawnKey(1712, 		2610, CreateItem.Keys.Uncommon.toString());
		

		
		
//		ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 3614, 2340);
//		stuff.add(activatableCave);
//		activatables.add(activatableCave);
		
		
		
		spawnStatueSave(3300, 1700);
		
		LiadrinFirstEncounter liadrinFirstEncounter = new LiadrinFirstEncounter(tileMap, false, true, false, true, true, "Liadrin", 2680, 1800, this);
		characterList.add(liadrinFirstEncounter);
		
		spawnSkeleton(900,  2100,  true);
		
		spawnSign(
				1357, 
				2250, 
				new String[] 
					{ 
						"Greetings mortal, I am a welcome sign intended to make you feel,\n well... Welcome!",
						"What an odd sign..."
					}, 
					new int[]
					{
						2,
						0
					}
				);
		
		
		spawnSign(
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
	}
	
	public void update()
	{
		super.update();
		if(player.getLocationX() < 3750 && player.getLocationY() > 2640)
		{
			
			gameStateManager.setState(GameStateManager.MysteriousDungeon);
		}
	}

}
