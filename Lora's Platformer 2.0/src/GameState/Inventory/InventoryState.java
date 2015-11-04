package GameState.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entity.Item.Item;
import Entity.Player.Player;
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
	
	protected int numberOfColumns;
	protected int numberOfRows;
	protected int numberOfSlots;
	protected int slotWidth = 60;
	protected int slotHeight = 60;
	
	protected int selectedSlotX = 0;
	protected int selectedSlotY = 0;
	
	int inventoryBackgroundWidth = 600;
	int inventoryBackgroundHeight = 600;

	int inventoryBackgroundLocationX = GamePanel.WIDTH / 2 - inventoryBackgroundWidth / 2;
	int inventoryBackgroundLocationY = GamePanel.HEIGHT / 2 - inventoryBackgroundHeight / 2;
	
	int inventorySlotWidth = 60;
	int inventorySlotHeight = 60;
	
	protected Player player;
	protected Inventory inventory;
	protected Item[][] items;
	
	
	public InventoryState(
			GameStateManager 
			gameStateManager
			)
	{
		this.gameStateManager = gameStateManager;

	}
	
	public void initialize() 
	{

	}
	
	public void initialize(Player player)
	{
		this.player = player;
		
		if(player == null)
		{
			System.out.println("player is null");
		}
		System.out.println("Using inventory");
		this.inventory = player.getInventory();
		
		numberOfColumns = inventory.getColumns();
		numberOfRows = inventory.getRows();
		numberOfSlots = numberOfColumns * numberOfRows;
		
		this.items = inventory.getItems();

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

		
		int newX = inventoryBackgroundLocationX + 60;
		int newY = inventoryBackgroundLocationY + 80;
		
		int newWidth = inventoryBackgroundWidth - 100;
		int newHeight = inventoryBackgroundHeight - 100;
		
		
		int spacingX = newWidth / numberOfColumns;
		int spacingY = newHeight / numberOfRows;
		
		
		
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				int inventorySlotLocationX = newX + spacingX * j;
				int inventorySlotLocationY = newY + spacingY * i;
				
				
				
				if(items[i][j] != null)
				{
					graphics.drawImage(
							items[i][j].getSprites()[0],
							inventorySlotLocationX,
							inventorySlotLocationY,
							items[i][j].getWidth(),
							items[i][j].getHeight(),
							null
							);
					
					graphics.drawString(items[i][j].getStacks() + "", inventorySlotLocationX, inventorySlotLocationY);
				}
				
				
				
				if(selectedSlotX == j && selectedSlotY == i)
				{
					graphics.setColor(Color.BLUE);
					graphics.drawRect(					
							inventorySlotLocationX,
							inventorySlotLocationY,
							slotWidth,
							slotHeight
							);
				}
				else
				{
					graphics.drawImage(
							Content.InventorySquare[0][0],
							inventorySlotLocationX,
							inventorySlotLocationY,
							slotWidth,
							slotHeight,
							null
							);
				}
			}
		}
	}
	
	
	private void select()
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
			if(selectedSlotX > 0)
			{
				selectedSlotX--;
			}
		}
		
		if(k == KeyEvent.VK_RIGHT)
		{
			if(selectedSlotX < numberOfColumns - 1)
			{
				selectedSlotX++;
			}
		}
		
		
		if(k == KeyEvent.VK_UP)
		{
			if(selectedSlotY > 0) 
			{
				selectedSlotY --;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			if(selectedSlotY < numberOfRows - 1) 
			{
				selectedSlotY ++;
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
		
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		
	}

}