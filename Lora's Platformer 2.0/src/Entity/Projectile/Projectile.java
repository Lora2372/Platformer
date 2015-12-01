package Entity.Projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Audio.JukeBox;
import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Projectile extends MapObject
{
	
	protected boolean hit;
	protected BufferedImage[] sprites;
	
	protected boolean friendly; 
	
	protected String projectileType;
	
	protected int damage;
	protected int manacost;
	
	protected int explosionRadius;
		
	protected double aim;
	
	protected MainMap mainMap;

	protected Unit owner;
	
	public Projectile(
			TileMap tileMap,
			MainMap mainMap,
			Unit owner,
			boolean right,
			boolean up,
			boolean down,
			double aim,
			boolean friendly,
			int projectileWidth, 
			int projectileHeight,
			int collisionWidth,
			int collisionHeight,
			double moveSpeed,
			String projectileType
			) 
	{
		super(tileMap);
		
		this.owner = owner;
		this.mainMap = mainMap;
		this.facingRight = right;
		this.up = up;
		this.down = down;
		this.friendly = friendly;
		this.width = projectileWidth;
		this.height = projectileHeight;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.moveSpeed = moveSpeed;
		this.projectileType = projectileType;
		this.aim = aim;
		this.rotation = aim;
		
		rotation = aim;
		
		this.directionX = Math.cos(aim) * moveSpeed;
		this.directionY = Math.sin(aim) * moveSpeed;
		
		rotaded = true;
		
		hit = false;		

		// Load sprites
		
		
		setProjectile();
		animation.setFrames(sprites);		
		animation.setDelay(70);
			
	}
	
	public int getCost() { return manacost; }
	
	public boolean getHit() { return hit; }
	
	public void setProjectile() { }
	
	public String getProjectileType() { return projectileType; }
	
	public boolean getFriendly() { return friendly; }
	public void setFriendly(boolean b) { friendly = b;};
	
	public boolean getFacing() { return facingRight;}
	public void setFacing(boolean b) { facingRight = b;}
	
	public double getDirectionX() { return directionX;}
	public double getDirectionY() { return directionY;}
	public void setDirection(double directionX, double directionY)
	{
		this.directionX = directionX;
		this.directionY = directionY;
		
		rotation += Math.PI;
	}
	
	
	// Functions that figures out whether or not the fireball has hit something.
	public void setHit()
	{
		if(hit) return;
		hit = true;
		
		JukeBox.play(projectileType + "Impact");
		
		explode();

		removeMe = true;
	}
	
	public void bounce()
	{
		JukeBox.play(projectileType + "Active");
		
		setFacing(!facingRight);
		setDirection(directionX * - 1.05, directionY * -1.05);
		setFriendly(!friendly);
	}
	
	public void update(ArrayList<Unit> characterList)
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if(locationX > tileMap.getWidth() || locationX < 0 || locationY < 0 || locationY > tileMap.getHeight())
		{
			setHit();
		}
		
		
		if(directionX == 0)
		{
			setHit();
		}
		
		animation.update();
	}
	
	public void explode()
	{

	}
	
	public void draw(Graphics2D graphics)
	{	
		setMapPosition();
			
		super.draw(graphics);
	}
}
