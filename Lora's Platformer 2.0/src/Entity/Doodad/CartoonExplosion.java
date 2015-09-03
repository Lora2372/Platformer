package Entity.Doodad;

import TileMap.TileMap;

public class CartoonExplosion extends Doodad
{
	public CartoonExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				60, 
				60, 
				"/Sprites/Effects/CartoonExplosion.gif",
				new int[] {6},
				true,
				true,
				true
		);
	}
}
