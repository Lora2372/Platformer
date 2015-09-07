package Entity.Doodad;

import TileMap.TileMap;

public class FireballSmallExplosion extends Doodad
{
	public FireballSmallExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				60, 
				60, 
				"/Sprites/Effects/FireballSmallExplosion.png",
				new int[] {9},
				true,
				true,
				true
		);
	}
}