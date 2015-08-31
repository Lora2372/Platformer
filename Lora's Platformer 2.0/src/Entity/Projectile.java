package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.Audio;
import TileMap.TileMap;

public class Projectile extends MapObject
{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	protected BufferedImage[] hitSprites;
	
	protected boolean right; 
	protected boolean up; 
	protected boolean down; 
	protected boolean friendly; 
	protected int width; 
	protected int height;
	protected int explosionWidth;
	protected int explosionHeight;
	protected int collisionWidth;
	protected int collisionHeight;
	protected double moveSpeed;
	protected String projectilePath;
	protected String explosionPath;
	protected int explosionParts;
	protected int damage;
	protected int explosionRadius;
	protected String explosionSound;
	
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
			String projectilePath,
			int projectileParts,
			String explosionPath,
			int explosionParts,
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
		this.projectilePath = projectilePath;
		this.explosionPath = explosionPath;
		this.explosionRadius = explosionRadius;
		this.explosionParts = explosionParts;
		this.explosionSound = explosionSound;
		
		if(right) dx = this.moveSpeed;
		else dx = -this.moveSpeed;
		
		if(down) dy = this.moveSpeed;
		if(up) dy = -this.moveSpeed;
		
		
		hit = false;

		
		// Load sprites
		
		try
		{
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					this.projectilePath
				)
			);
			
			sprites = new BufferedImage[projectileParts];
			for(int i = 0; i  < sprites.length; i++)
			{
				sprites[i] = spritesheet.getSubimage(
						i * width,  
						0,  
						width,  
						height
					);
			}
			
			spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					this.explosionPath
				)
			);
			
			hitSprites = new BufferedImage[explosionParts];
			for(int i = 0; i < hitSprites.length; i++)
			{
				hitSprites[i] = spritesheet.getSubimage(
						i * explosionWidth, 
						0, 
						explosionWidth, 
						explosionHeight
					);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Functions that figures out whether or not the fireball has hit something.
	public void setHit()
	{
		if(hit) return;
		hit = true;
				
		
		try
		{
			Audio sound = new Audio();
			sound.playSound(explosionSound);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		height = explosionHeight;
		width = explosionWidth;
		
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		
		
		dx = 0;
		dy = 0;
	}
	
	public boolean shouldRemove() 
	{
		return remove; 
	}
	
	public void update()
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if(x > tileMap.getWidth() || x < 0 || y < 0 || y > tileMap.getHeight())
		{
			setHit();
		}
		
		
		if((dx == 0 || ((down || up) && dy == 0)) && !hit)
		{
			setHit();
		}
		
		animation.update();
		if(hit == true && animation.hasPlayedOnce())
		{
			remove = true;
		}
	}
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		if(facingRight)
		{
			graphics.drawImage(

			animation.getImage(),
			(int)(x + xmap - width / 2),
			(int)(y + ymap - height / 2),
			null
			);
		}
		else
		{
			graphics.drawImage(
					animation.getImage(),
					(int)(x + xmap - width / 2 + width),
					(int)(y + ymap - height / 2),
					-width,
					height,
					null
					);
			
			
		}

		
	}

}
