package Entity.Unit;

import java.util.ArrayList;

import TileMap.TileMap;
import GameState.MainMap;

public class Slug extends Unit
{
	protected int cooldown;
	protected int timer;
	
	public Slug(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
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
				spawnX,
				spawnY,
				mainMap
				
				);
		
		this.unitType = "Slug";
		timer = 0;
		cooldown = 100;
		
		
		
	}
	
	

	
	
	public void updateAI(ArrayList<Unit> characterList)
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
		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, true);
		
		if(enemiesDetected.size() > 0)
		{
			if(timer > cooldown)
			{
				timer = 0;
//				System.out.println("FIRE THE FIREBALL!");
				fireBallSmallCasting = true;
			}
		}
		
		if(timer > cooldown * 10) timer = cooldown;
		

	}
	
	

}
