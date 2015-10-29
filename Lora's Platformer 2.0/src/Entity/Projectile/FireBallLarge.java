package Entity.Projectile;

import Entity.Explosion.FireBallLargeExplosion;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallLarge extends Projectile
{
	protected FireBallLargeExplosion fireBallLargeExplosion;

	public FireBallLarge(
			TileMap tileMap,
			MainMap mainMap,
			boolean right, 
			boolean up, 
			boolean down, 
			double angle,
			boolean friendly,
			int damage
		)
	{
		
		super(
				tileMap, 															//Tile map
				mainMap,
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
				damage,																// Explosion damage
				40,																	// manacost
				240, 																// Explosion radius
				"FireBallLarge"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.FireBallLarge[0];
	}
	
	public void explode()
	{
		fireBallLargeExplosion = new FireBallLargeExplosion(tileMap, mainMap, locationX, locationY, friendly);
		mainMap.addExplosion(fireBallLargeExplosion);
	}
}
