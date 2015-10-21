package Entity.Explosion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Audio.JukeBox;
import Entity.MapObject;
import Entity.Unit;
import GameState.MainMap;
import TileMap.TileMap;

public class Explosion extends MapObject
{
	
	protected boolean remove;
	protected BufferedImage[] sprites;
	
	protected boolean friendly; 
	
	protected int explosionWidth;
	protected int explosionHeight;
		
	protected int damage;

	protected String explosionType;
	
	protected MainMap mainMap;
		
	public Explosion(
			TileMap tileMap,
			MainMap mainMap,
			boolean friendly,
			double locationX,
			double locationY,
			int width,
			int height,
			int collisionWidth,
			int collisionHeight,
			int damage,
			String explosionType
			) 
	{
		super(tileMap);
		
		this.mainMap = mainMap;
		this.friendly = friendly;
		this.width = width;
		this.height = height;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.explosionType = explosionType;
		this.damage = damage;	
		this.unkillable = true;
		this.invulnerable = true;
		this.untouchable = true;
		this.locationX = locationX;
		this.locationY = locationY;
		this.facingRight = true;
		
		// Load sprites
		
		
		setExplosion();
		animation.setFrames(sprites);		
		animation.setDelay(70);
			
	}
	public void setExplosion() { }
	
	public String getExplosionType() { return explosionType; }
	
	public boolean getFriendly() { return friendly; }
	public void setFriendly(boolean b) { friendly = b;};
	
	
	// Functions that figures out whether or not the fireball has hit something.
	public void setHit()
	{
		ArrayList<Unit> characterList = mainMap.getCharacterList();
		
		
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit character = characterList.get(i);

			if(character.getFriendly() != friendly)
				if(character.intersects(this))
					character.hit(damage);
		}
		
		JukeBox.play(explosionType + "Impact");
		
		height = explosionHeight;
		width = explosionWidth;
		
	}
	
	public boolean shouldRemove() 
	{
		return remove; 
	}
	
	public void update(ArrayList<Unit> characterList)
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		animation.update();
		
		if(animation.hasPlayedOnce())
			remove = true;
	}
	
	public void draw(Graphics2D graphics)
	{				
		super.draw(graphics);
	}
}