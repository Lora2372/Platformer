package Entity;



import TileMap.TileMap;

public class LargeFireball extends Projectile
{

	public LargeFireball(
			TileMap tileMap, 
			boolean right, 
			boolean up, 
			boolean down, 
			boolean friendly
		)
	{
		
		super(
				tileMap, 															//Tile map
				right, 																// Facing right
				up,  																// Holding up arrow
				down,  																// Holding down arrow
				friendly, 															// Whether the spell is friendly or hostile
				120, 																// Projectile width
				120, 																// Projectile height
				480, 																// Explosion width
				480, 																// Explosion height
				56, 																// Collision width
				56, 																// Collision height
				7.6, 																// Projectile speed
				"/Sprites/Effects/largeFireball.png", 								// Projectile path
				3, 																	// Projectile pictures
				"/Sprites/Effects/largeFireballExplosion.png", 						// Explosion path
				14, 																// Explosion parts
				3, 																	// Explosion damage
				240, 																// Explosion radius
				"Resources/Sound/FireballImpact.wav"								// Explosion sound
			);
	}
		
}
