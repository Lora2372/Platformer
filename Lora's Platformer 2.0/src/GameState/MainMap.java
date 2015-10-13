package GameState;


import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.*;
import Entity.Player.*;
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
	
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
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
		
		// Update fireBalls
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
						CartoonExplosion cartoonExplosion = new CartoonExplosion(tileMap, characterList.get(i).getx(), characterList.get(i).gety());
						stuff.add(cartoonExplosion);
						
						characterList.remove(i);
						i--;
					}
					else
					{
						// Player died...
						System.out.println("Game Over...");
						
						gameover = true;

						JukeBox.stop("Battle9");
						JukeBox.stop("Dungeon1");
						JukeBox.stop("MysteriousBattle");
						JukeBox.stop("MysteriousConversation");
						
						JukeBox.play("GameOver");						
						soundTimer = System.nanoTime();
					}
				}

//				System.out.println("isdead? " + character.isDead() + ", name: " + character.getName());
			}
		}
		
	// Update character
		if(characterList != null)
		{

//			System.out.println("player x: " + player.getx() + ", player y: " + player.gety() + ", position x: " + (GamePanel.WIDTH / 2 - player.getx()) + ", position y: " + (GamePanel.HEIGHT / 2 - player.gety()));
			tileMap.setPosition(
					GamePanel.WIDTH / 2 - player.getx(), 
					GamePanel.HEIGHT / 2 - player.gety()
					);
		}	
		else if (characterList == null)
		{
			System.out.println("WHY IS THE CHARACTERLIST NULL?!");
			if(player == null)
			{
				System.out.println("AND WHY THE FUCK IS THE PLAYER NULL?!");
			}
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
		
		// Draw fireBalls
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
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
	
	public void spawnWaterfall(double x, double y)
	{
		Waterfall waterfall = new Waterfall(tileMap, x, y);
		stuff.add(waterfall);
		
	}
	
	public void spawnSummonEffect(double x, double y)
	{
		SummoningEffect summoningEffect = new SummoningEffect(tileMap, x, y);
		stuff.add(summoningEffect);
		
	}
	
	public void spawnTorch(double x, double y)
	{
		Torch torch = new Torch(tileMap, x, y);
		stuff.add(torch);
	}
	
	public void spawnSign(double x, double y, String[] conversation, int[] whoTalks)
	{
		ActivatableSign activatableSign = new ActivatableSign(tileMap, x, y, player, conversation, whoTalks);
		activatables.add(activatableSign);
		stuff.add(activatableSign);
	}
	
	public void spawnChest(double x, double y, String chestType)
	{
		ActivatableChest activatableChestCommon = new ActivatableChest(tileMap, x, y, chestType);
		activatables.add(activatableChestCommon);
		stuff.add(activatableChestCommon);
	}
	
	public void spawnStatueSave(double x, double y)
	{
		StatueSave statueSave = new StatueSave(tileMap, x, y);
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
			gameStateManager.paused = !gameStateManager.paused;			
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
		if(k == KeyEvent.VK_V) player.setSexytime1();
		if(k == KeyEvent.VK_B) player.setSexytime2();
		
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
