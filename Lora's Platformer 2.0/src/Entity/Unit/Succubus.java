package Entity.Unit;

import java.util.ArrayList;
import TileMap.TileMap;
import Audio.JukeBox;
import GameState.MainMap;
import Main.Content;

public class Succubus extends Unit
{
	protected int cooldown;
	protected int timer;
	
	protected int[] numberofSounds;
	public enum soundTypes { Attack, Hurt, Jump, Chargeup}
	
	public Succubus(
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
				50,		 															//maxHealth
				0.005,	 															// healthRegen
				100,		 														// mana
				100,		 														// maxMana
				0.1,	 															// manaRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				0.1,	 															// staminaRegen
				800,																// sightRange
				120,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				"/Art/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 400, 400, 400, 400, 125, 120, 100, 100, 100, 100, 100, 100, 500, 400, 400, 400, 400 }, // animationDelay
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
		
		this.unitType = "Succubus";
		timer = 0;
		cooldown = 300;
		
		
		portrait = Content.PortraitSuccubus[0];
		
		
		numberofSounds = new int[soundTypes.values().length];
		
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists("Succubus" + soundTypes.values()[i] + "0" + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
		
	}
	
	public void iAmHit()
	{
		try
		{
			int RNG = mainMap.RNG(1, numberofSounds[1]);

			JukeBox.play("Succubus" + soundTypes.Hurt + (RNG < 10 ? "0" : "") + RNG);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		setStunned(2000);
		
	}
	
	public void playCastSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		JukeBox.play("Succubus" + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playPunchSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		JukeBox.play("Succubus" + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
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
			if(enemiesDetected.get(0).getLocationX() > locationX) facingRight = true;
			else facingRight = false;
			
			if(timer > cooldown)
			{
				timer = 0;
//				System.out.println("FIRE THE FIREBALL!");
				fireBallLargeCasting = true;
			}
		}
		
		if(timer > cooldown * 10) timer = cooldown;
	}
	
	

}