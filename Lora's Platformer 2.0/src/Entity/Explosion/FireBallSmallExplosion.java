package Entity.Explosion;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallSmallExplosion extends Explosion
{
	public FireBallSmallExplosion(
			TileMap tileMap, 
			MainMap mainMap,
			double locationX, 
			double locationY,
			boolean friendly
			)
	{
		super
		(
				tileMap, 
				mainMap,
				friendly,
				locationX, 
				locationY, 
				60,
				60,
				60, 
				60,
				20,
				"FireBallSmallExplosion"
		);
	}
	public void setExplosion()
	{
		sprites = Content.FireBallSmallExplosion[0];
	}
}
