package Entity.Projectile;

import Entity.Explosion.FireBallSmallExplosion;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallSmall extends Projectile
{
	protected FireBallSmallExplosion fireBallSmallExplosion;
	
	public FireBallSmall(
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
				35, 																// Projectile width
				35, 																// Projectile height
				30, 																// Collision width
				30, 																// Collision height
				5, 																	// Projectile speed
				damage,																// Explosion damage
				20,
				30, 																// Explosion radius
				"FireBallSmall"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.FireBallSmall[0];
	}
	
		public void explode()
		{
			fireBallSmallExplosion = new FireBallSmallExplosion(tileMap, mainMap, locationX, locationY, friendly);
			mainMap.addExplosion(fireBallSmallExplosion);
		}
}
