package Entity.Projectile;



import java.awt.Graphics2D;

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
				45, 																// Projectile width
				45, 																// Projectile height
				45, 																// Explosion width
				45, 																// Explosion height
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
		
//		public void updateExplosion()
//		{
//			if(fireBallSmallExplosion != null)
//			{
//				fireBallSmallExplosion.animation.update();
//				if(fireBallSmallExplosion.animation.hasPlayedOnce())
//				{
//					fireBallSmallExplosion.removeMe();
//					remove = true;
//				}
//			}
//		}
		
		public void drawExplosion(Graphics2D graphics)
		{
			fireBallSmallExplosion.draw(graphics);
		}
}
