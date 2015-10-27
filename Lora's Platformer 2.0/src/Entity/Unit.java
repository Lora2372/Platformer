package Entity;

import java.util.ArrayList;
import java.util.Random;
import Audio.JukeBox;
import javax.imageio.ImageIO;
import Entity.Doodad.*;
import Entity.Player.ConversationBox;
import Entity.Player.Player;
import Entity.Projectile.ArcaneBall;
import Entity.Projectile.ElectricBall;
import Entity.Projectile.FireBallLarge;
import Entity.Projectile.FireBallSmall;
import Entity.Projectile.Projectile;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Unit extends MapObject
{
	protected MainMap mainMap;
	protected ConversationBox conversationbox;
	
	// Character stuff
	protected String name;
	protected boolean player;
	
	
	protected ArrayList<Unit> charactersHit = new ArrayList<Unit>();
	
	protected ElectricBall electricBall;
	
	
	protected BufferedImage[] portrait;
	
	protected double spawnX;
	protected double spawnY;
	
	protected double aim;
	
	protected int health;
	protected int maxHealth;
	protected int healthCounter;
	protected int healthRegen;
	
	protected int sightRangeX;
	protected int sightRangeY;
	
	protected boolean tennisPlayer;
	
	protected boolean sexytime1;
	protected boolean sexytime2;
	
	protected int mana;
	protected int maxMana;
	protected int manaCounter;
	protected int manaRegen;
	
	protected int stamina;
	protected int maxStamina;
	protected int staminaCounter;
	protected int staminaRegen;
	
	protected int damageOnTouch;
	protected boolean friendly;
	
	protected boolean activatable;
	
	protected boolean removeMe;
	
	protected boolean dead;
	protected boolean stunned;
	protected long flinchTimer;

	protected boolean inConversation;
	
	protected String spritePath;
	protected int[] animationState;
	
	protected double saveFallSpeed;
	
	// Small fireBall
	protected boolean fireBallSmallCasting;
	protected boolean fireBallSmallDoneCasting;
	protected boolean fireBallSmallHolding;
	
	protected int fireBallSmallManaCost;
	protected int fireBallSmallDamage;
	
	// Large fireBall
	protected boolean fireBallLargeCasting;
	protected boolean fireBallLargeDoneCasting;
	protected boolean fireBallLargeHolding;
	
	protected int fireBallLargeManaCost;
	protected int fireBallLargeDamage;
	
	// electricBall
	protected boolean electricBallCasting;
	protected boolean electricBallDoneCasting;
	protected boolean electricBallHolding;
	
	protected int electricBallManaCost;
	protected int electricBallDamage;
	
	// arcaneBall
	protected boolean arcaneBallCasting;
	protected boolean arcaneBallDoneCasting;
	protected boolean arcaneBallHolding;
	
	protected int arcaneBallManaCost;
	protected int arcaneBallDamage;
	
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
	
	// Magic Shield
	protected boolean magicShieldCasting;
	protected boolean magicShieldDoneCasting;
	protected boolean magicShieldHolding;
	
	protected int magicShieldManaCost;
	
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
	public Unit(
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
			int health,
			int maxHealth,
			int healthRegen,			
			int stamina,
			int maxStamina,
			int staminaRegen,
			int sightRangeX,
			int sightRangeY,
			int punchCost,
			int punchDamage,			
			int punchRange,
			int dashCost,
			int dashDamage,	
			int dashRange,
			double dashSpeed,
			int mana, 
			int maxMana,
			int manaRegen,
			int fireBallSmallManaCost,
			int fireBallSmallDamage,	
			int fireBallLargeManaCost,
			int fireBallLargeDamage,
			int electricBallManaCost,
			int electricBallDamage,
			int arcaneBallManaCost,
			int arcaneBallDamage,
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
			double spawnX,
			double spawnY,
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
		this.healthRegen = healthRegen;
		this.stamina = stamina;
		this.maxStamina = maxStamina;
		this.staminaRegen = staminaRegen;
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
		this.manaRegen = manaRegen;
		this.fireBallSmallManaCost = fireBallSmallManaCost;
		this.fireBallSmallDamage = fireBallSmallDamage;
		this.fireBallLargeManaCost = fireBallLargeManaCost;
		this.fireBallLargeDamage = fireBallLargeDamage;
		this.electricBallManaCost = electricBallManaCost;
		this.electricBallDamage = electricBallDamage;
		this.arcaneBallManaCost = arcaneBallManaCost;
		this.arcaneBallDamage = arcaneBallDamage;
		this.spritePath = spritePath;
		this.animationState = animationState;
		this.numFrames = numFrames;
		this.animationDelay = animationDelay;
		this.damageOnTouch = damageOnTouch;
		this.friendly = friendly;
		this.activatable = activatable;
		this.name = name;
		this.invulnerable = invulnerable;
		this.untouchable = untouchable;
		this.unkillable = unkillable;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.mainMap = mainMap;
				
		healthCounter = 0;
		manaCounter = 0;
		staminaCounter = 0;
		
		setPosition(spawnX, spawnY);
		
		portrait = Content.PortraitPlayer[0];
		
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
	
	public TileMap getTileMap() { return tileMap; }
	
	public void calculateAim(Unit character)
	{
		
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
					tempY += 25;		}
		}
		else
		{
			tempX = character.locationX;
			tempY = character.locationY;			
		}
		
		aim = Math.atan2(tempY - locationY, tempX - locationX);
	}
	
	public void setTennisPlayer(boolean b) { tennisPlayer = b; }
	
	public BufferedImage[] getPortrait() { return portrait;}
	
	public boolean isPlayer() { return player; }
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getMana() { return mana; }
	public int getMaxMana() { return maxMana; }
	public int getStamina() { return stamina; }
	public int getMaxStamina() { return maxStamina; }
	
	public int getFireBallSmallManaCost() { return fireBallSmallManaCost; }
	public int getFireBallLargeManaCost() { return fireBallLargeManaCost; }
	
	public int getDashStaminaCost()  { return dashCost; }
	public int getPunchStaminaCost() { return punchCost; }
	
	public boolean getActivatable() { return activatable; }
	
	public boolean getRemoveMe() { return removeMe; }
	
	public void interact(Player player)
	{

	}
	
	public void setSpawnPoint(double spawnX, double spawnY) 
	{
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	public void respawn()
	{
		locationX = spawnX;
		locationY = spawnY;
		dead = false;
		health = maxHealth;
	}
	
	public void inControl(boolean b) {inControl = b;}
	public void untouchable(boolean b) { untouchable = b; }
	public void invulnerable(boolean b) { invulnerable = b; }
	
	public boolean getUntouchable() { return untouchable; }
	public boolean getInvulnerable() { return invulnerable; }
	
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
		if(mana > fireBallSmallManaCost && inControl && !fireBallSmallHolding)
		{
			fireBallSmallCasting = true;
			inControl = false;			
		}
	}
	public void releaseSmallFireBall() { fireBallSmallHolding = false; }
	
	public void setCastingLargeFireBall()
	{
		if(mana > fireBallLargeManaCost && inControl && !fireBallLargeHolding)
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
	
	public String getName() { return name; }
	
	public double getDirectionX() { return directionX; }
	public double getDirectionY() { return directionY; }
	

	
	public boolean getInConversation() { return inConversation; }
	public void setInConversation(boolean b) { inConversation = b; }
	
	public void updateAI(ArrayList<Unit> characterList){}
	
	public int getPunchRange() { return punchRange; }
	
	public boolean getFacingRight() { return facingRight; }
	
	public boolean getFriendly() { return friendly; }

	public boolean isDead() { return dead; }
	
	public boolean getSpawning() { return spawning; };
	public void setSpawning(boolean b) { initializeSpawning = b; }
	
	public double getSpawnX() { return spawnX; }
	public double getSpawnY() { return spawnY; }
		
	public void hit(int damage)
	{
		if(dead) return;
		health -= damage;
		if( health < 0)health = 0;
		if(health == 0 && !unkillable)
		{
			dead = true;
		}
		iAmHit();
		if(!stunned)setStunned(500);
		inControl = false;
		flinchTimer = System.nanoTime();
	}
	
	public void iAmHit() { }
	
	public void playCastSound(){}
	
	public void playPunchSound(){}
	
	public void playJumpSound() { }
	
	
	public void update(ArrayList<Unit> characterList)
	{
		// Update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
				
		if(initializeSpawning)
		{
			summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
			initializeSpawning = false;
			spawning = true;
			JukeBox.play("Teleport");
		}
		
		if(summoningEffect != null && !summoningEffect.animation.hasPlayedOnce())
		{
			summoningEffect.update();
		}
		if(spawning && summoningEffect.animation.hasPlayedOnce())
		{
			summoningEffect = null;
			spawning = false;
			if(!inConversation)
				inControl = true;
		}
		
		if(locationX > tileMap.getWidth() || locationX < 0 || locationY > tileMap.getHeight())
		{
			setPosition(spawnX, spawnY);
			initializeSpawning = true;
			directionX = 0;
			directionY = 0;
			inControl = false;
		}
		
		// Regeneration
		healthCounter++;
		if(healthCounter > healthRegen && healthCounter != -1 && healthRegen != 0)
		{
			healthCounter = 0;
			if(health < maxHealth) health++;
		}

		manaCounter++;
		if(manaCounter > manaRegen && manaCounter != -1)
		{
			manaCounter = 0;
			if(mana < maxMana) mana++;
		}
		
		staminaCounter++;
		if(staminaCounter > staminaRegen && staminaCounter != -1)
		{
			staminaCounter = 0;
			if(stamina < maxStamina) stamina++;
		}
		
		

		
		// Set animations
		
		
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
			mana -= arcaneBallManaCost;
			arcaneBallCasting = false;
			arcaneBallDoneCasting = true;
			
			
			ArcaneBall arcaneBall = new ArcaneBall(tileMap, mainMap, facingRight, up, down, aim, friendly, arcaneBallDamage);
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

				mana -= fireBallSmallManaCost;
				fireBallSmallCasting = false;
				fireBallSmallDoneCasting = true;
		
				calculateAim(null);
				
				FireBallSmall fireBall = new FireBallSmall(tileMap, mainMap, facingRight, up, down, aim, friendly, fireBallSmallDamage);
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
				
				ElectricBallChargeUp electricBallChargeUp = new ElectricBallChargeUp(tileMap, locationX, locationY - 20);
				mainMap.addEffect(electricBallChargeUp);
				
				if(directionY == 0) directionX = 0;
				JukeBox.play("ElectricBallChargeUp");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= electricBallManaCost;
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
				
				
				electricBall = new ElectricBall(tileMap, mainMap, facingRight, up, down, aim, friendly, electricBallDamage);
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
				mana -= fireBallLargeManaCost;
				fireBallLargeCasting = false;
				fireBallLargeDoneCasting = true;
				
				calculateAim(null);
				FireBallLarge fireBall = new FireBallLarge(tileMap, mainMap, facingRight, up, down, aim, friendly, fireBallLargeDamage);
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
				
				MagicShield magicShield = new MagicShield(tileMap, locationX, locationY);
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
		//*Walking                                                                       *
		//********************************************************************************	
		else if((left || right) && inControl)
		{
			if(currentAction != animationState[1])
			{
				currentAction = animationState[1];
				animation.setFrames(sprites.get(animationState[1]));
				animation.setDelay(animationDelay[1]);
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
							character.getx() > locationX &&
							character.getx() < locationX + sightRangeX &&
							character.gety() > locationY - height - sightRangeY /2 &&
							character.gety() < locationY + height / 2 + sightRangeY / 2
						)
					{
						enemiesDetected.add(character);
					}
				}
				if(!facingRight || !onlyInFrontOfYou)
				{
					if
						(
								character.getx() < locationX &&
								character.getx() > locationX - sightRangeX &&
								character.gety() > locationY - height - sightRangeY /2 &&
								character.gety() < locationY + height / 2 + sightRangeY / 2
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
						projectile.getx() > locationX &&
						projectile.getx() < locationX + punchRange &&
						projectile.gety() > locationY - height / 2 &&
						projectile.gety() < locationY + height / 2
				)
				{
					projectile.bounce();
				}
			}
			else
			{
				if
				(
						projectile.getx() < locationX &&
						projectile.getx() > locationX - punchRange &&
						projectile.gety() > locationY - height / 2 &&
						projectile.gety() < locationY + height / 2
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
						projectile.getx() > locationX &&
						projectile.getx() < locationX + 100 &&
						projectile.gety() > locationY - 60 &&
						projectile.gety() < locationY + 60
				)
				{

					if(tennisTimer >= tennisCooldown)
					{
						tennisTimer = 0;
						
						Random randomizer = new Random();
						int random2 = randomizer.nextInt((100 - 0) + 1);
						if(random2 < 70)
						{
							startPunch = true;
							projectile.bounce();
						}
					}
				}
			}
			else
			{
				if
				(
						projectile.getx() < locationX &&
						projectile.getx() > locationX - 100 &&
						projectile.gety() > locationY - 60 &&
						projectile.gety() < locationY + 60
				)
				{
					if(tennisTimer >= tennisCooldown)
					{
						tennisTimer = 0;
					
						Random randomizer = new Random();
						int random2 = randomizer.nextInt((100 - 0) + 1);
						if(random2 < 70)
						{
							startPunch = true;
							projectile.bounce();
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
								character.getx() > locationX &&
								character.getx() < locationX + punchRange &&
								character.gety() > locationY - height / 2 &&
								character.gety() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(punchDamage);
						}
					}
					else
					{
						if
						(
							character.getx() < locationX &&
							character.getx() > locationX - punchRange &&
							character.gety() > locationY - height / 2 &&
							character.gety() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(punchDamage);
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
								character.getx() > locationX &&
								character.getx() < locationX + dashRange &&
								character.gety() > locationY - height / 2 &&
								character.gety() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(dashDamage);
						}
					}
					else
					{
						if
						(
							character.getx() < locationX &&
							character.getx() > locationX - dashRange &&
							character.gety() > locationY - height / 2 &&
							character.gety() < locationY + height / 2
						)
						{
							charactersHit.add(character);
							character.hit(dashDamage);
						}
					}
				}
			}
		} // End for loop		
	}
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		// Draw Summoning Effect
		if(summoningEffect != null)
		{
			summoningEffect.draw(graphics);
		}
		
		
		// Draw player
		
		if(spawning) return;
		
		
		super.draw(graphics);
		
		if(conversationbox != null)
		{			
			if(conversationbox.inConversation())
			{
				conversationbox.draw(graphics);
			}
		}
	}
}
