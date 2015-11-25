package GameState.Maps;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Player.HUD;
import Entity.Player.Player;
import Entity.Unit.Succubus;
import GameState.GameStateManager;
import GameState.MainMap;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class Tutorial extends MainMap
{

	protected int tutorialProgress = 0;
	
	protected Succubus succubus;
	
	protected Conversation conversation;
	
	protected HUD hud;
	
	protected boolean alreadyDoneThis = false;
	
	public Tutorial(GameStateManager gameStatemanager,
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
		conversation = player.getConversation();
		
		player.setTileMap(tileMap);
		player.setCurrentMap("DeepWoods");
		
		spawnTorch(3810, 400);
		spawnTorch(4650, 400);
		
		if(!player.getLoaded())
		{
			player.setPosition(400, 200);
			player.setSpawnPoint(400, 200);
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		player.setSpawning(true);
		
		player.getHUD().setShowQuestFrame(true);
		player.setUnkillable(true);
		doneInitializing = true;
	}
	
	public void update()
	{
		super.update();
		try
		{
			if(player.getInConversation())
			{
				if(player.getConversationState().getConversationTracker() >= player.getConversationState().getConversationLength())
				{
					player.getConversationState().endConversation();
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
					player.getConversationState().startConversation(player, null, null,
							conversation.tutorialWelcomeMessage, 
							conversation.tutorialWelcomeMessageWhoTalks);
					
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
						player.getConversationState().startConversation(player, null, null, 
								conversation.tutorialMoveMessage, 
								conversation.tutorialMoveMessageWhoTalks);
						
						
					}
				}
			}
			
			if(tutorialProgress == 2)
			{
				hud.setQuestCurrent(2);
				if(player.getJumping() && !player.getInConversation())
				{
					
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialJumpMessage, 
							conversation.tutorialJumpMessageWhoTalks);
				}
			}
			
			
			if(tutorialProgress == 3)
			{
				hud.setQuestCurrent(3);
				if(player.getLocationX() > 1420 && !player.getInConversation())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleSmallMessage, 
							conversation.tutorialHoleSmallMessageWhoTalks);
				}
			}		
			if(tutorialProgress == 4)
			{	
				hud.setQuestCurrent(4);
				if(player.getLocationX() > 1820 && !player.getInConversation() && !player.getFalling())
				{
					hud.setShowQuestFrame(false);
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleSmallPassedMessage, 
							conversation.tutorialHoleSmallPassedMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 5)
			{
				if(player.getLocationX() > 2600 && !player.getInConversation() && !player.getFalling())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialMovementMasteredMessage, 
							conversation.tutorialMovementMasteredMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 6)
			{	
				if(player.getLocationX() > 2950 && !player.getInConversation() && !player.getFalling())
				{	
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialFireBallSmallMessage, 
							conversation.tutorialFireBallSmallMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 7)
			{
				hud.setQuestCurrent(5);
				hud.setShowQuestFrame(true);
				if(player.getFireBallSmallDoneCasting() && !player.getInConversation())
				{	
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialFireBallLargeMessage, 
							conversation.tutorialFireBallLargeMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 8)
			{	
				hud.setQuestCurrent(6);
				if(player.getFireBallLargeDoneCasting() && player.getUp() && !player.getInConversation())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialDashMessage, 
							conversation.tutorialDashMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 9)
			{
				hud.setQuestCurrent(7);
				if(player.getDashing() && !player.getInConversation())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialPunchMessage, 
							conversation.tutorialPunchMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 10)
			{
				hud.setQuestCurrent(8);
				if(player.getPunching() && !player.getInConversation())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialPunchDoneMessage, 
							conversation.tutorialPunchDoneMessageWhoTalks);
				}
			}
			
			
			if(tutorialProgress == 11)
			{
				if(!player.getInConversation())
				{
					hud.setQuestCurrent(9);
					succubus = spawnSuccubus(3500, player.getLocationY(), false, "Tutorial");
					characterList.add(succubus);
					
					
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialEnemySuccubusMessage, 
							conversation.tutorialEnemySuccubusMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 12)
			{
				if(succubus.isDead())
				{
					if(!player.getInConversation())
						player.getConversationState().startConversation(player, null, null, 
								conversation.tutorialTempleEnterMessage, 
								conversation.tutorialTempleEnterMessageWhoTalks);
					else
						if(player.getConversationState().getConversationTracker() == 1)
						{
							if(!alreadyDoneThis)
							{
								alreadyDoneThis = true;
								JukeBox.play("Close");
								tileMap.loadMap("/Maps/TutorialB.map");
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
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleLargeMessage, 
							conversation.tutorialHoleLargeMessageWhoTalks);
				}
			}
			
			if(tutorialProgress == 14)
			{
				hud.setQuestCurrent(11);
				if(!player.getInConversation() && player.getLocationX() > 4500 && !player.getFalling())
				{
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleLargePassedMessage, 
							conversation.tutorialHoleLargePassedMessageWhoTalks);
				}
			}
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}