package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MainMap.MainMap;
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
			MainMap mainMap,
			GameStateManager gameStateManager,
			double locationX,
			double locationY,
			String uniqueID,
			int currentAction
		) 
	{
		super
			(
				tileMap,
				mainMap,
				locationX, 
				locationY, 
				50, 
				50,
				50,
				50,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				currentAction,
				"Lever",
				CreateDoodad.doodadName.get("Lever")
			);
		
		this.uniqueID = uniqueID;
		
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
		
		if(doodadType.equals(CreateDoodad.Other.Lever.toString()))
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
			else if(currentAction == 3)
			{
				sprites = Content.LeverClosing[0];
			}
		}
		
		animation.setFrames(sprites);
		animation.setDelay(200);
	}
	
	public void interact(Player player)
	{
		if(!player.getInConversation())
		{
			if(currentAction == 0)
			{
				setDoodad(1);
			}
			else if(currentAction == 2)
			{
				setDoodad(3);
			}
			
			playSound();
		}
		else
		{
			if(player.getConversationState().getConversationOver())
			{
				player.getConversationState().endConversation();
			}
		}
	}
	
	
	public void playSound() 
	{ 
		JukeBox.play("Switch03");
	}
}