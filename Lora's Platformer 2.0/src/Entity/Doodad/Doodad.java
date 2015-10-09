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
	protected boolean removeMe;
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
			boolean untouchable,
			boolean invulnerable,
			boolean runOnce,
			boolean activatable,
			boolean active,
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
		this.doodadType = doodadType;
		locationX = spawnX;
		locationY = spawnY;
		
		
		setPosition(spawnX, spawnY);
		currentAction = 0;
		setDoodad(currentAction);
		animation.setFrames(sprites);
		animation.setDelay(100);
		portrait = Content.PortraitSign[0];
		
	}
	
	public void setDoodad(int currentAction) { }
	
	public BufferedImage[] getPortrait() { return portrait; }
	
	public void interact(Player player)
	{
		if(!active) active = true;
	}
	
	public void activateSound() { }
	
	public boolean getActive() { return active; }
	
	public void update()
	{
		
		animation.update();
		
		if(!spent)
		{
			if(active)
			{
				if(currentAction!= 1)
				{
					currentAction = animationState[1];
					setDoodad(currentAction);
					animation.setFrames(sprites);
					animation.setDelay(60);
					activateSound();
				}
				if(animation.hasPlayedOnce())
				{
					if(currentAction != animationState[2])
					{
						currentAction = animationState[2];
						setDoodad(currentAction);
						animation.setFrames(sprites);
						animation.setDelay(1000);
						spent = true;
					}
				}
			}
		}
		
		if(!animation.hasPlayedOnce()) return;
		
		if(runOnce)
		{
			removeMe = true;
		}
		
	}
	
	public boolean removeMe()
	{
		return removeMe;
	}
	
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		graphics.drawImage(

		animation.getImage(),
		(int)(locationX + mapPositionX - width / 2),
		(int)(locationY + mapPositionY - height / 2),
		null
		);
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
