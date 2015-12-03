package GameState.Maps;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.Shrine;
import Entity.Doodad.Activatable.Door;
import Entity.Doodad.Activatable.Portal;
import Entity.Player.Player;
import Entity.Unit.Fiona;
import GameState.GameStateManager;
import GameState.Conversation.ConversationDataFionasSanctum;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.GameOver;
import TileMap.TileMap;

public class FionasSanctum extends MainMap
{
	protected boolean pathBlocked;
	
	protected Shrine activatableShrine;
	
	protected Fiona fiona;
	
	protected boolean bossEngaged;
	protected boolean bossDefeated;
	
	protected int unlockConversationOnce;
	
	protected ConversationDataFionasSanctum conversation;
	
	protected Door bossDoor;
	
	public static int startLocationX = 120;
	public static int startLocationY = 780;
	
	protected boolean shrineStartConversation;
	protected boolean shrineTouched = false;
	
	protected int shrineChoiceMade;
	protected int shrineCurrentProgress = 0;

	
	public FionasSanctum
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
				"FionasSanctum"	
			);
		

		
		conversation = new ConversationDataFionasSanctum();
		
		spawnDoodad.spawnTorch(390, 410);
		spawnDoodad.spawnTorch(810, 410);

		spawnDoodad.spawnTorch(390, 650);
		spawnDoodad.spawnTorch(810, 650);


		
		fiona = new Fiona(tileMap, false, false, false, false, true, "Fiona", 400, 200, this, player);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new Shrine(tileMap, this, 600, 760);
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
	
	
	public void useDoodad(Doodad doodad)
	{		
		
		// If the player is not yet in a conversation and has not yet used the shrine, start the first conversation:
		if(!player.getInConversation() && shrineChoiceMade == 0)
		{
			conversationState.startConversation(player, null, null, conversation.interactWithFionasShrine(), conversation.interactWithFinonasShrineWhoTalks());
			return;
		}
		
		// If the player currently is in a conversation but has not yet made the choice to touch the shrine:
		if(player.getInConversation() && shrineChoiceMade == 0)
		{
			if(conversationState.getConversationOver())
			{
				shrineChoiceMade = conversationState.getChoiceMade();
				if(shrineChoiceMade == 1)
				{
					conversationState.startConversation(player, fiona, null, conversation.interactWithFionasShrineChoiceYes(), conversation.interactWithFinonasShrineChoiceYesWhoTalks());

				}
				else
				{
					conversationState.startConversation(player, fiona, null, conversation.interactWithFionasShrineChoiceNo(), conversation.interactWithFinonasShrineChoiceNoWhoTalks());
				}
			}
		}
		
		// Player didn't want to start the encounter.
		if(shrineChoiceMade == 2 && conversationState.getConversationOver())
		{
			conversationState.endConversation();
			shrineChoiceMade = 0;
			return;
		}
		// If the player has decided to start the encounter and therefore used the shrine:
		if(player.getInConversation() && shrineChoiceMade == 1)
		{
			if(conversationState.getConversationTracker() == 0)
			{
				// Play humming sound
				JukeBox.loop("Darkness");
				JukeBox.stop("FionasSanctum");

			}
			else if(conversationState.getConversationTracker() == 1)
			{
				// Play laugh
				fiona.playRecoverSound();
				
			}
			else if(conversationState.getConversationTracker() == 2)
			{
				// Close the door
				bossDoor.setDoodad(0);
				JukeBox.play("Close");
			}
			
			else if(conversationState.getConversationTracker() == 5 && shrineCurrentProgress != 5)
			{
				shrineCurrentProgress = 5;
				// Fiona reveals herself
				fiona.setPosition(player.getLocationX() + 200, player.getLocationY() - 300);
				fiona.spawn();
				fiona.setHidden(false);
				fiona.inControl(false);
				conversationState.lockConversation(true);
				
				JukeBox.loop("MysteriousConversation");
				JukeBox.stop("Darkness");
			}
			else if(conversationState.getConversationOver())
			{
				conversationState.endConversation();
				shrineStartConversation = true;
				doodad.setActive(true);
				fiona.inControl(true);
				bossEngaged = true;
				JukeBox.stop("MysteriousConversation");
				JukeBox.loop("MysteriousBattle");
				player.getHUD().addBoss(fiona);
			}
		}
	}
	
	public void update()
	{
		super.update();
		
		// We don't want the player to be able to progress the conversation whilst Fiona is spawning
		
		if(unlockConversationOnce != 2)
		{
			if(fiona.getSpawning())
			{
				unlockConversationOnce = 1;
			}
		}

		
		if(unlockConversationOnce == 1)
		{
			if(!fiona.getSpawning())
			{
				unlockConversationOnce = 2;
				player.getConversationState().lockConversation(false);
			}
		}
		
		
		if(bossEngaged)
		{
			if(bossDefeated)
			{
				if(!player.getInConversation())
				{
					gameStateManager.stopMusic();
					JukeBox.loop("MysteriousConversation");
					conversationState.startConversation(player, fiona, null, conversation.fionaDefeated, conversation.fionaDefeatedWhoTalks);
				}
				
				if(!conversationState.getConversationOver())
				{
					
					
					if(player.getConversationState().getConversationTracker() == 3)
					{
						if(!fiona.getDeSpawning())
						{
							player.getConversationState().lockConversation(true);
							fiona.deSpawn();
							player.getHUD().removeBoss();
						}
					}
					
					if(fiona.getHidden())
					{
						player.getConversationState().lockConversation(false);
					}
					
					if(player.getConversationState().getConversationTracker() == 4)
					{
						JukeBox.play("Close");
						JukeBox.loop("MysteriousDungeon");
						JukeBox.stop("MysteriousConversation");
						try 
						{
							tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						tileMap.loadMap("/Maps/FionasSanctumB.map");
						tileMap.setPosition(0, 0);
					}
					
					if(player.getConversationState().getConversationTracker() >= conversation.fionaDefeated.length)
					{
						player.getConversationState().endConversation();
					}
				}
				
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