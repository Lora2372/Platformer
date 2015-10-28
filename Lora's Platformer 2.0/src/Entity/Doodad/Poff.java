package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class Poff extends Doodad
{

	public Poff(
			TileMap tileMap, 
			double spawnX,
			double spawnY
			) 
	{
		super(
				tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				0,
				0,
				true, 
				true, 
				true,
				false,
				false,
				"Poff"
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.Poff[0];
	}
}