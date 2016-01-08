package Entity.Unit;

import java.util.ArrayList;
import Audio.JukeBox;
import javax.imageio.ImageIO;
import Entity.Animation;
import Entity.MapObject;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.DoodadData;
import Entity.Item.Item;
import Entity.Player.Buff;
import Entity.Player.Player;
import Entity.Projectile.ArcaneBall;
import Entity.Projectile.ElectricBall;
import Entity.Projectile.FireBallLarge;
import Entity.Projectile.FireBallSmall;
import Entity.Projectile.Projectile;
import Entity.Projectile.ProjectileData;
import GameState.MainMap.MainMap;
import Main.Content;
import Main.DrawingConstants;
import Main.GamePanel;
import TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Unit extends MapObject
{
	protected MainMap mainMap;
	
	// Character stuff
	protected String unitType;
	protected String name;
	protected boolean player;
	
	
	protected ArrayList<Buff> buffs;
	
	protected int silver;
	protected int gold;
	
	public enum soundTypes { Attack, Hurt, Jump , Loot, CannotCarry, CannotOpen, Chargeup, Hit, Recover}
	
	
	protected int[] numberofSounds;
	
	protected ArrayList<Unit> charactersHit = new ArrayList<Unit>();
	
	protected ElectricBall electricBall;
	
	
	protected BufferedImage[] portrait;
	
	protected double spawnLocationX;
	protected double spawnLocationY;
	
	protected double aim;
		
	protected int sightRangeX;
	protected int sightRangeY;
	
	protected boolean tennisPlayer;
	protected int tennisSuccessChance = 90;
	
	protected boolean sexytime1;
	protected boolean sexytime2;

	protected double health;
	protected double maxHealth;
	protected double healthRegenOriginal;
	protected double healthRegenCurrent;
	
	protected double mana;
	protected double maxMana;
	protected double manaRegenOriginal;
	protected double manaRegenCurrent;
	
	protected double stamina;
	protected double maxStamina;
	protected double staminaRegenOriginal;
	protected double staminaRegenCurrent;
	
	protected double breath;
	
	protected double bonusDamageMagical;
	protected double bonusDamagePhysical;
	
	protected int damageOnTouch;
	
	protected boolean friendly;
	
	protected boolean activatable;
		
	protected boolean dead;
	protected boolean stunned;

	protected boolean inConversation;
	
	protected String spritePath;
	protected int[] animationState;
	
	protected double saveFallSpeed;
	
	// Small fireBall
	protected boolean fireBallSmallCasting;
	protected boolean fireBallSmallDoneCasting;
	protected boolean fireBallSmallHolding;
	
	// Large fireBall
	protected boolean fireBallLargeCasting;
	protected boolean fireBallLargeDoneCasting;
	protected boolean fireBallLargeHolding;
	
	// electricBall
	protected boolean electricBallCasting;
	protected boolean electricBallDoneCasting;
	protected boolean electricBallHolding;
	
	// arcaneBall
	protected boolean arcaneBallCasting;
	protected boolean arcaneBallDoneCasting;
	protected boolean arcaneBallHolding;
	
	// Punch
	protected boolean startPunch;
	protected boolean endPunch;
	protected boolean holdingPunch;
	
	protected int punchDamage;
	protected int punchRange;
	protected int punchCost;
	
	protected int tennisTimer = 40;
	protected int tennisCooldown = 40;
	
	// Dash
	protected boolean startDash;
	protected boolean endDash;
	protected boolean holdingDash;
	
	protected int dashDamage;
	protected int dashRange;
	protected int dashCost;
	
	// Run
	protected double runCost = 0.7;
	
	// Magic Shield
	protected boolean magicShieldCasting;
	protected boolean magicShieldDoneCasting;
	protected boolean magicShieldHolding;
	
	protected int magicShieldManaCost;
	
	
	// Custom animation
	protected boolean customAnimation;
	
	//  Animations 
	protected ArrayList<BufferedImage[]> sprites;
	protected int[] numFrames;
	protected int[] animationDelay;
	

	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	
	
//	protected static final int animationState[0]					= 0;
//	protected static final int animationState[1]					= 1;
//	protected static final int animationState[2]					= 2;
//	protected static final int animationState[3]					= 3;
//	
//	protected static final int animationState[4]					= 4;
//	protected static final int animationState[5]					= 5;
//		
//	protected static final int animationState[6]					= 6;
//	protected static final int animationState[7] 					= 7;
//	
//	protected static final int animationState[8]				 	= 8;
//	protected static final int animationState[9] 					= 9;
//	
//	protected static final int animationState[10] 					= 10;
//	protected static final int animationState[11]					= 11;
//	
//	protected static final int animationState[12] 					= 12;
//	protected static final int animationState[13] 					= 13;
//	protected static final int animationState[14]					= 14;
//	
//	protected static final int animationState[14]					= 14;
//	protected static final int animationState[15] 					= 15;
	
	// Constructor
	public Unit
	(
		TileMap tileMap,
		int width,
		int height,
		int collisionWidth,
		int collisionHeight,
		double moveSpeed,
		double maxSpeed,
		double stopSpeed,
		double fallSpeed,
		double maxFallSpeed,
		double jumpStart,
		double stopJumpSpeed,
		boolean facingRight,
		boolean inControl,
		double health,
		double maxHealth,
		double healthRegen,	
		double mana, 
		double maxMana,
		double manaRegen,
		double stamina,
		double maxStamina,
		double staminaRegen,
		int sightRangeX,
		int sightRangeY,
		int punchCost,
		int punchDamage,			
		int punchRange,
		int dashCost,
		int dashDamage,	
		int dashRange,
		double dashSpeed,
		String spritePath,
		int[] animationState,
		int[] numFrames,
		int[] animationDelay,
		int damageOnTouch,
		boolean friendly,
		boolean untouchable,
		boolean invulnerable,
		boolean unkillable,
		boolean activatable,
		String name,
		String unitType,
		double spawnLocationX,
		double spawnLocationY,
		MainMap mainMap
	)
	{
		super(tileMap);		
		this.width = width;
		this.height = height;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.moveSpeed = moveSpeed;
		this.maxSpeed = maxSpeed;
		this.stopSpeed = stopSpeed;
		this.fallSpeed = fallSpeed;
		this.maxFallSpeed = maxFallSpeed;
		this.jumpStart = jumpStart;
		this.stopJumpSpeed = stopJumpSpeed;
		this.facingRight = facingRight;
		this.inControl = inControl;
		this.health = health;
		this.maxHealth = maxHealth;
		this.healthRegenOriginal = healthRegen;
		this.healthRegenCurrent = healthRegen;
		this.stamina = stamina;
		this.maxStamina = maxStamina;
		this.staminaRegenOriginal = staminaRegen;
		this.staminaRegenCurrent = staminaRegen;
		this.sightRangeX = sightRangeX;
		this.sightRangeY = sightRangeY;
		this.punchCost = punchCost;
		this.punchDamage = punchDamage;
		this.punchRange = punchRange;
		this.dashCost = dashCost;
		this.dashDamage = dashDamage;
		this.dashRange = dashRange;
		this.dashSpeed = dashSpeed;
		this.mana = mana;
		this.maxMana = maxMana;
		this.manaRegenOriginal = manaRegen;
		this.manaRegenCurrent = manaRegen;
		this.spritePath = spritePath;
		this.animationState = animationState;
		this.numFrames = numFrames;
		this.animationDelay = animationDelay;
		this.damageOnTouch = damageOnTouch;
		this.friendly = friendly;
		this.activatable = activatable;
		this.name = name;
		this.unitType = unitType;
		this.invulnerable = invulnerable;
		this.untouchable = untouchable;
		this.unkillable = unkillable;
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
		this.mainMap = mainMap;
		
		buffs = new ArrayList<Buff>();
		
		setPosition(spawnLocationX, spawnLocationY);		
		
		numberofSounds = new int[soundTypes.values().length];
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists(unitType + soundTypes.values()[i] + (i < 10 ? "0" : "") + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
		
		// Load the sprites.
		try{
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResource(
							 spritePath)
					);
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < numFrames.length; i++)
			{
				BufferedImage[] bufferedImage =
						new BufferedImage[numFrames[i]];
				for(int z = 0; z < numFrames[i]; z++)
				{
					bufferedImage[z] = spritesheet.getSubimage(
							z * width,
							i * height,
							width,
							height
							);
				}
				
				sprites.add(bufferedImage);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		animation = new Animation();
		
		currentAction = animationState[0];
		animation.setFrames(sprites.get(animationState[0]));
		animation.setDelay(400);
	}
	
	
	public void setMainMap(MainMap mainMap) 
	{ 
		this.mainMap = mainMap;
	}
	
	public MainMap getMainMap()
	{
		return mainMap;
	}
	
	public TileMap getTileMap() { return tileMap; }
	
	
	public void calculateAim(Unit character)
	{
		if(player) return;
		

		double tempX = locationX;
		double tempY = locationY;
		
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
			tempX = character.locationX;
			tempY = character.locationY;			
		}
		
		
		aim = Math.atan2(tempY - locationY, tempX - locationX);
	}
	
	public Buff hasBuff(String buff)
	{
		for(int i = 0; i < buffs.size(); i++)
		{
			if(buffs.get(i).getBuff().toString().equals(buff))
			{
				return buffs.get(i);
			}
		}
		return null;
	}
	
	public void setTennisPlayer(boolean b) { tennisPlayer = b; }
	
	public BufferedImage[] getPortrait() { return portrait;}
	
	public boolean isPlayer() { return player; }
	
	public double getHealth() { return health; }
	public double getMaxHealth() { return maxHealth; }
	
	public void restoreHealth(double healthRestored)
	{
		health += healthRestored;
		if(health > maxHealth) health = maxHealth;
	}
	
	public double getMana() { return mana; }
	public double getMaxMana() { return maxMana; }
	
	public void restoreMana(double manaRestored)
	{
		mana += manaRestored;
		if(mana > maxMana) mana = maxMana;
	}
	
	public double getStamina() { return stamina; }
	public double getMaxStamina() { return maxStamina; }

	public void restoreStamina(double staminaRestored)
	{
		stamina += staminaRestored;
		if(stamina > maxStamina) stamina = maxStamina;
	}
	
	public double getBreath() { return breath; }
	
	
	public int getDashStaminaCost()  { return dashCost; }
	public int getPunchStaminaCost() { return punchCost; }
	
	public boolean getActivatable() { return activatable; }
		
	public void interact(Player player)
	{

	}
	
	public void setSpawnPoint(double spawnLocationX, double spawnLocationY) 
	{
		this.spawnLocationX = spawnLocationX;
		this.spawnLocationY = spawnLocationY;
	}
	
	public void respawn()
	{
		directionX = 0;
		directionY = 0;
		initializeSpawning = true;
		locationX = spawnLocationX;
		locationY = spawnLocationY;
		dead = false;
		health = maxHealth;
	}
	
	public void inControl(boolean b) {inControl = b;}
	public boolean getInControl() { return inControl; }
	
	public double getBonusDamagePhysical() { return bonusDamagePhysical; }
	public double getBonusDamageMagical() { return bonusDamageMagical; }
	
	public void setBonusDamagePhysical(double bonusDamagePhysical) { this.bonusDamagePhysical = bonusDamagePhysical; }
	public void setBonusDamageMagical(double bonusDamageMagical) { this.bonusDamageMagical = bonusDamageMagical; }
	
	public boolean getFacingRight() { return facingRight; }
	public boolean getFriendly() { return friendly; }
	public boolean getUntouchable() { return untouchable; }
	public boolean getInvulnerable() { return invulnerable; }
	public boolean getUnkillable() { return unkillable; }
	public String getName() { return name; }
	public String getUnitType() { return unitType; }
	
	
	public int getSilver() { return silver; }
	public int getGold() { return gold; }
	
	public void addSilver(int silver) { this.silver += silver; }
	public void addGold(int gold) { this.gold += gold; }
	
	
	public double getDirectionX() { return directionX; }
	public double getDirectionY() { return directionY; }
	

	
	public boolean getInConversation() { return inConversation; }
	public void setInConversation(boolean b) 
	{ 
		inConversation = b; 
		
	}
	
	public void setFacingRight(boolean facingRight) { this.facingRight = facingRight; }
	public void setFriendly(boolean friendly) { this.friendly = friendly; }
	public void setUntouchable(boolean untouchable) { this.untouchable = untouchable; }
	public void setInvulnerable(boolean invulnerable) { this.invulnerable = invulnerable; }
	public void setUnkillable(boolean unkillable) { this.unkillable = unkillable; }
	public void setName(String name) { this.name = name; }
	public void setSpawnLocationX(double spawnLocationX) { this.spawnLocationX = spawnLocationX; }
	public void setSpawnLocationY(double spawnLocationY) { this.spawnLocationY = spawnLocationY; }
	public void setUnitType(String unitType) { this.unitType = unitType; }

	public boolean isDead() { return dead; }
	
	public boolean getSpawning() { return spawning; };
	public void spawn() { initializeSpawning = true; }
	
	public boolean getDeSpawning() { return deSpawning; }
	public void deSpawn() { initializeDeSpawning = true; }
	
	public double getSpawnLocationX() { return spawnLocationX; }
	public double getSpawnLocationY() { return spawnLocationY; }
	
	
	public boolean getFalling() { return falling; }
	public boolean getJumping() { return jumping; }
	public boolean getLeft() { return left; }
	public boolean getRight() { return right; }
	public boolean getUp() { return up; }
	public boolean getDown() { return down; }
	public boolean getFireBallSmallDoneCasting() { return fireBallSmallDoneCasting; }
	public boolean getFireBallLargeDoneCasting() { return fireBallLargeDoneCasting; }
	public boolean getMagicShieldDoneCasting() { return magicShieldDoneCasting; }
	public boolean getPunching() { return endPunch; }
	public boolean getDashing() { return endDash; }

	
	public void setCastingSmallFireBall()
	{
		if(mana > ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallSmall.toString()) && inControl && !fireBallSmallHolding)
		{
			fireBallSmallCasting = true;
			inControl = false;			
		}
	}
	public void releaseSmallFireBall() { fireBallSmallHolding = false; }
	
	public void setCastingLargeFireBall()
	{
		if(mana > ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallLarge.toString()) && inControl && !fireBallLargeHolding)
		{
			fireBallLargeCasting = true;
			inControl = false;			
		}
	}
	public void releaseLargeFireBall() { fireBallLargeHolding = false; }
	
	
	public void setCastingMagicShield()
	{
		if(mana > magicShieldManaCost && inControl && !magicShieldHolding)
		{
			magicShieldCasting = true;
			inControl = false;
		}
	}
	
	public boolean getEndPunch() { return endPunch; }
	
	public void setPunching()
	{
		if(stamina > punchCost && inControl && !holdingPunch)
		{
			startPunch = true;
			inControl = false;
		}
	}
	public void releasePunch() { holdingPunch = false; }
	
	public void setDashing(boolean b)
	{
		if(b && inControl)
		{
			if((stamina > dashCost && !holdingDash && !falling && !jumping) && inControl)
			{
				startDash = true;
				inControl = false;
			}
		}

	}
	
	public void releaseDash() { holdingDash = false; }
	
	public void setGliding(boolean b)
	{
		if(inControl) gliding = b;
	}
	
	
	
	public void setSexytime1() { sexytime1 = true; }
	public void setSexytime2() { sexytime2 = true; }
	

	
	public void updateAI(ArrayList<Unit> characterList){}
	
	public int getPunchRange() { return punchRange; }
		
	public void hit(double damage, Unit owner)
	{
		
		
		if(dead || invulnerable && owner != null)
		{
			return;
		}
		System.out.println("Damaging " + name + " for: " + damage + " damage.");
		
		health -= damage;
		
		if( health < 0)
		{
			health = 0;
		}
		
		if(health == 0 && !unkillable)
		{
			dead = true;
		}
		
		playIsHit();
		
		if(damage > 10)
		{
			if(!stunned)
			{
				setStunned(500);
			}
			inControl = false;
		}
		
		
	}
	
	public void update(ArrayList<Unit> characterList)
	{
		try
		{
			int buffLocationX = GamePanel.WIDTH - 100;
			int buffLocationY = 50;
			
			for(int i = 0; i < buffs.size(); i++)
			{
				Buff buff = buffs.get(i);
				if(buff != null)
				{
					buff.setLocation(buffLocationX, buffLocationY);
					buff.update();
					
					
					if(buff.getExpired())
					{
						buffs.remove(i);
						i--;
					}
					
					buffLocationX -= buff.getWidth() + 10;
				}
				else
				{
					buffs.remove(i);
					i--;
				}

			}
			
			// Update position
			getNextPosition();
			try
			{
				checkTileMapCollision();
			}
			catch(Exception exception)
			{
				System.out.println("Crash at: " + name);
				exception.printStackTrace();
			}
			setPosition(xtemp, ytemp);
			
			if(initializeSpawning)
			{
				summoningEffect = new SummoningEffect(tileMap, mainMap, locationX, locationY);
				initializeSpawning = false;
				spawning = true;
			}
			
			if(initializeDeSpawning)
			{
				summoningEffect = new SummoningEffect(tileMap, mainMap, locationX, locationY);
				initializeDeSpawning = false;
				deSpawning = true;
			}
			
			if(summoningEffect != null && !summoningEffect.getAnimation().hasPlayedOnce())
			{
				summoningEffect.update();
			}
			
			if(spawning && summoningEffect.getHasPlayedOnce())
			{
				summoningEffect = null;
				spawning = false;
				hidden = false;
				untouchable = false;
				if(!inConversation)
					inControl = true;
			}
			
			if(deSpawning && summoningEffect.getHasPlayedOnce())
			{
				summoningEffect = null;
				deSpawning = false;
				hidden = true;
				untouchable = true;
				inControl = false;
				
				removeMe = !player;
			}
			
			if(locationX > tileMap.getWidth() || locationX < 0 || locationY > tileMap.getHeight())
			{
				setPosition(spawnLocationX, spawnLocationY);
				initializeSpawning = true;
				directionX = 0;
				directionY = 0;
				inControl = false;
			}
			
			// Regenerate health
			if(healthRegenCurrent > 0)
			{
				health += healthRegenCurrent;
			}
			
			if(health > maxHealth) 
			{
				health = maxHealth;
			}
			
			// Regenerate mana
			if(manaRegenCurrent > 0)
			{
				mana += manaRegenCurrent;
			}
			
			if(mana > maxMana) 
			{
				mana = maxMana;
			}
			
			// Regenerate stamina
			
			if(staminaRegenCurrent > 0)
			{
				stamina += staminaRegenCurrent;
			}
			
			if(stamina > maxStamina) 
			{
				stamina = maxStamina;
			}
			
			// Update breath
			breath += (inWater? -0.1: 0.1);
			if(breath <= 0)
			{
				breath = 0;
				hit(0.1, null);
			}
			
			if(breath > 100)
			{
				breath = 100;
			}
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}

		
		// Set animations
		
		if(customAnimation)
		{
			return;
		}
		//********************************************************************************
		//*Sexy time 1!                                                                  *
		//********************************************************************************	
		if(sexytime1)
		{
			if(currentAction != animationState[14])
			{
				directionX = 0;
				gliding = false;
				left = false;
				right = false;
				inControl = false;
				currentAction = animationState[14];
				animation.setFrames(sprites.get(animationState[14]));
				animation.setDelay(animationDelay[14]);
			}
			
			if(sexytime1 && animation.hasPlayedOnce())
			{
				sexytime1 = false;
				inControl = true;
			}
		}
		
		
		//********************************************************************************
		//*Sexy time 2!                                                                  *
		//********************************************************************************	
		else if(sexytime2)
		{
			if(currentAction != animationState[15])
			{
				inControl = false;
				gliding = false;
				left = false;
				right = false;
				directionX = 0;
				currentAction = animationState[15];
				animation.setFrames(sprites.get(animationState[15]));
				animation.setDelay(animationDelay[15]);
			}
			if(sexytime2 && animation.hasPlayedOnce())
			{
				sexytime2 = false;
				inControl = true;
			}

		}
		
		
		//********************************************************************************
		//*Flinching	                                                                 *
		//********************************************************************************
		else if(stunned)
		{
			if(animation.hasPlayedOnce())
			{
				stunned = false;
				inControl = true;
			}
		}
		//********************************************************************************
		//*Regular punch                                                                 *
		//********************************************************************************
		else if(startPunch)
		{
			if(currentAction != animationState[4])
			{
				currentAction = animationState[4];
				animation.setFrames(sprites.get(animationState[4]));
				animation.setDelay(animationDelay[4]);
				
				if(directionY == 0) directionX = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
				stamina -= punchCost;
				startPunch = false;
				endPunch = true;
				
				currentAction = animationState[5];
				animation.setFrames(sprites.get(animationState[5]));
				animation.setDelay(animationDelay[5]);
				playPunchSound();
			}
		}
		
		else if(currentAction == animationState[5] && endPunch == true)
		{
			if(animation.hasPlayedOnce())
			{
				endPunch = false;
				charactersHit = new ArrayList<Unit>();
				inControl = true;				
				// Deal damage?
			}
		}
		
		//********************************************************************************
		//*Dash attack                                                                   *
		//********************************************************************************	
		else if(startDash)
		{
			if(currentAction != animationState[6])
			{
				currentAction = animationState[6];
				animation.setFrames(sprites.get(animationState[6]));
				animation.setDelay(animationDelay[6]);
				if(directionY == 0) directionX = 0;

				directionY = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
				stamina -= dashCost;
				
				if(facingRight) directionX = dashSpeed;
				else directionX -= dashSpeed;
				
				startDash = false;
				endDash = true;
				
				try
				{
					playPunchSound();	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				currentAction = animationState[7];
				animation.setFrames(sprites.get(animationState[7]));
				animation.setDelay(animationDelay[7]);
			}
		}
		else if(currentAction == animationState[7] && endDash)
		{
			if(animation.hasPlayedOnce())
			{
				directionX = 0;
								
				charactersHit = new ArrayList<Unit>();
				
				endDash = false;
				inControl = true;
				// Deal damage?
			}
		}
		
		//********************************************************************************
		//*arcaneBall                                                                  *
		//********************************************************************************	
		else if(arcaneBallCasting)
		{
			mana -= ProjectileData.projectileCost.get(ProjectileData.Projectiles.ArcaneBall.toString());
			arcaneBallCasting = false;
			arcaneBallDoneCasting = true;
			
			
			ArcaneBall arcaneBall = new ArcaneBall(tileMap, mainMap, this, facingRight, up, down, aim, friendly);
			arcaneBall.setPosition(locationX, locationY - 20);
			mainMap.addProjectile(arcaneBall);
			
			currentAction = animationState[0];
			animation.setFrames(sprites.get(animationState[0]));
			animation.setDelay(animationDelay[0]);
			
			JukeBox.play("ElectricBallActive");
			JukeBox.play("ElectricBallThrow");
			playCastSound();
		}
		
		
		//********************************************************************************
		//*Small fireBall                                                                *
		//********************************************************************************	
		else if(fireBallSmallCasting)
		{
			
			if(currentAction != animationState[8])
			{
				currentAction = animationState[8];
				animation.setFrames(sprites.get(animationState[8]));
				animation.setDelay(animationDelay[8]);
//				if(directionY == 0) directionX = 0;
				JukeBox.play("FireBallSmallLaunch");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallSmall.toString());
				fireBallSmallCasting = false;
				fireBallSmallDoneCasting = true;
		
				calculateAim(null);
				
				FireBallSmall fireBall = new FireBallSmall(tileMap, mainMap, this, facingRight, up, down, aim, friendly);
				fireBall.setPosition(locationX, locationY - 20);
				mainMap.addProjectile(fireBall);
				playCastSound();
				
				currentAction = animationState[9];
				animation.setFrames(sprites.get(animationState[9]));
				animation.setDelay(animationDelay[9]);
				
				
				// Create new fireBall stuff here
			}
		}
		
		else if(currentAction == animationState[9] && fireBallSmallDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				fireBallSmallDoneCasting = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*electricBall                                                                  *
		//********************************************************************************	
		else if(electricBallCasting)
		{
			
			if(currentAction != animationState[8])
			{
				currentAction = animationState[8];
				animation.setFrames(sprites.get(animationState[8]));
				animation.setDelay(animationDelay[8] * 3);
				
				ElectricBallChargeUp electricBallChargeUp = new ElectricBallChargeUp(tileMap, mainMap, locationX, locationY - 20);
				mainMap.addEffect(electricBallChargeUp);
				
				if(directionY == 0) directionX = 0;
				JukeBox.play("ElectricBallChargeUp");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= ProjectileData.projectileCost.get(ProjectileData.Projectiles.ElectricBall.toString());
				electricBallCasting = false;
				electricBallDoneCasting = true;
				
				ArrayList<Unit> enemiesDetected = detectEnemy(characterList, false);
				if(enemiesDetected != null)
				{
					if(enemiesDetected.size() > 0)
					{
						calculateAim(enemiesDetected.get(0));
					}
					else
					{
						return;
					}
				}
				
				
				electricBall = new ElectricBall(tileMap, mainMap, this, facingRight, up, down, aim, friendly);
				electricBall.setPosition(locationX, locationY - 20);
				mainMap.addProjectile(electricBall);
				
				currentAction = animationState[9];
				animation.setFrames(sprites.get(animationState[9]));
				animation.setDelay(animationDelay[9]);
				
				JukeBox.play("ElectricBallActive");
				JukeBox.play("ElectricBallThrow");
				playCastSound();
			}
		}
		
		else if(currentAction == animationState[9] && electricBallDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				electricBallDoneCasting = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*Large fireBall                                                                *
		//********************************************************************************	
		else if(fireBallLargeCasting)
		{
			if(currentAction != animationState[10])
			{
				currentAction = animationState[10];
				animation.setFrames(sprites.get(animationState[10]));
				animation.setDelay(animationDelay[10]);
				if(directionY == 0) directionX = 0;
				JukeBox.play("FireBallLargeLaunch");
			}
			if(animation.hasPlayedOnce())
			{
				mana -= ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallLarge.toString());
				fireBallLargeCasting = false;
				fireBallLargeDoneCasting = true;
				
				calculateAim(null);
				FireBallLarge fireBall = new FireBallLarge(tileMap, mainMap, this, facingRight, up, down, aim, friendly);
				fireBall.setPosition(locationX, locationY);
				mainMap.addProjectile(fireBall);
				
				playCastSound();
				
				currentAction = animationState[11];
				animation.setFrames(sprites.get(animationState[11]));
				animation.setDelay(animationDelay[11]);
			}
		}
		
		else if(currentAction == animationState[11] && fireBallLargeDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				fireBallLargeDoneCasting = false;
				inControl = true;
			}
		}	
		
		//********************************************************************************
		//*Magic Shield                                                                  *
		//********************************************************************************	
		else if(magicShieldCasting)
		{
			if(currentAction != animationState[10])
			{
				currentAction = animationState[10];
				animation.setFrames(sprites.get(animationState[10]));
				animation.setDelay(animationDelay[10]);
				if(directionY == 0) directionX = 0;
				JukeBox.play("FireBallLargeLaunch");
			}
			if(animation.hasPlayedOnce())
			{
				mana -= magicShieldManaCost;
				magicShieldCasting = false;
				magicShieldDoneCasting = true;
				
				MagicShield magicShield = new MagicShield(tileMap, mainMap, locationX, locationY);
				mainMap.addEffect(magicShield);
				
				playCastSound();
				
				currentAction = animationState[11];
				animation.setFrames(sprites.get(animationState[11]));
				animation.setDelay(animationDelay[11]);
			}
		}
		
		else if(currentAction == animationState[11] && magicShieldDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				magicShieldDoneCasting = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*Gliding                                                                       *
		//********************************************************************************	
		
		else if(directionY > 0)
		{
			if(gliding)
			{
				if(currentAction != animationState[13])
				{
					currentAction = animationState[13];
					animation.setFrames(sprites.get(animationState[13]));
					animation.setDelay(animationDelay[13]);
				}
			}
			else if(currentAction != animationState[2])
			{
				currentAction = animationState[2];
				animation.setFrames(sprites.get(animationState[2]));
				animation.setDelay(animationDelay[2]);
			}
		}
				
		
		
		else if(directionY < 0)
		{
			//********************************************************************************
			//*Flying                                                                       *
			//********************************************************************************	
			if(flying)
			{
				if(currentAction != animationState[14])
				{
					currentAction = animationState[14];
					animation.setFrames(sprites.get(animationState[14]));
					animation.setDelay(animationDelay[14]);
				}
			}
			//********************************************************************************
			//*Jumping                                                                       *
			//********************************************************************************	
			else if(currentAction != animationState[3])
			{
				currentAction = animationState[3];
				animation.setFrames(sprites.get(animationState[3]));
				animation.setDelay(animationDelay[3]);
			}
			

		}
		
		//********************************************************************************
		//*Running                                                                       *
		//********************************************************************************	
		else if((left || right) && inControl && running)
		{
			if(stamina > runCost)
			{
				stamina -= runCost;
			}
			else
			{
				running = false;
			}
			if(currentAction != animationState[16])
			{
				currentAction = animationState[16];
				animation.setFrames(sprites.get(animationState[1]));
				animation.setDelay((long) (animationDelay[1] / waterResistance / runSpeedMultiplier));
			}

		}
		
		
		//********************************************************************************
		//*Walking                                                                       *
		//********************************************************************************	
		else if((left || right) && inControl)
		{
			if(currentAction != animationState[1])
			{
				currentAction = animationState[1];
				animation.setFrames(sprites.get(animationState[1]));
				animation.setDelay((long) (animationDelay[1] / waterResistance));
			}

		}
		
		
		//********************************************************************************
		//*Idle                                                                          *
		//********************************************************************************	
		else
		{
			if(currentAction != animationState[0])
			{
				currentAction = animationState[0];
				animation.setFrames(sprites.get(animationState[0]));
				animation.setDelay(animationDelay[0]);
			}
		}
		animation.update();
		
		// Set direction
		if(inControl && !flying)
		{
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
	}
	
	public void setStunned(int stunDuration)
	{
		gliding = false;
		left = false;
		right = false;
		startDash = false;
		endDash = false;
		jumping = false;
				
		stunned = true;
		
		currentAction = animationState[12];
		animation.setFrames(sprites.get(animationState[12]));
		animation.setDelay(stunDuration);
	}
	
	public boolean getStunned() { return stunned; }
	
	public ArrayList<Unit> detectEnemy(ArrayList<Unit> characterList, boolean onlyInFrontOfYou)
	{
		ArrayList<Unit> enemiesDetected = new ArrayList<Unit>();
		
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit character = characterList.get(i);
			
			if(character.getFriendly() != friendly && character.inControl)
			{
				
				if(facingRight || !onlyInFrontOfYou)
				{
					if
					(		
						character.getLocationX() > locationX &&
						character.getLocationX() < locationX + sightRangeX &&
						character.getLocationY() > locationY - height - sightRangeY /2 &&
						character.getLocationY() < locationY + height / 2 + sightRangeY / 2
					)
					{
						enemiesDetected.add(character);
					}
				}
				if(!facingRight || !onlyInFrontOfYou)
				{
					if
					(
						character.getLocationX() < locationX &&
						character.getLocationX() > locationX - sightRangeX &&
						character.getLocationY() > locationY - height - sightRangeY /2 &&
						character.getLocationY() < locationY + height / 2 + sightRangeY / 2
					)
					{
						enemiesDetected.add(character);
					}
				}
			}
		}
		
		return enemiesDetected;
	}
	

	
	public void checkProjectile(Projectile projectile)
	{
		if(endPunch)
		{
			if(facingRight)
			{
				if
				(
					projectile.getLocationX() > locationX &&
					projectile.getLocationX() < locationX + punchRange &&
					projectile.getLocationY() > locationY - height / 2 &&
					projectile.getLocationY() < locationY + height / 2
				)
				{
					projectile.bounce();
				}
			}
			else
			{
				if
				(
					projectile.getLocationX() < locationX &&
					projectile.getLocationX() > locationX - punchRange &&
					projectile.getLocationY() > locationY - height / 2 &&
					projectile.getLocationY() < locationY + height / 2
				)
				{
					projectile.bounce();
				}
			}
		}
		else if(tennisPlayer && inControl)
		{
			tennisTimer++;
			if(tennisTimer >= tennisCooldown * 10) tennisTimer = tennisCooldown;
			
			if(facingRight)
			{
				if
				(
					projectile.getLocationX() > locationX &&
					projectile.getLocationX() < locationX + 100 &&
					projectile.getLocationY() > locationY - 60 &&
					projectile.getLocationY() < locationY + 60
				)
				{

					if(tennisTimer >= tennisCooldown)
					{
						tennisTimer = 0;
						
						if(mainMap.RNG(0, 100) < tennisSuccessChance)
						{
							startPunch = true;
							projectile.bounce();
							tennisSuccessChance -= 5;
							System.out.println("tennisSuccessChance: " + tennisSuccessChance);
						}
					}
				}
			}
			else
			{
				if
				(
					projectile.getLocationX() < locationX &&
					projectile.getLocationX() > locationX - 100 &&
					projectile.getLocationY() > locationY - 60 &&
					projectile.getLocationY() < locationY + 60
				)
				{
					if(tennisTimer >= tennisCooldown)
					{
						tennisTimer = 0;
						if(mainMap.RNG(0, 100) < tennisSuccessChance)
						{
							startPunch = true;
							projectile.bounce();
							tennisSuccessChance -= 5;
							System.out.println("tennisSuccessChance: " + tennisSuccessChance);
						}
					}
				}
			}
		}
	}
	
	
	public void checkAttack(ArrayList<Unit> characterList)
	{
		
		for(int i = 0; i < characterList.size(); i++)
		{			
			
			Unit character = characterList.get(i);
			if(character.getFriendly() != friendly && !character.getUntouchable() && !character.getInvulnerable() && !charactersHit.contains(character))
			{
				//********************************************************************************
				//*Punching                                                                      *
				//********************************************************************************	
				
				
				if(endPunch)
				{
					if(facingRight)
					{
						if
						(
							character.getLocationX() > locationX &&
							character.getLocationX() < locationX + punchRange &&
							character.getLocationY() > locationY - height / 2 &&
							character.getLocationY() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(punchDamage, this);
						}
					}
					else
					{
						if
						(
							character.getLocationX() < locationX &&
							character.getLocationX() > locationX - punchRange &&
							character.getLocationY() > locationY - height / 2 &&
							character.getLocationY() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(punchDamage, this);
						}
					}
				}

				//********************************************************************************
				//*Dashing                                                                      *
				//********************************************************************************	
				if(endDash)
				{
					if(facingRight)
					{
						if
						(
							character.getLocationX() > locationX &&
							character.getLocationX() < locationX + dashRange &&
							character.getLocationY() > locationY - height / 2 &&
							character.getLocationY() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(dashDamage, this);
						}
					}
					else
					{
						if
						(
							character.getLocationX() < locationX &&
							character.getLocationX() > locationX - dashRange &&
							character.getLocationY() > locationY - height / 2 &&
							character.getLocationY() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(dashDamage, this);
						}
					}
				}
			}
		} // End for loop		
	}
	
	public void emoteExclamation()
	{
		mainMap.spawnDoodad.spawnEmotionBubble(locationX + width / 2, locationY - height / 2, DoodadData.EmotionBubbles.Exclamation);
	}
	
	public void addBuff(Buff buff)
	{
		for(int i = 0; i < buffs.size(); i++)
		{
			if(buffs.get(i).getBuff().equals(buff.getBuff()))
			{
				buffs.remove(i);
				i--;
			}
		}
		buffs.add(buff);
	}
	
	public ArrayList<Buff> getBuffs()
	{
		return buffs;
	}
	
	public void useItem(int inventorySlotX, int inventorySlotY)
	{
		Item item = inventory.getItem(inventorySlotX, inventorySlotY);
		if(item != null && item.getConsumable())
		{
			item.use(this);
		}
	}
	
	public void playCastSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playPunchSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[0]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Attack + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playIsHit()
	{
		int RNG = mainMap.RNG(1, numberofSounds[1]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Hurt + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playJumpSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[2]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Jump + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playLootSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[3]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Loot + (RNG < 10 ? "0" : "") + RNG);	
	}
	
	public void playCannotCarrySound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[4]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.CannotCarry + (RNG < 10 ? "0" : "") + RNG);	
	}
	
	public void playCannotOpenSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[5]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.CannotOpen + (RNG < 10 ? "0" : "") + RNG);	
	}
	
	public void playHitSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[7]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Hit + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void playRecoverSound()
	{
		int RNG = mainMap.RNG(1, numberofSounds[8]);
		if(RNG == -1)
			return;
		JukeBox.play(unitType + soundTypes.Recover + (RNG < 10 ? "0" : "") + RNG);
	}
	
	public void setAnimationState(int animationState)
	{
		currentAction = this.animationState[animationState];
		animation.setFrames(sprites.get(this.animationState[animationState]));
		animation.setDelay(this.animationDelay[animationState]);
	}

	
	
	public void draw(Graphics2D graphics)
	{
		try
		{
			setMapPosition();
			
			// Draw Summoning Effect
			if(summoningEffect != null)
			{
				summoningEffect.draw(graphics);
			}
			
			
			
			
			if(spawning) return;
			
			// Draw unit
			super.draw(graphics);
		}
		catch(Exception exception)
		{
			System.out.println("Exception in Unit: " + name);
			exception.printStackTrace();
		}
	}
	
	public void drawNamePlate(Graphics2D graphics)
	{
		try
		{
			if(player || hidden)
			{
				return;
			}
			
			int length = (int)graphics.getFontMetrics().getStringBounds(name, graphics).getWidth();
			int height = (int)graphics.getFontMetrics().getStringBounds(name, graphics).getHeight();
			int drawX = (int)(locationX + mapPositionX) - length / 2;
			int drawY = (int)(locationY + mapPositionY) - this.height / 2 - (height / 3);
			
			graphics.setFont(new Font("Arial", Font.PLAIN, 14));
			graphics.setColor(Color.BLACK);
			graphics.drawString(name, DrawingConstants.shiftWest( (int) drawX, 1), DrawingConstants.shiftNorth( (int) drawY, 1));
			graphics.drawString(name, DrawingConstants.shiftWest( (int) drawX, 1), DrawingConstants.shiftSouth( (int) drawY, 1));
			graphics.drawString(name, DrawingConstants.shiftEast( (int) drawX, 1), DrawingConstants.shiftNorth( (int) drawY, 1));
			graphics.drawString(name, DrawingConstants.shiftEast( (int) drawX, 1), DrawingConstants.shiftSouth( (int) drawY, 1));
			
			graphics.setColor(new Color(friendly? 100 : 255 , 100, friendly ? 250 : 0));
			graphics.setFont(new Font("Arial", Font.PLAIN, 14));
			graphics.drawString(name, drawX, drawY);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void drawHealthBars(Graphics2D graphics)
	{
		try
		{
			if(player || hidden  || health == maxHealth)
			{
				return;
			}
			
			int length = 75;
			int height = 20;
			int drawX = (int)(locationX + mapPositionX) - length / 2;
			int drawY = (int)(locationY + mapPositionY) - this.height / 2;
			
			graphics.drawImage
			(
				Content.BarFrame[0][0],
				drawX,
				drawY,
				length,
				height,
				null
			);

			graphics.drawImage
			(
				Content.HealthBar[0][0],
				drawX,
				drawY,
				(int)((health/maxHealth) * length),
				height,
				null
			);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
