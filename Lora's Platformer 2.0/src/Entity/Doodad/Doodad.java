package Entity.Doodad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Entity.Animation;
import Entity.MapObject;

public class Doodad extends MapObject
{
	protected String spritePath;
	protected int[] numFrames;
	protected ArrayList<BufferedImage[]> sprites;
	
	
	
	public Doodad(
			TileMap tileMap, 
			double spawnX, 
			double spawnY,
			int width,
			int height,
			String spritePath,
			int[] numFrames
			)
	{
		super(tileMap);
		
		this.width = width;
		this.height = height;
		this.spritePath = spritePath;
		this.numFrames = numFrames;
		
		x = spawnX;
		y = spawnY;
		
		
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
		
		
		animation = new Animation();
		
		currentAction = 0;
		animation.setFrames(sprites.get(0));
		animation.setDelay(100);

		
	}
	
	
	public void update()
	{
		animation.update();
	}
	
	
	public void draw(Graphics2D graphics)
	{
		setMapPosition();
		
		graphics.drawImage(

		animation.getImage(),
		(int)(x + xmap - width / 2),
		(int)(y + ymap - height / 2),
		null
		);
	}
}
