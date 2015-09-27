package Entity.Doodad;

import TileMap.TileMap;

public class Torch extends Doodad
{
	public Torch(
			TileMap tileMap,
			double spawnX,
			double spawnY
			
			)
	{
		super(
				tileMap,
				spawnX,
				spawnY,
				150,
				150,
				150,
				150,
				"/Art/Sprites/Doodads/Torch.png",
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {8},
				true,
				true,
				false,
				false,
				false
				);
	}
}
