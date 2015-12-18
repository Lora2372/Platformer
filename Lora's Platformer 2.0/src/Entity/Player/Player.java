package Entity.Player;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.glass.events.KeyEvent;

import Entity.Unit.Unit;
import GameState.GameStateManager;
import GameState.Conversation.ConversationData;
import GameState.Conversation.ConversationState;
import Main.Content;
import TileMap.TileMap;

public class Player extends Unit
{
	protected HUD hud;
	
	protected ArrayList<Boolean> loading;
	protected boolean useMouse = false;
	protected boolean displayHealthBars = true;
	protected boolean displayNamePlates = true;
	
	protected double mouseLocationX;
	protected double mouseLocationY;	
	
	protected int timesDefeatedFiona = 0;
	
	protected String currentMap;
	
	protected ConversationData conversation;
	protected ConversationState conversationState;
	
	protected double atmosphereTemperature = 20;	
	
	protected Buff campfireBuff;
	protected Buff warmthBuff;
	protected Buff wetBuff;
	
	protected double warmth = 10;
	protected double warmMaximum = 100;
	protected double warmMinimum = - 10;
	
	protected double wetLevel = 0;
	protected double wetLevelMaximum = 100;
	protected double wetLevelMinimum = 0;
	
	protected HashMap<KeyBind, Integer> keyBindMap;
	
	//  Animations 
	
	
	public enum KeyBind
	{
		MoveLeft,
		MoveRight,
		AimDown,
		AimUp,
		Jump,
		Interact,
		Glide,
		Run,
		CastSmallFireBall,
		CastLargeFireBall,
		CastDash,
		CastPunch,
		UseItem1,
		UseItem2,
		UseItem3,
		UseItem4,
		UseItem5,
		UseItem6,
		UseItem7,
		UseItem8,
		UseItem9,
		TossItem,
		OpenInventory
	};
	
	protected int[] keyBinds = new int[]
	{
		KeyEvent.VK_LEFT,
		KeyEvent.VK_RIGHT,
		KeyEvent.VK_DOWN,
		KeyEvent.VK_UP,
		KeyEvent.VK_SPACE,
		KeyEvent.VK_E,
		KeyEvent.VK_R,
		KeyEvent.VK_SHIFT,
		KeyEvent.VK_A,
		KeyEvent.VK_S,
		KeyEvent.VK_D,
		KeyEvent.VK_F,
		KeyEvent.VK_1,
		KeyEvent.VK_2,
		KeyEvent.VK_3,
		KeyEvent.VK_4,
		KeyEvent.VK_5,
		KeyEvent.VK_6,
		KeyEvent.VK_7,
		KeyEvent.VK_8,
		KeyEvent.VK_9,
		KeyEvent.VK_R,
		KeyEvent.VK_B
		
	};
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	// Constructor
	public Player
		(
			String name, 
			TileMap tileMap,
			ConversationState conversationState
		)
	{
		super
		(
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
			10,	 	 															// punchCost
			10,		 															// punchDamage
			80, 	 															// punchRange
			40,		 															// dashCost
			40,		 															// dashDamage
			40,		 															// dashRange
			20, 	 															// dashSpeed
			"/Art/Sprites/Characters/Lora.png", 								// spritePath
			new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15, 16},			// animationStates
			new int[]{6, 6, 1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},			// numImages
			new int[] { 400, 40, 100, 80, 125, 120, 100, 100, 100, 100, 100, 100, 500, 100, 100, 3000, 3000 },
			0,																	// damageOnTouch
			true,																// friendly
			false,
			false,
			false,
			false,
			name,
			"Player",
			0,
			0,
			null
				
		);
		
		
		
		keyBindMap = new HashMap<KeyBind, Integer>();
		
		for(int i = 0; i < KeyBind.values().length; i++)
		{
			keyBindMap.put(KeyBind.values()[i], i);			
		}
		 
		loading = new ArrayList<Boolean>();
		
		for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
		{
			boolean newBoolean = false;
			loading.add(newBoolean);
		}
		
		conversation = new ConversationData(this);
		this.conversationState = conversationState;
		player = true;

		portrait = Content.PortraitPlayer[0];

		hud = new HUD(this);
		
		wetBuff = new Buff(CreateBuff.Buffs.Dry, -1, 0, this, Content.BuffDry[0][0]);
		addBuff(wetBuff);
		
