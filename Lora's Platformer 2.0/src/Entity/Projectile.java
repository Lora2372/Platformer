package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Audio.JukeBox;
import GameState.MainMap;
import TileMap.TileMap;

public class Projectile extends MapObject
{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	
	protected boolean friendly; 
	
	protected int explosionWidth;
	protected int explosionHeight;
	
	protected String projectileType;
	
	protected int damage;
	protected int manacost;
	
	protected int explosionRadius;
	
	protected String explosionSound;
	
	protected double aim;
	
	protected MainMap mainMap;
	
	private boolean exploding;
	
	public Projectile(
			TileMap tileMap,
			MainMap mainMap,
			boolean right,
			boolean up,
			boolean down,
			double aim,
			boolean friendly,
			int projectileWidth, 
			int projectileHeight,
			int explosionWidth,
			int explosionHeight,
			int collisionWidth,
			int collisionHeight,
			double moveSpeed,
			int damage,
			int manacost,
			int explosionRadius,
			String explosionSound
			) 
	{
		super(tileMap);
		
		this.mainMap = mainMap;
		this.facingRight = right;
		this.up = up;
		this.down = down;
		this.friendly = friendly;
		this.width = projectileWidth;
		this.height = projectileHeight;
		this.explosionWidth = explosionWidth;
		this.explosionHeight = explosionHeight;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.moveSpeed = moveSpeed;
		this.explosionRadius = explosionRadius;
		this.explosionSound = explosionSound;
		this.damage = damage;
		this.manacost = manacost;
		this.aim = aim;
		this.rotation = aim;
		
		
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
	}
	
	
	// Functions that figures out whether or not the fireball has hit something.
	public void setHit()
	{
		ArrayList<Unit> characterList = mainMap.getCharacterList();
		
		if(hit) return;
		hit = true;
				
		collisionWidth = explosionRadius;
		collisionHeight = explosionRadius;
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit character = characterList.get(i);
			if(character.getFriendly() != friendly)
			{
				if(this.intersects(character))
				{
					character.hit(damage);
				}
			}
		}
		
		JukeBox.play(explosionSound + "Impact");
		
		height = explosionHeight;
		width = explosionWidth;
		
		explode(tileMap, locationX, locationY);
		exploding = true;
		
		
		directionX = 0;
		directionY = 0;
	}
	
	public boolean shouldRemove() 
	{
		return remove; 
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
		
		if(!exploding) animation.update();
		
		else
		{
			// Look into merging all explosions into one class later, to prevent repeating code further...
			
			updateExplosion();
			
		}

	}
	
	public void explode(TileMap tilemap, double x, double y)
	{

	}
	
	public void updateExplosion()
	{
		
	}
	
	public void drawExplosion(Graphics2D graphics)
	{
		
	}
	
	public void draw(Graphics2D graphics)
	{	
		setMapPosition();
	
		if(exploding)
		{
			drawExplosion(graphics);
			return;
		}
		
		super.draw(graphics);
	}

}
