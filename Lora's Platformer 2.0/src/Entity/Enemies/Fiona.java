package Entity.Enemies;

import java.util.ArrayList;

import TileMap.TileMap;
import Entity.Character;
import GameState.MysteriousDungeon;
import Main.Content;

public class Fiona extends Character
{
	protected int cooldown;
	protected int timer;
	
	protected int moveTimer;
	protected int moveCooldown;
	protected boolean moving;
	
	
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
				50,		 															// health
				5, 		 															//maxHealth
				30,		 															// healthCounter
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				800,																// sightRange
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
				"/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,0,0,0,0,1,2,4,2,4,0,0,0,0},				// animationStates
				new int[]{7, 2, 6, 2, 1, 4, 4, 1, 6},								// numImages
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
		cooldown = 200;
		
		flying = true;
		
		portrait = Content.PortraitLiadrin[0];
		
		moveCooldown = 400;
//		moving = true;
//		right = true;
//		up = true;
	}
	
	
	
	public void updateAI(ArrayList<Character> characterList)
	{
		if(!inControl) return;

		
		// GANNONDORF TENNIS SHIT SHIT!
		// She will hug the corners, channel up a new energy ball spell,
		// it will go towards the player, she's immune to all other attacks,
		// bounce the ball back to her until one of you fail!
		// She will also have another attack that you
		// can not bounce back, you need to avoid this attack,
		// this as to not make it too easy for the player as they need to move!
		
		//If the player moves to one corner, she moves to the other!
		
		

		moveTimer++;
		
		if(moveTimer > 500)
		{
			moveTimer = 0;
			if(up)
			{
				down = true;
				up = false;
			}
			else
			{
				down = false;
				up = true;
			}
		}
		
		
		for(int i = 0; i < characterList.size(); i++)
		{
			if(characterList.get(i).isPlayer())
			{
				if(characterList.get(i).getx() > locationX)
					facingRight = true;
				else facingRight = false;
				break;
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
//				castingLargeFireball = true;
				fireballMediumCasting = true;
			}
		}
		
		if(timer > cooldown * 10) timer = cooldown;
	}
	
	

}
