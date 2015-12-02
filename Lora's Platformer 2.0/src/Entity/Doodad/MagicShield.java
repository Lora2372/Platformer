package Entity.Doodad;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class MagicShield extends Doodad
{

	public MagicShield
		(
			TileMap tileMap,
			MainMap mainMap,
			double spawnLocationX,
			double spawnLocationY
		) 
	{
		super(
				tileMap,
				mainMap,
				spawnLocationX, 
				spawnLocationY, 
				192, 
				192,
				192,
				192,
				0,
				0,
				false, 
				true, 
				true,
				false,
				false,
				0,
				"MagicShield",
				""
				);
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.MagicShield[0];
	}
}