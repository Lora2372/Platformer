package GameState;


import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Character;
import Entity.Enemies.*;
import Entity.Doodad.*;
import Audio.JukeBox;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import Sound.SoundPlayer;

public class Level1State extends GameState
{
	private TileMap tileMap;
	private Background background;
	
	private Player player;
	private ArrayList<Character> characterList;
	private ArrayList<Character> enemies;
	private ArrayList<Doodad> stuff;
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
			tileMap.loadMap("/Maps/LorasMap01008.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/Mountains5.png"), 0.1);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		characterList = new ArrayList<Character>();
		enemies = new ArrayList<Character>();
		stuff = new ArrayList<Doodad>();
		player = new Player(tileMap,"Lora", 720, 2200);
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
		System.out.println("Elapsed: " + elapsed);
		if(elapsed/1000 > 9)
		{
			System.out.println("Game Over.");
			JukeBox.stop("GameOver");
			gameStateManager.setState(0);
		}
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
						character.updateAI();
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
		
		// Draw HUD
		
		if(hud != null)
		{
			hud.draw(graphics);			
		}
		
	}
	
	public void spawnSuccubi(double x, double y)
	{
		String[] succubiNames = new String[]
				{
					"Fiona",
					"Rui",
					"Domwena"
				};
		
		Random randomizer = new Random();
	int random2 = randomizer.nextInt((2 - 0) + 1 + 0);
		
		Succubus succubus = new Succubus(tileMap,false,false, false, false, succubiNames[random2],x, y);
		characterList.add(succubus);
		enemies.add(succubus);
		
	}
	

	public void spawnPlayer(double x, double y)
	{
		// find me
	}
	
	
	public void spawnEnemies()
	{
		spawnSlug(1760, 1652);
		spawnSuccubi(2700, 1400);
	}
	
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		
		if(k == KeyEvent.VK_A) player.setCastingSmallFireball();
		if(k == KeyEvent.VK_S) player.setCastingLargeFireball();
		if(k == KeyEvent.VK_F) player.setPunching();
		if(k == KeyEvent.VK_D) player.setDashing(true);
		if(k == KeyEvent.VK_V) player.setSexytime1();
		if(k == KeyEvent.VK_B) player.setSexytime2();
		
		if(k == KeyEvent.VK_P) spawnSlug(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_O) spawnSuccubi(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_I) spawnWaterfall(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_U) spawnSummonEffect(player.getx(), player.gety()); 
		if(k == KeyEvent.VK_G) GPS(); 
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
	
	public void spawnSlug(double x, double y)
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
	
		Slug slug = new Slug(tileMap, true, false, false, false, names[random], x, y);
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
