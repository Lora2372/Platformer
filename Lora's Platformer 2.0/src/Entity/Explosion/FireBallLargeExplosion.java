package Entity.Explosion;

import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallLargeExplosion extends Explosion
{
	public FireBallLargeExplosion(
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
				240,
				240,
				240, 
				240,
				50,
				"FireBallLargeExplosion"
		);
	}
	public void setExplosion()
	{
		sprites = Content.FireBallLargeExplosion[0];
	}
}
