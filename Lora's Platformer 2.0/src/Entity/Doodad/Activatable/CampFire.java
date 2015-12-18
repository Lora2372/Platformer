package Entity.Doodad.Activatable;

import java.awt.Rectangle;
import Entity.Doodad.Doodad;
import Entity.Player.Buff;
import Entity.Player.CreateBuff;
import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class CampFire extends Doodad
{
	
	protected GameStateManager gameStateManager;
	
	protected Player player;
	
	public CampFire
		(
			TileMap tileMap, 
			MainMap mainMap,
			GameStateManager gameStateManager,
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
				128, 
				128,
				100,
				100,
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
		this.player = mainMap.getPlayer();
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
	
	public void update()
	{
		super.update();
		
		this.player = mainMap.getPlayer();
		
		Buff campfireBuff = player.getCampFireBuff();
		if( (new Rectangle((int)locationX - 200, (int)locationY - 200, 400, 400)).intersects(player.getRectangle()) )
		{
			if(campfireBuff == null)
			{
//				player.getHUD().fadeMessage("You begin to warm up.");
				campfireBuff = new Buff(CreateBuff.Buffs.CampFire, -1, 0.01, player, Content.CampFire[0][0]);
				player.setCampFireBuff(campfireBuff);
				player.addBuff(campfireBuff);
			}
		}
		else
		{
			if(campfireBuff != null)
			{
				campfireBuff.setExpire();
				player.setCampFireBuff(null);
			}
		}
		
	}
	
	public void playSound() 
	{ 
//		JukeBox.play("CampFire");
	}
}