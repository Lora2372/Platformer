package Entity;

import java.util.ArrayList;




import Audio.JukeBox;

import javax.imageio.ImageIO;

import Entity.Doodad.SummoningEffect;
import GameState.Level1State;
import TileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends MapObject
{
	protected Level1State level1state;
	
	// Character stuff
	protected String name;
	
	protected double spawnX;
	protected double spawnY;
	
	protected int health;
	protected int maxHealth;
	protected int healthCounter;
	protected int healthRegen;
	
	protected int sightRange;
	
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
	
	protected double saveFallSpeed;
	
	// Small fireball
	protected boolean castingSmallFireball;
	protected boolean doneCastingSmallFireball;
	protected boolean holdingSmallFireball;
	
	protected int smallFireballManaCost;
	protected int smallFireballDamage;
	
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
	
	protected boolean initializeSpawning;
	protected boolean spawning;
	protected SummoningEffect summoningEffect;
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
			int sightRange,
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
			int largeFireballManaCost,
			int largeFireballDamage,					
			String spritePath,
			int[] animationState,
			int[] numFrames,
			int damageOnTouch,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			String name,
			double spawnX,
			double spawnY,
			Level1State level1state
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
		this.sightRange = sightRange;
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
		this.smallFireballManaCost = smallFireballManaCost;
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
		this.level1state = level1state;
		
		healthCounter = 0;
		manaCounter = 0;
		staminaCounter = 0;
		
		setPosition(spawnX, spawnY);
		
		
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
	public int getMaxHealth() { return maxHealth; }
	public int getMana() { return mana; }
	public int getMaxMana() { return maxMana; }
	public int getStamina() { return stamina; }
	public int getMaxStamina() { return maxStamina; }
	
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
		
		
		// Jumping
		if(jumping && !falling && inControl)
		{
			// I'll leave the jump sound commented out until we find a better one.
//			JukeBox.play("jump");
			directionY = jumpStart;
			falling = true;
		}
		
		// Falling
//		System.out.println("character name: " + getName() + ", falling: " + falling);
		if(falling || swimming)
		{
			if(directionY > 0 && gliding) directionY += fallSpeed * 0.1;
			else directionY += fallSpeed;
		
			if(directionY > 0) jumping = false;
			if(directionY < 0 && !jumping) directionY += stopJumpSpeed;
			
			if(directionY > maxFallSpeed) directionY = maxFallSpeed;
		}
		
		
		
	}
	
	
	public void updateAI(ArrayList<Character> characterList){}
	
	public int getPunchRange() { return punchRange; }
	
	public boolean getFacingRight() { return facingRight; }
	
	public boolean getFriendly() { return friendly; }

	public boolean isDead() { return dead; }
	
	public boolean getSpawning() { return spawning; };
	
	public void setSpawning(boolean b) { initializeSpawning = b; }
	
	public void hit(int damage)
	{
		System.out.println("Damage: " + damage);
		if(dead || flinching) return;
		health -= damage;
		System.out.println(name + "'s health: " + health);
		if( health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		inControl = false;
		flinchTimer = System.nanoTime();
	}
	
	public void update(ArrayList<Character> characterList)
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
		if(healthCounter > healthRegen && healthCounter != -1)
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
				directionX = 0;
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
		//*Flinching	                                                                 *
		//********************************************************************************
		else if(flinching)
		{
			if(currentAction != animationState[12])
			{
				
				
				// Consider to keep or remove the loss of control of your character, should possibly
				// restrict that to different sorts of crowd control rather than all types of damage.
				
				gliding = false;
				left = false;
				right = false;
				
				currentAction = animationState[12];
				animation.setFrames(sprites.get(animationState[12]));
				animation.setDelay(300);
			}
			if(animation.hasPlayedOnce())
			{
				flinching = false;
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
				animation.setDelay(125);
				
				if(directionY == 0) directionX = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
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
				if(directionY == 0) directionX = 0;
				
				saveFallSpeed = fallSpeed;
				fallSpeed = 0;
				directionY = 0;
			}
			
			if(animation.hasPlayedOnce())
			{
				stamina -= dashCost;
				
				if(facingRight) directionX = dashSpeed;
				else directionX -= dashSpeed;
				
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
				directionX = 0;
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
				if(directionY == 0) directionX = 0;
				JukeBox.play("FireballSmallLaunch");				
			}
			if(animation.hasPlayedOnce())
			{

				mana -= smallFireballManaCost;
				castingSmallFireball = false;
				doneCastingSmallFireball = true;
				
				FireballSmall fireball = new FireballSmall(tileMap, facingRight, up, down, friendly, smallFireballDamage);
				fireball.setPosition(locationX, locationY - 20);
				level1state.addProjectile(fireball);
				
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
				if(directionY == 0) directionX = 0;
				JukeBox.play("FireballLargeLaunch");
			}
			if(animation.hasPlayedOnce())
			{
				mana -= largeFireballManaCost;
				castingLargeFireball = false;
				doneCastingLargeFireball = true;
				
				FireballLarge fireball = new FireballLarge(tileMap, facingRight, up, down, friendly, largeFireballDamage);
				fireball.setPosition(locationX, locationY);
				level1state.addProjectile(fireball);
				
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
		
		else if(directionY > 0)
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
	
	public ArrayList<Character> detectEnemy(ArrayList<Character> characterList)
	{
		ArrayList<Character> enemiesDetected = new ArrayList<Character>();
		
		for(int i = 0; i < characterList.size(); i++)
		{
			Character character = characterList.get(i);
			
			if(character.getFriendly() != friendly)
			{
				if(facingRight)
				{
					if
						(		
							character.getx() > locationX &&
							character.getx() < locationX + sightRange &&
							character.gety() > locationY - height&&
							character.gety() < locationY + height / 2
						)
					{
						enemiesDetected.add(character);
					}
				}
				else
				{
					if
						(
								character.getx() < locationX &&
								character.getx() > locationX - sightRange &&
								character.gety() > locationY - height &&
								character.gety() < locationY + height / 2
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
					System.out.println("Turning!");
					System.out.println(projectile.getFriendly() + "\n-----------------");
					projectile.setFacing(!projectile.getFacing());
					projectile.setDirection(projectile.getDirectionX() * - 1, 0);
					projectile.setFriendly(!projectile.getFriendly());
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
					System.out.println("Turning!");
					System.out.println(projectile.getFriendly() + "\n-----------------");
					projectile.setFacing(!projectile.getFacing());
					projectile.setDirection(projectile.getDirectionX() * - 1, 0);
					projectile.setFriendly(!projectile.getFriendly());
				}
			}
		}
	}
	
	
	public void checkAttack(ArrayList<Character> characterList)
	{
		
		for(int i = 0; i < characterList.size(); i++)
		{			
			
			Character character = characterList.get(i);
			if(character.getFriendly() != friendly)
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
							character.hit(dashDamage);
						}
					}
				}
			}
			

			
		} // End for loop		
		
		
		
//		for(int i = 0; i < projectiles.size(); i++)
//		{
//			System.out.println("Projectile: " + projectiles.get(i) + " collisionWidth: " + projectiles.get(i).collisionWidth);
//			for(int j = 0; j < characterList.size(); j++)
//			{
//				if()
//				if(projectiles.get(i).intersects(characterList.get(j)))
//				{
//					projectiles.get(i).setHit(characterList);
//				}
//			}
//
//		}
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
	}
}
