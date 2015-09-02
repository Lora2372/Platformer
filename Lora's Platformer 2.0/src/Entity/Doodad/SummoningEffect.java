package Entity.Doodad;

import TileMap.TileMap;

public class SummoningEffect extends Doodad
{
	public SummoningEffect(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				192, 
				192, 
				"/Sprites/Effects/SummonEffect.png",
				new int[] {14},
				true,
				true,
				true
		);
	}
}
