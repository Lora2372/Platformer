package Entity.Doodad;

import Main.Content;
import TileMap.TileMap;

public class CartoonExplosion extends Doodad
{
	public CartoonExplosion(TileMap tileMap, double x, double y )
	{
		super
		(
				tileMap, 
				x, 
				y, 
				60, 
				60,
				60,
				60,
				true,
				true,
				true,
				false,
				false,
				"CartoonExplosion"
		);
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.CartoonExplosion[0];
	}
}
