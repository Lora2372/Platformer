package GameState.Options;

import java.awt.image.BufferedImage;

public class ToggleObject extends OptionObject
{

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
			BufferedImage[] images
		) 
	{

		super(locationX, locationY, width, height, maxStates, minStates, currentState, text, images);
	}

	
	public void click()
	{
		if(currentState == 1)
		{
			currentState = 2;
		}
		else
		{
			currentState = 1;
		}
	}
	
}
