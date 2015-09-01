package Entity;

import java.util.ArrayList;

import TileMap.TileMap;

public class Player extends Character
{
	 
	//  Animations 
	
	
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	// Constructor
	public Player(TileMap tileMap, String name, double spawnX, double spawnY)
	{
		super(
				tileMap,  															// TileMap
				72, 	 															// Width
				120, 	 															// Height
				50, 	 															// Collision width
				100, 	 															// Collision height
				0.3, 	 															// Move speed
				3.2, 	 															// Max speed
				0.3, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				false,   															// facingRight
				true,  																// inControl
				100,	 															// health
				100,	 															//maxHealth
				50,		 															// healthCounter
				100,	 															// stamina
				100, 	 															// maxStamina
				50,		 															// staminaCounter
				40, 	 															// punchCost
				1, 		 															// punchDamage
				40, 	 															// punchRange
				40,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				100,	 															// mana
				100,	 															// maxMana
				50,		 															// manaCounter
				10,		 															// smallFireballManaCost
				4,		 															// smallFireballDamage
				40,		 															// largeFireballManaCost
				8, 																	// largeFireballDamage
				"/Sprites/Player/Lora.png", 										// spritePath
				new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15},				// animationStates
				new int[]{6, 6, 1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},			// numImages
				0,																	// damageOnTouch
				true,																// friendly
				name,
				spawnX,
				spawnY
				);
		
	}
}