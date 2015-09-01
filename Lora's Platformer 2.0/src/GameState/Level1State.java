package GameState;


import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Character;
import Entity.Enemies.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Level1State extends GameState
{
	private TileMap tileMap;
	private Background background;
	
	private Player player;
	private ArrayList<Character> characterList;
	private ArrayList<Character> enemies;
	private HUD hud;
	
	private boolean doneInitializing;
	

	
	public Level1State(GameStateManager gameStatemanager)
	{
		this.gameStateManager = gameStatemanager;
	}
	
	public void initialize()
	{
		try
		{
			tileMap = new TileMap(60);
			
			
			//tileMap.loadTiles("/Tilesets/tileset.png");
			
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/LorasMap01001.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/grassbg1.gif"), 0.1);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		characterList = new ArrayList<Character>();
		enemies = new ArrayList<Character>();
		
		player = new Player(tileMap,"Lora", 125, 100);
		player.setPosition(125, 100);
		characterList.add(player);
		
		hud = new HUD(player);
		
		doneInitializing = true;
		
		
	}
	
	public void update()
	{
		if(!doneInitializing) return;
		// Update Characters
		if(characterList != null)
		{	
			for(int i = 0; i < characterList.size(); i++)
			{
				if(!characterList.get(i).isDead())
				{
					characterList.get(i).checkAttack(characterList);
					
					characterList.get(i).update(characterList);
					if(characterList.get(i) != player)
					{
						characterList.get(i).updateAI();
					}
				}
				else
				{
					if(characterList.get(i) != player)
					{
						System.out.println(characterList.get(i).getName() + " has died.");
						characterList.remove(i);
						i--;
					}
					else
					{
						// Player died...
					}
				}

//				System.out.println("isdead? " + characterList.get(i).isDead() + ", name: " + characterList.get(i).getName());
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
	
	public void spawnEnemies()
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
		
		Slug slug = new Slug(tileMap, true, false, names[random], player.getx(), player.gety());
		characterList.add(slug);
		enemies.add(slug);
		slug.setPosition(player.getx(), player.gety());
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
		
		if(k == KeyEvent.VK_P) GPS(); 
	}
	public void GPS()
	{
		spawnEnemies();
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
