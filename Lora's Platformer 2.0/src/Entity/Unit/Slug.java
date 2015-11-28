package Entity.Unit;

import java.util.ArrayList;

import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Slug extends Unit
{
	protected int attackCooldown;
	protected int attackTimer;
	
	public Slug(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY,
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
				0,		 															// healthCounter
				0,		 															// mana
				0,		 															// maxMana
				0,		 															// manaRegen
				0,	 																// stamina
				0, 	 																// maxStamina
				0,		 															// staminaRegen
				800,																// sightRange
				60,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				"/Art/Sprites/Characters/slugger.gif",									// spritePath
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},						// animationStates
				new int[]{3},														// numImages
				new int[] { 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 },
				10,																	// damageOnTouch
				friendly,															// friendly				
				untouchable,
				invulnerable,
				unkillable,
				false,
				name,
				"Slug",
				spawnLocationX,
				spawnLocationY,
				mainMap
				
				);
		
		attackTimer = 0;
		attackCooldown = 100;
		
		
		
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		
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
		
		attackTimer++;
		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, true);
		
		if(enemiesDetected.size() > 0)
		{
			if(attackTimer > attackCooldown)
			{
				attackTimer = 0;
				fireBallSmallCasting = true;
			}
		}
		
		if(attackTimer > attackCooldown * 10) attackTimer = attackCooldown;
		

	}
	
	

}
