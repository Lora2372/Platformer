package Entity.Doodad;

import TileMap.TileMap;

public class Waterfall extends Doodad
{
	public Waterfall(
			TileMap tileMap,
			double spawnX,
			double spawnY
			
			)
	{
		super(
				tileMap,
				spawnX,
				spawnY,
				192,
				192,
				192,
				192,
				"/Art/Sprites/Doodads/Waterfall.png",
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {4},
				true,
				true,
				false,
				false,
				false
				);
	}
}
