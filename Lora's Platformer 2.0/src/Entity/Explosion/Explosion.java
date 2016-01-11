package Entity.Explosion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Explosion extends MapObject
{
   
    protected ArrayList<Unit> unitsHit;
   
    protected BufferedImage[] sprites;
   
    protected boolean friendly;
   
    protected int explosionWidth;
    protected int explosionHeight;
       
    protected int damage;
    protected Content.damageTypes damageType;
    protected String explosionType;
   
    protected MainMap mainMap;
   
    protected Unit owner;
   
    public Explosion
    	(
            TileMap tileMap,
            MainMap mainMap,
            Unit owner,
            boolean friendly,
            double locationX,
            double locationY,
            int width,
            int height,
            int collisionWidth,
            int collisionHeight,
            int damage,
            Content.damageTypes damageType,
            String explosionType
    	)
    {
        super(tileMap);
       
        this.mainMap = mainMap;
        this.owner = owner;
        this.friendly = friendly;
        this.width = width;
        this.height = height;
        this.collisionWidth = collisionWidth;
        this.collisionHeight = collisionHeight;
        this.explosionType = explosionType;
        this.damage = damage;
        this.damageType = damageType;
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
		try
		{
			checkTileMapCollision();
		}
		catch(Exception exception)
		{
			System.out.println("Crash at: " + explosionType);
			exception.printStackTrace();
		}
        setPosition(xtemp, ytemp);
               
        for(int i = 0; i < characterList.size(); i++)
        {
            Unit character = characterList.get(i);
            if(!unitsHit.contains(character))
            {
                if(character.getFriendly() != friendly || owner == null)
                {
                    if(character.intersects(this))
                    {
                        unitsHit.add(character);
                       
                        double characterLocationX = character.getLocationX();
                        double characterLocationY = character.getLocationY();
                       
                        double distance = Math.sqrt((characterLocationX - locationX) * (characterLocationX - locationX) + (characterLocationY - locationY)*(characterLocationY - locationY));
                       
                        double distanceDamageMultiplier = 1 - ( (distance - 50) / ( (width + height) / 2 ) );       
                       
                        if(distanceDamageMultiplier < 0)
                        {
                            System.out.println("distanceDamageMultiplier was negative");
                            distanceDamageMultiplier *= -1;
                        }
                        
                        double bonusDamage = 0;
                        
                        if(owner != null)
                        {
                        	bonusDamage = owner.getBonusDamageMagical();
                        }
                        
                        character.hit( (damage + bonusDamage ) * distanceDamageMultiplier, damageType, owner);
                    }
                }
            }
        }
       
       
        animation.update();
       
        if(animation.hasPlayedOnce())
        {
            removeMe = true;
        }
    }
   
    public void draw(Graphics2D graphics)
    {               
		try
		{
			super.draw(graphics);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
    }
}