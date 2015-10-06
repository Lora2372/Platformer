package Main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Audio.JukeBox;

public class Content 
{
	// HEAT-O, EXPLOSION!!!!!1!
	public static BufferedImage[][] CartoonExplosion 		= load("/Art/Sprites/Effects/CartoonExplosion.gif", 60, 60);
	public static BufferedImage[][] FireballLargeExplosion 	= load("/Art/Sprites/Effects/FireballLargeExplosion.png", 240, 240);
	public static BufferedImage[][] FireballSmallExplosion 	= load("/Art/Sprites/Effects/FireballSmallExplosion.png", 60, 60);
	public static BufferedImage[][] ElectricballExplosion 	= load("/Art/Sprites/Effects/ElectricballExplosion.png", 180, 180);
	
	// Projectiles
	public static BufferedImage[][] FireballLarge 			= load("/Art/Sprites/Effects/FireballLarge.png", 60, 60);
	public static BufferedImage[][] FireballMedium			= load("/Art/Sprites/Effects/FireballMediumNew.png", 60, 60);
	public static BufferedImage[][] FireballSmall 			= load("/Art/Sprites/Effects/FireballSmall.png", 45, 45);
	public static BufferedImage[][] FireballSwirling 		= load("/Art/Sprites/Effects/FireballSwirling.png", 60, 60);
	public static BufferedImage[][] FireballDouble 			= load("/Art/Sprites/Effects/FireballDouble.png", 60, 60);
	public static BufferedImage[][] Electricball			= load("/Art/Sprites/Effects/Electricball.png", 60, 60);
	public static BufferedImage[][] Arcaneball				= load("/Art/Sprites/Effects/Arcaneball.png", 96, 96);
	
	// Characters
	public static BufferedImage[][] Slugger 				= load("/Art/Sprites/Characters/slugger.gif", 60, 60);
	public static BufferedImage[][] Succubus 				= load("/Art/Sprites/Characters/Succubus.png", 100, 100);
	public static BufferedImage[][] Player 					= load("/Art/Sprites/Characters/Lora.png", 72, 120);
	
	
	//Doodads
	public static BufferedImage[][] Teleport 				= load("/Art/Sprites/Effects/Teleport.png", 192, 192);
	public static BufferedImage[][] Sign 					= load("/Art/Sprites/Doodads/Sign.png", 50, 50);
	public static BufferedImage[][] SignLeft 				= load("/Art/Sprites/Doodads/SignLeft.png", 50, 50);
	public static BufferedImage[][] SignRight 				= load("/Art/Sprites/Doodads/SignRight.png", 50, 50);
	public static BufferedImage[][] Mushroom1 				= load("/Art/Sprites/Doodads/Mushroom01.png", 50, 50);
	public static BufferedImage[][] Mushroom2 				= load("/Art/Sprites/Doodads/Mushroom02.png", 50, 50);
	public static BufferedImage[][] Torch 					= load("/Art/Sprites/Doodads/Torch.png", 150, 150);
	public static BufferedImage[][] Waterfall 				= load("/Art/Sprites/Doodads/Waterfall.png", 192, 192);

	// Foreground
	public static BufferedImage[][] GameOver				= load("/Art/HUD/Foregrounds/GameOver.png", 544, 416);
	public static BufferedImage[][] ConversationGUI			= load("/Art/HUD/Foregrounds/Conversation GUI.png", 454, 138);
	
	// Portraits
	public static BufferedImage[][] PortraitPlayer			=load("/Art/HUD/Portraits/PortraitPlayer.png", 94, 94);
	public static BufferedImage[][] PortraitSign			=load("/Art/HUD/Portraits/PortraitSign.png", 94, 94);
	public static BufferedImage[][] PortraitLiadrin			=load("/Art/HUD/Portraits/PortraitLiadrin.png", 94, 94);
	
	public static enum mapMusic 
	{ 
		Menu,
		Level1,
		Dungeon1,
		MysteriousBattle,
		MysteriousConversation,
		DeepWoods
	}
	
	
	public static  void loadContent()
	{				
				
		InputStream inputStream = Content.class.getResourceAsStream("/Sound/Character Sounds");

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String soundFile;
		String folder;
		
		try 
		{
			while ((folder = reader.readLine()) != null)
			{
				InputStream inputStreamFile = Content.class.getResourceAsStream("/Sound/Character Sounds/" + folder);
				BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStreamFile));
				while((soundFile = fileReader.readLine()) != null )
				{
					String tempString = (soundFile.substring(soundFile.length()-4, soundFile.length()));
					if(tempString.equals(".mp3"))
					{	
						JukeBox.load("/Sound/Character Sounds/" + folder + "/" + soundFile, folder + soundFile.substring(0, soundFile.length() - 4));	
					}
				}
				fileReader.close();
			}
			
			reader.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Projectile sound effects
		JukeBox.load("/Sound/Spell Effects/FireballLargeLaunch.mp3", "FireballLargeLaunch");
		JukeBox.load("/Sound/Spell Effects/FireballSmallLaunch.mp3", "FireballSmallLaunch");	
		
		JukeBox.load("/Sound/Spell Effects/EnergyballChargeUp.mp3", "EnergyballChargeUp");
		JukeBox.load("/Sound/Spell Effects/ElectricballActive.mp3", "ElectricballActive");
		
		JukeBox.load("/Sound/Spell Effects/ElectricballChargeUp.mp3", "ElectricballChargeUp");
		JukeBox.load("/Sound/Spell Effects/ElectricballLaunch.mp3", "ElectricballLaunch");
		JukeBox.load("/Sound/Spell Effects/ElectricballImpact.mp3", "ElectricballImpact");
		
		JukeBox.load("/Sound/Spell Effects/FireballLargeImpact.mp3", "FireballLargeImpact");
		JukeBox.load("/Sound/Spell Effects/FireballSmallImpact.mp3", "FireballSmallImpact");
		
		// Spell sound effect
		JukeBox.load("/Sound/Spell Effects/Teleport.mp3", "teleport");
		
		// Music
		JukeBox.load("/Sound/Music/Menu.mp3", "Menu");
		JukeBox.load("/Sound/Music/LorasCavern.mp3", "Level1");
		JukeBox.load("/Sound/Music/MysteriousDungeon.mp3", "Dungeon1");
		JukeBox.load("/Sound/Music/MysteriousConversation.mp3", "MysteriousConversation");
		JukeBox.load("/Sound/Music/MysteriousBattle.mp3", "MysteriousBattle");
		JukeBox.load("/Sound/Music/DeepWoods.mp3", "DeepWoods");
		
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
