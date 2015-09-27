package Entity.Enemies;

import java.util.ArrayList;
import java.util.Random;

import Audio.JukeBox;
import Entity.Unit;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Wolf extends Unit
{
	protected int cooldown;
	protected int timer;
	
	public Wolf(
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
				5, 		 															//maxHealth
				30,		 															// healthCounter
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				500,																// sightRange
				120,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				30,		 															// dashDamage
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
				"/Sprites/Characters/Wolf.png",									// spritePath
				new int[] {0, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2, 2},						// animationStates
				new int[]{6, 6, 1, 1, 2, 3},												// numImages
				new int[] { 200, 300, 200, 200, 300, 200, 300, 200, 300, 200, 300, 200, 500},
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
		
		timer = 200;
		cooldown = 200;
		
//		portrait = Content.PortraitLiadrin[0];
	}
	
	public void iAmHit()
	{
//		JukeBox.play("FionaGrunt07");
	}
	
	public void playCastSound()
	{
//		Random random = new Random();
//		int myRandom = random.nextInt((2 - 1) + 1) + 1;
//		JukeBox.play("FionaCast0" + myRandom);
	}
	
	public void playPunchSound()
	{
//		JukeBox.play("FionaPunch01");
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		
		if(!inControl) return;
		
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
		

		if(timer >= cooldown * 10) timer = cooldown;
		
		ArrayList<Unit> enemiesDetected = detectEnemy(characterList, true);
		if(enemiesDetected != null)
		{
			if(enemiesDetected.size() > 0)
			{
				if(timer >= cooldown)
				{
					timer = 0;
					setDashing(true);
				}
			}
		}
		
	}
	
	

}