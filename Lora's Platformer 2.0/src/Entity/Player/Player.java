package Entity.Player;


import java.util.Random;

import Audio.JukeBox;
import Entity.Unit;
import TileMap.TileMap;

public class Player extends Unit
{
	
	protected int numberofAttackSounds;
	protected int numberofHurtSounds;
	protected int numberofJumpSounds;
	
	protected int[] numberofSounds;
	
	protected HUD hud;
	
	protected boolean loaded = false;
	
//	protected ArrayList<Projectile> a
	
	public enum soundTypes { Attack, Hurt, Jump , Loot}
	
	protected String currentMap;
	
	protected Conversation conversation;
	
	//  Animations 
	
	
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	// Constructor
	public Player(String name, TileMap tileMap)
	{
		super(
				tileMap,															// TileMap
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
				0.005,	 																// healthRegen
				100,	 															// mana
				100,	 															// maxMana
				0.05,	 																// manaRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				0.05,	 																// staminaRegen
				800,																// sightRange
				120,
				5,	 	 															// punchCost
				1, 		 															// punchDamage
				80, 	 															// punchRange
				40,		 															// dashCost
				40,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				20,		 															// smallFireballManaCost
				20,		 															// smallFireballDamage
				40,		 															// largeFireballManaCost
				50, 																// largeFireballDamage
				30,																	// electricBallManaCost
				70,																	// electricBallDamage
				0,
				0,
				"/Art/Sprites/Characters/Lora.png", 									// spritePath
				new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15},				// animationStates
				new int[]{6, 6, 1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},			// numImages
				new int[] { 400, 40, 100, 80, 125, 120, 100, 100, 100, 100, 100, 100, 500, 100, 100, 3000, 3000 },
				0,																	// damageOnTouch
				true,																// friendly
				false,
				false,
				false,
				false,
				name,
				0,
				0,
				null
				
				);
		
		conversation = new Conversation(this);
		
		player = true;
		conversationbox = new ConversationBox(this);

		hud = new HUD(this);
		
		numberofSounds = new int[soundTypes.values().length];
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists("Female01" + soundTypes.values()[i] + "0" + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
	}
	
	public Conversation getConversation() { return conversation; }
	
	public String getCurrentMap() { return currentMap; }
	public void setCurrentMap(String newMap) { currentMap = newMap; }
	
	public boolean getLoaded() { return loaded; }
	public void setLoaded(boolean loaded) { this.loaded = loaded; }
	
	public HUD getHUD() { return hud; }
	
	public void iAmHit()
	{
		Random random = new Random();
		
		int max = numberofSounds[1];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes.Hurt + "0" + myRandom);
	}
	
	
	public void playJumpSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[2];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes.Jump + "0" + myRandom);
	}
	
	public void playCastSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes.Attack + "0" + myRandom);
	}
	
	public void playPunchSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[0];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes.Attack + "0" + myRandom);
	}
	
	public void playLootSound()
	{
		Random random = new Random();
		
		int max = numberofSounds[3];
		int min = 1;
		
		int myRandom = random.nextInt((max - min) + 1) + min;
		JukeBox.play("Female01" + soundTypes.Loot + "0" + myRandom);
				
	}
	
	
	public ConversationBox getConversationBox()
	{
		return conversationbox;
	}
}