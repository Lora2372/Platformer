package Entity.Enemies;

import java.util.ArrayList;
import java.util.Random;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Character;
import GameState.MysteriousDungeon;
import Main.Content;

public class Fiona extends Character
{
	protected int cooldown;
	protected int timer;
	

	
	protected int moving = 0; // 0 don't move, 1 = move left, 2 = move 3
	
	protected boolean isHit = false;

	protected int numberofGrunts;
	
	
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
				"/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 400, 400, 400, 400, 125, 120, 100, 100, 100, 100, 100, 100, 500, 400, 400, 400, 400 }, // animationDelay
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
		cooldown = 600;
		
		
		flying = true;
		
		portrait = Content.PortraitLiadrin[0];
		
		setTennisPlayer(true);
				

		
	}
	

	
	public void iAmHit()
	{
		JukeBox.play("FionaGrunt07");
		if(!isHit)
		{
			setStunned(5000);
			isHit = true;
		}
	}
	
	public void playCastSound()
	{
		Random random = new Random();
		int myRandom = random.nextInt((2 - 1) + 1) + 1;
		JukeBox.play("FionaCast0" + myRandom);
	}
	
	public void playPunchSound()
	{
		JukeBox.play("FionaPunch01");
	}
	
//	public void playStunnedSound()
//	{
//		JukeBox.play("Grunt07");
//	}
	
	public void updateAI(ArrayList<Character> characterList)
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
		

		
		ArrayList<Character> enemiesDetected = detectEnemy(characterList, false);
		
		if(enemiesDetected != null)
		{
			if(enemiesDetected.size() > 0)
			{
				Character enemy = enemiesDetected.get(0);
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
		
		

		if( (electricball == null || electricball.getHit()) && directionX == 0 && directionY == 0)
		{
			timer++;
			if(timer > cooldown)
			{	
				timer = 0;
				electricballCasting = true;
			}
		}


		
	}
	
	

}
