package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
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
import GameState.Conversation.ConversationState;
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
	
	protected boolean holdingInteractButton;
	
	protected boolean displayHealthBars;
	protected boolean displayNamePlates;
	
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
		gameStateManager.setState(0, false);
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
	
	public ArrayList<Doodad> getDoodadList()
	{
		return stuff;
	}
	
	public void update()
	{
		if(!doneInitializing) return;
		// Update Characters
		
		displayHealthBars = player.getDisplayHealthBars();
		displayNamePlates = player.getDisplayNamePlates();
		
		if(mousePressed)
		{
			player.setFacingRight(player.getLocationX() + tileMap.getX() < mouseLocationX);
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
	
	public void reset()
	{
		projectiles = new ArrayList<Projectile>();
		explosions = new ArrayList<Explosion>();
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
					Unit unit = characterList.get(i);
					unit.draw(graphics);
					if(player.getDisplayHealthBars())
					{
						unit.drawHealthBars(graphics);
					}
					
					if(displayHealthBars)
					{
						unit.drawHealthBars(graphics);
					}
					if(displayNamePlates)
					{
						unit.drawNamePlate(graphics);
					}
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
		if(max < min)
		{
			return -1;
		}
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public Unit spawnUnit(UnitData unitData)
	{
		if(unitData.getUnitType().equals("Slug"))
		{
			return spawnSlug(unitData.getFacingRight(), unitData.getFriendly(), unitData.getUntouchable(), unitData.getInvulnerable(), unitData.getUnkillable(), unitData.getName(), unitData.getSpawnLocationX(), unitData.getSpawnLocationY(), unitData.getCurrentMap());
		}
		
		return null;
	}
	
	public Slug spawnSlug
		(			
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY,
			String currentMap	
		)
	{	
		String[] slugNames = new String[]
		{
				"Cookie",
				"Steven",
				"Morgan",
				"Tom",
				"Carl",
				"John"			
		};
		
		if(name == null)
		{
			name = slugNames[RNG(0, slugNames.length - 1)];
		}
		
		Slug slug = new Slug(tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, this, currentMap);
		slug.setCurrentMap(currentMap);
		characterList.add(slug);
		return slug;
	}
	
	public Slug spawnSlug(double spawnLocationX, double spawnLocationY, boolean facingRight, String name, String currentMap)
	{
		return spawnSlug(facingRight, false, false, false, false, name, spawnLocationX, spawnLocationY, currentMap);
	}
	
	
	public Succubus spawnSuccubus
		(			
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY,
			String currentMap
		)
	{
		
		String[] succubiNames = new String[]
		{
			"Rui",
			"Domwena",
			"Elerlith",
			"Synys",
			"Kallith",
			"Fierneth",
			"Catvina",
			"Bronlissa",
			"Cariel",
			"Darxia",
			"Nimnys"
		};
		
		if(name == null)
		{
			name = succubiNames[RNG(0, succubiNames.length - 1)];
		}
				
		Succubus succubus = new Succubus(tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, this, currentMap);
		System.out.println(currentMap);
		characterList.add(succubus);
		return succubus;
	}
	
	public Succubus spawnSuccubus(double spawnLocationX, double spawnLocationY, boolean facingRight, String currentMap)
	{
		return spawnSuccubus(facingRight, false, false, false, false, null, spawnLocationX, spawnLocationY, currentMap);
	}
	
	public Wolf spawnWolf
		(			
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY,
			String currentMap
		)
	{
		
		String[] wolfNames = new String[]
		{
			"Rhuudym",
			"Pryyfenn",
			"Phathun",
			"Jhazeem",
			"Maahzon",
			"Gzaalum",
			"Drootom"
		};
		
		if(name == null)
		{
			name = wolfNames[RNG(0, wolfNames.length - 1)];
		}
		
		Wolf wolf = new Wolf(tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, this, currentMap);
		return wolf;
	}
	
	public Wolf spawnWolf(double spawnLocationX, double spawnLocationY, boolean facingRight, String currentMap)
	{
		return spawnWolf(facingRight, false, false, false, false, null, spawnLocationX, spawnLocationY, currentMap);
	}
	
	public Skeleton spawnSkeleton(double locationX, double locationY, boolean facingRight, String currentMap)
	{
		Skeleton skeleton = new Skeleton(tileMap, facingRight, false, false, false, false, "Jesse Cox", locationX, locationY, this, currentMap);
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
					potion = new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.Healing.toString());
				}
				else if(potionDropped == 2)
				{
					potion= new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.Mana.toString());
				}
				else if(potionDropped == 3)
				{
					potion = new Potion(tileMap, false, 0, 0, owner, stacks, CreateItem.Potions.Stamina.toString());
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

	public void spawnPlayer(double locationX, double y)
	{
		// find me
	}
	
	
	public void spawnEnemies()
	{
	}
	

	
	public void interact()
	{
		if(holdingInteractButton)
		{
			return;
		}
		holdingInteractButton = true;
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
	
	public GameStateManager getGameStateManager()
	{
		return gameStateManager;
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
		Sign activatableSign = new Sign(tileMap, locationX, locationY, player, conversation, whoTalks);
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
	
	public Lever spawnLever(double locationX, double locationY, int currentAction)
	{
		Lever lever = new Lever(tileMap, gameStateManager, locationX, locationY, currentAction);
		activatables.add(lever);
		stuff.add(lever);
		return lever;
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
		StatueSave statueSave = new StatueSave(tileMap, locationX, locationY + 10, gameStateManager, this);
		activatables.add(statueSave);
		stuff.add(statueSave);
	}
	
	public void GPS()
	{
		System.out.println("player X: " + player.getLocationX() + ", playerY: " + player.getLocationY());
	}
	
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ESCAPE) 
		{
			gameStateManager.paused = !gameStateManager.getPaused();
			gameStateManager.setBrowsingInventory(false);
		}
		
		if(key == KeyEvent.VK_B)
		{
			if(player.getInConversation())
			{
				return;
			}
			gameStateManager.setBrowsingInventory(!gameStateManager.getBrowsingInventory());
			gameStateManager.pause(gameStateManager.getBrowsingInventory());
		}
		
		if(gameStateManager.paused) return;
		
		if(key == KeyEvent.VK_LEFT) player.setLeft(true);
		if(key == KeyEvent.VK_RIGHT) player.setRight(true);
		if(key == KeyEvent.VK_DOWN) player.setDown(true);
		if(key == KeyEvent.VK_UP) player.setUp(true);
		if(key == KeyEvent.VK_SPACE) player.setJumping(true);
		if(key == KeyEvent.VK_E) interact();
		if(key == KeyEvent.VK_R)player.setGliding(true); 
		
		if(key == KeyEvent.VK_A) player.setCastingSmallFireBall();
		if(key == KeyEvent.VK_S) player.setCastingLargeFireBall();
		if(key == KeyEvent.VK_F) player.setPunching();
		if(key == KeyEvent.VK_D) player.setDashing(true);
		if(key == KeyEvent.VK_G) player.setCastingMagicShield();
		if(key == KeyEvent.VK_Z) player.drinkPotion(CreateItem.Potions.Healing.toString());
		if(key == KeyEvent.VK_X) player.drinkPotion(CreateItem.Potions.Mana.toString());
		if(key == KeyEvent.VK_C) player.drinkPotion(CreateItem.Potions.Stamina.toString());
		
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_M)
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
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_N)
		{
			player.setPosition(player.getLocationX(), player.getLocationY() - 200);
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_L)
		{
			Key myKey = new Key(tileMap, false, 0, 0, player, 1, "Boss");
			player.getInventory().addItem(myKey);
			items.add(myKey);
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_K)
		{
			Key myKey = new Key(tileMap, false, 0, 0, player, 1, "Uncommon");
			player.getInventory().addItem(myKey);
			items.add(myKey);
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_9)
		{
			player.restoreHealth(100);
			player.restoreMana(100);
			player.restoreStamina(100);
		}
		if(key == KeyEvent.VK_G) GPS(); 
	}
	
	
	public void keyReleased(int key)
	{
		if(key == KeyEvent.VK_LEFT) player.setLeft(false);
		if(key == KeyEvent.VK_RIGHT) player.setRight(false);
		if(key == KeyEvent.VK_DOWN) player.setDown(false);
		if(key == KeyEvent.VK_SPACE) player.setJumping(false);
		if(key == KeyEvent.VK_UP) player.setUp(false);
		if(key == KeyEvent.VK_R) player.setGliding(false);
		if(key == KeyEvent.VK_E) holdingInteractButton = false;
		
		if(key == KeyEvent.VK_A) player.setCastingSmallFireBall();
		if(key == KeyEvent.VK_S) player.setCastingLargeFireBall();
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

	public void mouseMoved(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
		
		player.setMouseLocationX(mouseLocationX);
		player.setMouseLocationY(mouseLocationY);
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
		
		player.setMouseLocationX(mouseLocationX);
		player.setMouseLocationY(mouseLocationY);
	}
}
