package Entity;



import TileMap.TileMap;

public class SmallFireball extends Projectile
{

	public SmallFireball(
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
				"/Sprites/Effects/smallFireball.png",	 								// Projectile path
				4, 																	// Projectile pictures
				"/Sprites/Effects/smallFireballExplosion.png", 							// Explosion path
				14, 																// Explosion parts
				damage,																// Explosion damage
				60, 																// Explosion radius
				"Resources/Sound/FireballImpact.wav"								// Explosion sound
			);
	}
		
}
