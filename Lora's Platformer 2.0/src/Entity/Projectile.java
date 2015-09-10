package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Audio.JukeBox;

import Main.Content;
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
	protected int explosionRadius;
	protected String explosionSound;
		
	private boolean exploding;
	
	public Projectile(
			TileMap tileMap, 
			boolean right, 
			boolean up, 
			boolean down, 
			boolean friendly, 
			int projectileWidth, 
			int projectileHeight,
			int explosionWidth,
			int explosionHeight,
			int collisionWidth,
			int collisionHeight,
			double moveSpeed,
			int damage,
			int explosionRadius,
			String explosionSound
			) 
	{
		super(tileMap);
		
		
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
		
		
		if(right) directionX = this.moveSpeed;
		else directionX = -this.moveSpeed;
		
		if(down) directionY = this.moveSpeed;
		if(up) directionY = -this.moveSpeed;
		
		
		hit = false;		

		// Load sprites
		
		
		sprites = Content.FireballLarge[0];
		animation.setFrames(sprites);		
		animation.setDelay(70);
		
//		try
//		{
//			BufferedImage spritesheet = ImageIO.read(
//				getClass().getResourceAsStream(
//					this.projectilePath
//				)
//			);
//			
//			sprites = new BufferedImage[projectileParts];
//			for(int i = 0; i  < sprites.length; i++)
//			{
//				sprites[i] = spritesheet.getSubimage(
//						i * width,  
//						0,  
//						width,  
//						height
//					);
//			}
//			
//			animation = new Animation();
//			animation.setFrames(sprites);
//			animation.setDelay(70);
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}		
	}
	
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
	public void setHit(ArrayList<Character> characterList)
	{
		if(hit) return;
		hit = true;
				
		collisionWidth = explosionRadius;
		collisionHeight = explosionRadius;
		for(int i = 0; i < characterList.size(); i++)
		{
			Character character = characterList.get(i);
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
	
	public void update(ArrayList<Character> characterList)
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if(locationX > tileMap.getWidth() || locationX < 0 || locationY < 0 || locationY > tileMap.getHeight())
		{
			setHit(characterList);
		}
		
		
		if((directionX == 0 || ((down || up) && directionY == 0)) && !hit)
		{
			setHit(characterList);
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
