package Entity.Explosion;

import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ArcaneBallExplosion extends Explosion
{
	public ArcaneBallExplosion(
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
				90, 
				90,
				30,
				"ArcaneBallExplosion"
		);
	}
	
	public void setExplosion()
	{
		sprites = Content.ArcaneBallExplosion[0];
	}
}
