package Entity.Enemies;

import TileMap.TileMap;
import Entity.Character;

public class Slug extends Character
{
	protected int turnTime;
	protected int turnTimer;
	
	public Slug(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			String name,
			double spawnX,
			double spawnY
			) 
	{
		super(
				tileMap,  															// TileMap
				60, 	 															// Width
				60, 	 															// Height
				40, 	 															// Collision width
				40, 	 															// Collision height
				0.1, 	 															// Move speed
				0.6, 	 															// Max speed
				0.1, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-4, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				1,		 															// health
				1, 		 															//maxHealth
				-1,		 															// healthCounter
				0,	 																// stamina
				0, 	 																// maxStamina
				-1,		 															// staminaCounter
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				0,		 															// mana
				0,		 															// maxMana
				-1,		 															// manaCounter
				0,		 															// smallFireballManaCost
				0,		 															// smallFireballDamage
				0,		 															// largeFireballManaCost
				0, 																	// largeFireballDamage
				"/Sprites/Enemies/slugger.gif",										// spritePath
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},						// animationStates
				new int[]{3},														// numImages
				10,																	// damageOnTouch
				friendly,															// friendly				
				name,
				spawnX,
				spawnY
				
				);
		
		turnTimer = 50;
		turnTime = 50;
		
		
		
	}
	public void updateAI()
	{
		//System.out.println("dx: " + dx + ", turnTimer: " + turnTimer);
		turnTimer++;
		if(dx == 0 && turnTimer > turnTime)
		{			
			turnTimer = 0;
			if(facingRight)
			{
				System.out.println("turning left");
				facingRight = false;
				right = false;
				left = true;
			}
			else
			{
				System.out.println("turning right");
				facingRight = true;
				left = false;
				right = true;
			}
			
		}
		
		if(turnTimer == 50)
		{
			System.out.println("FIRE THE FIREBALL!");
			castingSmallFireball = true;
		}
	}
	
	

}
