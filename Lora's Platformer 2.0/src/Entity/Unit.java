package Entity;

import java.util.ArrayList;
import java.util.Random;

import Audio.JukeBox;

import javax.imageio.ImageIO;

import Entity.Doodad.ElectricballChargeUp;
import Entity.Doodad.SummoningEffect;
import Entity.Player.ConversationBox;
import Entity.Player.Player;
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
	
	protected Electricball electricball;
	
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
	protected boolean inControl;
	protected boolean inConversation;
	
	protected String spritePath;
	protected int[] animationState;
	
	protected double saveFallSpeed;
	
	// Small fireball
	protected boolean fireballSmallCasting;
	protected boolean fireballSmallDoneCasting;
	protected boolean fireballSmallHolding;
	
	protected int fireballSmallManaCost;
	protected int fireballSmallDamage;
	
	// Large fireball
	protected boolean fireballLargeCasting;
	protected boolean fireballLargeDoneCasting;
	protected boolean fireballLargeHolding;
	
	protected int fireballLargeManaCost;
	protected int fireballLargeDamage;
	
	// electricball
	protected boolean electricballCasting;
	protected boolean electricballDoneCasting;
	protected boolean electricballHolding;
	
	protected int electricballManaCost;
	protected int electricballDamage;
	
	// arcaneball
	protected boolean arcaneballCasting;
	protected boolean arcaneballDoneCasting;
	protected boolean arcaneballHolding;
	
	protected int arcaneballManaCost;
	protected int arcaneballDamage;
	
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
	
	// Gliding;
	protected boolean gliding;
	
	// Flying
	 protected boolean flying;
	
	 
	//  Animations 
	protected ArrayList<BufferedImage[]> sprites;
	protected int[] numFrames;
	protected int[] animationDelay;
	
	protected boolean initializeSpawning;
	protected boolean spawning;
	protected SummoningEffect summoningEffect;
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
			int fireballSmallManaCost,
			int fireballSmallDamage,	
			int fireballLargeManaCost,
			int fireballLargeDamage,
			int electricballManaCost,
			int electricballDamage,
			int arcaneballManaCost,
			int arcaneballDamage,
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
		this.fireballSmallManaCost = fireballSmallManaCost;
		this.fireballSmallDamage = fireballSmallDamage;
		this.fireballLargeManaCost = fireballLargeManaCost;
		this.fireballLargeDamage = fireballLargeDamage;
		this.electricballManaCost = electricballManaCost;
		this.electricballDamage = electricballDamage;
		this.arcaneballManaCost = arcaneballManaCost;
		this.arcaneballDamage = arcaneballDamage;
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
		
		if(name == "Lora") System.out.println("Running character");
		
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
	
	public int getFireballSmallManaCost() { return fireballSmallManaCost; }
	public int getFireballLargeManaCost() { return fireballLargeManaCost; }
	
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
	
	public void setCastingSmallFireball()
	{
		if(mana > fireballSmallManaCost && inControl && !fireballSmallHolding && inControl)
		{
			fireballSmallCasting = true;
			inControl = false;			
		}
	}
	public void releaseSmallFireball() { fireballSmallHolding = false; }
	
	public void setCastingLargeFireball()
	{
		if(mana > fireballLargeManaCost && inControl && !fireballLargeHolding && inControl)
		{
			fireballLargeCasting = true;
			inControl = false;			
		}
	}
	public void releaseLargeFireball() { fireballLargeHolding = false; }
	
	public boolean getEndPunch() { return endPunch; }
	
	public void setPunching()
	{
		if(stamina > punchCost && inControl && !holdingPunch && inControl)
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
	
	protected void getNextPosition()
	{
		if(spawning) return;
		// Movement
		if(left && inControl)
		{

			directionX -= moveSpeed;
			if(directionX < -maxSpeed)
			{
				directionX = -maxSpeed;
			}
		}
		else if(right && inControl)
		{

			directionX += moveSpeed;
			if(directionX > maxSpeed)
			{
				directionX = maxSpeed;
			}
		}
		else
		{
			if(directionX > 0)
			{
				directionX -= stopSpeed;
				if(directionX < 0)
				{
					directionX = 0;
				}
			}
			else if(directionX < 0)
			{
				directionX += stopSpeed;
				if(directionX > 0)
				{
					directionX = 0;
				}
			}
		}
		
		// Cannot move while attacking
		//Might implement this later
		
		if(flying)
		{
			if(down)
			{
				directionY += moveSpeed;
				if(directionY > maxSpeed) directionY = maxSpeed; 
			}
			else if(up)
			{
				directionY -= moveSpeed;
				if(directionY*-1 > maxSpeed) directionY = maxSpeed*-1;
			}
			
			if(right)
			{
				directionX += moveSpeed;
				if(directionX > maxSpeed) directionX = maxSpeed;
			}
			else if(left)
			{
				directionX -= moveSpeed;
				if(directionX*-1 > maxSpeed) directionX = maxSpeed*-1;
			}
			
		}
		
		
		// Jumping
		if(jumping && !falling && inControl && !flying)
		{
			// I'll leave the jump sound commented out until we find a better one.
//			JukeBox.play("jump");
			playJumpSound();
			directionY = jumpStart;
			falling = true;
		}
		
		// Falling
//		System.out.println("character name: " + getName() + ", falling: " + falling);
		if( (falling || swimming) && !flying)
		{
			if(directionY > 0 && gliding) directionY += fallSpeed * 0.1;
			else directionY += fallSpeed;
		
			if(directionY > 0) jumping = false;
			if(directionY < 0 && !jumping) directionY += stopJumpSpeed;
			
			if(directionY > maxFallSpeed) directionY = maxFallSpeed;
		}
		
		
		
	}
	
	public boolean getInConversation() { return inConversation; }
	public void setInConversation(boolean b) { inConversation = b; }
	
	public void updateAI(ArrayList<Unit> characterList){}
	
	public int getPunchRange() { return punchRange; }
	
	public boolean getFacingRight() { return facingRight; }
	
	public boolean getFriendly() { return friendly; }

	public boolean isDead() { return dead; }
	
	public boolean getSpawning() { return spawning; };
	
	public void setSpawning(boolean b) { initializeSpawning = b; }
	
	public void hit(int damage)
	{
		if(dead) return;
		health -= damage;
		if( health < 0)health = 0;
		if(health == 0 && !unkillable)
		{
			System.out.println(name + " has died.");
			System.out.println("Unkillable: " + unkillable);
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
		
//		System.out.println(name + "'s health: " + health);
		
		if(initializeSpawning)
		{
			summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
			initializeSpawning = false;
			spawning = true;
			JukeBox.play("teleport");
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
			System.out.println("spawnX: " + spawnX + ", spawnY: " + spawnY);
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
				
				playPunchSound();
				
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
		//*arcaneball                                                                  *
		//********************************************************************************	
		else if(arcaneballCasting)
		{
			mana -= arcaneballManaCost;
			arcaneballCasting = false;
			arcaneballDoneCasting = true;
			
			
			Arcaneball arcaneball = new Arcaneball(tileMap, mainMap, facingRight, up, down, aim, friendly, arcaneballDamage);
			arcaneball.setPosition(locationX, locationY - 20);
			mainMap.addProjectile(arcaneball);
			
			currentAction = animationState[0];
			animation.setFrames(sprites.get(animationState[0]));
			animation.setDelay(animationDelay[0]);
			
			JukeBox.play("ElectricballActive");
			JukeBox.play("ElectricballThrow");
			playCastSound();
		}
		
		
		//********************************************************************************
		//*Small fireball                                                                *
		//********************************************************************************	
		else if(fireballSmallCasting)
		{
			
			if(currentAction != animationState[8])
			{
				currentAction = animationState[8];
				animation.setFrames(sprites.get(animationState[8]));
				animation.setDelay(animationDelay[8]);
//				if(directionY == 0) directionX = 0;
				JukeBox.play("FireballSmallLaunch");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= fireballSmallManaCost;
				fireballSmallCasting = false;
				fireballSmallDoneCasting = true;
		
				calculateAim(null);
				
				FireballSmall fireball = new FireballSmall(tileMap, mainMap, facingRight, up, down, aim, friendly, fireballSmallDamage);
				fireball.setPosition(locationX, locationY - 20);
				mainMap.addProjectile(fireball);
				
				playCastSound();
				
				currentAction = animationState[9];
				animation.setFrames(sprites.get(animationState[9]));
				animation.setDelay(animationDelay[9]);
				
				
				// Create new fireball stuff here
			}
		}
		
		else if(currentAction == animationState[9] && fireballSmallDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				fireballSmallDoneCasting = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*electricball                                                                  *
		//********************************************************************************	
		else if(electricballCasting)
		{
			
			if(currentAction != animationState[8])
			{
				currentAction = animationState[8];
				animation.setFrames(sprites.get(animationState[8]));
				animation.setDelay(animationDelay[8] * 3);
				
				ElectricballChargeUp electricballChargeUp = new ElectricballChargeUp(tileMap, locationX, locationY - 20);
				mainMap.addEffect(electricballChargeUp);
				
				if(directionY == 0) directionX = 0;
				JukeBox.play("ElectricballChargeUp");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= electricballManaCost;
				electricballCasting = false;
				electricballDoneCasting = true;
				
				ArrayList<Unit> enemiesDetected = detectEnemy(characterList, false);
				if(enemiesDetected != null)
				{
					if(enemiesDetected.size() > 0)
					{
						calculateAim(enemiesDetected.get(0));
					}
					else
					{
						System.out.println("No enemy found..");
						return;
					}
				}
				
				
				
				
				electricball = new Electricball(tileMap, mainMap, facingRight, up, down, aim, friendly, electricballDamage);
				electricball.setPosition(locationX, locationY - 20);
				mainMap.addProjectile(electricball);
				
				currentAction = animationState[9];
				animation.setFrames(sprites.get(animationState[9]));
				animation.setDelay(animationDelay[9]);
				
				JukeBox.play("ElectricballActive");
				JukeBox.play("ElectricballThrow");
				playCastSound();
			}
		}
		
		else if(currentAction == animationState[9] && electricballDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				electricballDoneCasting = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*Large fireball                                                                *
		//********************************************************************************	
		else if(fireballLargeCasting)
		{
			if(currentAction != animationState[10])
			{
				currentAction = animationState[10];
				animation.setFrames(sprites.get(animationState[10]));
				animation.setDelay(animationDelay[10]);
				if(directionY == 0) directionX = 0;
				JukeBox.play("FireballLargeLaunch");
			}
			if(animation.hasPlayedOnce())
			{
				mana -= fireballLargeManaCost;
				fireballLargeCasting = false;
				fireballLargeDoneCasting = true;
				
				calculateAim(null);
				FireballLarge fireball = new FireballLarge(tileMap, mainMap, facingRight, up, down, aim, friendly, fireballLargeDamage);
				fireball.setPosition(locationX, locationY);
				mainMap.addProjectile(fireball);
				
				playCastSound();
				
				currentAction = animationState[11];
				animation.setFrames(sprites.get(animationState[11]));
				animation.setDelay(animationDelay[11]);
				
				
				// Create new fireball stuff here
			}
		}
		
		else if(currentAction == animationState[11] && fireballLargeDoneCasting)
		{
			if (animation.hasPlayedOnce()) 
			{
				fireballLargeDoneCasting = false;
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
	
//	public ArrayList<Projectile> detectProjectiles
	
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
	
	public void turnProjectile(Projectile projectile)
	{
		JukeBox.play(projectile.explosionSound + "Active");
		
		projectile.setFacing(!projectile.getFacing());
		projectile.setDirection(projectile.getDirectionX() * - 1.05, projectile.getDirectionY() * -1.05);
		projectile.setFriendly(!projectile.getFriendly());
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
					turnProjectile(projectile);
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
					turnProjectile(projectile);
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
							turnProjectile(projectile);
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
							turnProjectile(projectile);
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
