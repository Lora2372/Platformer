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
				60,
				60,
				"/Art/Sprites/Effects/FireballSmallExplosion.png",
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
