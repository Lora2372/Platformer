package Entity;



import java.awt.Graphics2D;

import Entity.Doodad.FireballLargeExplosion;
import TileMap.TileMap;

public class FireballLarge extends Projectile
{
	protected FireballLargeExplosion fireballLargeExplosion;

	public FireballLarge(
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
				240, 																// Explosion width
				240, 																// Explosion height
				56, 																// Collision width
				56, 																// Collision height
				7.6, 																// Projectile speed
				damage,																// Explosion damage
				240, 																// Explosion radius
				"FireballLarge"														// Explosion sound
			);
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
