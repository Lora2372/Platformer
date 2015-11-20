package GameState.Options;

import java.awt.image.BufferedImage;

import Audio.JukeBox;
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
	
	
	public void update()
	{
		if(name.equals("useMouse"))
		{
			currentState = !player.getUseMouse() ? 1 : 2;
		}
		
		if(name.equals("displayHealthBar"))
		{
			currentState = !player.getDisplayHealthBars() ? 1 : 2;
		}
		
		if(name.equals("displayNamePlates"))
		{
			currentState = !player.getDisplayNamePlates() ? 1 : 2;
		}
	}
	
	
	public void click()
	{
		if(name.equals("useMouse"))
		{
			player.setUseMouse(!player.getUseMouse());
		}
		if(name.equals("displayHealthBar"))
		{
			player.setDisplayHealthBars(!player.getDisplayHealthBars());
		}
		if(name.equals("displayNamePlates"))
		{
			player.setDisplayNamePlates(!player.getDisplayNamePlates());
		}
		
		JukeBox.play("Switch01");
	}
	
}
