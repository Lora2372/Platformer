package Entity.Projectile;

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
			boolean friendly
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
				40, 																// Collision width
				40, 																// Collision height
				5, 																	// Projectile speed
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
}
