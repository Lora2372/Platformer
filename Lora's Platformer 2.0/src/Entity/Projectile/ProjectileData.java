package Entity.Projectile;

import java.util.HashMap;

public class ProjectileData 
{
	public ProjectileData()
	{
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
