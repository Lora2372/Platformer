package Entity.Doodad;

import java.util.ArrayList;

import Entity.Unit.Unit;
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
				5, 
				8, 
				3, 
				19, 
				8,
				8, 
				false, 
				true, 
				false, 
				false, 
				true, 
				0, 
				"RainDrop",
				""
			);
		removeOutsideScreen = true;
		doodadName = "RainDrop";
	}

	public void setDoodad(int currentAction)
	{
		sprites = Content.RainDrop[0];
	}
	
	public void update()
	{
		super.update();

		ArrayList<Unit> characterList = mainMap.getCharacterList();
		
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit character = characterList.get(i);
			if(character.intersects(this))
			{
				if(character.isPlayer())
				{
					mainMap.getPlayer().addWetLevel(3);
				}
				removeMe = true;
			}
		}
		
		if(!falling || locationX > tileMap.getWidth() || locationX < 0 || locationY < 0 || locationY > tileMap.getHeight())
		{
			removeMe = true;
		}
	}
}