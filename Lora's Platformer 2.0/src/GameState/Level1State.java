package GameState;


import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Character;
import Entity.Enemies.*;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.*;
import Entity.Player.*;
import Audio.JukeBox;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;


public class Level1State extends GameState
{
	private TileMap tileMap;
	private Background background;
	private GameOver gameoverScreen;
	
	private Player player;
	private ArrayList<Character> characterList;
	private ArrayList<Character> enemies;
	private ArrayList<Doodad> stuff;
	private ArrayList<Doodad> activatables;
	private ArrayList<Projectile> projectiles;
	private HUD hud;
	private boolean doneInitializing;
	
	protected long soundTimer;
	
	protected boolean gameover;
	
	public Level1State(GameStateManager gameStatemanager)
	{
		this.gameStateManager = gameStatemanager;
		initialize();
	}
	
	public void initialize()
	{
		try
		{
			tileMap = new TileMap(60);
			
			
			//tileMap.loadTiles("/Tilesets/tileset.png");
			
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/LorasMap01012.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/Mountains5.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		characterList = new ArrayList<Character>();
		enemies = new ArrayList<Character>();
		stuff = new ArrayList<Doodad>();
		activatables = new ArrayList<Doodad>();
		
		projectiles = new ArrayList<Projectile>();
		player = new Player(tileMap,"Lora", 720, 2200, this);
		characterList.add(player);
		
		player.setSpawning(true);
		
				
		
		
		spawnTorch(800, 1600);
		spawnTorch(1850,1400);
		
		
		hud = new HUD(player);
		spawnEnemies();
		doneInitializing = true;

		
		JukeBox.loop("Battle9");
		
		soundTimer = 0;
	}
	
	public void GameOverUpdate()
	{
		
		long elapsed = (System.nanoTime() - soundTimer) / 1000000;
		if(elapsed/1000 > 9)
		{
			JukeBox.stop("GameOver");
			gameStateManager.setState(0);
		}
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
						projectiles.get(j).setHit(characterList);
						projectiles.get(k).setHit(characterList);
					}
				}

			}
			
			for(int i = 0; i < characterList.size(); i++)
			{
				Character character = characterList.get(i);
				if(character.getFriendly() != projectiles.get(j).getFriendly())
				{
					character.checkProjectile(projectile);
					
					if(projectiles.get(j).intersects(character))
					{
						projectiles.get(j).setHit(characterList);
					}	
				}
			}
			
		}
	}
	
	public void addProjectile(Projectile projectile)
	{
		projectiles.add(projectile);
	}
	
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	public ArrayList<Character> getCharacterList()
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
		
		// Update fireballs
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
				Character character = characterList.get(i);
				
				if(!character.isDead())
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
						System.out.println(character.getName() + " has died.");
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
				stuff.get(i).draw(graphics);
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
		
		// Draw fireballs
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
		}
		
		// Draw HUD
		
		if(hud != null)
		{
			hud.draw(graphics);			
		}
		
		if(ConversationBox.inConversation())
		{
			ConversationBox.draw(graphics); 
		}
		
	}
	
	public void spawnSuccubi(double x, double y, boolean facingRight)
	{
		String[] succubiNames = new String[]
				{
					"Fiona",
					"Rui",
					"Domwena"
				};
		
		Random randomizer = new Random();
		int random2 = randomizer.nextInt((2 - 0) + 1 + 0);
		
		Succubus succubus = new Succubus(tileMap, facingRight,false, false, false, succubiNames[random2],x, y, this);
		characterList.add(succubus);
		enemies.add(succubus);
		
	}
	

	public void spawnPlayer(double x, double y)
	{
		// find me
	}
	
	
	public void spawnEnemies()
	{
		spawnSlug(1690, 1600, false);
		spawnSuccubi(2700, 1400, false);
		spawnSuccubi(1339,1900, true);
		spawnChestCommon(989, 2250);
		spawnChestUncommon(989 + 120, 2250);
		spawnChestRare(989 + 240, 2250);
		
		spawnChestRare(1923, 1170);
		
		spawnChestRare(2844, 1650);
		
		spawnChestUncommon(1712, 2610);
		
		spawnSign(1357, 2250);
	}
	
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_E) interact();
		
		if(k == KeyEvent.VK_A) player.setCastingSmallFireball();
		if(k == KeyEvent.VK_S) player.setCastingLargeFireball();
		if(k == KeyEvent.VK_F) player.setPunching();
		if(k == KeyEvent.VK_D) player.setDashing(true);
		if(k == KeyEvent.VK_V) player.setSexytime1();
		if(k == KeyEvent.VK_B) player.setSexytime2();
		
		if(k == KeyEvent.VK_P) spawnSlug(player.getx(), player.gety(), player.getFacingRight()); 
		if(k == KeyEvent.VK_O) spawnSuccubi(player.getx(), player.gety(), player.getFacingRight()); 
		if(k == KeyEvent.VK_I) spawnWaterfall(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_U) spawnSummonEffect(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_G) GPS(); 
		if(k == KeyEvent.VK_C) spawnChestCommon(player.getx(), player.gety());
		if(k == KeyEvent.VK_V)spawnSign(player.getx(), player.gety()); 
	}
	
	public void interact()
	{
		if(player.getFalling() || player.getJumping())
		{
			player.setGliding(true);
		}
		else
		{
			for(int i = 0; i < activatables.size(); i++)
			{
				if(player.intersects(activatables.get(i)))
				{
					activatables.get(i).interact();
				}
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
	
	public void spawnSign(double x, double y)
	{
		ActivatableSign activatableSign = new ActivatableSign(tileMap, x, y, player);
		activatables.add(activatableSign);
		stuff.add(activatableSign);
	}
	
	public void spawnChestCommon(double x, double y)
	{
		ActivatableChestCommon activatableChestCommon = new ActivatableChestCommon(tileMap, x, y);
		activatables.add(activatableChestCommon);
		stuff.add(activatableChestCommon);
	}
	
	public void spawnChestUncommon(double x, double y)
	{
		ActivatableChestUncommon activatableChestUncommon = new ActivatableChestUncommon(tileMap, x, y);
		activatables.add(activatableChestUncommon);
		stuff.add(activatableChestUncommon);
	}
	
	public void spawnChestRare(double x, double y)
	{
		ActivatableChestRare activatableChesetRare = new ActivatableChestRare(tileMap, x, y);
		activatables.add(activatableChesetRare);
		stuff.add(activatableChesetRare);
	}
	
	public void spawnSlug(double x, double y, boolean facingRight)
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
		Random randomizer = new Random();
		int random = randomizer.nextInt((5 - 0) + 1 + 0);
	
		Slug slug = new Slug(tileMap, facingRight, false, false, false, names[random], x, y, this);
		characterList.add(slug);
		enemies.add(slug);
	}
	
	public void GPS()
	{
//		spawnEnemies();
		System.out.println("player X: " + player.getx() + ", playerY: " + player.gety());
	}
	
	public void keyReleased(int k)
	{
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_SPACE) player.setJumping(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
		
		if(k == KeyEvent.VK_A) player.setCastingSmallFireball();
		if(k == KeyEvent.VK_S) player.setCastingLargeFireball();
		if(k == KeyEvent.VK_Z) player.setPunching();
	}
	
}
