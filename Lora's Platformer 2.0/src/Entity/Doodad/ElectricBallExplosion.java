package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class ElectricBallExplosion  extends Doodad
{
	public ElectricBallExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				180, 
				180,
				180,
				180,
				true,
				true,
				true,
				false,
				false,
				"ElectricBallExplosion"
		);
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.ElectricBallExplosion[0];
	}
}
