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
			double spawnLocationX,
			double spawnLocationY
			) 
	{
		super(tileMap, 
				spawnLocationX, 
				spawnLocationY, 
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
				CreateDoodad.doodadName.get("CaveEntrance")
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
