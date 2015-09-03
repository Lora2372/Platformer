package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Entity.Doodad.FireballLargeExplosion;
import Entity.Doodad.FireballSmallExplosion;
import Sound.SoundPlayer;
import TileMap.TileMap;

public class Projectile extends MapObject
{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	
	protected boolean friendly; 
	protected int explosionWidth;
	protected int explosionHeight;
	protected String projectilePath;
	protected int damage;
	protected int explosionRadius;
	protected String explosionSound;
	
	private HashMap<String, SoundPlayer> sfx;
	
	private FireballLargeExplosion fireballLargeExplosion;
	private FireballSmallExplosion fireballSmallExplosion;
	
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
			String projectilePath,
			int projectileParts,
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
		this.explosionRadius = explosionRadius;
		this.explosionSound = explosionSound;
		this.damage = damage;
		
		
		if(right) dx = this.moveSpeed;
		else dx = -this.moveSpeed;
		
		if(down) dy = this.moveSpeed;
		if(up) dy = -this.moveSpeed;
		
		
		hit = false;

		
		sfx = new HashMap<String, SoundPlayer>();
		sfx.put("FireballLargeImpact", new SoundPlayer("/Sound/FireballLargeImpact.mp3"));
		sfx.put("FireballSmallImpact", new SoundPlayer("/Sound/FireballSmallImpact.mp3"));
		
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
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean getFriendly() { return friendly; }

	
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
		
		sfx.get(explosionSound + "Impact").play();
		
		height = explosionHeight;
		width = explosionWidth;
		
		
		if(explosionSound == "FireballLarge") fireballLargeExplosion = new FireballLargeExplosion(tileMap, x, y);
		else if(explosionSound == "FireballSmall") fireballSmallExplosion = new FireballSmallExplosion(tileMap, x, y);
		exploding = true;
		
		
		dx = 0;
		dy = 0;
	}
	
	public boolean shouldRemove() 
	{
		return remove; 
	}
	
	public void update(ArrayList<Character> characterList)
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if(x > tileMap.getWidth() || x < 0 || y < 0 || y > tileMap.getHeight())
		{
			setHit(characterList);
		}
		
		
		if((dx == 0 || ((down || up) && dy == 0)) && !hit)
		{
			setHit(characterList);
		}
		
		if(!exploding) animation.update();
		
		else
		{
			// Look into merging all explosions into one class later, to prevent repeating code further...
			if(fireballLargeExplosion != null)
			{
				if(fireballLargeExplosion.animation.hasPlayedOnce())
				{
					fireballLargeExplosion = null;
					remove = true;
				}
				else
				{
					fireballLargeExplosion.update();
				}
			}
			else if(fireballSmallExplosion != null)
			{
				if(fireballSmallExplosion.animation.hasPlayedOnce())
				{
					fireballSmallExplosion = null;
					remove = true;
				}
				else
				{
					fireballSmallExplosion.update();
				}
			}
		}

	}
	
	public void draw(Graphics2D graphics)
	{	
		setMapPosition();
	
		if(exploding)
		{
			if(fireballSmallExplosion != null) fireballSmallExplosion.draw(graphics);
			else if(fireballLargeExplosion != null) fireballLargeExplosion.draw(graphics);
			return;
		}
		
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
