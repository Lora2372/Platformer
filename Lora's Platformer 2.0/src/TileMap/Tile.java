package TileMap;

import java.awt.image.BufferedImage;



public class Tile 
{
	private BufferedImage image;
	private int type;
	
	// Tyle types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	protected boolean water = false;
	
	public Tile(BufferedImage image, int type, boolean isWater)
	{
		this.image = image;
		this.type = type;
		this.water = isWater;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public int getType()
	{
		return type;
	}
	
	public boolean isWater()
	{
		return water;
	}
	
}
