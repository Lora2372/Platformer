package Entity.Enemies;

import java.util.ArrayList;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Character;
import Entity.ElectricBall;
import Entity.Doodad.FireballMediumChargeUp;
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
				80, 	 															// Collision width
				80, 	 															// Collision height
				0.5, 	 															// Move speed
				5.0, 	 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				500,		 															// health
				500, 		 															//maxHealth
				30,		 															// healthCounter
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				2000,																// sightRange
				2000,
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
				30,																	// electricBallManaCost
				70,																	// electricBallDamage
				"/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
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
		cooldown = 600;
		
		flying = true;
		
		portrait = Content.PortraitLiadrin[0];
		
		setTennisPlayer(true);
		
		moveCooldown = 400;
//		moving = true;
//		right = true;
//		up = true;
	}
	

	
	
	
	public void updateAI(ArrayList<Character> characterList)
	{
		if(!inControl) return;

		
		// GANNONDORF TENNIS THIS SHIT!
		// She will hug the corners, channel up a new energy ball spell,
		// it will go towards the player, she's immune to all other attacks,
		// bounce the ball back to her until one of you fail!
		// She will also have another attack that you
		// can not bounce back, you need to avoid this attack,
		// this as to not make it too easy for the player as they need to move!
		
		//If the player moves to one corner, she moves to the other!
		
		
		ArrayList<Character> enemiesDetected = detectEnemy(characterList, false);
		
		if(enemiesDetected != null)
		{
			if(enemiesDetected.size() > 0)
			{
				if(enemiesDetected.get(0).getx() > locationX) facingRight = true;
				else facingRight = false;
			}

		}
		
//		if(moving)
//		{
//			ArrayList<Character> enemiesDetected = detectEnemy(characterList);
//			
//			if(enemiesDetected != null)
//			{
//				if(enemiesDetected.size() > 0)
//				{
//					Character enemy = enemiesDetected.get(0);
//					
//					if(!right && !left)
//						right = true;
//					
//					if(enemy.getx() > locationX)
//					{
//
//						if(right)
//						{
//							left = true;
//							right = false;
//						}
//						
//					}
//					else
//					{
//						if(left)
//						{
//							left = false;
//							right = true;
//						}
//					}
//				}
//			}
//		}
//		else
//		{
//			moveTimer++;
//			if(moveTimer > moveCooldown)
//			{
//				moving = true;
//				moveTimer = 0;
//			}
//		}
		
		

		if(electricball == null || electricball.getHit())
		{
			timer++;
			if(timer > cooldown)
			{
				right = false;
				left = false;
				
				moving = false;
				moveTimer = 0;
				timer = 0;
				electricballCasting = true;
			}
		}


		
	}
	
	

}
