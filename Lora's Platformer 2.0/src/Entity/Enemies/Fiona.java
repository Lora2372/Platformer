package Entity.Enemies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.ArcaneBall;
import Entity.Unit;
import Entity.Doodad.SummoningEffect;
import Entity.Player.Conversation;
import Entity.Player.Player;
import GameState.MysteriousDungeon;
import Main.Content;

public class Fiona extends Unit
{
	protected int cooldown;
	protected int timer;
	
	protected boolean arcaneBallMode;
	protected int arcaneBallMoving;
	protected int arcaneBallTimer;
	protected int arcaneBallCooldown;
	
	protected int moving = 0; // 0 don't move, 1 = move left, 2 = move 3
	protected int leftX = 240;
	protected int rightX = 940;
	
	protected boolean isHit = false;


	
	protected boolean defeated;
	protected boolean used;
	protected boolean conversationOver;
	protected int conversationProgress = 0;
	
	protected Player player;
	
	protected int[] numberofSounds;
	public enum soundTypes { Attack, Hurt, Jump, Chargeup}
	
	protected MysteriousDungeon mysteriousDungeon;
	
	public Fiona(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnX,
			double spawnY,
			MysteriousDungeon mysteriousDungeon,
			Player player
			) 
	{
		super(
				tileMap,  															// TileMap
				100, 	 															// Width
				100, 	 															// Height
				80, 	 															// Collision width
				80, 	 															// Collision height
				0.5, 	 															// Move speed
				5.0, 	 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				500,		 															// health
				500, 		 															//maxHealth
				0,		 															// healthRegen
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				2000,																// sightRange
				2000,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				100,		 															// mana
				100,		 															// maxMana
				30,		 															// manaCounter
				20,		 															// smallFireBallManaCost
				20,		 															// smallFireBallDamage
				40,		 															// largeFireBallManaCost
				50, 																	// largeFireBallDamage
				30,																	// electricBallManaCost
				70,																	// electricBallDamage
				0,
				40,
				"/Art/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 200, 200, 200, 200, 125, 120, 100, 100, 100, 100, 100, 100, 500, 200, 200, 200, 200 }, // animationDelay
				0,																	// damageOnTouch
				friendly,															// friendly			
				untouchable,
				invulnerable,
				unkillable,
				false,
				name,
				spawnX,
				spawnY,
				mysteriousDungeon
				);
		
		this.player = player;
		this.mysteriousDungeon = mysteriousDungeon;
		timer = 0;
		cooldown = 300;
		
		arcaneBallTimer = 30;
		arcaneBallCooldown = 30;
		
		flying = true;
		
		portrait = Content.PortraitLiadrin[0];
		
		setTennisPlayer(true);
				

		numberofSounds = new int[soundTypes.values().length];

		
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists("Fiona" + soundTypes.values()[i] + "0" + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
	}
	

	
	public void iAmHit()
	{
		Random random = new Random();
		
		int max = numberofSounds[1];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Fiona" + soundTypes.Hurt + "0" + myRandom);
		if(!isHit)
		{
			setStunned(5000);
			isHit = true;
		}
	}
	
	
	
	public void playJumpSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[2];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Fiona" + soundTypes.Jump + "0" + myRandom);
	}
	
	public void playCastSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Fiona" + soundTypes.Attack + "0" + myRandom);
	}
	
	public void playPunchSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Fiona" + soundTypes.Attack + "0" + myRandom);
	}
	
