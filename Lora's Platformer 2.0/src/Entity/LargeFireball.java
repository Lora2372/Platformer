package Entity;



import TileMap.TileMap;

public class LargeFireball extends Projectile
{

	public LargeFireball(
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
				"/Sprites/Effects/largeFireball.png", 								// Projectile path
				3, 																	// Projectile pictures
				damage,																// Explosion damage
				240, 																// Explosion radius
				"FireballLarge"														// Explosion sound
			);
	}
		
}
