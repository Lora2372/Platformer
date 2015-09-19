package Entity.Enemies;

import java.util.ArrayList;

import TileMap.TileMap;
import Entity.Character;
import GameState.MainMap;

public class Slug extends Character
{
	protected int cooldown;
	protected int timer;
	
	public Slug(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			String name,
			double spawnX,
			double spawnY,
			MainMap mainMap
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
				800,																// sightRange
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
				20,		 															// smallFireballDamage
				0,		 															// largeFireballManaCost
				0, 																	// largeFireballDamage
				"/Sprites/Characters/slugger.gif",									// spritePath
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},						// animationStates
				new int[]{3},														// numImages
				10,																	// damageOnTouch
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
		cooldown = 100;
		
		
		
	}
	
	

	
	
	public void updateAI(ArrayList<Character> characterList)
	{
		//System.out.println("directionX: " + directionX + ", turnTimer: " + turnTimer);

		
		if(directionX == 0)
		{
			if(facingRight)
			{
				facingRight = false;
				right = false;
				left = true;
			}
			else
			{
				facingRight = true;
				left = false;
				right = true;
			}
			
		}
		
		timer++;
		
		ArrayList<Character> enemiesDetected = detectEnemy(characterList);
		
		if(enemiesDetected.size() > 0)
		{
			if(timer > cooldown)
			{
				timer = 0;
//				System.out.println("FIRE THE FIREBALL!");
				fireballSmallCasting = true;
			}
		}
		
		if(timer > cooldown * 10) timer = cooldown;
		

	}
	
	

}
