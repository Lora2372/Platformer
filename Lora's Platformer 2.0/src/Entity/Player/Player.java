package Entity.Player;


import java.util.Random;

import Audio.JukeBox;
import Entity.Unit;
import GameState.MainMap;
import TileMap.TileMap;

public class Player extends Unit
{
	
	protected int numberofAttackSounds;
	protected int numberofHurtSounds;
	protected int numberofJumpSounds;
	
	protected int[] numberofSounds;
	
//	protected ArrayList<Projectile> a
	
	String[] soundTypes = new String[]
	{
		"Attack",
		"Hurt",
		"Jump"
	};
	
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
				120,
				5,	 	 															// punchCost
				1, 		 															// punchDamage
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
				30,																	// electricBallManaCost
				70,																	// electricBallDamage
				"/Sprites/Characters/Lora.png", 									// spritePath
				new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15},				// animationStates
				new int[]{6, 6, 1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},			// numImages
				new int[] { 400, 40, 100, 80, 125, 120, 100, 100, 100, 100, 100, 100, 500, 100, 100, 3000, 3000 },
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
		System.out.println("Running player");
		player = true;
		conversationbox = new ConversationBox(this);

		
		

		
		numberofSounds = new int[soundTypes.length];

		
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists("Female01Attack0" + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
	}
	
	public void iAmHit()
	{
		Random random = new Random();
		
		int max = numberofSounds[1];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes[1] + "0" + myRandom);
	}
	
	
	public void playJumpSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[2];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes[2] + "0" + myRandom);
	}
	
	public void playCastSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes[0] + "0" + myRandom);
	}
	
	public void playPunchSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes[0] + "0" + myRandom);
	}
	
	
	public ConversationBox getConversationBox()
	{
		return conversationbox;
	}
}