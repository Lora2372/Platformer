package Entity.Doodad;

import Entity.Doodad.Activatable.DoodadData;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class EmotionBubble extends Doodad
{
	public EmotionBubble
		(
			TileMap tileMap,
			MainMap mainMap,
			double spawnLocationX,
			double spawnLocationY,
			DoodadData.EmotionBubbles type
		)
	{
		super
		(
				tileMap,
				mainMap,
				spawnLocationX, 
				spawnLocationY, 
				40, 
				40,
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
				type.toString(),
				""
				
		);
		
//		JukeBox.play("Teleport");
		
	}
	public void setDoodad(int currentAction)
	{
		sprites = Content.EmotionBubbleExclamation[0];
		delay = 120;
	}
}