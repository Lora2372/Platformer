package Entity.Projectile;

import Entity.Explosion.ArcaneBallExplosion;
import GameState.MainMap;
import Main.Content;
import TileMap.TileMap;

public class ArcaneBall extends Projectile
{
	
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
				90, 																// Projectile width
				90, 																// Projectile height
				60, 																// Collision width
				60, 																// Collision height
				5, 																	// Projectile speed
				damage,																// Explosion damage
				50,
				96, 																// Explosion radius
				"ArcaneBall"														// Explosion sound
			);
	}
	
	public void setProjectile()
	{
		sprites = Content.ArcaneBall[0];
	}
	
		public void explode()
		{
			ArcaneBallExplosion arcaneBallExplosion = new ArcaneBallExplosion(tileMap, mainMap, locationX, locationY, friendly);
			mainMap.addExplosion(arcaneBallExplosion);
		}
}