package Entity.Explosion;

import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallSmallExplosion extends Explosion
{
	public FireBallSmallExplosion(
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
				60,
				60,
				60, 
				60,
				20,
				Content.damageTypes.Fire,
				"FireBallSmallExplosion"
		);
	}
	public void setExplosion()
	{
		sprites = Content.FireBallSmallExplosion[0];
	}
}
