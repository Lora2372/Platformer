package Entity.Doodad.Activatable;

import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
import Main.Content;
import TileMap.TileMap;

public class Lever extends Doodad
{
	protected boolean used;
	
	
	protected boolean locked;
	protected boolean successfullyOpened;
	protected GameStateManager gameStateManager;
	
	public Lever
		(
			TileMap tileMap, 
			GameStateManager gameStateManager,
			double locationX,
			double locationY,
			int currentAction
		) 
	{
		super
			(
				tileMap, 
				locationX, 
				locationY, 
				26, 
				29,
				26,
				29,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				currentAction,
				"Lever",
				DoodadData.doodadName.get("Lever")
			);
		
		if(currentAction == 2)
		{
			used = true;
			spent = true;
			this.locked = false;
		}

		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		this.currentAction = currentAction;
		
		if(doodadType.equals(DoodadData.Doors.Boss.toString()))
		{
			portrait = Content.PortraitLever[0];
			if(currentAction == 0)
			{
				sprites = Content.LeverClosed[0];
			}
			else if(currentAction == 1)
			{
				sprites = Content.LeverOpening[0];
			}
			else if(currentAction == 2)
			{
				sprites = Content.LeverOpened[0];
			}
		}
		
		animation.setFrames(sprites);
	}
	
	public void interact(Player player)
	{
		
		active = true;
	}
	
	public void playSound() 
	{ 
//		JukeBox.play("OpenChest" + doodadType);
	}
}