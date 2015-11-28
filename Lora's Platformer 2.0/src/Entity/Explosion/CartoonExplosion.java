package Entity.Explosion;

import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class CartoonExplosion extends Explosion
{
	public CartoonExplosion(
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
				0,
				"CartoonExplosion"
		);
	}
	
	public void setExplosion()
	{
		sprites = Content.CartoonExplosion[0];
	}
}
