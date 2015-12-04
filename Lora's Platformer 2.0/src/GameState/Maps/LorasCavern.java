package GameState.Maps;


import java.io.IOException;
import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.Chest;
import Entity.Doodad.Activatable.CreateDoodad;
import Entity.Doodad.Activatable.Lever;
import Entity.Item.CreateItem;
import Entity.Item.Potion;
import Entity.Player.*;
import Entity.Unit.*;
import GameState.GameStateManager;
import GameState.Conversation.ConversationDataLorasCavern;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class LorasCavern extends MainMap
{
	
	public static int startLocationX = 720;
	public static int startLocationY = 2220;
	
	protected int welcomeMessage = 0; // 0 = unstarted, 1 = choice made, -1 = done
	
	protected ConversationDataLorasCavern conversation;

	
	protected boolean liadrinConversationOver;
	
	protected int choiceLiadrin = 0;
	protected int chocieLever = 0;
	
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
	
		conversation = new ConversationDataLorasCavern();
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
		
			liadrin = new Liadrin(tileMap, false, true, false, true, true, 2680, 1800, this);
			characterList.add(liadrin);
			
			Chest chest = spawnDoodad.spawnChest(1923, 1170, true, 0, "Uncommon");
			dropPotion(CreateItem.Potions.Healing.toString(), 100, 1, chest);
			dropPotion(CreateItem.Potions.Mana.toString(), 100, 2, chest);
			dropCoin(CreateItem.Coins.Silver.toString(), 100, 3, chest);
			
			Lever lever = spawnDoodad.spawnLever(startLocationX + 180, startLocationY + 60, 0);
			lever.setPosition(lever.getLocationX(), lever.getLocationY() - lever.getCollisionHeight());
			spawnItem.spawnKey(1712, 		2610, CreateItem.Keys.Uncommon.toString(), 1);
			spawnItem.spawnHerb(2276, 1450, CreateItem.Herbs.Sun.toString(), 1);
			spawnItem.spawnHerb(3004, 1270, CreateItem.Herbs.Sun.toString(), 1);
		}
		else
		{
			welcomeMessage = -1;
		}
		
		liadrin.setHidden(true);
		
		player.setCurrentMap(GameStateManager.GameMaps.LorasCavern.toString());
		
		spawnDoodad.spawnStatueSave(3300, 1700);
		

		
		spawnDoodad.spawnSign
			(
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

		player.spawn();
		player.setUnkillable(false);	
		doneInitializing = true;
	}
	
	public void initialize()
	{
		super.initialize();
		player.setCurrentMap(GameStateManager.GameMaps.LorasCavern.toString());
	}
	
	public void useUnit(Unit unit)
	{
		
		if(unit.getUnitType().equals(CreateUnit.Units.Liadrin.toString()))
		{
			// If the player has not yet started talking to Liadrin, do so.
			if(!player.getInConversation() && choiceLiadrin == 0)
			{
				conversationState.startConversation(player, liadrin, null, conversation.liadrinFirstEncounter(), conversation.liadrinFirstEncounterWhoTalks());
				return;
			}
			
			// If the player is currently in a conversation but has not yet made the choice
			if(player.getInConversation() && choiceLiadrin == 0)
			{
				if(conversationState.getConversationOver())
				{
					choiceLiadrin = conversationState.getChoiceMade();
					if(choiceLiadrin == 1)
					{
						player.getConversationState().startConversation(player, liadrin, null, conversation.liadrinFirstEncounterChoiceEasy(), conversation.liadrinFirstEncounterChoiceEasyWhoTalks());
					}
					else
					{
						player.getConversationState().startConversation(player, liadrin, null, conversation.liadrinFirstEncounterChoiceHard(), conversation.liadrinFirstEncounterChoiceHardWhoTalks());
					}
				}
			}

//			 Player thought it was easy:
			if(player.getInConversation() && choiceLiadrin == 1)
			{
				if(conversationState.getConversationOver())
				{
					liadrinConversationOver = true;
				}
			}
			
			// Player thought it was hard:
			if(player.getInConversation() && choiceLiadrin == 2)
			{
				if(conversationState.getConversationOver())
				{
					Potion healingPotion = new Potion(tileMap, this, false, 0, 0, player, 2, CreateItem.Potions.Healing.toString());
					player.getInventory().addItem(healingPotion);
					
					liadrinConversationOver = true;
				}
			}
			
			if(liadrinConversationOver)
			{
				System.out.println("Ending liadrin conversation");
				conversationState.endConversation();
				liadrin.deSpawn();
			}

		}
	}
	
	public void useDoodad(Doodad doodad)
	{
		if(doodad.getDoodadType().equals(CreateDoodad.Other.Lever.toString()))
		{
			try
			{
				if(doodad.getCurrentAction() == 2)
				{
					tileMap.setMapSingleBlock(17, 36, 31);
					tileMap.setMapSingleBlock(17, 37, 31);
					JukeBox.play("Close");
				}
				
				if(doodad.getCurrentAction() == 0)
				{
					tileMap.setMapSingleBlock(17, 36, 119);
					tileMap.setMapSingleBlock(17, 37, 119);
					JukeBox.play("Close");
				}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public void update()
	{
		super.update();
		
		if(!player.getSpawning())
		{
			if(welcomeMessage == 0)
			{
				if(!player.getInConversation())
				{
					conversationState.startConversation(player, liadrin, null, conversation.lorasCavernWelcomeMessage, conversation.lorasCavernWelcomeMessageWhoTalks);
				}
				else
				{
					if(conversationState.getConversationOver())
					{
						if(conversationState.getChoiceMade() == 1)
						{
							conversationState.startConversation(player, liadrin, null, conversation.lorasCavernWelcomeMessageChoiceYes, conversation.lorasCavernWelcomeMessageWhoTalksChoiceYes);
						}
						else
						{
							conversationState.startConversation(player, liadrin, null, conversation.lorasCavernWelcomeMessageChoiceNo, conversation.lorasCavernWelcomeMessageWhoTalksChoiceNo);
						}
						
						welcomeMessage = 1;
					}
				}
			}
			else if(welcomeMessage == 1)
			{
				if(conversationState.getConversationOver())
				{
					welcomeMessage = -1;
					conversationState.endConversation();
				}
			}
		}
		
		
		if(liadrin.getHidden())
		{
			if(player.getLocationX() > 2500 && player.getLocationX() < 2600 && player.getLocationY() > 1850 && player.getLocationY() < 1950)
			{
				liadrin.setHidden(false);
				liadrin.spawn();
			}
		}
		
		if(player.getLocationX() < 3750 && player.getLocationY() > 2640)
		{
			player.setSpawnPoint(MysteriousDungeon.startLocationX, MysteriousDungeon.startLocationY);
			gameStateManager.setState(GameStateManager.MysteriousDungeon);
		}
	}
}
