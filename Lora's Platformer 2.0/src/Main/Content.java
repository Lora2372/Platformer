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
	public static BufferedImage[][] ElectricballExplosion 	= load("/Sprites/Effects/ElectricballExplosion.png", 180, 180);
	
	// Projectiles
	public static BufferedImage[][] FireballLarge 			= load("/Sprites/Effects/FireballLarge.png", 60, 60);
	public static BufferedImage[][] FireballMedium			= load("/Sprites/Effects/FireballMediumNew.png", 60, 60);
	public static BufferedImage[][] FireballSmall 			= load("/Sprites/Effects/FireballSmall.png", 60, 60);
	public static BufferedImage[][] FireballSwirling 		= load("/Sprites/Effects/FireballSwirling.png", 60, 60);
	public static BufferedImage[][] FireballDouble 			= load("/Sprites/Effects/FireballDouble.png", 60, 60);
	public static BufferedImage[][] Electricball			= load("/Sprites/Effects/Electricball.png", 60, 60);
	
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

	// Foreground
	public static BufferedImage[][] GameOver				= load("/Foregrounds/GameOver.png", 544, 416);
	public static BufferedImage[][] ConversationGUI			= load("/Foregrounds/Conversation GUI.png", 454, 138);
	
	// Portraits
	public static BufferedImage[][] PortraitPlayer			=load("/Foregrounds/Portraits/PortraitPlayer.png", 94, 94);
	public static BufferedImage[][] PortraitSign			=load("/Foregrounds/Portraits/PortraitSign.png", 94, 94);
	public static BufferedImage[][] PortraitLiadrin			=load("/Foregrounds/Portraits/PortraitLiadrin.png", 94, 94);
	
	public static String[] mapMusic = new String[]
	{
		"Menu",
		"Level1",
		"Dungeon1"
	};
	
	public static  void loadContent()
	{		
		JukeBox.load("/Sound/jump.mp3", "jump");
		
		// Projectile sound effects
		JukeBox.load("/Sound/FireballLargeLaunch.mp3", "FireballLargeLaunch");
		JukeBox.load("/Sound/FireballSmallLaunch.mp3", "FireballSmallLaunch");	
		
		JukeBox.load("/Sound/EnergyballChargeUp.mp3", "EnergyballChargeUp");
		JukeBox.load("/Sound/ElectricballActive.mp3", "ElectricballActive");
		
		JukeBox.load("/Sound/ElectricballChargeUp.mp3", "ElectricballChargeUp");
		JukeBox.load("/Sound/ElectricballLaunch.mp3", "ElectricballLaunch");
		JukeBox.load("/Sound/ElectricballImpact.mp3", "ElectricballImpact");

		JukeBox.load("/Sound/FireballLargeImpact.mp3", "FireballLargeImpact");
		JukeBox.load("/Sound/FireballSmallImpact.mp3", "FireballSmallImpact");
		
		// Spell sound effect
		JukeBox.load("/Sound/Teleport.mp3", "teleport");
		
		
		// Music
		JukeBox.load("/Sound/Music/MusicMenu.mp3", "Menu");
		JukeBox.load("/Sound/Music/MusicLevel1.mp3", "Level1");
		JukeBox.load("/Sound/Music/MusicDungeon1.mp3", "Dungeon1");
		JukeBox.load("/Sound/Music/MysteriousConversation.mp3", "MysteriousConversation");
		JukeBox.load("/Sound/Music/MysteriousBattle.mp3", "MysteriousBattle");
		
		JukeBox.load("/Sound/Music/GameOver.mp3",  "GameOver");
		
		// Doodad sound effect
		JukeBox.load("/Sound/Doodads/OpenChestUncommon.mp3", "OpenChestUncommon");
		JukeBox.load("/Sound/Doodads/OpenChestCommon.mp3", "OpenChestCommon");
		JukeBox.load("/Sound/Doodads/OpenChestRare.mp3", "OpenChestRare");
		
		JukeBox.load("/Sound/Doodads/Close.mp3", "Close");


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
