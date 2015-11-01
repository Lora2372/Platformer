package Entity.Doodad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.Content;
import TileMap.TileMap;
import Entity.MapObject;
import Entity.Player.Player;

public class Doodad extends MapObject
{
	protected String spritePath;
	
	protected int[] animationState;
	protected int[] numFrames;
	
	protected BufferedImage[] portrait;
	
	protected BufferedImage[] sprites;
	
	protected boolean runOnce;
	protected boolean activatable;
	protected boolean active;
	protected boolean spent;
	
	protected String doodadType;
	
	public Doodad(
			TileMap tileMap, 
			double spawnX, 
			double spawnY,
			int width,
			int height,
			int collisionWidth,
			int collisionHeight,
			double fallSpeed,
			double maxFallSpeed,
			boolean untouchable,
			boolean invulnerable,
			boolean runOnce,
			boolean activatable,
			boolean active,
			int currentAction,
			String doodadType
			)
	{
		super(tileMap);
		
		this.width = width;
		this.height = height;
		this.untouchable = untouchable;
		this.invulnerable = invulnerable;
		this.runOnce = runOnce;
		this.activatable = activatable;
		this.active = active;
		this.collisionHeight = collisionHeight;
		this.collisionWidth = collisionWidth;
		this.fallSpeed = fallSpeed;
		this.maxFallSpeed = maxFallSpeed;
		this.doodadType = doodadType;
		locationX = spawnX;
		locationY = spawnY;
		
		facingRight = false;
		
		setPosition(spawnX, spawnY);
		this.currentAction = currentAction;
		setDoodad(currentAction);
		animation.setFrames(sprites);
		animation.setDelay(100);
		portrait = Content.PortraitSign[0];
		
	}
	
	public void setDoodad(int currentAction) { }
	
	public BufferedImage[] getPortrait() { return portrait; }
	
	public void interact(Player player)
	{
//		if(!active) active = true;
	}
	
	public void playSound() { }
	
	public boolean getActive() { return active; }
	
	public void update()
	{

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		
		
		if(!spent)
		{
			if(active)
			{
				if(currentAction!= 1)
				{
					currentAction = 1;
					setDoodad(currentAction);
					animation.setFrames(sprites);
					animation.setDelay(60);
					playSound();
				}
				if(animation.hasPlayedOnce())
				{
					if(currentAction != 2)
					{
						currentAction = 2;
						setDoodad(currentAction);
						animation.setFrames(sprites);
						animation.setDelay(1000);
						spent = true;
					}
				}
			}
		}
		
		if(animation.hasPlayedOnce() && runOnce)
		{
			removeMe = true;
		}
		else
		{
			animation.update();
		}
		
		
	}
		
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		super.draw(graphics);
	}

	public void setDoodadType(String newDoodadType)
	{
		doodadType = newDoodadType;
	}
	
	public String getDoodadType()
	{
		return doodadType;
	}
}
