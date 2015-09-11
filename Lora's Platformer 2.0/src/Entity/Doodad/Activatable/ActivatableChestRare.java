package Entity.Doodad.Activatable;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;

public class ActivatableChestRare extends Doodad
{

	public ActivatableChestRare(
			TileMap tileMap, 
			double spawnX,
			double spawnY
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				60,
				60,
				"/Sprites/Doodads/ChestRare.png", 
				new int[] {0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {1, 4, 1},
				false, 
				true, 
				false,
				true,
				false
				);

	}
	
	public void activateSound() 
	{ 
		JukeBox.play("OpenChestRare");
	}
	
}
