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
				0,
				0,
				"/Art/Sprites/Effects/SummonEffect.png",
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {14},
				true,
				true,
				true,
				false,
				false
				
		);
	}
}
