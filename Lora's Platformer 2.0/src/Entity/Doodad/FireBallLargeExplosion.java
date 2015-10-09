package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class FireBallLargeExplosion extends Doodad
{
	public FireBallLargeExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				240, 
				240,
				240,
				240,
				true,
				true,
				true,
				false,
				false,
				"FireBallLargeExplosion"
		);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.FireBallLargeExplosion[0];
	}
}
