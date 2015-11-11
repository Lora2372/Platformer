package GameState.Options;

import java.awt.image.BufferedImage;

import Entity.Player.Player;

public class ToggleObject extends OptionObject
{
	protected String name;
	protected Player player;
	
	public ToggleObject
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
			String name,
			Player player
		) 
	{
		super(locationX, locationY, width, height, maxStates, minStates, currentState, text, images, name);
	
		this.name = name;
	
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void click()
	{
		if(currentState == 1)
		{
			if(name.equals("Mouse"))
			{
				player.setUsingMouse(false);
			}
			currentState = 2;
		}
		else
		{
			currentState = 1;
			if(name.equals("Mouse"))
			{
				player.setUsingMouse(true);
			}
		}
	}
	
}
