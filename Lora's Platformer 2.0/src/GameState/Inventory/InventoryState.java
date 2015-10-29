package GameState.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameState.GameState;
import GameState.GameStateManager;
import Main.Content;
import Main.GamePanel;
import TileMap.Background;

public class InventoryState extends GameState
{
	private Background background;
	
	private ArrayList<InventorySlot> inventorySlots;
	
	private String title = "Inventory";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	protected int numberOfColumns = 3;
	protected int numberOfRows = 3;
	protected int numberOfSlots = numberOfColumns * numberOfRows;
	
	protected int selectedSlot = 0;
	
	int inventoryBackgroundWidth = 500;
	int inventoryBackgroundHeight = 500;

	int inventoryBackgroundLocationX = GamePanel.WIDTH / 2 - inventoryBackgroundWidth / 2;
	int inventoryBackgroundLocationY = GamePanel.HEIGHT / 2 - inventoryBackgroundHeight / 2;
	
	int inventorySlotWidth = 60;
	int inventorySlotHeight = 60;
	
	
	public InventoryState(GameStateManager gameStateManager)
	{
		this.gameStateManager = gameStateManager;
		
		inventorySlots = new ArrayList<InventorySlot>();
		
		for(int i = 0; i < numberOfRows ; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				int inventorySlotLocationX = inventoryBackgroundLocationX + 60 * j;
				int inventorySlotLocationY = inventoryBackgroundLocationY + 60 * i;
				
				InventorySlot inventorySlot = new InventorySlot(inventorySlotLocationX, inventorySlotLocationY, inventorySlotWidth, inventorySlotHeight);
				
				inventorySlots.add(inventorySlot);
				
			}

		}
		
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
		
	
		graphics.drawImage(
				Content.InventoryBackground[0][0],
				inventoryBackgroundLocationX,
				inventoryBackgroundLocationY,
				inventoryBackgroundWidth,
				inventoryBackgroundHeight,
				null
				);

		int spacing = 10;

		int currentSlot = 0;
		for(int i = 0; i < (numberOfRows); i++)
		{
			for(int j = 0; j < (numberOfColumns); j++)
			{
				InventorySlot inventorySlot = inventorySlots.get(currentSlot);
				if(currentSlot == selectedSlot)
				{
					graphics.setColor(Color.BLUE);
					graphics.drawRect(					
							inventorySlot.getLocationX() + inventorySlot.getWidth() * j + spacing * j,
							inventorySlot.getLocationY() + inventorySlot.getHeight() * i + spacing * i,
							inventorySlot.getWidth(),
							inventorySlot.getHeight()
							);
				}
				else
				{
					graphics.drawImage(
							Content.InventorySquare[0][0],
							inventorySlot.getLocationX() + inventorySlot.getWidth() * j + spacing * j,
							inventorySlot.getLocationY() + inventorySlot.getHeight() * i + spacing * i,
							inventorySlot.getWidth(),
							inventorySlot.getHeight(),					
							null
							);
				}

				
				currentSlot++;
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
		
		if(k == KeyEvent.VK_LEFT)
		{
			if(selectedSlot > 0)
			{
				selectedSlot--;
			}
		}
		
		if(k == KeyEvent.VK_RIGHT)
		{
			if(selectedSlot < numberOfSlots)
			{
				selectedSlot++;
			}
		}
		
		
		if(k == KeyEvent.VK_UP)
		{
			if(selectedSlot - numberOfColumns > 0) 
			{
				selectedSlot -= numberOfColumns;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			if(selectedSlot + numberOfColumns < numberOfSlots) 
			{
				selectedSlot += numberOfColumns;
			}
		}		
	}

	public void keyReleased(int k) 
	{
		
	}
	
}