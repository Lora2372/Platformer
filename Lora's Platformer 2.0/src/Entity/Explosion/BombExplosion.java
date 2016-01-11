package Entity.Explosion;

import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class BombExplosion extends Explosion
{
	public BombExplosion
		(
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
				128,
				256,
				128, 
				256,
				50,
				Content.damageTypes.Fire,
				"BombExplosion"
		);
	}
	public void setExplosion()
	{
		sprites = Content.BombExplosion[0];
	}
}