package Entity.Doodad;

import Audio.JukeBox;
import Main.Content;
import TileMap.TileMap;

public class SummoningEffect extends Doodad
{
	public SummoningEffect(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				192, 
				192,
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
				"SummoningEffect"
				
		);
		
		JukeBox.play("Teleport");
		
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.Teleport[0];
	}
}
