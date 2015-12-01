package Entity.Projectile;

import Entity.Explosion.FireBallSmallExplosion;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class FireBallSmall extends Projectile
{
	protected FireBallSmallExplosion fireBallSmallExplosion;
	
	public FireBallSmall(
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
				35, 																// Projectile width
				35, 																// Projectile height
				30, 																// Collision width
				30, 																// Collision height
				5, 																	// Projectile speed
				"FireBallSmall"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.FireBallSmall[0];
	}
	
		public void explode()
		{
			fireBallSmallExplosion = new FireBallSmallExplosion(tileMap, mainMap, owner, locationX, locationY, friendly);
			mainMap.addExplosion(fireBallSmallExplosion);
		}
}
