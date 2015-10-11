package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Player;
import Main.Content;
import Main.JSONWriter;
import TileMap.TileMap;

public class StatueSave extends Doodad
{


	
	public StatueSave(
			TileMap tileMap, 
			double spawnX,
			double spawnY
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				60,
				60,
				false, 
				true, 
				false,
				true,
				false,
				"StatueSave"
				);
		
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.StatueSave[0];

	}
	
	public void interact(Player player)
	{
		JSONWriter.saveFile(player, (int)spawnX, (int)spawnY);
		activateSound();
	}
	
	
	public void activateSound() 
	{ 
		JukeBox.play("Save");
	}
	
}