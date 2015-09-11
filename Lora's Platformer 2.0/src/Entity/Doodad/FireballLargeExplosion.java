package Entity.Doodad;

import TileMap.TileMap;

public class FireballLargeExplosion extends Doodad
{
	public FireballLargeExplosion(TileMap tileMap, double x, double y )
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
				"/Sprites/Effects/FireballLargeExplosion.png",
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {9},
				true,
				true,
				true,
				false,
				false
		);
	}
}
