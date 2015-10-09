package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class ElectricBallRChargeUp extends Doodad
{

	public ElectricBallRChargeUp(
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
				true, 
				true, 
				true,
				false,
				false,
				"ElectricballChargeUp"
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.ElectricBallChargeUp[0];
	}
}