package Entity.Doodad.Activatable;

import TileMap.TileMap;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
import Main.Content;

public class ActivatableCave extends Doodad
{
	protected GameStateManager gameStateManager;
	
	public ActivatableCave(
			TileMap tileMap,
			GameStateManager gameStateManager,
			double spawnX,
			double spawnY
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				100, 
				96,
				100,
				96,
				0,
				0,
				false, 
				true, 
				false,
				true,
				false,
				0,
				"CaveEntrance",
				DoodadData.doodadName.get("CaveEntrance")
				);
		
		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.CaveEntrance[0];
	}
	
	public void interact(Player player)
	{

			
	}
	
	public void activateSound() 
	{ 
//		JukeBox.play("EnterCave");
	}
	
}
