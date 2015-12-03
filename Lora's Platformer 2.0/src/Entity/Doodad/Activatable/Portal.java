package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MainMap.MainMap;
import GameState.Maps.DeepWoods;
import Main.Content;
import TileMap.TileMap;

public class Portal extends Doodad
{
	protected boolean used;
	
	protected int silver;
	protected int gold;
	
	protected boolean locked;
	protected boolean successfullyOpened;
	protected GameStateManager gameStateManager;
	
	public Portal(
			TileMap tileMap, 
			MainMap mainMap,
			GameStateManager gameStateManager,
			double spawnLocationX,
			double spawnLocationY
			) 
	{
		super
			(
				tileMap,
				mainMap,
				spawnLocationX, 
				spawnLocationY, 
				120, 
				120,
				120,
				140,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				"Portal",
				CreateDoodad.doodadName.get("Portal")
			);

		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Portal[0];
	}
	
	public void interact(Player player)
	{
		if(gameStateManager.getCurrentState() == GameStateManager.FionasSanctum)
		{

			player.setPosition(DeepWoods.startLocationX, DeepWoods.startLocationY);
			player.spawn();
			

			gameStateManager.setState(GameStateManager.DeepWoods);
		}
	}
	
	public void playSound() 
	{ 
		JukeBox.play("EnterPortal");
	}
}