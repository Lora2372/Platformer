package Entity.Explosion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Explosion extends MapObject
{
	
	protected ArrayList<Unit> unitsHit;
	
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
		this.untouchable = false;
		this.locationX = locationX;
		this.locationY = locationY;
		this.facingRight = false;

		unitsHit = new ArrayList<Unit>();
		
		setExplosion();
		animation.setFrames(sprites);		
		animation.setDelay(70);
			
	}
	public void setExplosion() { }
	
	public String getExplosionType() { return explosionType; }
	
	public boolean getFriendly() { return friendly; }
	public void setFriendly(boolean b) { friendly = b;};
	
	
	
	public void update(ArrayList<Unit> characterList)
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
			
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit character = characterList.get(i);
			if(!unitsHit.contains(character))
			{
				if(character.getFriendly() != friendly)
					if(character.intersects(this))
					{
						unitsHit.add(character);
						
//						double distance(int x1, int y1, int x2, int y2) {
//						    int dx = x2 - x1;
//						    int dy = y2 - y1;
//						    return Math.sqrt(dx * dx + dy * dy);
//						}
//						
//						double distanceX = character.getLocationX() - locationX;
//						double distanceY = character.getLocationY() - locationY;
//						double  distance = Math.sqrt(distanceX * distanceX * + distanceY * distanceY);
						character.hit(damage);
					}
			}
		}
		
		
		animation.update();
		
		if(animation.hasPlayedOnce())
			removeMe = true;
	}
	
	public boolean interescts(MapObject object)
	{
		return false;
	}
	
	
	public void draw(Graphics2D graphics)
	{				
		super.draw(graphics);
	}
}