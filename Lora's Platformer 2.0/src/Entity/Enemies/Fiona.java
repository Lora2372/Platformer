package Entity.Enemies;

import java.util.ArrayList;
import java.util.Random;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Arcaneball;
import Entity.Unit;
import GameState.MysteriousDungeon;
import Main.Content;

public class Fiona extends Unit
{
	protected int cooldown;
	protected int timer;
	
	protected boolean arcaneballMode;
	protected int arcaneballMoving;
	protected int arcaneballTimer;
	protected int arcaneballCooldown;
	
	protected int moving = 0; // 0 don't move, 1 = move left, 2 = move 3
	
	protected boolean isHit = false;

	protected int[] numberofSounds;
	
	
	public enum soundTypes { Attack, Hurt, Jump, Chargeup}
	
//	String[] soundTypes = new String[]
//	{
//		"Attack",
//		"Hurt",
//		"Jump"
//	};
	
	
	
	
	public Fiona(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			String name,
			double spawnX,
			double spawnY,
			MysteriousDungeon mainMap
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
				20,		 															// smallFireballManaCost
				20,		 															// smallFireballDamage
				40,		 															// largeFireballManaCost
				50, 																	// largeFireballDamage
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
				false,
				name,
				spawnX,
				spawnY,
				mainMap
				);
		
		timer = 0;
		cooldown = 300;
		
		arcaneballTimer = 30;
		arcaneballCooldown = 30;
		
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
	
	public void updateAI(ArrayList<Unit> characterList)
	{

		// GANNONDORF TENNIS THIS SHIT!
		// She will hug the corners, channel up a new energy ball spell,
		// it will go towards the player, she's immune to all other attacks,
		// bounce the ball back to her until one of you fail!
		// She will also have another attack that you
		// can not bounce back, you need to avoid this attack,
		// this as to not make it too easy for the player as they need to move!
		
		//If the player moves to one corner, she moves to the other!
		
	
		
		if(isHit)
		{
			directionY += 0.1;

			if(!stunned)
			{
				isHit = false;
				flying = true;
			}
			
			return;
		}
		
		if(!inControl) return;
		

		
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
						if(locationX > 360) moving = 1;
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
			if(locationX >= 360)
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
			if(locationX <= 880) right = true;
			else
			{
				right = false;
				moving = 0;
			}
		}
		
		
		if(arcaneballMode && !stunned)
		{
			arcaneballTimer++;
			
			if(arcaneballTimer >= arcaneballCooldown)
			{
				arcaneballTimer = 0;
				
				double tempX = locationX;
				double tempY = locationY + 100;
				
				
				aim = Math.atan2(tempY - locationY, tempX - locationX);
				Arcaneball arcaneball = new Arcaneball(tileMap, mainMap, facingRight, up, down, aim, friendly, arcaneballDamage);
				arcaneball.setPosition(locationX, locationY - 20);
				mainMap.addProjectile(arcaneball);
			}
			
			if(arcaneballMoving == 0)
			{
				JukeBox.play("FionaChargeup02");
				if(locationX >= 360)
				{
					left = true;
					arcaneballMoving = 1;
				}
				else
				{
					left = false;
				}
				if(locationX <= 880)
				{
					right = true;
					arcaneballMoving = 2;
				}
			}
			else if(directionX == 0)
			{
				System.out.println("Unstable mode, disabled..");
				arcaneballMode = false;
				arcaneballMoving = 0;
				right = false;
				left = false;
				
				if(locationX > 360) moving = 1;
				else moving = 2;
			}
		}
		

		if( (electricball == null || electricball.getHit()) && directionX == 0 && directionY == 0)
		{
			timer++;
			if(timer > cooldown)
			{	
				timer = 0;
				
				if(mainMap.RNG(1, 2) == 1)
				{
					JukeBox.play("FionaChargeup01");
					this.setStunned(1000);
					
					arcaneballMode = true;
					System.out.println("Unstable mode engaged.");
					return;
				}
				
				electricballCasting = true;
			}
		}


		
	}
	
	

}
