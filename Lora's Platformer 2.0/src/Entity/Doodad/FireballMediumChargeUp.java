package Entity.Doodad;

import TileMap.TileMap;

public class FireballMediumChargeUp extends Doodad
{

	public FireballMediumChargeUp(
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
				"/Sprites/Effects/FireballMediumChargingUp.png", 
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {6},
				true, 
				true, 
				true,
				false,
				false
				);
	}
	
}