package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class MagicShield extends Doodad
{

	public MagicShield(
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