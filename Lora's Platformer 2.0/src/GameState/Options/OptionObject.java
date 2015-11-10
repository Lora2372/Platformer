package GameState.Options;

import java.awt.Color;
import java.awt.Rectangle;

public class OptionObject 
{
	
	protected double locationX;
	protected double locationY;
	
	protected int width;
	protected int height;
	
	protected Rectangle rectangle;
	
	protected int maxStates;
	protected int minStates;
	protected int currentState;
	
	protected Color color;
	
	protected String[] text;
	
	public OptionObject(double locationX, double locationY, int width, int height, int maxStates, int minStates, int currentState, String[] text)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		this.width = width;
		this.height = height;
		this.color = Color.RED;
		this.maxStates = maxStates;
		this.minStates = minStates;
		this.currentState = currentState;
		this.text = text;
		
		rectangle = new Rectangle
			(
				(int)locationX,
				(int)locationY,
				width,
				height
			);
		
	}
	
	public double getLocationX() { return locationX; }
	public double getLocationY() { return locationY; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public Rectangle getRectangle() { return rectangle; }
	
	public void setColor(Color color) { this.color = color; }
	public Color getColor() { return color; }
	
	public int getCurrentState() { return currentState; }
	
	public String[] getText() { return text; }
	
}
