package GameState.Maps;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import Entity.Unit.Succubus;
import GameState.GameStateManager;
import GameState.MainMap;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class Tutorial extends MainMap
{

	protected int tutorialProgress = 0;
	
	protected Succubus succubus;
	
	protected Conversation conversation;
	
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
				if(!player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialWelcomeMessage, 
							conversation.tutorialWelcomeMessageWhoTalks);
			
			if(tutorialProgress == 1)
				if(player.getLocationX() - 400 > 200 || player.getLocationX() - 400 < -200)
				if(player.getLeft() || player.getRight() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialMoveMessage, 
							conversation.tutorialMoveMessageWhoTalks);
			
			if(tutorialProgress == 2)
				if(player.getJumping() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialJumpMessage, 
							conversation.tutorialJumpMessageWhoTalks);
			
			if(tutorialProgress == 3)
				if(player.getLocationX() > 1420 && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleSmallMessage, 
							conversation.tutorialHoleSmallMessageWhoTalks);
					
			if(tutorialProgress == 4)
				if(player.getLocationX() > 1820 && !player.getInConversation() && !player.getFalling())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleSmallPassedMessage, 
							conversation.tutorialHoleSmallPassedMessageWhoTalks);
			
			if(tutorialProgress == 5)
				if(player.getLocationX() > 2600 && !player.getInConversation() && !player.getFalling())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialMovementMasteredMessage, 
							conversation.tutorialMovementMasteredMessageWhoTalks);
			
			if(tutorialProgress == 6)
				if(player.getLocationX() > 2950 && !player.getInConversation() && !player.getFalling())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialFireBallSmallMessage, 
							conversation.tutorialFireBallSmallMessageWhoTalks);
			
			if(tutorialProgress == 7)
				if(player.getFireBallSmallDoneCasting() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialFireBallLargeMessage, 
							conversation.tutorialFireBallLargeMessageWhoTalks);
			
			if(tutorialProgress == 8)
				if(player.getFireBallLargeDoneCasting() && player.getUp() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialDashMessage, 
							conversation.tutorialDashMessageWhoTalks);
			
			if(tutorialProgress == 9)
				if(player.getDashing() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialPunchMessage, 
							conversation.tutorialPunchMessageWhoTalks);
			
			if(tutorialProgress == 10)
				if(player.getPunching() && !player.getInConversation())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialPunchDoneMessage, 
							conversation.tutorialPunchDoneMessageWhoTalks);
			
			
			if(tutorialProgress == 11)
				if(!player.getInConversation())
				{
					succubus = new Succubus(tileMap, false, false, false, false, false, "Rui", 3500, player.getLocationY(), this);
					enemies.add(succubus);
					characterList.add(succubus);
					
					
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialEnemySuccubusMessage, 
							conversation.tutorialEnemySuccubusMessageWhoTalks);
				}
			
			if(tutorialProgress == 12)
				if(succubus.isDead())
					if(!player.getInConversation())
						player.getConversationState().startConversation(player, null, null, 
								conversation.tutorialTempleEnterMessage, 
								conversation.tutorialTempleEnterMessageWhoTalks);
					else
						if(player.getConversationState().getConversationTracker() == 1)
						{
							JukeBox.play("Close");
							tileMap.loadMap("/Maps/TutorialB.map");
						}


			if(tutorialProgress == 13)
				if(!player.getInConversation() && player.getLocationX() > 4000)
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleLargeMessage, 
							conversation.tutorialHoleLargeMessageWhoTalks);
			
			if(tutorialProgress == 14)
				if(!player.getInConversation() && player.getLocationX() > 4500 && !player.getFalling())
					player.getConversationState().startConversation(player, null, null, 
							conversation.tutorialHoleLargePassedMessage, 
							conversation.tutorialHoleLargePassedMessageWhoTalks);
			
			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}