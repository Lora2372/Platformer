package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Enemies.Succubus;
import Entity.Player.Conversation;
import Entity.Player.Player;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class Tutorial extends MainMap
{

	protected int tutorialProgress = 0;
	
	protected Succubus succubus;
	
	public Tutorial(GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player
			) 
	{
		super(gameStatemanager, 
				tileMap,
				player);
		
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
				if(player.getConversationBox().getConversationTracker() >= player.getConversationBox().getConversationLength())
				{
					player.getConversationBox().endConversation();
					tutorialProgress++;
				}
			}

			if(!player.getFalling())
				if(player.getx() < 2500)
					player.setSpawnPoint(1420, player.gety() - 50);
				else
					player.setSpawnPoint(4000, 320);
					
					
			if(tutorialProgress == 0 && !player.getFalling())
				if(!player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialWelcomeMessage, 
							Conversation.tutorialWelcomeMessageWhoTalks);
			
			if(tutorialProgress == 1)
				if(player.getx() - 400 > 200 || player.getx() - 400 < -200)
				if(player.getLeft() || player.getRight() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialMoveMessage, 
							Conversation.tutorialMoveMessageWhoTalks);
			
			if(tutorialProgress == 2)
				if(player.getJumping() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialJumpMessage, 
							Conversation.tutorialJumpMessageWhoTalks);
			
			if(tutorialProgress == 3)
				if(player.getx() > 1420 && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialHoleSmallMessage, 
							Conversation.tutorialHoleSmallMessageWhoTalks);
					
			if(tutorialProgress == 4)
				if(player.getx() > 1820 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialHoleSmallPassedMessage, 
							Conversation.tutorialHoleSmallPassedMessageWhoTalks);
			
			if(tutorialProgress == 5)
				if(player.getx() > 2600 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialMovementMasteredMessage, 
							Conversation.tutorialMovementMasteredMessageWhoTalks);
			
			if(tutorialProgress == 6)
				if(player.getx() > 2950 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialFireBallSmallMessage, 
							Conversation.tutorialFireBallSmallMessageWhoTalks);
			
			if(tutorialProgress == 7)
				if(player.getFireBallSmallDoneCasting() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialFireBallLargeMessage, 
							Conversation.tutorialFireBallLargeMessageWhoTalks);
			
			if(tutorialProgress == 8)
				if(player.getFireBallLargeDoneCasting() && player.getUp() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialDashMessage, 
							Conversation.tutorialDashMessageWhoTalks);
			
			if(tutorialProgress == 9)
				if(player.getDashing() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialPunchMessage, 
							Conversation.tutorialPunchMessageWhoTalks);
			
			if(tutorialProgress == 10)
				if(player.getPunching() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialPunchDoneMessage, 
							Conversation.tutorialPunchDoneMessageWhoTalks);
			
			
			if(tutorialProgress == 11)
				if(!player.getInConversation())
				{
					succubus = new Succubus(tileMap, false, false, false, false, false, "Rui", 3500, player.gety(), this);
					enemies.add(succubus);
					characterList.add(succubus);
					
					
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialEnemySuccubusMessage, 
							Conversation.tutorialEnemySuccubusMessageWhoTalks);
				}
			
			if(tutorialProgress == 12)
				if(succubus.isDead())
					if(!player.getInConversation())
						player.getConversationBox().startConversation(player, null, null, 
								Conversation.tutorialTempleEnterMessage, 
								Conversation.tutorialTempleEnterMessageWhoTalks);
					else
						if(player.getConversationBox().getConversationTracker() == 1)
						{
							JukeBox.play("Close");
							tileMap.loadMap("/Maps/TutorialB.map");
						}


			if(tutorialProgress == 13)
				if(!player.getInConversation() && player.getx() > 4000)
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialHoleLargeMessage, 
							Conversation.tutorialHoleLargeMessageWhoTalks);
			
			if(tutorialProgress == 14)
				if(!player.getInConversation() && player.getx() > 4500 && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, 
							Conversation.tutorialHoleLargePassedMessage, 
							Conversation.tutorialHoleLargePassedMessageWhoTalks);
			
			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}