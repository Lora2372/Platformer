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
   
    protected Unit owner;
   
    public Explosion(
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
                {
                    if(character.intersects(this))
                    {
                        unitsHit.add(character);
                       
                        double characterLocationX = character.getLocationX();
                        double characterLocationY = character.getLocationY();
                       
                        double distance = Math.sqrt((characterLocationX - locationX) * (characterLocationX - locationX) + (characterLocationY - locationY)*(characterLocationY - locationY));
                       

                       
                        double distanceDamageMultiplier = 1 - ( (distance - 60) / ( (width + height) / 2 ) );       
                       
                        if(distanceDamageMultiplier < 0)
                        {
                            System.out.println("distanceDamageMultiplier was negative");
                            distanceDamageMultiplier *= -1;
                        }
                       
                        System.out.println("distanceDamageMultiplier: " + distanceDamageMultiplier);
                       
                        character.hit( (damage + owner.getBonusDamageMagical() ) * distanceDamageMultiplier, owner);
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
        super.draw(graphics);
    }
}