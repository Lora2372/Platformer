package Entity.Player;

import java.util.Random;
import Audio.JukeBox;
import Entity.Unit.Unit;
import Entity.Unit.Succubus.soundTypes;
import GameState.Conversation.Conversation;
import GameState.Conversation.ConversationState;
import TileMap.TileMap;

public class Player extends Unit
{
	
	protected int numberofAttackSounds;
	protected int numberofHurtSounds;
	protected int numberofJumpSounds;
	
	protected int[] numberofSounds;
	
	protected HUD hud;
	
	protected boolean loaded = false;
	protected boolean usingMouse;
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	
//	protected ArrayList<Projectile> a
	
	public enum soundTypes { Attack, Hurt, Jump , Loot, CannotOpen}
	
	protected String currentMap;
	
	protected Conversation conversation;
	protected ConversationState conversationState;
	//  Animations 
	
	
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	// Constructor
	public Player(
			String name, 
			TileMap tileMap,
			ConversationState conversationState
			)
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
				0.005,	 															// healthRegen
				100,	 															// mana
				100,	 															// maxMana
				0.05,	 															// manaRegen
				100,	 															// stamina
				100, 	 															// maxStamina
				0.05,	 															// staminaRegen
				800,																// sightRangeX
				120,																// sightRangeY
				5,	 	 															// punchCost
				1, 		 															// punchDamage
				80, 	 															// punchRange
				40,		 															// dashCost
				40,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
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
		this.unitType = "Player";
		conversation = new Conversation(this);
		this.conversationState = conversationState;
		player = true;

		hud = new HUD(this);
		
		numberofSounds = new int[soundTypes.values().length];
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists("Player" + soundTypes.values()[i] + "0" + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
	}
	
	public Conversation getConversation() { return conversation; }
	
	public ConversationState getConversationState() { return conversationState; }
	
	public String getCurrentMap() { return currentMap; }
	public void setCurrentMap(String newMap) { currentMap = newMap; }
	
	public boolean getLoaded() { return loaded; }
	public void setLoaded(boolean loaded) { this.loaded = loaded; }
	
	public boolean getUsingMouse() { return usingMouse; }
	public void setUsingMouse(boolean well) { usingMouse = well; }
	
	public HUD getHUD() { return hud; }
	
	
	public void calculateAim(Unit character)
	{
		double tempX;
		double tempY;
		
		if(!usingMouse)
		{
			tempX = locationX;
			tempY = locationY;
		
			if(character == null)
			{
				if(facingRight)
				{
					tempX += 50;
					if(up)
						tempY -= 25;
					else if(down)
						tempY += 25;					
				}
				else
				{
					tempX -= 50;
					if(up)
						tempY -= 25;
					else if(down)
						tempY += 25;		
				}
			}
			else
			{
				tempX = character.getLocationX();
				tempY = character.getLocationY();			
			}
			
		}
		else
		{
			
			tempX = mouseLocationX - tileMap.getX();
			tempY = mouseLocationY - tileMap.getY();
		}
			
		aim = Math.atan2(tempY - locationY, tempX - locationX);
	}
	
	
	public void iAmHit()
	{
		int RNG = mainMap.RNG(1, numberofSounds[1]);
		JukeBox.play(unitType + soundTypes.Hurt + (RNG < 10 ? "0" : "") + RNG);
	}
	
	
	public void playJumpSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[2]);
		JukeBox.play(unitType + soundTypes.Jump + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playCastSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		JukeBox.play(unitType + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playPunchSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		JukeBox.play(unitType + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playLootSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[3]);
		JukeBox.play(unitType + soundTypes.Loot + (RNG < 10 ? "0" : "") + RNG);	
	}
	
	public void playCannotOpenSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[4]);
		JukeBox.play(unitType + soundTypes.CannotOpen + (RNG < 10 ? "0" : "") + RNG);	
	}
	
	public void setMouseLocationX(double mouse)
	{
		this.mouseLocationX = mouse;
	}
	
	public void setMouseLocationY(double mouse)
	{
		this.mouseLocationY = mouse;
	}
	
}