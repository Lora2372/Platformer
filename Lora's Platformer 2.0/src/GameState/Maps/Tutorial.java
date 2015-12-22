package GameState.Maps;

import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.ItemData;
import Entity.Item.Potion;
import Entity.Player.HUD;
import Entity.Player.Player;
import Entity.Unit.Succubus;
import GameState.GameStateManager;
import GameState.Conversation.ConversationDataTutorial;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class Tutorial extends MainMap
{

	protected int tutorialProgress = 0;
	
	protected Succubus succubus;
	
	protected ConversationDataTutorial conversation;
	protected ConversationState conversationState;
	
	protected HUD hud;
	
	protected boolean alreadyDoneThis = false;
	
	protected Potion potion;
	
	protected int choiceMade = 0;
	
	protected enum doodadIDs
	{
		openTemple
	}
	
	public Tutorial
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
				"Tutorial"
			);
		this.conversationState = conversationState;
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/TutorialA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/DeepWoods01.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		this.player = player;
		this.hud = player.getHUD();
		conversation = new ConversationDataTutorial(player);
		
		player.setTileMap(tileMap);
		player.setCurrentMap("Tutorial");
		
		spawnDoodad.spawnTorch(3810, 400);
		spawnDoodad.spawnTorch(4650, 400);
		
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
			player.setPosition(400, 200);
			player.setSpawnPoint(400, 200);
		}
		else
		{
			player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
		}
		player.spawn();
		
		spawnDoodad.spawnCampFire(6880, 500);
		spawnDoodad.spawnLever(5100, 370, doodadIDs.openTemple.toString(), 0);
		
		player.getHUD().setShowQuestFrame(true);
		player.setUnkillable(true);
		doneInitializing = true;
	}
	
	public void openTempleExit(boolean playSound)
	{
		if(playSound)
		{
			JukeBox.play("Close");
		}
		tileMap.setMapSingleBlock(87, 8, 31);
		tileMap.setMapSingleBlock(87, 9, 31);
	}
	
	public void closeTempleExit(boolean playSound)
	{
		if(playSound)
		{
			JukeBox.play("Close");
		}
		tileMap.setMapSingleBlock(87, 8, 119);
		tileMap.setMapSingleBlock(87, 9, 119);
	}
	
	public void useDoodad(Doodad doodad)
	{
		try
		{
			// Lever that opens the starting "cell"
			if(doodad.getUniqueID().equals(doodadIDs.openTemple.toString()))
			{
				if(doodad.getCurrentAction() == 2)
				{
					if(tutorialProgress == 15)
					{
						tutorialProgress = 16;
					}
					if(tutorialProgress == 16)
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialTempleExitOpen(), 
								conversation.tutorialTempleExitOpenWhoTalks());
					}
					openTempleExit(true);
				}		
				if(doodad.getCurrentAction() == 0)
				{
					closeTempleExit(true);
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void update()
	{
		super.update();
		try
		{
			if(player.getInConversation())
			{
				if(conversationState.getConversationOver())
				{
					conversationState.endConversation();
					tutorialProgress++;
				}
			}

			if(!player.getFalling())
				if(player.getLocationX() < 2500)
					player.setSpawnPoint(1420, player.getLocationY() - 50);
				else
					player.setSpawnPoint(4000, 320);
					
					
			if(tutorialProgress == 0 && !player.getFalling())
			{
				if(!player.getInConversation())
				{
					conversationState.startConversation(player, null, null,
							conversation.tutorialWelcome, 
							conversation.tutorialWelcomeWhoTalks);
					
					hud.setQuest(conversation.tutorialInstructions());
					hud.setShowQuestFrame(true);
					hud.setQuestCurrent(0);
				}
			}
			
			if(tutorialProgress == 1)
			{
				hud.setQuestCurrent(1);
				
				if(player.getLocationX() - 400 > 200 || player.getLocationX() - 400 < -200)
				{
					if(player.getLeft() || player.getRight() && !player.getInConversation())
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialMove, 
								conversation.tutorialMoveWhoTalks);
						
						
					}
				}
			}
			
			if(tutorialProgress == 2)
			{
				hud.setQuestCurrent(2);
				if(player.getJumping() && !player.getInConversation())
				{
					
					conversationState.startConversation(player, null, null, 
							conversation.tutorialJump, 
							conversation.tutorialJumpWhoTalks);
				}
			}
			
			
			if(tutorialProgress == 3)
			{
				hud.setQuestCurrent(3);
				if(player.getLocationX() > 1420 && !player.getInConversation())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialHoleSmall, 
							conversation.tutorialHoleSmallWhoTalks);
				}
			}		
			if(tutorialProgress == 4)
			{	
				hud.setQuestCurrent(4);
				if(player.getLocationX() > 1820 && !player.getInConversation() && !player.getFalling())
				{
					hud.setShowQuestFrame(false);
					conversationState.startConversation(player, null, null, 
							conversation.tutorialHoleSmallPassed, 
							conversation.tutorialHoleSmallPassedWhoTalks);
				}
			}
			
			if(tutorialProgress == 5)
			{
				if(player.getLocationX() > 2600 && !player.getInConversation() && !player.getFalling())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialMovementMastered, 
							conversation.tutorialMovementMasteredWhoTalks);
				}
			}
			
			if(tutorialProgress == 6)
			{	
				if(player.getLocationX() > 2950 && !player.getInConversation() && !player.getFalling())
				{	
					conversationState.startConversation(player, null, null, 
							conversation.tutorialFireBallSmall, 
							conversation.tutorialFireBallSmallWhoTalks);
				}
			}
			
			if(tutorialProgress == 7)
			{
				hud.setQuestCurrent(5);
				hud.setShowQuestFrame(true);
				if(player.getFireBallSmallDoneCasting() && !player.getInConversation())
				{	
					conversationState.startConversation(player, null, null, 
							conversation.tutorialFireBallLarge, 
							conversation.tutorialFireBallLargeWhoTalks);
				}
			}
			
			if(tutorialProgress == 8)
			{	
				hud.setQuestCurrent(6);
				if(player.getFireBallLargeDoneCasting() && player.getUp() && !player.getInConversation())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialDash, 
							conversation.tutorialDashWhoTalks);
				}
			}
			
			if(tutorialProgress == 9)
			{
				hud.setQuestCurrent(7);
				if(player.getDashing() && !player.getInConversation())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialPunch, 
							conversation.tutorialPunchWhoTalks);
				}
			}
			
			if(tutorialProgress == 10)
			{
				hud.setQuestCurrent(8);
				if(player.getPunching() && !player.getInConversation())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialPunchDone, 
							conversation.tutorialPunchDoneWhoTalks);
				}
			}
			
			
			if(tutorialProgress == 11)
			{
				if(!player.getInConversation())
				{
					hud.setQuestCurrent(9);
					succubus = spawnUnit.spawnSuccubus(3500, player.getLocationY(), false);
					characterList.add(succubus);
					
					
					conversationState.startConversation(player, null, null, 
							conversation.tutorialEnemySuccubus, 
							conversation.tutorialEnemySuccubusWhoTalks);
				}
			}
			
			if(tutorialProgress == 12)
			{
				if(succubus.isDead())
				{
					if(!player.getInConversation())
						conversationState.startConversation(player, null, null, 
								conversation.tutorialTempleEnter, 
								conversation.tutorialTempleEnterWhoTalks);
					else
						if(conversationState.getConversationTracker() == 1)
						{
							if(!alreadyDoneThis)
							{
								alreadyDoneThis = true;
								JukeBox.play("Close");
								tileMap.setMapSingleBlock(60, 8, 31);
								tileMap.setMapSingleBlock(60, 9, 31);
								hud.setQuestCurrent(10);
							}
						}
				}
			}

			if(tutorialProgress == 13)
			{	
				alreadyDoneThis = false;
				if(!player.getInConversation() && player.getLocationX() > 4000 && player.getLocationY() < 390)
				{	
					conversationState.startConversation(player, null, null, 
							conversation.tutorialHoleLarge, 
							conversation.tutorialHoleLargeWhoTalks);
				}
			}
			
			if(tutorialProgress == 14)
			{
				hud.setQuestCurrent(11);
				if(!player.getInConversation() && player.getLocationX() > 4500 && !player.getFalling())
				{
					
					conversationState.startConversation(player, null, null, 
							conversation.tutorialHoleLargePassed, 
							conversation.tutorialHoleLargePassedWhoTalks);
				}
			}
			
			if(tutorialProgress == 15)
			{
				hud.setQuestCurrent(12);
				if(!player.getInConversation() && player.getLocationX() > 5100 && player.getLocationY() > 400 && !player.getFalling())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialTempleExitClosed(), 
							conversation.tutorialTempleExitClosedWhoTalks());
				}
			}
			
			if(tutorialProgress == 16)
			{
				hud.setQuestCurrent(13);
			}

			if(tutorialProgress == 17)
			{
				hud.setQuestCurrent(14);
				if(!player.getInConversation() && player.getLocationX() > 5600 && !player.getFalling())
				{
					hud.setShowQuestFrame(false);
					closeTempleExit(false);
					conversationState.startConversation(player, null, null, 
							conversation.tutorialNiceWeather(), 
							conversation.tutorialNiceWeatherWhoTalks());
				}
				else if(player.getInConversation())
				{
					if(conversationState.getConversationTracker() == 1)
					{
						if(!raining)
						{
							setRaining(true);
						}
					}
				}
			}
			
			if(tutorialProgress == 18)
			{
				hud.setShowQuestFrame(true);
				hud.setQuestCurrent(15);
				if(!player.getInConversation() && player.getLocationX() > 6500 && !player.getFalling())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialCampFire(), 
							conversation.tutorialCampFireWhoTalks());
				}
			}
			
			if(tutorialProgress == 19)
			{
				hud.setQuestCurrent(16);
				if(player.getWarmth() >= 20 && player.getWet() < 10)
				{
					if(raining)
					{
						setRaining(false);
					}
					if(!player.getInConversation() && !player.getFalling())
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialWarmedUp(), 
								conversation.tutorialWarmedUpWhoTalks());
					}
					else if(player.getInConversation())
					{
						if(conversationState.getConversationTracker() == 1)
						{
							if(potion == null)
							{
								potion = spawnItem.spawnPotion(6570, 300, ItemData.Potions.Healing.toString(), 1);
								potion.initializeSpawning();
							}
						}
					}
				}
			}
			
			if(tutorialProgress == 20)
			{
				hud.setQuestCurrent(17);
				if(player.getInventory().hasItem(ItemData.Potions.Healing.toString()) != null)
				{
					hud.setShowQuestFrame(false);
					if(!player.getInConversation() && !player.getFalling())
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialHealingPotionTheChoice(), 
								conversation.tutorialHealingPotionTheChoiceWhoTalks());
					}
					else
					{
						if(player.getInConversation())
						{
							choiceMade = conversationState.getChoiceMade();
						}
					}
				}
			}
			
			if(tutorialProgress == 21)
			{
				if(choiceMade == 0)
				{
					System.out.println("Something is wrong");
				}
				if(choiceMade == 1)
				{
					if(!player.getInConversation() && !player.getFalling())
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialHealingPotionChoiceTaste(), 
								conversation.tutorialHealingPotionChoiceTasteWhoTalks());
					}
				}
				
				else if(choiceMade == 2)
				{
					if(!player.getInConversation() && !player.getFalling())
					{
						conversationState.startConversation(player, null, null, 
								conversation.tutorialHealingPotionChoiceUseful(), 
								conversation.tutorialHealingPotionChoiceUsefulWhoTalks());
					}
				}
			}
			
			
			if(tutorialProgress == 22)
			{
				if(!player.getInConversation() && !player.getFalling())
				{
					conversationState.startConversation(player, null, null, 
							conversation.tutorialHealingPotionDamage(), 
							conversation.tutorialHealingPotionDamageWhoTalks());
				}
				else if(player.getInConversation())
				{
					if(conversationState.getConversationTracker() == 3 && player.getHealth() == player.getMaxHealth())
					{
						player.hit(60, null);
					}
				}
			}
			
			if(tutorialProgress == 23)
			{
				hud.setQuestCurrent(18);
				hud.setShowQuestFrame(true);
				if(!player.getInConversation() && !player.getFalling())
				{
					if(player.getInventory().hasItem(ItemData.Potions.Healing.toString()) != null)
					{
						hud.setShowQuestFrame(false);
						conversationState.startConversation(player, null, null, 
								conversation.tutorialHealingPotionDrunk(), 
								conversation.tutorialHealingPotionDrunkWhoTalks());
					}

				}
			}
			
			if(tutorialProgress == 24)
			{
				System.out.println("The tutorial is over. <'-'>");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_P) 
		{
			player.setSpawnLocationX(4670);
			player.setSpawnLocationY(420);
			player.respawn();
			tutorialProgress = 14;
			return;
		}
		super.keyPressed(key);
	}
}