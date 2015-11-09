package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

import Main.GamePanel;
import TileMap.*;
import Entity.Explosion.Explosion;
import Entity.Item.Coin;
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Item.Key;
import Entity.Item.Potion;
import Entity.MapObject;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.*;
import Entity.Player.*;
import Entity.Projectile.Projectile;
import Entity.Unit.*;
import Audio.JukeBox;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainMap extends GameState
{
	protected TileMap tileMap;
	protected Background background;
	protected GameOver gameoverScreen;
	protected Player player;
	protected ArrayList<Unit> characterList;
	protected ArrayList<Doodad> stuff;
	protected ArrayList<Doodad> activatables;
	protected ArrayList<Item> items;
	protected ArrayList<Projectile> projectiles;
	protected ArrayList<Explosion> explosions;
	protected boolean doneInitializing;
	
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	protected boolean mousePressed;
	
	protected ConversationState conversationState;
	
	protected long soundTimer;
	
	protected boolean gameover;
	
	public MainMap(
			GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
			
			)
	{
		this.gameStateManager = gameStatemanager;
		this.conversationState = conversationState;
		this.tileMap = tileMap;
		this.player = player;
		initialize();
	}
	
	public void initialize()
	{

		characterList = new ArrayList<Unit>();
		stuff = new ArrayList<Doodad>();
		activatables = new ArrayList<Doodad>();
		items = new ArrayList<Item>();
		
		projectiles = new ArrayList<Projectile>();
		explosions = new ArrayList<Explosion>();
		soundTimer = 0;
		
		if(tileMap == null)
		{
			System.out.println("The tileMap is null");
		}
		
		if(player == null)
		{
			System.out.println("Player created");
			player = new Player("Lora", tileMap, conversationState);
		}
		player.setMainMap(this);
		characterList.add(player);
	}
	
	public void GameOverUpdate()
	{
		
		long elapsed = (System.nanoTime() - soundTimer) / 1000000;
		if(elapsed/1000 > 9)
		{
			JukeBox.stop("GameOver");
			loadMenu();
		}
	}
	
	public void loadMenu()
	{
		gameStateManager.setState(0);
	}
	
	public void checkProjectiles()
	{
		//********************************************************************************
		//*Projectile                                                                    *
		//********************************************************************************	
		for(int j = 0; j < projectiles.size(); j++)
		{
			Projectile projectile = projectiles.get(j);
			for(int k = 0; k < projectiles.size(); k++)
			{
				if(projectiles.get(j).getFriendly() != projectiles.get(k).getFriendly())
				{
					if(projectiles.get(j).intersects(projectiles.get(k)))
					{
						projectiles.get(j).setHit();
						projectiles.get(k).setHit();
					}
				}

			}
			
			for(int i = 0; i < characterList.size(); i++)
			{
				
				Unit character = characterList.get(i);
				if(character.getFriendly() != projectiles.get(j).getFriendly() && !character.getUntouchable() && !character.getInvulnerable())
				{
					character.checkProjectile(projectile);
					
					if(projectiles.get(j).intersects(character))
					{
						projectiles.get(j).setHit();
					}	
				}
			}
			
			for(int i = 0; i < stuff.size(); i++)
			{
				Doodad thing = stuff.get(i);
				if(thing.getDoodadType().equals("MagicShield"))
				{
					if(projectiles.get(j).intersects(thing))
					{
						projectiles.get(j).bounce();
					}
				}
			}
			
		}
	}
	
	
	public void addEffect(Doodad effect)
	{
		stuff.add(effect);
	}
	
	public void addProjectile(Projectile projectile)
	{
		projectiles.add(projectile);
	}
	
	public void addExplosion(Explosion explosion)
	{
		explosions.add(explosion);
	}
	
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	public ArrayList<Explosion> getExplosions()
	{
		return explosions;
	}
	
	public ArrayList<Unit> getCharacterList()
	{
		return characterList;
	}
	
	public void update()
	{
		if(!doneInitializing) return;
		// Update Characters
		mouseLocationX = MouseInfo.getPointerInfo().getLocation().getX();
		mouseLocationY = MouseInfo.getPointerInfo().getLocation().getY();
		
		if(mousePressed)
		{
			player.setFacingRight(mouseLocationX > (GamePanel.WIDTH / 2));
			player.setRight(player.getFacingRight());
			player.setLeft(!player.getFacingRight());
		}

		
		if(gameover)
		{
			GameOverUpdate();
			return;
		}
		
		// Update Projectiles
		if(projectiles != null)
		{
			for(int i = 0; i < projectiles.size(); i++)
			{
				projectiles.get(i).update(characterList);
				if(projectiles.get(i).getRemoveMe())
				{
					projectiles.remove(i);
					i--;
				}
			}
		}
		checkProjectiles();
		
		// Update explosions
		if(explosions != null)
		{
			for(int i = 0; i < explosions.size(); i++)
			{
				explosions.get(i).update(characterList);
				if(explosions.get(i).getRemoveMe())
				{
					explosions.remove(i);
					i--;
				}
			}
		}
		
		// Update all characters
		if(characterList != null)
		{	
			for(int i = 0; i < characterList.size(); i++)
			{
				Unit character = characterList.get(i);
				
				if(character.getRemoveMe())
				{
					characterList.remove(character);
					i--;
				}
				
				else if(!character.isDead())
				{
					character.checkAttack(characterList);
					
					character.update(characterList);
					if(character != player)
					{
						character.updateAI(characterList);
					}
					
				}
				else
				{
					if(character != player)
					{
						Poff poff = new Poff(tileMap,characterList.get(i).getLocationX(), characterList.get(i).getLocationY());
						stuff.add(poff);
						
						int tempRows = character.getInventory().getRows();
						int tempColumns = character.getInventory().getColumns();
						
						Item items[][] = character.getInventory().getItems();
						
						for(int x = 0; x < tempRows; x++)
						{
							for(int y = 0; y < tempColumns; y++)
							{
								if(items[x][y] != null)
								{
									items[x][y].drop();
								}
							}
						}
						
						characterList.remove(i);
						i--;
					}
					else
					{
						// Player died...
						System.out.println("Game Over...");
						
						gameover = true;
						
						gameStateManager.stopMusic();
						
						JukeBox.play("GameOver");						
						soundTimer = System.nanoTime();
					}
				}
			}
		}
		
	// Update character
		if(characterList != null)
		{
			tileMap.setPosition(
					GamePanel.WIDTH / 2 - player.getLocationX(), 
					GamePanel.HEIGHT / 2 - player.getLocationY()
					);
		}
		
		// Update doodads
		if(stuff != null)
		{
			for(int i = 0; i < stuff.size(); i++)
			{
				if(stuff.get(i).getRemoveMe())
				{
					stuff.remove(i);
					i--;
				}
				else
				{
					stuff.get(i).update();					
				}
			}
		}
		
		if(items != null)
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getRemoveMe())
				{
					items.remove(i);
				}
				else
				{
					items.get(i).update();
				}
			}
		}
		
		// Set background
		if(background != null)
		{
			background.setPosition(tileMap.getX(), tileMap.getY());
		}
		
	}
	

	
	public void draw(Graphics2D graphics)
	{
		if(!doneInitializing) return;

		if(gameover)
		{
			gameoverScreen.draw(graphics);
			return;
		}
		
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		
		// Draw brackground
		if(background != null)
		{
			background.draw(graphics);			
		}
		
		
		
		// Draw tilemap
		if(tileMap != null)
		{
			
			tileMap.draw(graphics);
		}
		
		
		if(stuff != null)
		{
			for(int i = 0; i < stuff.size(); i++)
			{
				try
				{
					stuff.get(i).draw(graphics);					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		if(items != null)
		{
			try
			{
				for(int i = 0; i < items.size(); i++)
				{
					if(items.get(i) != null)
					{
						items.get(i).draw(graphics);
					}
					else
					{
						System.out.println("item is null");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		// Draw Characters!
		if(characterList != null)
		{
			try
			{
				for(int i = 0; i < characterList.size(); i++)
				{
					characterList.get(i).draw(graphics);
				}
			}
			catch(NullPointerException exception)
			{
				System.out.println("Null...");
				exception.printStackTrace();
			}
		}

		// Draw Projectiles
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
		}
		
		// Draw Explosions
		for(int i = 0; i < explosions.size(); i++)
		{
			explosions.get(i).draw(graphics);
		}
		
		
		// Draw HUD		
		if(player.getHUD() != null)
		{
			player.getHUD().draw(graphics);			
		}
	}
	
	public int RNG(int min, int max)
	{
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public Slug spawnSlug(double x, double y, boolean facingRight, String name)
	{
		
		
		String[] names = new String[]
				{
					"cookie",
					"steven",
					"morgan",
					"tom",
					"carl",
					"john"				
				};
		
		if(name == null)
		{
			Random randomizer = new Random();
			int random = randomizer.nextInt((5 - 0) + 1 + 0);
			name = names[random];
		}

	
		Slug slug = new Slug(tileMap, facingRight, false, false, false, false, name, x, y, this);
		characterList.add(slug);
		return slug;
	}
	
	public Succubus spawnSuccubus(double x, double y, boolean facingRight)
	{
		String[] succubiNames = new String[]
		{
					"Rui",
					"Domwena"
		};
		
		Random randomizer = new Random();
		int random2 = randomizer.nextInt((1 - 0) + 1 + 0);
		
		Succubus succubus = new Succubus(tileMap, facingRight,false, false, false, false, succubiNames[random2],x, y, this);
		characterList.add(succubus);
		
		return succubus;
	}
	
	public Wolf spawnWolf(double x, double y, boolean facingRight)
	{
		String[] wolfNames = new String[]
		{
			"Cookie"
		};
		
		Wolf wolf = new Wolf(tileMap, facingRight, false, false, false, false, wolfNames[0], x, y, this);
		characterList.add(wolf);
		return wolf;
	}
	
	public Skeleton spawnSkeleton(double locationX, double locationY, boolean facingRight)
	{
		Skeleton skeleton = new Skeleton(tileMap, facingRight, false, false, false, false, "Jesse Cox", locationX, locationY, this);
		characterList.add(skeleton);
		return skeleton;
	}
	
	public void dropPotion(String potionType, int chance, int stacks, MapObject owner)
	{
		int oneToOneHundred = RNG(0, 100);
		int potionDropped = RNG(1,3);
		
		if(oneToOneHundred <= chance)
		{
			Potion potion = null;
			if(!potionType.equals("Any"))
			{
				potion = new Potion(tileMap, false, 0, 0, owner, stacks, potionType);
			}
			else
			{
				if(potionDropped == 1)
				{
					potion = new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.PotionHealing.toString());
				}
				else if(potionDropped == 2)
				{
					potion= new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.PotionMana.toString());
				}
				else if(potionDropped == 3)
				{
					potion = new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.PotionStamina.toString());
				}
			}
			if(potion == null)
			{
				System.out.println("Why is it null?");
			}
			owner.getInventory().addItem(potion);
			items.add(potion);
		}
	}
	
	public void dropKey(String keyType, int chance, Unit owner)
	{
		int oneToOneHundred = RNG(0, 100);
		if(oneToOneHundred <= chance)
		{
			Key key = new Key(tileMap, false, 0, 0, owner, 1, keyType);

			owner.getInventory().addItem(key);
			items.add(key);
		}
	}
	
	public void dropCoin(String coinType, int chance, int stacks, MapObject owner)
	{
		int oneToOneHundred = RNG(0, 100);
		if(oneToOneHundred <= chance)
		{
			Coin coin = new Coin(tileMap, false, 0, 0, owner, stacks, coinType);

			owner.getInventory().addItem(coin);
			items.add(coin);
		}
	}

	public void spawnPlayer(double x, double y)
	{
		// find me
	}
	
	
	public void spawnEnemies()
	{
	}
	

	
	public void interact()
	{
		if(player.getInConversation())
		{
			player.getConversationState().progressConversation();
		}
		for(int i = 0; i < activatables.size(); i++)
		{
			if(player.intersects(activatables.get(i)))
			{
				activatables.get(i).interact(player);
			}
		}
		
		for(int i = 0; i < items.size(); i++)
		{
			if(player.intersects(items.get(i)))
			{
				items.get(i).interact(player);
			}
		}
		
		for(int i = 0; i < characterList.size(); i++)
		{
			if(player.intersects(characterList.get(i)) && characterList.get(i).getActivatable())
			{
				characterList.get(i).interact(player);
			}
		}
	}
	
	public void spawnWaterfall(double locationX, double locationY)
	{
		Waterfall waterfall = new Waterfall(tileMap, locationX, locationY);
		stuff.add(waterfall);
		
	}
	
	public void spawnSummonEffect(double locationX, double locationY)
	{
		SummoningEffect summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
		stuff.add(summoningEffect);
		
	}
	
	public void spawnTorch(double locationX, double locationY)
	{
		Torch torch = new Torch(tileMap, locationX, locationY);
		stuff.add(torch);
	}
	
	public CampFire spawnCampFire(double locationX, double locationY)
	{
		CampFire campFire = new CampFire(tileMap, gameStateManager, locationX, locationY);
		stuff.add(campFire);
		activatables.add(campFire);
		return campFire;
		
	}
	
	public void spawnSign(double locationX, double locationY, String[] conversation, int[] whoTalks)
	{
		ActivatableSign activatableSign = new ActivatableSign(tileMap, locationX, locationY, player, conversation, whoTalks);
		activatables.add(activatableSign);
		stuff.add(activatableSign);
	}
	
	public Door spawnDoor(double locationX, double locationY, boolean locked, int currentAction, String doorType)
	{
		Door door = new Door(tileMap, gameStateManager, locationX, locationY, locked, currentAction, doorType);
		activatables.add(door);
		stuff.add(door);
		return door;
	}
	
	public Chest spawnChest(double locationX, double locationY, boolean locked,String chestType)
	{
		Chest chest = new Chest(tileMap, locationX, locationY, locked, chestType);
		activatables.add(chest);
		stuff.add(chest);
		return chest;
	}
	
	public Key spawnKey(double locationX, double locationY, String keyType)
	{
		Key key = new Key(tileMap, true, locationX, locationY, null, 1, keyType);
		items.add(key);
		return key;
	}
	
	public Potion spawnPotion(double locationX, double locationY, String potionType)
	{
		Potion potion = new Potion(tileMap, true, locationX, locationY, null, 1, potionType);
		items.add(potion);
		return potion;
	}
	
	public Coin spawnCoin(double locationX, double locationY, String coinType)
	{
		Coin coin = new Coin(tileMap, true, locationX, locationY, null, 1, coinType);
		items.add(coin);
		return coin;
	}
	
	public void spawnStatueSave(double locationX, double locationY)
	{
		StatueSave statueSave = new StatueSave(tileMap, locationX, locationY + 10);
		activatables.add(statueSave);
		stuff.add(statueSave);
	}
	
	public void GPS()
	{
//		spawnEnemies();
		System.out.println("player X: " + player.getLocationX() + ", playerY: " + player.getLocationY());
	}
	
	
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_ESCAPE) 
		{
			gameStateManager.paused = !gameStateManager.getPaused();
			gameStateManager.browsingInventory(false);
		}
		
		if(k == KeyEvent.VK_B)
		{
			gameStateManager.browsingInventory = !gameStateManager.getBrowsingInventory();
			gameStateManager.pause(gameStateManager.getBrowsingInventory());
		}
		
		if(gameStateManager.paused) return;
		
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_E) interact();
		if(k == KeyEvent.VK_R)player.setGliding(true); 
		
		if(k == KeyEvent.VK_A) player.setCastingSmallFireBall();
		if(k == KeyEvent.VK_S) player.setCastingLargeFireBall();
		if(k == KeyEvent.VK_F) player.setPunching();
		if(k == KeyEvent.VK_D) player.setDashing(true);
		if(k == KeyEvent.VK_G) player.setCastingMagicShield();
		if(k == KeyEvent.VK_Z) player.drinkPotion(CreateItem.Potions.PotionHealing.toString());
		if(k == KeyEvent.VK_X) player.drinkPotion(CreateItem.Potions.PotionMana.toString());
		if(k == KeyEvent.VK_C) player.drinkPotion(CreateItem.Potions.PotionStamina.toString());
		
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if( k == KeyEvent.VK_M)
		{
			if(player.getFacingRight())
			{
				player.setPosition(player.getLocationX() + 200, player.getLocationY());
			}
			else
			{
				player.setPosition(player.getLocationX() - 200, player.getLocationY()); 
			}
		}
		
		if(k == KeyEvent.VK_L)
		{
			Key myKey = new Key(tileMap, false, 0, 0, player, 1, "Boss");
			player.getInventory().addItem(myKey);
			items.add(myKey);
		}

		if(k == KeyEvent.VK_P) spawnSlug(player.getLocationX(), player.getLocationY(), player.getFacingRight(), null); 
		if(k == KeyEvent.VK_O) spawnSuccubus(player.getLocationX(), player.getLocationY(), player.getFacingRight()); 
		if(k == KeyEvent.VK_I) spawnWaterfall(player.getLocationX(), player.getLocationY()); 
		if(k == KeyEvent.VK_U) spawnSummonEffect(player.getLocationX(), player.getLocationY()); 
		if(k == KeyEvent.VK_G) GPS(); 
	}
	
	
	public void keyReleased(int k)
	{
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_SPACE) player.setJumping(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_R) player.setGliding(false);
		
		if(k == KeyEvent.VK_A) player.setCastingSmallFireBall();
		if(k == KeyEvent.VK_S) player.setCastingLargeFireBall();
	}


	public void mouseClicked(MouseEvent mouse) 
	{
		
	}


	public void mouseEntered(MouseEvent mouse) 
	{
		
	}

	public void mouseExited(MouseEvent mouse) 
	{
		
	}

	public void mousePressed(MouseEvent mouse) 
	{
		mousePressed = true;
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		mousePressed = false;
		player.setRight(false);
		player.setLeft(false);
	}
}
