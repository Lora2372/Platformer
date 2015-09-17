package Entity.Player;


import Entity.Character;
import GameState.MainMap;
import TileMap.TileMap;

public class Player extends Character
{
	 
	//  Animations 
	
	
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	// Constructor
	public Player(TileMap tileMap, String name, double spawnX, double spawnY, MainMap level1state)
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
				-11, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				false,   															// facingRight
				true,  																// inControl
				100,	 															// health
				100,	 															//maxHealth
				50,		 															// healthCounter
				100,	 															// stamina
				100, 	 															// maxStamina
				25,		 															// staminaCounter
				800,																// sightRange
				5,	 	 															// punchCost
				5, 		 															// punchDamage
				80, 	 															// punchRange
				40,		 															// dashCost
				40,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				100,	 															// mana
				100,	 															// maxMana
				25,		 															// manaCounter
				20,		 															// smallFireballManaCost
				20,		 															// smallFireballDamage
				40,		 															// largeFireballManaCost
				50, 																// largeFireballDamage
				"/Sprites/Characters/Lora.png", 									// spritePath
				new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15},				// animationStates
				new int[]{6, 6, 1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},			// numImages
				0,																	// damageOnTouch
				true,																// friendly
				true,
				false,
				false,
				name,
				spawnX,
				spawnY,
				level1state
				
				);
		
		conversationbox = new ConversationBox(this);

		
	}
	
	public ConversationBox getConversationBox()
	{
		return conversationbox;
	}
}