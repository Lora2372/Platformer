package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Audio.JukeBox;
import Main.GamePanel;
import TileMap.Background;

public class PauseState extends GameState
{
	private Background background;
	private int currentChoice = 0;
	
	private String[] options =
		{
			"Resume",
			"Options",
			"Return to title screen",
			"Quit"
		};
	
	private int[] optionsWidth = new int[options.length]; 
	
	private String title = "Game paused";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	protected Rectangle mouseRectangle;
	protected Rectangle[] textRectangles = new Rectangle[options.length];
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	public PauseState(GameStateManager gameStateManager)
	{
		this.gameStateManager = gameStateManager;
		
		try
		{
			background = new Background(getClass().getResource("/Art/HUD/Foregrounds/ScreenPaused.png"), 0);
			background.setVector(0, 0);
			
			
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gophic",
					Font.PLAIN,
					20
					);
			
			font = new Font("Arial", Font.PLAIN, 28);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		background.update();
	}
	
	public void reset()
	{
		currentChoice = 0;
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
		
		background.draw(graphics);
		
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		int stringLength = (int)graphics.getFontMetrics().getStringBounds(title, graphics).getWidth();
		int textX = GamePanel.WIDTH/2 - stringLength / 2;
		
		graphics.drawString(title, textX, GamePanel.HEIGHT / 5);
		
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
			
			if(i == currentChoice)
			{
				graphics.setColor(Color.YELLOW);
			}
			else
			{
				graphics.setColor(Color.RED);
			}
			stringLength = (int) graphics.getFontMetrics().getStringBounds(options[i], graphics).getWidth();
			textX = GamePanel.WIDTH / 2 - stringLength / 2;
			graphics.drawString(options[i], textX, GamePanel.HEIGHT / 2 + i * 30);
		}
	}
	
	
	private void select()
	{
		JukeBox.play("DecisionMake");
		if(currentChoice == 0)
		{
			// Resume
			gameStateManager.pause(false);
		}
		if(currentChoice == 1)
		{
			gameStateManager.options(true);
		}
		if(currentChoice == 2)
		{
			currentChoice = 0;
			gameStateManager.pause(false);
			gameStateManager.options(false);
			gameStateManager.setState(0);
		}
		if(currentChoice == 3)
		{
			System.exit(0);
		}
	}

	public void initialize() 
	{
		
	}

	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_ESCAPE)
		{
			currentChoice = 0;
			select();
		}
		
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		
		if(k == KeyEvent.VK_UP)
		{
			currentChoice--;
			if(currentChoice == -1) currentChoice = options.length - 1;
		}
		if(k == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			if(currentChoice == options.length) currentChoice = 0;
		}
	}

	public void keyReleased(int k) 
	{
		
	}
	
	public void mouseClicked(MouseEvent mouse) 
	{
		for(int i = 0; i < options.length; i++)
		{
			if(textRectangles[i].intersects(mouseRectangle))
			{
				select();
			}
		}
	}


	public void mouseEntered(MouseEvent mouse) 
	{
		
	}

	public void mouseExited(MouseEvent mouse) 
	{
		
	}

	public void mousePressed(MouseEvent mouse) 
	{
		
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
