package Entity.Doodad;

import TileMap.TileMap;

public class ElectricballChargeUp extends Doodad
{

	public ElectricballChargeUp(
			TileMap tileMap, 
			double spawnX,
			double spawnY
			) 
	{
		super(
				tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				0,
				0,
				"/Sprites/Effects/ElectricballChargeUp.png", 
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {3},
				true, 
				true, 
				true,
				false,
				false
				);
	}
	
}