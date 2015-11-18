package Entity.Unit;

import java.util.ArrayList;
import GameState.MainMap;
import TileMap.TileMap;

public class Skeleton extends Unit
{
	protected int cooldown;
	protected int timer;
	
	
	protected int slice;
	
	public Skeleton(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
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
				70, 	 															// Collision width
				108, 	 															// Collision height
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
				50, 		 														//maxHealth
				0.005,	 															// healthRegen
				100,		 														// mana
				100,		 														// maxMana
				0.1,	 															// manaRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				0.1,	 															// staminaRegen
				100,																// sightRange
				120,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				30,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				"/Art/Sprites/Characters/Skeleton.png",									// spritePath
				new int[] {0, 0, 0, 0, 1, 2, 4, 5, 4, 5, 4, 3, 2, 2},						// animationStates
				new int[]{6, 1, 3, 6, 4, 4},												// numImages
				new int[] { 120, 120, 120, 120, 500, 120, 120, 120, 120, 120, 120, 120, 120},
				0,																	// damageOnTouch
				friendly,															// friendly			
				untouchable,
				invulnerable,
				unkillable,
				false,
				name,
				spawnX,
				spawnY,
				level1state
				);
		
		this.unitType = "Skeleton";
		timer = 200;
		cooldown = 200;
		
		slice = 0;
		
		
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
	
	
	public void update(ArrayList<Unit> characterList)
	{
		if(customAnimation)
		{
			if(slice == 0)
			{
				slice = mainMap.RNG(1,  1);
				if(slice == 1)
				{
					inControl = false;
					currentAction = animationState[4];
					animation.setFrames(sprites.get(animationState[4]));
					animation.setDelay(animationDelay[4]);
					if(directionY == 0) directionX = 0;
				}

			}
			else if(animation.hasPlayedOnce())
			{
				if(slice == 1)
				{
					// Play sound
					currentAction = animationState[5];
					animation.setFrames(sprites.get(animationState[5]));
					animation.setDelay(animationDelay[5]);
					slice = -1;
				}
				
				else if(slice == -1)
				{
					if(animation.hasPlayedOnce())
					{
						inControl = true;
						slice = 0;
						customAnimation = false;
					}
				}
			}
			animation.update();
		}
		
		super.update(characterList);
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		
		if(!inControl || falling) return;
		
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
					customAnimation = true;
				}
			}
		}
		
	}
}