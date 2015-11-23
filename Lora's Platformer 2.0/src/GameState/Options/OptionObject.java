package GameState.Options;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entity.Player.Player;

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
	
	protected BufferedImage[] images;
	
	public OptionObject
		(
			double locationX, 
			double locationY, 
			int width, 
			int height, 
			int maxStates, 
			int minStates, 
			int currentState, 
			String[] text,
			BufferedImage[] images,
			String name
		)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		this.width = width;
		this.height = height;
		this.color = Color.white;
		this.maxStates = maxStates;
		this.minStates = minStates;
		this.currentState = currentState;
		this.text = text;
		this.images = images;
		
		
		rectangle = new Rectangle
		(
			(int)locationX,
			(int)locationY,
			width,
			height
		);
	}
	
	public void setLocation(double locationX, double locationY) 
	{ 
		this.locationX = locationX;
		this.locationY = locationY;
		this.rectangle.x = (int)locationX;
		this.rectangle.y = (int)locationY;
	}
	
	public double getLocationX() { return locationX; }
	public double getLocationY() { return locationY; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public Rectangle getRectangle() { return rectangle; }
	
	public void setColor(Color color) { this.color = color; }
	public Color getColor() { return color; }
	
	public int getCurrentState() { return currentState; }
	
	public void setPlayer(Player player)
	{
		
	}
	
	public void update() 
	{
		
	}
	
	public String getText() 
	{ 
	
		if(text.length < currentState)
		{
			return null;
		}
		return text[currentState-1]; 
	}
	
	public BufferedImage getImage() 
	{ 
		if(images.length < currentState)
		{
			return null;
		}
		return images[currentState-1]; 
	
	}
	
	public void click() { }
	
}
