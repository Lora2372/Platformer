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
				116, 
				110,
				116,
				110,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
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
		playSound();
		player.restoreHealth(player.getMaxHealth());
		player.restoreMana(player.getMaxMana());
		player.restoreStamina(player.getMaxStamina());
	}
	
	
	public void playSound() 
	{ 
		JukeBox.play("Save");
	}
	
}