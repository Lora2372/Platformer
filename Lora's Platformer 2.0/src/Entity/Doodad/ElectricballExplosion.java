package Entity.Doodad;

import TileMap.TileMap;

public class ElectricballExplosion  extends Doodad
{
	public ElectricballExplosion(TileMap tileMap, double x, double y )
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
				"/Art/Sprites/Effects/ElectricballExplosion.png",
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
