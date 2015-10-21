package Entity.Projectile;

import java.awt.Graphics2D;

import Entity.Explosion.ElectricBallExplosion;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ElectricBall extends Projectile
{
	protected ElectricBallExplosion electricBallExplosion;
	
	public ElectricBall(
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
				60, 																// Projectile width
				60, 																// Projectile height
				60, 																// Explosion width
				60, 																// Explosion height
				40, 																// Collision width
				40, 																// Collision height
				5, 																	// Projectile speed
				damage,																// Explosion damage
				50,
				60, 																// Explosion radius
				"ElectricBall"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.ElectricBall[0];
	}
	
	public void explode()
	{
		electricBallExplosion = new ElectricBallExplosion(tileMap, mainMap, locationX, locationY, friendly);
		mainMap.addExplosion(electricBallExplosion);
	}
		
//		public void updateExplosion()
//		{
//			if(electricBallExplosion != null)
//			{
//				electricBallExplosion.animation.update();
//				if(electricBallExplosion.animation.hasPlayedOnce())
//				{
//					electricBallExplosion.removeMe();
//					remove = true;
//				}
//			}
//		}
		
		public void drawExplosion(Graphics2D graphics)
		{
			electricBallExplosion.draw(graphics);
		}
}
