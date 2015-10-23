package Entity.Explosion;

import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ElectricBallExplosion  extends Explosion
{
	public ElectricBallExplosion(
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
				180,
				180,
				180, 
				180,
				70,
				"ElectricBallExplosion"
		);
	}
	
	public void setExplosion()
	{
		sprites = Content.ElectricBallExplosion[0];
	}
}