//	public void playStunnedSound()
//	{
//		JukeBox.play("Grunt07");
//	}
	
	public void castArcaneBall()
	{
		double tempX = locationX;
		double tempY = locationY + 100;
		
		
		aim = Math.atan2(tempY - locationY, tempX - locationX);
		ArcaneBall arcaneBall = new ArcaneBall(tileMap, mainMap, facingRight, up, down, aim, friendly, arcaneBallDamage);
		arcaneBall.setPosition(locationX, locationY - 20);
		mainMap.addProjectile(arcaneBall);
	}
	
	public void startConversation()
	{
		JukeBox.stop("MysteriousBattle");
		JukeBox.loop("MysteriousConversation");
		used = true;
		System.out.println("Engaging conversation");
		player.getConversationBox().startConversation(player, this, null, Conversation.fionaDefeated, Conversation.fionaDefeatedWhoTalks);
	}
	
	
	public void updateAI(ArrayList<Unit> characterList)
	{

		// GANNONDORF TENNIS THIS SHIT!
		// She will hug the corners, channel up a new energy Ball spell,
		// it will go towards the player, she's immune to all other attacks,
		// bounce the Ball back to her until one of you fail!
		// She will also have another attack that you
		// can not bounce back, you need to avoid this attack,
		// this as to not make it too easy for the player as they need to move!
		
		//If the player moves to one corner, she moves to the other!
		
		if(used)
		{
			if(!conversationOver)
			{
				if(player.getConversationBox().getConversationTracker() == 3)
				{
					if(summoningEffect == null && conversationProgress == 0)
					{
						summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
						mainMap.addEffect(summoningEffect);
						JukeBox.play("Teleport");
						player.getHUD().removeBoss();
						player.getConversationBox().lockConversation(true);
						conversationProgress = 1;
					}
				}
				
				if(player.getConversationBox().getConversationTracker() == 5 && conversationProgress != 3)
				{
					conversationProgress = 3;
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
					tileMap.loadMap("/Maps/MysteriousDungeonC.map");
					tileMap.setPosition(0, 0);
					mysteriousDungeon.setDefeated(true);
				}
				
				if(player.getConversationBox().getConversationTracker() >= Conversation.fionaDefeated.length)
				{
					player.getConversationBox().endConversation();
				}
			}
		}
		if(summoningEffect != null)
		{
			if(summoningEffect.removeMe())
			{
				summoningEffect = null;
				player.getConversationBox().lockConversation(false);
				hidden = true;
				conversationProgress = 2;
			}
		}
		
		if(isHit)
		{
			directionY += 0.1;

			if(!stunned)
			{
				isHit = false;
				flying = true;
			}
			
			if(health <= 0)
			{
				defeated = true;
				health = 1;
				if(!used)
				{
					startConversation();
				}
				
			}
			
			return;
		}
		
		if(!inControl || defeated) return;
		

		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, false);
		
		if(enemiesDetected != null)
		{
			if(enemiesDetected.size() > 0)
			{
				Unit enemy = enemiesDetected.get(0);
				if(enemy.getx() > locationX) facingRight = true;
				else facingRight = false;
				
				
				if(locationY > 210)
				{
					directionY -= 0.1;
					untouchable = true;
				}
				else
				{
					
					if(directionY != 0)
					{
						untouchable = false;
						directionY = 0;
						locationY = 210;
						if(locationX > leftX) moving = 1;
						else moving = 2;
					}
				}
				
				if(locationY < 210)
				{
					locationY = 210;
				}
			}

		}
		
		
		if(moving == 1)
		{
			if(locationX >= leftX)
			{
				left = true;
			}
			else 
			{
				left = false;
				moving = 0;
			}
		}
		else if(moving == 2)
		{
			if(locationX <= rightX) right = true;
			else
			{
				right = false;
				moving = 0;
			}
		}
		
		
		if(arcaneBallMode && !stunned)
		{
			arcaneBallTimer++;
			
			if(arcaneBallTimer >= arcaneBallCooldown)
			{
				arcaneBallTimer = 0;
				
				castArcaneBall();
			}
			
			if(arcaneBallMoving == 0)
			{
				JukeBox.play("FionaChargeup02");
				if(locationX >= leftX)
				{
					left = true;
					arcaneBallMoving = 1;
				}
				else
				{
					left = false;
				}
				if(locationX <= rightX)
				{
					right = true;
					arcaneBallMoving = 2;
				}
			}
			else if(directionX == 0)
			{
				castArcaneBall();
				
				System.out.println("Unstable mode, disabled..");
				arcaneBallMode = false;
				arcaneBallMoving = 0;
				right = false;
				left = false;
				
				if(locationX > leftX) moving = 1;
				else moving = 2;
			}
		}
		

		if( !hidden &&((electricBall == null || electricBall.getHit()) && directionX == 0 && directionY == 0))
		{
			timer++;
			if(timer > cooldown)
			{	
				timer = 0;
				
				if(mainMap.RNG(1, 2) == 1)
				{
					JukeBox.play("FionaChargeup01");
					this.setStunned(1000);
					
					arcaneBallMode = true;
					System.out.println("Unstable mode engaged.");
					return;
				}
				
				electricBallCasting = true;
			}
		}


		
	}
	
	

}
