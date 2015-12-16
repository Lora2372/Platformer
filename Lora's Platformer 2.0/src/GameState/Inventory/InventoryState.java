package GameState.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Audio.JukeBox;
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
	
	protected int selectedSlotX = 0;
	protected int selectedSlotY = 0;
	
	
	int inventoryBackgroundWidth = 600;
	int inventoryBackgroundHeight = 600;

	int inventoryBackgroundLocationX = GamePanel.WIDTH / 2 - inventoryBackgroundWidth / 2;
	int inventoryBackgroundLocationY = GamePanel.HEIGHT / 2 - inventoryBackgroundHeight / 2;
	
	int inventorySlotWidth = 60;
	int inventorySlotHeight = 60;
	
	protected boolean movingItem;
	protected int movingItemFromSlotX = 0;
	protected int movingItemFromSlotY = 0;
	
	protected Player player;
	protected Inventory inventory;
	protected Item[][] items;
	
	protected Rectangle mouseRectangle;
	protected Rectangle[][] inventorySlotRectangles;
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
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
			return;
		}
		this.inventory = player.getInventory();
		
		numberOfColumns = inventory.getNumberOfColumns();
		numberOfRows = inventory.getNumberOfRows();
		numberOfSlots = numberOfColumns * numberOfRows;
		
		inventorySlotRectangles = new Rectangle[numberOfRows][numberOfColumns];
				
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
			
			font = new Font("Arial", Font.PLAIN, 14);
			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if(inventory.getNumberOfColumns() != numberOfColumns || inventory.getNumberOfRows() != numberOfRows)
		{
			initialize(player);
		}
	}
	
	public void saveToRAM()
	{
		
	}
	
	public void updateChoice(int slotX, int slotY)
	{
		if(selectedSlotX != slotX || selectedSlotY != slotY)
		{
			selectedSlotX = slotX;
			selectedSlotY = slotY;
			JukeBox.play("DecisionChange");
			
			try
			{
				player.getConversationState().displayItem(player, items[selectedSlotY][selectedSlotX]);
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	

	
	public void draw(Graphics2D graphics)
	{
		
		try
		{			
			graphics.setColor(titleColor);
			graphics.setFont(titleFont);
			int stringLength = (int)graphics.getFontMetrics().getStringBounds(title, graphics).getWidth();
			int textX = GamePanel.WIDTH/2 - stringLength / 2;
			
			graphics.drawString(title, textX, GamePanel.HEIGHT / 5);
			
			graphics.setFont(font);
			
		
			graphics.drawImage
				(
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
			
			
			
			if(inventorySlotWidth > spacingX - 5)
			{
				System.out.println("inventorySlotWidth: " + inventorySlotWidth + "}\nspacingX -5: " + (spacingX -5));
				inventorySlotWidth = spacingX - 5;
			}
			
			if(inventorySlotHeight > spacingY - 5)
			{
				System.out.println("inventorySlotHeight: " + inventorySlotHeight + "}\nspacingY -5: " + (spacingY -5));
				inventorySlotHeight = spacingY - 5;
			}
			
			
			
			mouseRectangle = new Rectangle((int)mouseLocationX, (int)mouseLocationY, 1, 1);
			
			
			for(int i = 0; i < numberOfRows; i++)
			{
				for(int j = 0; j < numberOfColumns; j++)
				{
					int inventorySlotLocationX = newX + spacingX * j;
					int inventorySlotLocationY = newY + (inventorySlotHeight + 5) * i;
					
					inventorySlotRectangles[i][j] = new Rectangle
						(
							inventorySlotLocationX, 
							inventorySlotLocationY, 
							inventorySlotWidth,
							inventorySlotHeight
						);
					
					if(inventorySlotRectangles[i][j].intersects(mouseRectangle))
					{
						updateChoice(j, i);
					}
					
					
					if(items[i][j] != null)
					{
						graphics.drawImage
							(
								items[i][j].getSprites()[0],
								inventorySlotLocationX,
								inventorySlotLocationY,
								inventorySlotWidth,
								inventorySlotHeight,
								null
							);
						
						graphics.setColor(Color.WHITE);
						graphics.drawString
							(
								items[i][j].getStacks() + "",
								inventorySlotLocationX + inventorySlotWidth / 2,
								inventorySlotLocationY + inventorySlotHeight + 12
							);
					}
					
					if(j == movingItemFromSlotX && i == movingItemFromSlotY && movingItem)
					{
						graphics.setColor(Color.RED);
						graphics.drawRect
						(					
							inventorySlotLocationX,
							inventorySlotLocationY,
							inventorySlotWidth,
							inventorySlotHeight
						);
					}
					else if(j == selectedSlotX && i == selectedSlotY)
					{
						
						graphics.setColor(Color.BLUE);
						graphics.drawRect
							(					
								inventorySlotLocationX,
								inventorySlotLocationY,
								inventorySlotWidth,
								inventorySlotHeight
							);
					}
					else
					{
						graphics.drawImage
						(
							Content.InventorySquare[0][0],
							inventorySlotLocationX,
							inventorySlotLocationY,
							inventorySlotWidth,
							inventorySlotHeight,
							null
						);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void select()
	{
		if(movingItem)
		{
			movingItem = false;
			Item tempItem = items[movingItemFromSlotY][movingItemFromSlotX];
			items[movingItemFromSlotY][movingItemFromSlotX] = items[selectedSlotY][selectedSlotX];
			items[selectedSlotY][selectedSlotX] = tempItem;
		}
		else
		{
			movingItem = true;
			movingItemFromSlotY = selectedSlotY;
			movingItemFromSlotX = selectedSlotX;
		}
	}

	public void keyPressed(int k) 
	{
		if(k == player.getKeyBind(Player.KeyBind.OpenInventory))
		{
			player.getConversationState().endConversation();
			gameStateManager.setBrowsingInventory(false);
			gameStateManager.setPaused(false);
		}
		
		if(k == player.getKeyBind(Player.KeyBind.TossItem))
		{
			items[selectedSlotY][selectedSlotX].drop();
			items[selectedSlotY][selectedSlotX] = null;
		}
		
		if(k == KeyEvent.VK_W)
		{
			items[selectedSlotY][selectedSlotX].use(player);
		}
		
		if(k == KeyEvent.VK_ENTER || k == player.getKeyBind(Player.KeyBind.Interact))
		{
			select();
		}
		
		if(k == player.getKeyBind(Player.KeyBind.MoveLeft))
		{
			if(selectedSlotX > 0)
			{
				updateChoice(selectedSlotX - 1, selectedSlotY);
			}	
		}
		
		if(k == player.getKeyBind(Player.KeyBind.MoveRight))
		{
			if(selectedSlotX < numberOfColumns - 1)
			{
				updateChoice(selectedSlotX + 1, selectedSlotY);
			}
		}
		
		
		if(k == player.getKeyBind(Player.KeyBind.AimUp))
		{
			if(selectedSlotY > 0) 
			{
				updateChoice(selectedSlotX, selectedSlotY - 1);
			}
		}
		
		if(k == player.getKeyBind(Player.KeyBind.AimDown))
		{
			if(selectedSlotY < numberOfRows - 1) 
			{
				updateChoice(selectedSlotX, selectedSlotY + 1);
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
		select();
//		for(int y = 0; y < numberOfRows; y++)
//		{
//			for(int x = 0; x < numberOfColumns; x++)
//			{
//				if(mouseRectangle.intersects(inventorySlotRectangles[y][x]))
//				{
//					if(items[y][x] != null)
//					{
//						movingItem = true;
//					}
//					movingItemFromSlotX = x;
//					movingItemFromSlotY = y;
//				}
//			}
//		}
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		select();
//		if(movingItem)
//		{
//			for(int y = 0; y < numberOfRows; y++)
//			{
//				for(int x = 0; x < numberOfColumns; x++)
//				{
//					if(mouseRectangle.intersects(inventorySlotRectangles[y][x]))
//					{
//						if(items[y][x] == null)
//						{
//							items[y][x] = items[movingItemFromSlotY][movingItemFromSlotX];
//							items[movingItemFromSlotY][movingItemFromSlotX] = null;
//						}
//					}
//				}
//			}
//		}
//		movingItem = false;
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