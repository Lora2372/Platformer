package Entity.Item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.MapObject;
import Entity.Doodad.Poff;
import Entity.Player.Player;
import Entity.Unit.Unit;
import TileMap.TileMap;

public class Item extends MapObject
{
	
	
	protected boolean inWorld; 				// If false, it's in an inventory/chest/whatever, otherwise in the world.
	
	protected int inventorySlotX;
	protected int inventorySlotY;
	
	
	protected boolean stackable;
	protected int stacks;
	
	protected MapObject owner; 				// Whatever is carrying it, assuming something/someone is.
	
	protected boolean consumable;
	
	protected Poff poff;
	
	protected String itemType;
	
	protected boolean activatable;
	
	protected BufferedImage[] sprites;
	
	public Item(
			TileMap tileMap,
			boolean inWorld,
			int width,
			int height,
			int collisionWidth,
			int collisionHeight,
			double fallSpeed,
			double maxFallSpeed,
			double locationX,
			double locationY,
			MapObject owner,
			boolean stackable,
			int stacks,
			boolean consumable,
			String itemType
			) 
	{
		super(tileMap);
		
		this.inWorld = inWorld;
		
		this.width = width;
		this.height = height;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.fallSpeed = fallSpeed;
		this.maxFallSpeed = maxFallSpeed;
		
		this.untouchable = false;
		this.invulnerable = true;
		this.unkillable = true;
		this.activatable = true;
		
		
		if(inWorld)
		{
			this.locationX = locationX;
			this.locationY = locationY;
		}
		else
		{
			this.owner = owner;
		}
		
		this.stackable = stackable;
		
		
		if(stackable)
		{
			this.stacks = stacks;
		}
		else
		{
			this.stacks = 1;
		}
		
		this.consumable = consumable;
		this.itemType = itemType;
		
		setItem();
		animation.setFrames(sprites);
		animation.setDelay(100);
		
	}

	
	public boolean getInWorld() { return inWorld; }
	public void setInWorld(boolean inWorld) { this.inWorld = inWorld; }
	
	
	public MapObject getOwner() { return owner; }
	public void setOwner(MapObject newOwner) { owner = newOwner; }
	
	
	public boolean getStackable() { return stackable; }
	
	
	public int getStacks() { return stacks; }
	public void setStacks(int stacks) { this.stacks = stacks; }
	
	
	public boolean getConsumable() { return consumable; }
	
	
	public String getItemType() { return itemType; }
	
	public void setItem() { }
	
	public BufferedImage[] getSprites() { return sprites; }
	
	public void update()
	{
		if(!inWorld)
		{
			return;
		}
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(initializeSpawning)
		{
			poff = new Poff(tileMap, locationX, locationY);
			initializeSpawning = false;
			spawning = true;
		}
		
		
		if(poff != null && !poff.getAnimation().hasPlayedOnce())
		{
			poff.update();
		}
		
		if(spawning && poff.getAnimation().hasPlayedOnce())
		{
			poff = null;
			spawning = false;
		}
		
		if(locationX > tileMap.getWidth() || locationX < 0 || locationY > tileMap.getHeight())
		{
			setPosition(spawnX, spawnY);
			initializeSpawning = true;
			directionX = 0;
			directionY = 0;
		}
		
		animation.update();
	}
	
	public void interact(Player player)
	{
		player.getInventory().addItem(this);
		owner = player;
	}
	
	public void playSound() { }
	
	public void use(Unit user)
	{
		playSound();
		if(consumable)
		{
			stacks--;
			if(stacks <= 0)
			{
				removeMe = true;			
				owner.getInventory().removeItem(this);
			}
		}
	}
	
	
	public void draw(Graphics2D graphics)
	{
		if(!inWorld)
		{
			return;
		}
		
		setMapPosition();
		
		if(poff != null)
		{
			poff.draw(graphics);
		}
		
		if(spawning) return;
		
		super.draw(graphics);
		
	}
	

	
	
	
}
