package Entity.Doodad;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ElectricBallChargeUp extends Doodad
{

	public ElectricBallChargeUp
		(
			TileMap tileMap,
			MainMap mainMap,
			double spawnLocationX,
			double spawnLocationY
		) 
	{
		super(
				tileMap,
				mainMap,
				spawnLocationX, 
				spawnLocationY, 
				60, 
				60,
				0,
				0,
				0,
				0,
				true, 
				true, 
				true,
				false,
				false,
				0,
				"ElectricballChargeUp",
				""
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.ElectricBallChargeUp[0];
	}
}