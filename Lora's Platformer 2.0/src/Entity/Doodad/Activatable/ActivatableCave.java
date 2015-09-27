package Entity.Doodad.Activatable;

import TileMap.TileMap;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;

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
				360, 
				360,
				360,
				360,
				"/Art/Sprites/Doodads/CaveEntrance.png", 
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {1},
				false, 
				true, 
				false,
				true,
				false
				);
		
		this.gameStateManager = gameStateManager;
	}
	
	public void interact(Player player)
	{
		gameStateManager.setState(2);
	}
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}
