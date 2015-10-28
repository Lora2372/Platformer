package GameState.Inventory;

import java.awt.image.BufferedImage;

import Main.Content;

public class InventorySlot 
{
	protected int locationX;
	protected int locationY;
	
	protected int width;
	protected int height;
	
	protected BufferedImage sprites = Content.InventorySquare[0][0];
	
	public InventorySlot(
			int locationX,
			int locationY,
			int width,
			int height
			
			)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		
		this.width = width;
		this.height = height;
		
		
	}
	
	public int getLocationX() {return locationX; }
	public int getLocationY() { return locationY; }
	
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public BufferedImage getSprites() { return sprites; }
	
	
}
