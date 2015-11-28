package Entity.Unit;

import java.util.ArrayList;

import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Wolf extends Unit
{
	protected int attackCooldown;
	protected int attackTimer;
	
	public Wolf(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY,
			MainMap mainMap,
			String currentMap
			) 
	{
		super(
				tileMap,  															// TileMap
				110, 	 															// Width
				64, 	 															// Height
				100, 	 															// Collision width
				60, 	 															// Collision height
				0.1, 	 															// Move speed
				1.0, 	 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				50,		 															// health
				50,		 															//maxHealth
				0.005,	 															// healthRegen
				100,		 														// mana
				100,		 														// maxMana
				0.1,	 															// manaRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				0.1,	 															// staminaRegen
				500,																// sightRange
				120,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				30,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				"/Art/Sprites/Characters/Wolf.png",									// spritePath
				new int[] {0, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2, 2},						// animationStates
				new int[]{6, 6, 1, 1, 2, 3},												// numImages
				new int[] { 200, 300, 200, 200, 300, 200, 300, 200, 300, 200, 300, 200, 500},
				0,																	// damageOnTouch
				friendly,															// friendly			
				untouchable,
				invulnerable,
				unkillable,
				false,
				name,
				"Wolf",
				spawnLocationX,
				spawnLocationY,
				mainMap,
				currentMap
				);
		
		attackTimer = 200;
		attackCooldown = 200;
		
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		
		if(!inControl) return;
		
		if(directionX == 0 && attackTimer > 0)
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
		

		if(attackTimer >= attackCooldown * 10) attackTimer = attackCooldown;
		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, true);
		if(enemiesDetected != null)
		{
			if(enemiesDetected.size() > 0)
			{
				if(attackTimer >= attackCooldown)
				{
					attackTimer = 0;
					setDashing(true);
				}
			}
		}
	}
}