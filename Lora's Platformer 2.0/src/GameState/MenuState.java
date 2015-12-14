package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import Audio.JukeBox;
import Entity.Doodad.Activatable.DoodadData;
import Entity.Item.ItemData;
import Entity.Player.*;
import Entity.Projectile.ProjectileData;
import Entity.Unit.CreateUnit;
import GameState.Conversation.ConversationState;
import Main.DrawingConstants;
import Main.GamePanel;
import Main.JSONReader;
import TileMap.Background;
import TileMap.TileMap;

public class MenuState extends GameState
{
	private Background backGround;
	
	private int currentChoice = 0;
	
	private String[] options =
	{
			"Tutorial",
			"New Game",
			"Load Game",
			"Options",
			"Quit"
	};
	
	private int[] optionsWidth = new int[options.length]; 
	
	private String title = "Lora's Platformer";
	
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	protected Player player;
	protected TileMap tileMap;
	
	protected Rectangle mouseRectangle;
	protected Rectangle[] textRectangles = new Rectangle[options.length];
	
	double mouseLocationX;
	double mouseLocationY;
	
	public MenuState(GameStateManager gameStateManager, ConversationState conversationState)
	{
		this.gameStateManager = gameStateManager;
		tileMap = new TileMap(60);
		
		// Initializing public static classes:
		@SuppressWarnings("unused")
		ItemData createItem = new ItemData(tileMap);
		@SuppressWarnings("unused")
		DoodadData doodadData = new DoodadData();
		@SuppressWarnings("unused")
		ProjectileData projectileData = new ProjectileData();
		@SuppressWarnings("unused")
		CreateUnit createUnit = new CreateUnit();
		@SuppressWarnings("unused")
		CreateBuff createBuff = new CreateBuff();
		@SuppressWarnings("unused")
		DrawingConstants drawingConstants = new DrawingConstants();
		
		player = new Player("Lora", tileMap, conversationState);
		
		gameStateManager.setPlayer(player);
		gameStateManager.setTileMap(tileMap);
		try
		{
			backGround = new Background(getClass().getResource("/Art/Backgrounds/MenuBackground.gif"), 1);
			backGround.setVector(0.3, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font
				(
					"Century Gophic",
					Font.PLAIN,
					50
				);
			
			font = new Font("Arial", Font.PLAIN, 28);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}



	public void initialize()
	{
		
	}
	
	public void update()
	{		
		backGround.update();
	}
	
	public void saveToRAM()
	{

	}
	
	public void updateChoice(int choice)
	{
		if(currentChoice != choice)
		{
			currentChoice = choice;
			JukeBox.play("DecisionChange");
		}
	}
	
	public void draw(Graphics2D graphics)
	{
		// Draw the background
		backGround.draw(graphics);
		
		// Draw title
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		
		int stringLength = DrawingConstants.getStringWidth(title, graphics);
		int textX = GamePanel.WIDTH/2 - stringLength/2;
		
		
		graphics.drawString(title, textX, GamePanel.HEIGHT / 5);
		
		String versionString = "Version: " + GamePanel.version;
		
		
		stringLength = (int) graphics.getFontMetrics().getStringBounds(versionString, graphics).getWidth();
		textX = GamePanel.WIDTH - stringLength - 20;
		int textY = GamePanel.HEIGHT - 20;
		
		graphics.drawString(versionString, textX, textY);
		
		// Draw menu options
		graphics.setFont(font);
		mouseRectangle = new Rectangle((int)mouseLocationX, (int)mouseLocationY, 1, 1);

		for(int i = 0; i < options.length; i++)
		{
			
			int textWidth = (int) graphics.getFontMetrics().getStringBounds(options[i], graphics).getWidth();
			int textHeight = (int) graphics.getFontMetrics().getStringBounds(options[i], graphics).getHeight();
			
			int textLocationX = GamePanel.WIDTH / 2 - textWidth / 2;
			int textLocationY = GamePanel.HEIGHT / 2 + i * textHeight;
			
			optionsWidth[i] = textWidth;
			

			textRectangles[i] = new Rectangle(
					textLocationX, 
					textLocationY - textHeight / 2, 
					textWidth, 
					textHeight
				);
			
			if(textRectangles[i].intersects(mouseRectangle))
			{
				updateChoice(i);
			}
			
			if( i == currentChoice)
			{
				graphics.setColor(Color.YELLOW);
			}
			else
			{
				graphics.setColor(Color.RED);
			}
			graphics.drawString(options[i], textLocationX, textLocationY);
		}
			
	}
	
	
	private void select()
	{
		JukeBox.play("DecisionMake");
		if(currentChoice == 0)
		{
			// Tutorial
			gameStateManager.setState(GameStateManager.TutorialState);

		}
		else if(currentChoice == 1)
		{
			// New Game
			for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
			{
					player.setLoading(i, false);
			}
			gameStateManager.setState(GameStateManager.LorasCavern);
		}
		else if(currentChoice == 2)
		{
			// Load Game
			if(JSONReader.load(player, tileMap))
			{
				player.setPosition(player.getSpawnLocationX(), player.getSpawnLocationY());
				player.setSpawnPoint(player.getSpawnLocationX(), player.getSpawnLocationY());
				
				String currentMap = player.getCurrentMap();
				if(currentMap.equals(GameStateManager.GameMaps.LorasCavern.toString()))
				{
					gameStateManager.setState(GameStateManager.LorasCavern);
				}
				else if(currentMap.equals(GameStateManager.GameMaps.MysteriousDungeon.toString()))
				{
					gameStateManager.setState(GameStateManager.MysteriousDungeon);
				}
				else if(currentMap.equals("DeepWoods"))
				{
					gameStateManager.setState(GameStateManager.DeepWoods);
				}
			}
			else
			{
				System.out.println("Couldn't find it.");
				gameStateManager.setState(GameStateManager.LorasCavern);
				
			}
		}
		if(currentChoice == 3)
		{
			// Options
			gameStateManager.options(true);
		}
		
		if(currentChoice == 4)
		{
			// Quit
			System.exit(0);
		}
	}
	
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		if(k == player.getKeyBind(Player.KeyBind.AimUp))
		{
			updateChoice(currentChoice -1);
			if(currentChoice == -1)
			{
				currentChoice = options.length - 1;
			}
			
		}
		
		if(k == player.getKeyBind(Player.KeyBind.AimDown))
		{
			updateChoice(currentChoice +1);
			if(currentChoice == options.length)
			{
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k)
	{
		
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
		for(int i = 0; i < options.length; i++)
		{
			if(textRectangles[i].intersects(mouseRectangle))
			{
				select();
			}
		}
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		
	}
	
	public void mouseMoved(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}
	
}
