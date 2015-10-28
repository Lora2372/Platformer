package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import Entity.Player.*;
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
			"Help",
			"Quit"
		};
	
	private String title = "Lora's Platformer";
	
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	protected Player player;
	protected TileMap tileMap;
	
	public MenuState(GameStateManager gameStateManager)
	{
		this.gameStateManager = gameStateManager;
		tileMap = new TileMap(60);
		player = new Player("Lora", tileMap);
		
		gameStateManager.setPlayer(player);
		gameStateManager.setTileMap(tileMap);
		try
		{
			backGround = new Background(getClass().getResource("/Art/Backgrounds/MenuBackground.gif"), 1);
			backGround.setVector(0.3, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gophic",
					Font.PLAIN,
					50);
			
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
	
	public void draw(Graphics2D graphics)
	{
		// Draw the background
		backGround.draw(graphics);
		
		// Draw title
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		
		int stringLength = (int)graphics.getFontMetrics().getStringBounds(title, graphics).getWidth();
		int textX = GamePanel.WIDTH/2 - stringLength/2;
		
		
		graphics.drawString(title, textX, GamePanel.HEIGHT / 5);
		
		String versionString = "Version: " + GamePanel.version;
		
		
		stringLength = (int) graphics.getFontMetrics().getStringBounds(versionString, graphics).getWidth();
		textX = GamePanel.WIDTH - stringLength - 20;
		int textY = GamePanel.HEIGHT - 20;
		
		graphics.drawString(versionString, textX, textY);
		
		// Draw menu options
		graphics.setFont(font);
		for(int i = 0; i < options.length; i++)
		{
			if( i == currentChoice)
			{
				graphics.setColor(Color.YELLOW);
			}
			else
			{
				graphics.setColor(Color.RED);
			}
			stringLength = (int)graphics.getFontMetrics().getStringBounds(options[i], graphics).getWidth();
			textX = GamePanel.WIDTH / 2 - stringLength / 2;
			graphics.drawString(options[i], textX, GamePanel.HEIGHT / 2 + i * 30);
		}
			
	}
	
	
	private void select()
	{
		if(currentChoice == 0)
		{
			// Tutorial
			gameStateManager.setState(GameStateManager.TutorialState);

		}
		else if(currentChoice == 1)
		{
			// New Game
			gameStateManager.setState(GameStateManager.LorasCavern);
		}
		else if(currentChoice == 2)
		{
			// Load Game
			if(JSONReader.load(player))
			{
				player.setLoaded(true);
				player.setPosition(player.getSpawnX(), player.getSpawnY());
				player.setSpawnPoint(player.getSpawnX(), player.getSpawnY());
				
				String currentMap = player.getCurrentMap();
				if(currentMap.equals("LorasCavern"))
					gameStateManager.setState(GameStateManager.LorasCavern);
				else if(currentMap.equals("MysteriousDungeon"))
					gameStateManager.setState(GameStateManager.MysteriousDungeon);
				else if(currentMap.equals("DeepWoods"))
					gameStateManager.setState(GameStateManager.DeepWoods);
//				gameStateManager.setState(GameStateManager.);
			}
			else
			{
				player.setLoaded(false);
				System.out.println("Couldn't find it.");
				gameStateManager.setState(GameStateManager.LorasCavern);
				
			}
		}
		if(currentChoice == 3)
		{
			// Help
			String message = "Disclaimer, the following help section is still under construction to be vastly improved in the hopefully near future...\n"
					+ "\n"
					+ "Greetings!\n"
					+ "Don't have much information for you yet but the controls are currently:\n"
					+ "Left arrow: Move left\n"
					+ "Right arrow: Move right\n"
					+ "Up arrow: Aim up\n"
					+ "Down arrow: Aim down\n"
					+ "Space: Jump\n"
					+ "A: Small fireball\n"
					+ "S: Large fireball\n"
					+ "D: Dash attack\n"
					+ "F: Punch attack\n"
					+ "R: Gliding in the air, interacting with objects on the ground.\n"
					+ "\n"
					+ "Extra stuff:\n"
					+ "O: Summon a succubus\n"
					+ "P: Summon a slug\n"
					+ "\n"
					+ "Have fun!";
			JOptionPane.showMessageDialog(null, message);
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
		if(k == KeyEvent.VK_UP)
		{
			currentChoice--;
			if(currentChoice == -1)
			{
				currentChoice = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			if(currentChoice == options.length)
			{
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k)
	{
		
	}
	
}
