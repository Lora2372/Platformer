package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Entity.Explosion.Explosion;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.*;
import Entity.Player.*;
import Entity.Projectile.Projectile;
import Audio.JukeBox;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainMap extends GameState
{
	protected TileMap tileMap;
	protected Background background;
	protected GameOver gameoverScreen;
	
	protected Player player;
	protected ArrayList<Unit> characterList;
	protected ArrayList<Unit> enemies;
	protected ArrayList<Unit> allies;
	protected ArrayList<Doodad> stuff;
	protected ArrayList<Doodad> activatables;
	protected ArrayList<Projectile> projectiles;
	protected ArrayList<Explosion> explosions;
	protected boolean doneInitializing;
	
	protected long soundTimer;
	
	protected boolean gameover;
	
	public MainMap(
			GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player
			
			)
	{
		this.gameStateManager = gameStatemanager;
		this.tileMap = tileMap;
		this.player = player;
		initialize();
	}
	
	public void initialize()
	{

		characterList = new ArrayList<Unit>();
		enemies = new ArrayList<Unit>();
		stuff = new ArrayList<Doodad>();
		activatables = new ArrayList<Doodad>();
		allies = new ArrayList<Unit>();
		
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
			player = new Player("Lora2", tileMap);
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
	
	public void addEnemy(Unit enemy)
	{
		enemies.add(enemy);
		characterList.add(enemy);
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
				if(projectiles.get(i).shouldRemove())
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
				if(explosions.get(i).shouldRemove())
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
					if(character.getFriendly()) 	allies.remove(character);
					if(!character.getFriendly()) 	enemies.remove(character);	
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
						if(!characterList.get(i).getFriendly())
						{
							for(int z = 0; z < enemies.size(); z++)
							{
								if(enemies.get(z) == characterList.get(i))
								{
									enemies.remove(z);
									z--;
								}
							}
						}
						Poff poff = new Poff(tileMap,characterList.get(i).getx(), characterList.get(i).gety());
						stuff.add(poff);
						
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
					GamePanel.WIDTH / 2 - player.getx(), 
					GamePanel.HEIGHT / 2 - player.gety()
					);
		}
		
		// Update doodads
		if(stuff != null)
		{
			for(int i = 0; i < stuff.size(); i++)
			{
				if(stuff.get(i).removeMe())
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
		else
		{
			System.out.println("Background is null");
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
		
		// Draw Characters!
		if(characterList != null)
		{
			
			for(int i = 0; i < characterList.size(); i++)
			{
				characterList.get(i).draw(graphics);
			}
		}
		
		// Draw Projectiles
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
		}
		
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
		return random.nextInt((max - min) + min);
	}
	
	public void spawnSlug(double x, double y, boolean facingRight, String name)
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
		enemies.add(slug);
	}
	
	public void spawnSuccubus(double x, double y, boolean facingRight)
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
		enemies.add(succubus);
		
	}
	
	public void spawnWolf(double x, double y, boolean facingRight)
	{
		String[] wolfNames = new String[]
		{
			"Cookie"
		};
		
		Wolf wolf = new Wolf(tileMap, facingRight, false, false, false, false, wolfNames[0], x, y, this);
		characterList.add(wolf);
		enemies.add(wolf);
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
			player.getConversationBox().progressConversation();
		}
		for(int i = 0; i < activatables.size(); i++)
		{
			if(player.intersects(activatables.get(i)))
			{
				activatables.get(i).interact(player);
			}
		}
		
		for(int i = 0; i < allies.size(); i++)
		{
			if(player.intersects(allies.get(i)) && allies.get(i).getActivatable())
			{
				allies.get(i).interact(player);
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
	
	public void spawnSign(double locationX, double locationY, String[] conversation, int[] whoTalks)
	{
		ActivatableSign activatableSign = new ActivatableSign(tileMap, locationX, locationY, player, conversation, whoTalks);
		activatables.add(activatableSign);
		stuff.add(activatableSign);
	}
	
	public void spawnChest(double locationX, double locationY, int silver, int gold, String chestType)
	{
		ActivatableChest activatableChestCommon = new ActivatableChest(tileMap, locationX, locationY, silver, gold, chestType);
		activatables.add(activatableChestCommon);
		stuff.add(activatableChestCommon);
	}
	
	public void spawnStatueSave(double locationX, double locationY)
	{
		StatueSave statueSave = new StatueSave(tileMap, locationX, locationY);
		activatables.add(statueSave);
		stuff.add(statueSave);
	}
	
	public void GPS()
	{
//		spawnEnemies();
		System.out.println("player X: " + player.getx() + ", playerY: " + player.gety());
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
		
		
		if( k == KeyEvent.VK_M)player.setPosition(player.getx() + 200, player.gety()); 

		if(k == KeyEvent.VK_P) spawnSlug(player.getx(), player.gety(), player.getFacingRight(), null); 
		if(k == KeyEvent.VK_O) spawnSuccubus(player.getx(), player.gety(), player.getFacingRight()); 
		if(k == KeyEvent.VK_I) spawnWaterfall(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_U) spawnSummonEffect(player.getx(), player.gety()); 
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
		if(k == KeyEvent.VK_Z) player.setPunching();
	}
}
