package Main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Audio.JukeBox;

public class Content 
{
	// HEAT-O, EXPLOSION!!!!!1!
	public static BufferedImage[][] CartoonExplosion 		= load("/Sprites/Effects/CartoonExplosion.gif", 60, 60);
	public static BufferedImage[][] FireballLargeExplosion 	= load("/Sprites/Effects/FireballLargeExplosion.png", 240, 240);
	public static BufferedImage[][] FireballSmallExplosion 	= load("/Sprites/Effects/FireballSmallExplosion.png", 60, 60);
	
	// Projectiles
	public static BufferedImage[][] FireballLarge 			= load("/Sprites/Effects/FireballLarge.png", 60, 60);
	public static BufferedImage[][] FireballSmall 			= load("/Sprites/Effects/FireballSmall.png", 60, 60);
	public static BufferedImage[][] FireballSwirling 		= load("/Sprites/Effects/FireballSwirling.png", 60, 60);
	public static BufferedImage[][] FireballDouble 			= load("/Sprites/Effects/FireballDouble.png", 60, 60);
	
	// Characters
	public static BufferedImage[][] Slugger 				= load("/Sprites/Characters/slugger.gif", 60, 60);
	public static BufferedImage[][] Succubus 				= load("/Sprites/Characters/Succubus.png", 100, 100);
	public static BufferedImage[][] Player 					= load("/Sprites/Characters/Lora.png", 72, 120);
	
	
	//Doodads
	public static BufferedImage[][] Teleport 				= load("/Sprites/Effects/Teleport.png", 192, 192);
	public static BufferedImage[][] Sign 					= load("/Sprites/Doodads/Sign.png", 50, 50);
	public static BufferedImage[][] SignLeft 				= load("/Sprites/Doodads/SignLeft.png", 50, 50);
	public static BufferedImage[][] SignRight 				= load("/Sprites/Doodads/SignRight.png", 50, 50);
	public static BufferedImage[][] Mushroom1 				= load("/Sprites/Doodads/Mushroom01.png", 50, 50);
	public static BufferedImage[][] Mushroom2 				= load("/Sprites/Doodads/Mushroom02.png", 50, 50);
	public static BufferedImage[][] Torch 					= load("/Sprites/Doodads/Torch.png", 150, 150);
	public static BufferedImage[][] Waterfall 				= load("/Sprites/Doodads/Waterfall.png", 192, 192);


	
	public static  void loadContent()
	{
		System.out.println("Loading sound files");
		
		JukeBox.load("/Sound/jump.mp3", "jump");
		JukeBox.load("/Sound/FireballLargeLaunch.mp3", "FireballLargeLaunch");
		JukeBox.load("/Sound/FireballSmallLaunch.mp3", "FireballSmallLaunch");				
		JukeBox.load("/Sound/Teleport.mp3", "teleport");
		
		JukeBox.load("/Sound/FireballLargeImpact.mp3", "FireballLargeImpact");
		JukeBox.load("/Sound/FireballSmallImpact.mp3", "FireballSmallImpact");
		JukeBox.load("/Sound/Music/Battle9.mp3", "Battle9");
		JukeBox.load("/Sound/Music/GameOver.mp3",  "GameOver");
		

	}
	
	public static BufferedImage[][] load(String filePath, int width, int height)
	{
		BufferedImage[][] ret;
		try
		{
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(filePath));
			int newWidth = spritesheet.getWidth() / width;
			int newHeight = spritesheet.getHeight() / height;
			ret = new BufferedImage[newHeight][newWidth];
			
			for(int i = 0; i < newHeight; i++)
			{
				for(int j = 0; j < newWidth; j++)
				{
					ret[i][j] = spritesheet.getSubimage(j * width,  i * height,  width,  height);
				}
			}
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
