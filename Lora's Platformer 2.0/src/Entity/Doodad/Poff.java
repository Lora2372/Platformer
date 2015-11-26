package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class Poff extends Doodad
{

	public Poff(
			TileMap tileMap, 
			double spawnLocationX,
			double spawnLocationY
			) 
	{
		super(
				tileMap, 
				spawnLocationX, 
				spawnLocationY, 
				60, 
				60,
				0,
				0,
				0,
				0,
				true, 
				true, 
				true,
				false,
				false,
				0,
				"Poff",
				""
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.Poff[0];
	}
}