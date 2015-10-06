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
				100, 
				96,
				100,
				96,
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
		if(gameStateManager.getState() == 1)
		{
			System.out.println("Loading MysteriousDungeon");
			gameStateManager.setState(2);
		}
		else if(gameStateManager.getState() == 2)
		{
			System.out.println("Should be loading deep woods");
			gameStateManager.setState(3);
		}
			
	}
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}