		warmthBuff = new Buff(CreateBuff.Buffs.Warm, -1, 0, this, Content.BuffWarm[0][0]);
		addBuff(warmthBuff);
		
	}
	
	public void setCampFireBuff(Buff buff)
	{
		campfireBuff = buff;
	}
	
	public Buff getCampFireBuff()
	{
		return campfireBuff;
	}
	
	public void update(ArrayList<Unit> characterList)
	{
		super.update(characterList);
		
		if(inWater)
		{
			addWetLevel(20);
			
		}
		
		if(!inWater)
		{
			addWetLevel( (0.001 + warmth / 10000) * -1  ); 
		}
		
		if(atmosphereTemperature < (warmth + (wetLevel / 10)) )
		{
			addWarmth( ( 0.01 + 2 * wetLevel / 10000) * -1 );
		}
		
		if(atmosphereTemperature > (warmth - (wetLevel / 10)) )
		{
			addWarmth(0.01 - wetLevel / 10000);
		}
		

		
		if(campfireBuff != null)
		{
			wetLevel -= 0.1;
			warmth += 0.1 * (1 - wetLevel / 100) ;
		}
				
		// Player is hot
		if(warmth >= 50)
		{
			if(!warmthBuff.getBuff().equals(CreateBuff.Buffs.Hot))
			{
				hud.fadeMessage("You feel rather hot.");
				warmthBuff.setBuff(CreateBuff.Buffs.Hot);
				warmthBuff.setSprites(Content.BuffHot[0][0]);
				healthRegenCurrent = healthRegenOriginal * 1.1;
				manaRegenCurrent = manaRegenOriginal * 1.1;
				staminaRegenCurrent = staminaRegenOriginal * 1.1;
				
			}
		}
		
		// Player is warm
		if(warmth >= 20 && warmth < 50)
		{
			if(!warmthBuff.getBuff().equals(CreateBuff.Buffs.Warm))
			{
				hud.fadeMessage("You feel warm.");
				warmthBuff.setBuff(CreateBuff.Buffs.Warm);
				warmthBuff.setSprites(Content.BuffWarm[0][0]);
				
				healthRegenCurrent = healthRegenOriginal;
				manaRegenCurrent = manaRegenOriginal;
				staminaRegenCurrent = staminaRegenOriginal;
			}
		}
		// Player is cold
		if(warmth < 20 && warmth > 0)
		{				
			if(!warmthBuff.getBuff().equals(CreateBuff.Buffs.Cold))
			{
				hud.fadeMessage("You feel cold.");
				warmthBuff.setBuff(CreateBuff.Buffs.Cold);
				warmthBuff.setSprites(Content.BuffCold[0][0]);
				
				healthRegenCurrent = healthRegenOriginal * 0.5;
				manaRegenCurrent = manaRegenOriginal * 0.5;
				staminaRegenCurrent = staminaRegenOriginal * 0.5;
			}
		}
		
		// Player is freeezing
		if(warmth <= 0)
		{
			if(!warmthBuff.getBuff().equals(CreateBuff.Buffs.Freezing))
			{
				hud.fadeMessage("You're freezing!");
				warmthBuff.setBuff(CreateBuff.Buffs.Freezing);
				warmthBuff.setSprites(Content.BuffFreezing[0][0]);
				
				healthRegenCurrent = 0;
				manaRegenCurrent = 0;
				staminaRegenCurrent = 0;
			}
		}
		
		// Player is dry
		if(wetLevel <= 20)
		{
			if(!wetBuff.getBuff().equals(CreateBuff.Buffs.Dry))
			{
				hud.fadeMessage("You feel dry.");
				wetBuff.setBuff(CreateBuff.Buffs.Dry);
				wetBuff.setSprites(Content.BuffDry[0][0]);
			}
		}
		
		// Player is wet
		if(wetLevel > 20 && wetLevel < 60)
		{
			if(!wetBuff.getBuff().equals(CreateBuff.Buffs.Wet))
			{
				hud.fadeMessage("You feel rather wet.");
				wetBuff.setBuff(CreateBuff.Buffs.Wet);
				wetBuff.setSprites(Content.BuffWet[0][0]);
			}
		}
		
		// Player is soaked
		if(wetLevel > 60)
		{
			if(!wetBuff.getBuff().equals(CreateBuff.Buffs.Soaked))
			{
				hud.fadeMessage("You feel completely soaked.");
				wetBuff.setBuff(CreateBuff.Buffs.Soaked);
				wetBuff.setSprites(Content.BuffSoaked[0][0]);
			}
		}
		
		
		
	}
	
	public void setAtmosphereTemperature(double atmosphereTemperature)
	{
		System.out.println("Atmosphere is now: " + atmosphereTemperature);
		this.atmosphereTemperature = atmosphereTemperature;
	}
	
	public double getWarmth()
	{
		return warmth;
	}
	
	public double getWet()
	{
		return wetLevel;
	}
	
	public void setHealth(double health)
	{
		this.health = health;
	}
	
	public void setMana(double mana)
	{
		this.mana = mana;
	}
	public void setStamina(double stamina)
	{
		this.stamina = stamina;
	}
	
	public void setWarmth(double warmth)
	{
		this.warmth = warmth;
	}
	
	public void setWet(double wet)
	{
		this.wetLevel = wet;
	}
	
	public void addWarmth(double warmth)
	{
		this.warmth += warmth;
		if(this.warmth > warmMaximum)
		{
			this.warmth = warmMaximum;
		}
		if(this.warmth < warmMinimum)
		{
			this.warmth = warmMinimum;
		}
	}
	
	public void addWetLevel(double wetLevel)
	{
		this.wetLevel += wetLevel;
		
		if(this.wetLevel > wetLevelMaximum)
		{
			this.wetLevel = wetLevelMaximum;
		}
		if(this.wetLevel < wetLevelMinimum)
		{
			this.wetLevel = wetLevelMinimum;
		}
	}
	
	public int getKeyBind(int index)
	{
		return keyBinds[index];
	}
	
	public int[] getKeyBinds()
	{
		return keyBinds;
	}
	
	public int getKeyBind(KeyBind e)
	{
		return keyBinds[keyBindMap.get(e)];
	}
	
	
	public void setKeyBind(int index, int keybind)
	{
		keyBinds[index] = keybind;
	}
		
	public ConversationState getConversationState() { return conversationState; }
	
	public boolean getLoading(int index) { return loading.get(index); }
	public void setLoading(int i, boolean loading) 
	{ 
		this.loading.set(i, loading);
	}
	
	public int getTimesDefeated()
	{
		if(currentMap.equals(GameStateManager.GameMaps.FionasSanctum.toString()))
		{
			return timesDefeatedFiona;
		}
		return 0;
	}
	
	public void setTimesDefeated(String currentMap, int timesDefeated)
	{
		if(currentMap.equals(this.currentMap))
		{
			timesDefeatedFiona = timesDefeated;
		}
	}
	
	
	public void setCurrentMap(String currentMap) { this.currentMap = currentMap; }
	public String getCurrentMap() { return currentMap; }
	
	public boolean getUseMouse() { return useMouse; }
	public void setUseMouse(boolean well) { useMouse = well; }
	
	public boolean getDisplayHealthBars() { return displayHealthBars; }
	public void setDisplayHealthBars(boolean well) { displayHealthBars = well; }

	public boolean getDisplayNamePlates() { return displayNamePlates; }
	public void setDisplayNamePlates(boolean well) { displayNamePlates = well; }

	public HUD getHUD() { return hud; }
	
	public void calculateAim(Unit character)
	{
		double tempX;
		double tempY;
		
		if(!useMouse)
		{
			tempX = locationX;
			tempY = locationY;
		
			if(character == null)
			{
				
				tempX += facingRight ? 50 : -50;
				
				if(up)
				{
					tempY -= 25;
				}
				else if(down)
				{
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
	
	public void mousePressed(MouseEvent mouse) 
	{
		for(int i = 0; i < buffs.size(); i++)
		{
			buffs.get(i).mousePressed(mouse);
		}
	}
	
	public void mouseMoved(MouseEvent mouse) 
	{
		this.mouseLocationX = mouse.getX();
		this.mouseLocationY = mouse.getY();
		
		for(int i = 0; i < buffs.size(); i++)
		{
			buffs.get(i).mouseMoved(mouse);
		}
		
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		this.mouseLocationX = mouse.getX();
		this.mouseLocationY = mouse.getY();
	}
}