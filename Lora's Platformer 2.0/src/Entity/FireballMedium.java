package Entity;

import java.awt.Graphics2D;

import Entity.Doodad.FireballLargeExplosion;
import Main.Content;
import TileMap.TileMap;

public class FireballMedium extends Projectile
{
	protected FireballLargeExplosion fireballLargeExplosion;
	
	public FireballMedium(
			TileMap tileMap, 
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
				right, 																// Facing right
				up,  																// Holding up arrow
				down,  																// Holding down arrow
				angle,
				friendly, 															// Whether the spell is friendly or hostile
				60, 																// Projectile width
				60, 																// Projectile height
				60, 																// Explosion width
				60, 																// Explosion height
				60, 																// Collision width
				60, 																// Collision height
				7.6, 																// Projectile speed
				damage,																// Explosion damage
				60, 																// Explosion radius
				"FireballMedium"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.FireballMedium[0];
	}
	
		public void explode(TileMap tilemap, double x, double y)
		{
			fireballLargeExplosion = new FireballLargeExplosion(tileMap, x, y);
		}
		
		public void updateExplosion()
		{
			if(fireballLargeExplosion != null)
			{
				fireballLargeExplosion.animation.update();
				if(fireballLargeExplosion.animation.hasPlayedOnce())
				{
					fireballLargeExplosion.removeMe();
					remove = true;
				}
			}
		}
		
		public void drawExplosion(Graphics2D graphics)
		{
			fireballLargeExplosion.draw(graphics);
		}
}
