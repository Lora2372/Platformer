package Entity.Unit;

import java.util.ArrayList;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Bunny extends Unit
{
	
	protected int hopTimer;
	protected int hopCooldown;
	
	public Bunny
		(
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
		super
			(
				tileMap,  															// TileMap
				32, 	 															// Width
				32, 	 															// Height
				32, 	 															// Collision width
				32, 	 															// Collision height
				0.1, 	 															// Move speed
				2, 		 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				500,		 														// health
				500, 		 														//maxHealth
				0,		 															// healthRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				30,		 															// staminaRegen
				100,		 														// mana
				100,		 														// maxMana
				30,		 															// manaRegen
				2000,																// sightRange
				2000,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				"/Art/Sprites/Characters/Bunny.png",									// spritePath
				new int[] {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},						// animationStates
				new int[]{1, 3, 0, 0, 0, 0, 0, 0, 0},								// numImages
				new int[] { 200, 300, 200, 200, 300, 200, 300, 200, 300, 200, 300, 200, 500},
				0,																	// damageOnTouch
				friendly,															// friendly			
				untouchable,
				invulnerable,
				unkillable,
				false,
				name,
				"Bunny",
				spawnLocationX,
				spawnLocationY,
				mainMap
			);
		
		
		hopTimer = 0;
		hopCooldown = 300;
		
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		if(!inControl) return;
		
		hopTimer++;
		
		if(hopTimer > hopCooldown)
		{
			if(left)
			{
				left = false;
			}
			else if(right)
			{
				right = false;
			}
			else
			{
				facingRight = (mainMap.RNG(1, 2) == 1 ? true : false);
				
				right = facingRight;
				left = !facingRight;
			}
			hopTimer = 0;
		}
		
	}

}
