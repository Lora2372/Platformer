package Entity.Doodad;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Torch extends Doodad
{
	public Torch
		(
			TileMap tileMap,
			MainMap mainMap,
			double spawnLocationX,
			double spawnLocationY
		)
	{
		super
			(
				tileMap,
				mainMap,
				spawnLocationX,
				spawnLocationY,
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
