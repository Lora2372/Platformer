package Entity.Doodad.Activatable;

import TileMap.TileMap;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.MainMap.MainMap;
import Main.Content;

public class Shrine extends Doodad
{
	
	public Shrine
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
				160, 
				160,
				160,
				160,
				0, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				"Shrine",
				DoodadData.doodadName.get("Shrine")
			);
		
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Shrine[0];
		portrait = Content.PortraitShrine[0];
	}
		
	
	public void interact(Player player)
	{
		// Don't want to activate it more than once.
		if(active)
		{
			return;
		}
		
		// Interacts with the shrine in the respective map.
		mainMap.useDoodad(this);

	}
}