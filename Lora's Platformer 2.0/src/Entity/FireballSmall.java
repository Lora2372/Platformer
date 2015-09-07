package Entity;



import java.awt.Graphics2D;

import Entity.Doodad.FireballSmallExplosion;
import TileMap.TileMap;

public class FireballSmall extends Projectile
{
	protected FireballSmallExplosion fireballSmallExplosion;
	
	public FireballSmall(
			TileMap tileMap, 
			boolean right, 
			boolean up, 
			boolean down, 
			boolean friendly,
			int damage
		)
	{
		
		super(
				tileMap, 															//Tile map
				right, 																// Facing right
				up,  																// Holding up arrow
				down,  																// Holding down arrow
				friendly, 															// Whether the spell is friendly or hostile
				60, 																// Projectile width
				60, 																// Projectile height
				60, 																// Explosion width
				50, 																// Explosion height
				50, 																// Collision width
				80, 																// Collision height
				7.6, 																// Projectile speed
				damage,																// Explosion damage
				60, 																// Explosion radius
				"FireballSmall"														// Explosion sound
			);
	}
		public void explode(TileMap tilemap, double x, double y)
		{
			fireballSmallExplosion = new FireballSmallExplosion(tileMap, x, y);
		}
		
		public void updateExplosion()
		{
			if(fireballSmallExplosion != null)
			{
				fireballSmallExplosion.animation.update();
				if(fireballSmallExplosion.animation.hasPlayedOnce())
				{
					fireballSmallExplosion.removeMe();
					remove = true;
				}
			}
		}
		
		public void drawExplosion(Graphics2D graphics)
		{
			fireballSmallExplosion.draw(graphics);
		}
}
