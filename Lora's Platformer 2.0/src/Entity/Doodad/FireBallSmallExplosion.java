package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class FireBallSmallExplosion extends Doodad
{
	public FireBallSmallExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				60, 
				60,
				60,
				60,
				true,
				true,
				true,
				false,
				false,
				"FireBallSmallExplosion"
		);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.FireBallSmallExplosion[0];
	}
}
