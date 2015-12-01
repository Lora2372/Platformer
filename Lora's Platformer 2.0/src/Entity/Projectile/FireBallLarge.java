package Entity.Projectile;

import Entity.Explosion.FireBallLargeExplosion;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallLarge extends Projectile
{
	protected FireBallLargeExplosion fireBallLargeExplosion;

	public FireBallLarge(
			TileMap tileMap,
			MainMap mainMap,
			Unit owner,
			boolean right, 
			boolean up, 
			boolean down, 
			double angle,
			boolean friendly
		)
	{
		
		super(
				tileMap, 															//Tile map
				mainMap,
				owner,
				right, 																// Facing right
				up,  																// Holding up arrow
				down,  																// Holding down arrow
				angle,
				friendly, 															// Whether the spell is friendly or hostile
				90, 																// Projectile width
				90, 																// Projectile height
				40, 																// Collision width
				40, 																// Collision height
				5, 																	// Projectile speed
				"FireBallLarge"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.FireBallLarge[0];
	}
	
	public void explode()
	{
		fireBallLargeExplosion = new FireBallLargeExplosion(tileMap, mainMap, owner, locationX, locationY, friendly);
		mainMap.addExplosion(fireBallLargeExplosion);
	}
}
