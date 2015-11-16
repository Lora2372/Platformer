package Entity.Doodad;

import Main.Content;
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
				0,
				0,
				true,
				true,
				false,
				false,
				false,
				0,
				"Torch",
				""
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.Torch[0];
	}
}
