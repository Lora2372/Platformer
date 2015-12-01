package Main;

import java.awt.Graphics2D;

public class DrawingConstants 
{
	public DrawingConstants()
	{
		// initializing?
	}
	
	public static int shiftNorth(int coordinate, int distance) 
	{
	   return (coordinate - distance);
	}
	
	public static int shiftSouth(int coordinate, int distance) {
	   return (coordinate + distance);
	}
	
	public static int shiftEast(int coordinate, int distance) 
	{
	   return (coordinate + distance);
	}
	
	public static int shiftWest(int coordinate, int distance) 
	{
	   return (coordinate - distance);
	}
	
	public static int getStringWidth(String text, Graphics2D graphics)
	{
		return (int)graphics.getFontMetrics().getStringBounds(text, graphics).getWidth();
	}
	
	public static int getStringHeight(String text, Graphics2D graphics)
	{
		return (int)graphics.getFontMetrics().getStringBounds(text, graphics).getHeight();
	}
}
