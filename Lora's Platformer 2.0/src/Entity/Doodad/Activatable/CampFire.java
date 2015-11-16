package Entity.Doodad.Activatable;

import Entity.Doodad.Doodad;
import Entity.Player.Player;
import GameState.GameStateManager;
import Main.Content;
import TileMap.TileMap;

public class CampFire extends Doodad
{
	
	protected GameStateManager gameStateManager;
	
	public CampFire(
			TileMap tileMap, 
			GameStateManager gameStateManager,
			double spawnX,
			double spawnY
			) 
	{
		super(
				tileMap, 
				spawnX, 
				spawnY, 
				128, 
				128,
				128,
				128,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				"CampFire",
				DoodadData.doodadName.get("CampFire")
				);

		this.gameStateManager = gameStateManager;
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.CampFire[0];
		portrait = Content.PortraitCampFire[0];
	}
	
	public void interact(Player player)
	{

	}
	
	public void playSound() 
	{ 
//		JukeBox.play("CampFire");
	}
}