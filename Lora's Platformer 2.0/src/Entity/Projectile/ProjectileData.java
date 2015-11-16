package Entity.Projectile;

import java.util.HashMap;

import TileMap.TileMap;

public class ProjectileData 
{
	
	
	protected TileMap tileMap;
	
	public ProjectileData(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		projectileCost = new HashMap<String, Integer>();
		projectileCost.put(Projectiles.ArcaneBall.toString(), 20);
		projectileCost.put(Projectiles.ElectricBall.toString(), 20);
		projectileCost.put(Projectiles.FireBallSmall.toString(), 20);
		projectileCost.put(Projectiles.FireBallLarge.toString(), 40);
	}
	
	
	public static enum Projectiles
	{
		ArcaneBall,
		ElectricBall,
		FireBallLarge,
		FireBallSmall
	}
	
	public  static HashMap<String, Integer> projectileCost = new HashMap<String, Integer>();
	
}
