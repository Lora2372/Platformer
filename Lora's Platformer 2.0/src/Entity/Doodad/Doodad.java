package Entity.Doodad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Content;
import TileMap.TileMap;
import Entity.Animation;
import Entity.MapObject;

public class Doodad extends MapObject
{
	protected String spritePath;
	
	protected int[] animationState;
	protected int[] numFrames;
	
	protected BufferedImage[] portrait;
	
	protected ArrayList<BufferedImage[]> sprites;
	
	protected boolean runOnce;
	protected boolean removeMe;
	protected boolean activatable;
	protected boolean active;
	protected boolean spent;
	
	protected String name;
	
	public Doodad(
			TileMap tileMap, 
			double spawnX, 
			double spawnY,
			int width,
			int height,
			int collisionWidth,
			int collisionHeight,
			String spritePath,
			int[] animationState,
			int[] numFrames,
			boolean untouchable,
			boolean invulnerable,
			boolean runOnce,
			boolean activatable,
			boolean active
			)
	{
		super(tileMap);
		
		this.width = width;
		this.height = height;
		this.spritePath = spritePath;
		this.animationState = animationState;
		this.numFrames = numFrames;
		this.untouchable = untouchable;
		this.invulnerable = invulnerable;
		this.runOnce = runOnce;
		this.activatable = activatable;
		this.active = active;
		this.collisionHeight = collisionHeight;
		this.collisionWidth = collisionWidth;
		
		locationX = spawnX;
		locationY = spawnY;
		
		try{
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResource(
							 spritePath)
					);
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < numFrames.length; i++)
			{
				BufferedImage[] bufferedImage =
						new BufferedImage[numFrames[i]];
				for(int z = 0; z < numFrames[i]; z++)
				{
					bufferedImage[z] = spritesheet.getSubimage(
							z * width,
							i * height,
							width,
							height
							);
				}
				
				sprites.add(bufferedImage);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setPosition(spawnX, spawnY);
		
		animation = new Animation();
		
		portrait = Content.PortraitSign[0];
		
//		currentAction = 0;
//		animation.setFrames(sprites.get(0));
		currentAction = animationState[0];
		animation.setFrames(sprites.get(animationState[0]));
		animation.setDelay(100);

		
	}
	
	public BufferedImage[] getPortrait() { return portrait; }
	
	public void interact()
	{
		if(!active) active = true;
	}
	
	public void activateSound() { }
	
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
					animation.setFrames(sprites.get(animationState[1]));
					animation.setDelay(60);
					activateSound();
				}
				if(animation.hasPlayedOnce())
				{
					if(currentAction != animationState[2])
					{
						currentAction = animationState[2];
						animation.setFrames(sprites.get(animationState[2]));
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

	public void setName(String newName)
	{
		name = newName;
	}
	
	public String getName()
	{
		return name;
	}
}
