package Entity.Projectile;

import java.awt.Graphics2D;

import Entity.Explosion.ElectricBallExplosion;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ArcaneBall extends Projectile
{
	protected ElectricBallExplosion eletricBallExplosion;
	
	public ArcaneBall(
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
				96, 																// Projectile width
				96, 																// Projectile height
				60, 																// Explosion width
				60, 																// Explosion height
				80, 																// Collision width
				60, 																// Collision height
				5, 																	// Projectile speed
				damage,																// Explosion damage
				50,
				96, 																// Explosion radius
				"ElectricBall"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.ArcaneBall[0];
	}
	
		public void explode()
		{
			eletricBallExplosion = new ElectricBallExplosion(tileMap, mainMap, locationX, locationY, friendly);
			mainMap.addExplosion(eletricBallExplosion);
		}
		
//		public void updateExplosion()
//		{
//			if(eletricBallExplosion != null)
//			{
//				eletricBallExplosion.animation.update();
//				if(eletricBallExplosion.animation.hasPlayedOnce())
//				{
//					eletricBallExplosion.removeMe();
//					remove = true;
//				}
//			}
//		}
		
		public void drawExplosion(Graphics2D graphics)
		{
			eletricBallExplosion.draw(graphics);
		}
}