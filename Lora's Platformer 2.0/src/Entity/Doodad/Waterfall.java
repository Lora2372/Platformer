package Entity.Doodad;

import Main.Content;
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
				0,
				0,
				true,
				true,
				false,
				false,
				false,
				0,
				"Waterfall"
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.Waterfall[0];
	}
}
