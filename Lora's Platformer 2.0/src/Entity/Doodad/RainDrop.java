package Entity.Doodad;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class RainDrop extends Doodad
{

	public RainDrop
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
				3, 
				19, 
				3, 
				19, 
				8,
				8, 
				true, 
				true, 
				false, 
				false, 
				true, 
				0, 
				"RainDrop",
				""
			);

	}

	public void setDoodad(int currentAction)
	{
		sprites = Content.RainDrop[0];
	}
	
	public void update()
	{
		super.update();

		if(!falling || locationX > tileMap.getWidth() || locationX < 0 || locationY < 0 || locationY > tileMap.getHeight())
		{
			removeMe = true;
		}
	}

}
