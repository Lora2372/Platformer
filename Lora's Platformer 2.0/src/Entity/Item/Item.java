package Entity.Item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entity.MapObject;
import Entity.Doodad.Poff;
import Entity.Player.Player;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class Item extends MapObject
{
	
	protected MainMap mainMap;
	
	protected boolean inWorld; 				// If false, it's in an inventory/chest/whatever, otherwise in the world.
	
	protected int inventorySlotX;
	protected int inventorySlotY;
	
	
	protected boolean stackable;
	protected int stacks;
	
	protected MapObject owner; 				// Whatever is carrying it, assuming something/someone is.
	
	protected boolean consumable;
	protected boolean expendable;
	
	protected Poff poff;
	
	protected String itemType;
	protected String descriptionName;
	protected String description;
	
	protected BufferedImage[] portrait;
	
	protected boolean activatable;
	
	protected BufferedImage[] sprites;
	
	public Item
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld,
			int width,
			int height,
			int collisionWidth,
			int collisionHeight,
			double stopSpeed,
			double fallSpeed,
			double maxFallSpeed,
			double locationX,
			double locationY,
			MapObject owner,
			boolean stackable,
			int stacks,
			boolean consumable,
			boolean expendable,
			String itemType,
			String descriptionName,
			String description
		) 
	{
		super(tileMap);
		
		this.mainMap = mainMap;
		
		this.inWorld = inWorld;
		
		this.width = width;
		this.height = height;
		this.collisionWidth = collisionWidth;
		this.collisionHeight = collisionHeight;
		this.stopSpeed = stopSpeed;
		this.fallSpeed = fallSpeed;
		this.maxFallSpeed = maxFallSpeed;
		
		this.untouchable = false;
		this.invulnerable = true;
		this.unkillable = true;
		this.activatable = true;
		
		this.descriptionName = descriptionName;
		this.description = description;
		
		
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
		this.expendable = expendable;
		this.itemType = itemType;
		
		setItem();
		animation.setFrames(sprites);
		animation.setDelay(100);
		
	}

	public void drop()
	{
		locationX = owner.getLocationX();
		locationY = owner.getLocationY();
		inWorld = true;
		
		int maxX = 5;
		int minX = -5;
		
		int maxY = -4;
		int minY = -8;
		
		directionX = mainMap.RNG(minX, maxX);
		directionY = mainMap.RNG(minY, maxY);
		
		directionX = directionX < 2 && directionX > 0 ? 2 : directionX;
		directionX = directionX > -2 && directionX <= 0 ? -2 : directionX;

		setOwner(null);
		
	}
	
	public boolean getInWorld() { return inWorld; }
	public void setInWorld(boolean inWorld) { this.inWorld = inWorld; }
	
	
	public MapObject getOwner() { return owner; }
	public void setOwner(MapObject newOwner) { owner = newOwner; }
	
	
	public boolean getStackable() { return stackable; }
	
	
	public int getStacks() { return stacks; }
	public void setStacks(int stacks) { this.stacks = stacks; }
	
	
	public boolean getConsumable() { return consumable; }
	public boolean getExpendable() { return expendable; }
	
	public String getItemType() { return itemType; }
	
	public String getDescriptionName() { return descriptionName; }
	
	public String getDescription() { return description; }
	
	public BufferedImage[] getPortrait() { return portrait;}
	
	public void setItem() { }
	
	public BufferedImage[] getSprites() { return sprites; }
	
	public void update()
	{
		if(!inWorld)
		{
			return;
		}
		getNextPosition();
		try
		{
			checkTileMapCollision();
		}
		catch(Exception exception)
		{
			System.out.println("Crash at: " + itemType);
			exception.printStackTrace();
		}
		setPosition(xtemp, ytemp);
		
		if(initializeSpawning)
		{
			poff = new Poff(tileMap, mainMap, locationX, locationY);
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
			setPosition(spawnLocationX, spawnLocationY);
			initializeSpawning = true;
			directionX = 0;
			directionY = 0;
		}
		
		animation.update();
	}
	
	public void interact(Player player)
	{
		playSound();
		player.getInventory().addItem(this);
		owner = player;
	}
	
	public void playSound() { }
	
	public void use(Unit user)
	{
		if(consumable || expendable)
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
