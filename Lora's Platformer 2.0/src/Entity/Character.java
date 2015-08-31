package Entity;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Audio;
import TileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends MapObject
{
	// Character stuff
	protected String name;
	
	protected double spawnX;
	protected double spawnY;
	
	protected int health;
	protected int maxHealth;
	protected int healthCounter;
	protected int healthRegen;
	
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
	
	protected boolean dead;
	protected boolean flinching;
	protected long flinchTimer;
	protected boolean inControl;
	
	protected String spritePath;
	protected int[] animationState;
	protected int spriteParts;
	
	protected double saveFallSpeed;
	
	// Small fireball
	protected boolean castingSmallFireball;
	protected boolean doneCastingSmallFireball;
	protected boolean holdingSmallFireball;
	
	protected int smallFireballManaCost;
	protected int smallFireballDamage;
	protected ArrayList<Projectile> projectiles;
	
	// Large fireball
	protected boolean castingLargeFireball;
	protected boolean doneCastingLargeFireball;
	protected boolean holdingLargeFireball;
	
	protected int largeFireballManaCost;
	protected int largeFireballDamage;
	
	// Punch
	protected boolean startPunch;
	protected boolean endPunch;
	protected boolean holdingPunch;
	
	protected int punchDamage;
	protected int punchRange;
	protected int punchCost;
	
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
	
	// Animation actions, these are enums similar to the GameState, we use them to determine the index of the sprite animation
	
	
//	protected static final int animationState[0]					= 0;
//	protected static final int animationState[1]				= 1;
//	protected static final int animationState[2]				= 2;
//	protected static final int animationState[3]				= 3;
//	
//	protected static final int animationState[4]				= 4;
//	protected static final int animationState[5]				= 5;
//	
//	protected static final int animationState[6]				= 6;
//	protected static final int animationState[7] 				= 7;
//	
//	protected static final int animationState[8] 	= 8;
//	protected static final int animationState[9] 		= 9;
//	
//	protected static final int animationState[10] 	= 10;
//	protected static final int animationState[11]		= 11;
//	
//	protected static final int animationState[12] 				= 12;
//	protected static final int animationState[13] 					= 13;
//	protected static final int animationState[14]					= 14;
//	
//	protected static final int animationState[14]				= 14;
//	protected static final int animationState[15] 			= 15;
	
	// Constructor
	public Character(
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
			int smallFireballManaCost,
			int smallFireballDamage,			
			int largeFireballCost,
			int largeFireballDamage,					
			String spritePath,
			int[] animationState,
			int[] numFrames,
			int damageOnTouch,
			boolean friendly,
			String name,
			double spawnX,
			double spawnY
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
		this.smallFireballManaCost = smallFireballDamage;
		this.smallFireballDamage = smallFireballDamage;
		this.largeFireballManaCost = largeFireballManaCost;
		this.largeFireballDamage = largeFireballDamage;
		this.spritePath = spritePath;
		this.animationState = animationState;
		this.numFrames = numFrames;
		this.damageOnTouch = damageOnTouch;
		this.friendly = friendly;
		this.name = name;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		
		healthCounter = 0;
		manaCounter = 0;
		staminaCounter = 0;
		
		projectiles = new ArrayList<Projectile>();
		
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
	
	public int getHealth() { return health; }
	public int getMaxhealth() { return maxHealth; }
	public int getMana() { return mana; }
	public int getMaxMana() { return maxMana; }
	public int getStamina() { return stamina; }
	public int getMaxStamina() { return maxStamina; }
	
	public void setSpawnPoint(double spawnX, double spawnY) 
	{
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	
	public void setCastingSmallFireball()
	{
		if(mana > smallFireballManaCost && inControl && !holdingSmallFireball && inControl)
		{
			castingSmallFireball = true;
			inControl = false;			
		}
	}
	public void releaseSmallFireball() { holdingSmallFireball = false; }
	
	public void setCastingLargeFireball()
	{
		if(mana > largeFireballManaCost && inControl && !holdingLargeFireball && inControl)
		{
			castingLargeFireball = true;
			inControl = false;			
		}
	}
	public void releaseLargeFireball() { holdingLargeFireball = false; }
	
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
			if((stamina > dashCost && !holdingDash) && inControl)
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
	
	protected void getNextPosition()
	{
		// Movement
		if(left && inControl)
		{

			dx -= moveSpeed;
			if(dx < -maxSpeed)
			{
				dx = -maxSpeed;
			}
		}
		else if(right && inControl)
		{

			dx += moveSpeed;
			if(dx > maxSpeed)
			{
				dx = maxSpeed;
			}
		}
		else
		{
			if(dx > 0)
			{
				dx -= stopSpeed;
				if(dx < 0)
				{
					dx = 0;
				}
			}
			else if(dx < 0)
			{
				dx += stopSpeed;
				if(dx > 0)
				{
					dx = 0;
				}
			}
		}
		
		// Cannot move while attacking
		//Might implement this later
		
		
		// Jumping
		if(jumping && !falling && inControl)
		{
			dy = jumpStart;
			falling = true;
		}
		
		// Falling
//		System.out.println("character name: " + getName() + ", falling: " + falling);
		if(falling)
		{
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
		
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
		}
		
		if(swimming)
		{
			
		}
		
		
	}
	
	
	public void updateAI(){}

	public boolean isDead() { return dead; }
	
	public void hit(int damage)
	{
		if(dead || flinching) return;
		health -= damage;
		if( health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	public void update()
	{
		// Update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		
		if(x > tileMap.getWidth() || x < 0 || y > tileMap.getHeight())
		{
			System.out.println("spawnX: " + spawnX + ", spawnY: " + spawnY);
			setPosition(spawnX, spawnY);
		}
		
		// Regeneration
		healthCounter++;
		if(healthCounter > healthRegen)
		{
			healthCounter = 0;
			if(health < maxHealth) health++;
		}

		manaCounter++;
		if(manaCounter > manaRegen)
		{
			manaCounter = 0;
			if(mana < maxMana) mana++;
		}
		
		staminaCounter++;
		if(staminaCounter > staminaRegen)
		{
			staminaCounter = 0;
			if(stamina < maxStamina) stamina++;
		}
		
		
		// Update fireballs
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).update();
			if(projectiles.get(i).shouldRemove())
			{
				projectiles.remove(i);
				i--;
			}
		}
		
		// Set animations
		
		
		//********************************************************************************
		//*Sexy time 1!                                                                  *
		//********************************************************************************	
		if(sexytime1)
		{
			if(currentAction != animationState[14])
			{
				dx = 0;
				gliding = false;
				left = false;
				right = false;
				inControl = false;
				currentAction = animationState[14];
				animation.setFrames(sprites.get(animationState[14]));
				animation.setDelay(3000);
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
				dx = 0;
				currentAction = animationState[15];
				animation.setFrames(sprites.get(animationState[15]));
				animation.setDelay(3000);
			}
			if(sexytime2 && animation.hasPlayedOnce())
			{
				sexytime2 = false;
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
				System.out.println("punching");
				currentAction = animationState[4];
				animation.setFrames(sprites.get(animationState[4]));
				animation.setDelay(125);
				
				if(dy == 0) dx = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
				System.out.println("done punching?");
				stamina -= punchCost;
				startPunch = false;
				endPunch = true;
				
				currentAction = animationState[5];
				animation.setFrames(sprites.get(animationState[5]));
				animation.setDelay(120);
			}
		}
		
		else if(currentAction == animationState[5] && endPunch == true)
		{
			if(animation.hasPlayedOnce())
			{
				endPunch = false;
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
				animation.setDelay(100);
				if(dy == 0) dx = 0;
				
				saveFallSpeed = fallSpeed;
				fallSpeed = 0;
				dy = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
				stamina -= dashCost;
				
				if(facingRight) dx = dashSpeed;
				else dx -= dashSpeed;
				
				startDash = false;
				endDash = true;
				
				currentAction = animationState[7];
				animation.setFrames(sprites.get(animationState[7]));
				animation.setDelay(100);
			}
		}
		else if(currentAction == animationState[7] && endDash)
		{
			if(animation.hasPlayedOnce())
			{
				dx = 0;
				fallSpeed = saveFallSpeed;
				
				endDash = false;
				inControl = true;
				// Deal damage?
			}
		}
		
		//********************************************************************************
		//*Small fireball                                                                *
		//********************************************************************************	
		else if(castingSmallFireball)
		{
			
			if(currentAction != animationState[8])
			{
				currentAction = animationState[8];
				animation.setFrames(sprites.get(animationState[8]));
				animation.setDelay(100);
				if(dy == 0) dx = 0;
				
				try
				{
					Audio sound = new Audio();
					sound.playSound("Resources/Sound/LaunchFireball.wav");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= smallFireballManaCost;
				castingSmallFireball = false;
				doneCastingSmallFireball = true;
				
				SmallFireball fireball = new SmallFireball(tileMap, facingRight, up, down, true);
				fireball.setPosition(x, y);
				projectiles.add(fireball);
				
				currentAction = animationState[9];
				animation.setFrames(sprites.get(animationState[9]));
				animation.setDelay(100);
				
				
				// Create new fireball stuff here
			}
		}
		
		else if(currentAction == animationState[9] && doneCastingSmallFireball)
		{
			if (animation.hasPlayedOnce()) 
			{
				doneCastingSmallFireball = false;
				inControl = true;
			}
		}
		
		//********************************************************************************
		//*Large fireball                                                                *
		//********************************************************************************	
		else if(castingLargeFireball)
		{
			if(currentAction != animationState[10])
			{
				currentAction = animationState[10];
				animation.setFrames(sprites.get(animationState[10]));
				animation.setDelay(100);
				if(dy == 0) dx = 0;
			}
			if(animation.hasPlayedOnce())
			{
				mana -= largeFireballManaCost;
				castingLargeFireball = false;
				doneCastingLargeFireball = true;
				
				LargeFireball fireball = new LargeFireball(tileMap, facingRight, up, down, true);
				fireball.setPosition(x, y);
				projectiles.add(fireball);
				
				currentAction = animationState[11];
				animation.setFrames(sprites.get(animationState[11]));
				animation.setDelay(100);
				
				
				// Create new fireball stuff here
			}
		}
		
		else if(currentAction == animationState[11] && doneCastingLargeFireball)
		{
			if (animation.hasPlayedOnce()) 
			{
				doneCastingLargeFireball = false;
				inControl = true;
			}
		}	
		
		//********************************************************************************
		//*Gliding                                                                       *
		//********************************************************************************	
		
		else if(dy > 0)
		{
			if(gliding)
			{
				if(currentAction != animationState[13])
				{
					currentAction = animationState[13];
					animation.setFrames(sprites.get(animationState[13]));
					animation.setDelay(100);
				}
			}
			else if(currentAction != animationState[2])
			{
				currentAction = animationState[2];
				animation.setFrames(sprites.get(animationState[2]));
				animation.setDelay(100);
			}
		}
				
		
		
		else if(dy < 0)
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
					animation.setDelay(100);
				}
			}
			//********************************************************************************
			//*Jumping                                                                       *
			//********************************************************************************	
			else if(currentAction != animationState[3])
			{
				currentAction = animationState[3];
				animation.setFrames(sprites.get(animationState[3]));
				animation.setDelay(80);
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
				animation.setDelay(40);
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
				animation.setDelay(400);
			}
		}
		
		animation.update();
		
		// Set direction
		if(inControl)
		{
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
	}
	
	
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		// Draw fireballs
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
		}
		
		// Draw player
		
		if(flinching)
		{
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) return;
					
		}
		
		if(facingRight)
		{
			graphics.drawImage(
					animation.getImage(),
					(int)(x + xmap - width / 2 + width),
					(int)(y + ymap - height / 2),
					-width,
					height,
					null
					);
		}
		else
		{
			graphics.drawImage(
					animation.getImage(),
					(int)(x + xmap - width / 2),
					(int)(y + ymap - height / 2),
					null
					);
		}
	}
	
	
	
	
	
	
}
