package Entity.Explosion;

import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ElectricBallExplosion  extends Explosion
{
	public ElectricBallExplosion(
			TileMap tileMap, 
			MainMap mainMap,
			Unit owner,
			double locationX, 
			double locationY,
			boolean friendly
			)
	{
		super
		(
				tileMap, 
				mainMap,
				owner,
				friendly,
				locationX, 
				locationY,
				180,
				180,
				180, 
				180,
				70,
				Content.damageTypes.Air,
				"ElectricBallExplosion"
		);
	}
	
	public void setExplosion()
	{
		sprites = Content.ElectricBallExplosion[0];
	}
}
