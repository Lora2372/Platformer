package Entity.Enemies;

import java.util.ArrayList;
import java.util.Random;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Unit;
import GameState.MainMap;
import Main.Content;

public class Succubus extends Unit
{
	protected int cooldown;
	protected int timer;
	
	public Succubus(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			String name,
			double spawnX,
			double spawnY,
			MainMap level1state
			) 
	{
		super(
				tileMap,  															// TileMap
				100, 	 															// Width
				100, 	 															// Height
				80, 	 															// Collision width
				80, 	 															// Collision height
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
				120,
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
				"/Art/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 400, 400, 400, 400, 125, 120, 100, 100, 100, 100, 100, 100, 500, 400, 400, 400, 400 }, // animationDelay
				0,																	// damageOnTouch
				friendly,															// friendly			
				untouchable,
				invulnerable,
				false,
				name,
				spawnX,
				spawnY,
				level1state
				);
		
		timer = 0;
		cooldown = 300;
		
		
		portrait = Content.PortraitLiadrin[0];
	}
	
	public void iAmHit()
	{
		JukeBox.play("FionaGrunt07");
	}
	
	public void playCastSound()
	{
		Random random = new Random();
		int myRandom = random.nextInt((2 - 1) + 1) + 1;
		JukeBox.play("FionaCast0" + myRandom);
	}
	
	public void playPunchSound()
	{
		JukeBox.play("FionaPunch01");
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
//		if(!friendly) return;
		//System.out.println("dx: " + dx + ", turnTimer: " + turnTimer);
		
		
		
//		turnTimer++;
//		if(dx == 0 && turnTimer > turnTime)
//		{			
//			turnTimer = 0;
//			if(facingRight)
//			{
//				facingRight = false;
//				right = false;
//				left = true;
//			}
//			else
//			{
//				facingRight = true;
//				left = false;
//				right = true;
//			}
//			
//		}
		
		if(!inControl) return;
		
		timer++;
		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, false);
		
		if(enemiesDetected.size() > 0)
		{
			if(enemiesDetected.get(0).getx() > locationX) facingRight = true;
			else facingRight = false;
			
			if(timer > cooldown)
			{
				timer = 0;
//				System.out.println("FIRE THE FIREBALL!");
				fireballLargeCasting = true;
			}
		}
		
		if(timer > cooldown * 10) timer = cooldown;
	}
	
	

}