package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
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
			GameStateManager gameStateManager,
			double spawnX,
			double spawnY
			) 
	{
		super(
				tileMap, 
				spawnX, 
				spawnY, 
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
				DoodadData.doodadName.get("Portal")
				);

		setDoodadType("Portal");
		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Portal[0];
	}
	
	public void interact(Player player)
	{
		if(gameStateManager.getState() == GameStateManager.FionasSanctum)
		{
			gameStateManager.setState(GameStateManager.DeepWoods);
		}
	}
	
	public void playSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
}