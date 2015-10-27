package GameState.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import GameState.GameState;
import GameState.GameStateManager;
import Main.Content;
import Main.GamePanel;
import TileMap.Background;

public class InventoryState extends GameState
{
	private Background background;
	

	
	private String title = "Inventory";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	protected int numberOfSlots = 9;
	
	protected int selectedSlot = 1;
	
	public InventoryState(GameStateManager gameStateManager)
	{
		this.gameStateManager = gameStateManager;
		
		try
		{
			background = new Background(getClass().getResource("/Art/HUD/Foregrounds/ScreenInventory.png"), 0);
			background.setVector(0, 0);
			
			
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gophic",
					Font.PLAIN,
					20
					);
			
			font = new Font("Arial", Font.PLAIN, 28);
			

			
			MouseListener mouseListener = new MouseListener()
			{

				public void mouseClicked(MouseEvent e) 
				{
//					if()
				}

				public void mouseEntered(MouseEvent e) 
				{
					
				}

				public void mouseExited(MouseEvent e) 
				{
					
				}

				public void mousePressed(MouseEvent e) 
				{
					
				}

				public void mouseReleased(MouseEvent e) 
				{
					
				}
				
			};
			
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
	

	
	public void draw(Graphics2D graphics)
	{
		
		background.draw(graphics);
		
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		int stringLength = (int)graphics.getFontMetrics().getStringBounds(title, graphics).getWidth();
		int textX = GamePanel.WIDTH/2 - stringLength / 2;
		
		graphics.drawString(title, textX, GamePanel.HEIGHT / 5);
		
		graphics.setFont(font);
		
		int tempX = 500;
		int tempY = 500;
		
		int tempWidth = 60;
		int tempHeight = 60;
		
		int spacing = 10;
		
		for(int i = 0; i < (numberOfSlots / 3); i++)
		{
			for(int j = 0; j < (numberOfSlots / 3); j++)
			{
				graphics.drawImage(
						Content.InventorySquare[0][0],
						tempX + tempWidth * i + spacing * i,
						tempY + tempHeight * j + spacing * j,
						tempWidth,
						tempHeight,					
						null
						);
			}

		}

	}
	
	
	private void select()
	{

	}

	public void initialize() 
	{
		
	}

	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_B)
		{
			gameStateManager.browsingInventory(false);
			gameStateManager.pause(false);
		}
		
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		
//		if(k == KeyEvent.VK_UP)
//		{
//			currentChoice--;
//			if(currentChoice == -1) currentChoice = options.length - 1;
//		}
//		if(k == KeyEvent.VK_DOWN)
//		{
//			currentChoice++;
//			if(currentChoice == options.length) currentChoice = 0;
//		}
	}

	public void keyReleased(int k) 
	{
		
	}
	
}