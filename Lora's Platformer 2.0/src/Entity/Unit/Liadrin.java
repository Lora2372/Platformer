package Entity.Unit;
import java.util.ArrayList;

import TileMap.TileMap;
import Entity.Player.Player;
import GameState.MainMap.MainMap;
import Main.Content;
public class Liadrin extends Unit
{
	
	protected boolean active;
	
	
	public Liadrin
		(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			double spawnLocationX,
			double spawnLocationY,
			MainMap mainMap
		) 
	{
		super
			(
				tileMap,  															// TileMap
				100, 	 															// Width
				100, 	 															// Height
				100, 	 															// Collision width
				100, 	 															// Collision height
				0.1, 	 															// Move speed
				1.0, 	 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				5,		 															// health
				5, 		 															//maxHealth
				30,		 															// healthCounter
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				800,																// sightRange
				0,
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
				"/Art/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 400, 400, 400, 400, 125, 120, 100, 100, 300, 300, 300,300, 500, 400, 400, 400, 400 }, // animationDelay
				0,																	// damageOnTouch
				friendly,															// friendly				
				untouchable,
				invulnerable,
				unkillable,
				true,
				"Liadrin",
				"Liadrin",
				spawnLocationX,
				spawnLocationY,
				mainMap
			);

		active = false;
		
		portrait = Content.PortraitSuccubus[0];
	}
	

	
	public void interact(Player player)
	{
		
		// Use the unit in its respective map
		mainMap.useUnit(this);
	}
	
	public void update(ArrayList<Unit> characterList)
	{
		super.update(characterList);
	}
}
