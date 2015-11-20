package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;
import Audio.JukeBox;

public class Content 
{
	// HEAT-O, EXPLOSION!!!!!1!
	public static BufferedImage[][] CartoonExplosion 		= load("/Art/Sprites/Effects/CartoonExplosion.gif", 60, 60);
	public static BufferedImage[][] FireBallLargeExplosion 	= load("/Art/Sprites/Effects/FireBallLargeExplosion.png", 512, 512);
	public static BufferedImage[][] FireBallSmallExplosion 	= load("/Art/Sprites/Effects/FireBallLargeExplosion.png", 512, 512);
	public static BufferedImage[][] ElectricBallExplosion 	= load("/Art/Sprites/Effects/ElectricBallExplosion.png", 180, 180);
	public static BufferedImage[][] ArcaneBallExplosion		= load("/Art/Sprites/Effects/ArcaneBallExplosion.png", 512, 512);
	
	// Projectiles
	public static BufferedImage[][] FireBallLarge 			= load("/Art/Sprites/Effects/FireBallLarge.png", 90, 90);
	public static BufferedImage[][] FireBallMedium			= load("/Art/Sprites/Effects/FireBallMediumNew.png", 60, 60);
	public static BufferedImage[][] FireBallSmall 			= load("/Art/Sprites/Effects/FireBallSmall.png", 35, 35);
	public static BufferedImage[][] FireBallSwirling 		= load("/Art/Sprites/Effects/FireBallSwirling.png", 60, 60);
	public static BufferedImage[][] FireBallDouble 			= load("/Art/Sprites/Effects/FireBallDouble.png", 60, 60);
	public static BufferedImage[][] ElectricBall			= load("/Art/Sprites/Effects/ElectricBall.png", 60, 60);
	public static BufferedImage[][] ElectricBallChargeUp	= load("/Art/Sprites/Effects/ElectricBallChargeUp.png", 60, 60);
	public static BufferedImage[][] ArcaneBall				= load("/Art/Sprites/Effects/ArcaneBall.png", 90, 90);

	
	// Characters
	public static BufferedImage[][] Slugger 				= load("/Art/Sprites/Characters/slugger.gif", 60, 60);
	public static BufferedImage[][] Succubus 				= load("/Art/Sprites/Characters/Succubus.png", 100, 100);
	public static BufferedImage[][] Player 					= load("/Art/Sprites/Characters/Lora.png", 72, 120);
	
	
	//Doodads
	public static BufferedImage[][] Sign 					= load("/Art/Sprites/Doodads/Sign.png", 50, 50);
	public static BufferedImage[][] SignLeft 				= load("/Art/Sprites/Doodads/SignLeft.png", 50, 50);
	public static BufferedImage[][] SignRight 				= load("/Art/Sprites/Doodads/SignRight.png", 50, 50);
	public static BufferedImage[][] Mushroom1 				= load("/Art/Sprites/Doodads/Mushroom01.png", 50, 50);
	public static BufferedImage[][] Mushroom2 				= load("/Art/Sprites/Doodads/Mushroom02.png", 50, 50);
	public static BufferedImage[][] Torch 					= load("/Art/Sprites/Doodads/Torch.png", 150, 150);
	public static BufferedImage[][] Waterfall 				= load("/Art/Sprites/Doodads/Waterfall.png", 192, 192);
	public static BufferedImage[][] ChestCommonClosed 		= load("/Art/Sprites/Doodads/ChestCommonClosed.png", 60, 60);
	public static BufferedImage[][] ChestCommonOpening 		= load("/Art/Sprites/Doodads/ChestCommonOpening.png", 60, 60);
	public static BufferedImage[][] ChestCommonOpened 		= load("/Art/Sprites/Doodads/ChestCommonOpened.png", 60, 60);
	public static BufferedImage[][] ChestUncommonClosed 	= load("/Art/Sprites/Doodads/ChestUncommonClosed.png", 60, 60);
	public static BufferedImage[][] ChestUncommonOpening 	= load("/Art/Sprites/Doodads/ChestUncommonOpening.png", 60, 60);
	public static BufferedImage[][] ChestUncommonOpened 	= load("/Art/Sprites/Doodads/ChestUncommonOpened.png", 60, 60);
	public static BufferedImage[][] ChestRareClosed 		= load("/Art/Sprites/Doodads/ChestRareClosed.png", 60, 60);
	public static BufferedImage[][] ChestRareOpening 		= load("/Art/Sprites/Doodads/ChestRareOpening.png", 60, 60);
	public static BufferedImage[][] ChestRareOpened 		= load("/Art/Sprites/Doodads/ChestRareOpened.png", 60, 60);
	public static BufferedImage[][] CaveEntrance			= load("/Art/Sprites/Doodads/CaveEntrance.png", 100, 96);
	public static BufferedImage[][] Shrine					= load("/Art/Sprites/Doodads/Shrine.png", 146, 156);
	public static BufferedImage[][] StatueSave				= load("/Art/Sprites/Doodads/StatueSave.png", 157, 149);
	public static BufferedImage[][] KeyUncommon				= load("/Art/Sprites/Doodads/KeyUncommon.png", 120, 120); 
	public static BufferedImage[][] KeyRare					= load("/Art/Sprites/Doodads/KeyRare.png", 120, 120); 
	public static BufferedImage[][] KeyBoss					= load("/Art/Sprites/Doodads/KeyBoss.png", 120, 120); 
	public static BufferedImage[][] PotionHealth			= load("/Art/Sprites/Doodads/PotionHealth.png", 60, 60); 
	public static BufferedImage[][] PotionMana				= load("/Art/Sprites/Doodads/PotionMana.png", 60, 60); 
	public static BufferedImage[][] PotionStamina			= load("/Art/Sprites/Doodads/PotionStamina.png", 60, 60); 
	public static BufferedImage[][] CoinSilver				= load("/Art/Sprites/Doodads/CoinSilver.png", 60, 60);
	public static BufferedImage[][] CoinGold				= load("/Art/Sprites/Doodads/CoinGold.png", 60, 60);
	public static BufferedImage[][] DoorBossClosed				= load("/Art/Sprites/Doodads/DoorBossClosed.png", 58, 63);
	public static BufferedImage[][] DoorBossOpening				= load("/Art/Sprites/Doodads/DoorBossOpening.png", 58, 63);
	public static BufferedImage[][] DoorBossOpened				= load("/Art/Sprites/Doodads/DoorBossOpened.png", 58, 63);
	public static BufferedImage[][] Portal					= load("/Art/Sprites/Doodads/Portal.png", 102, 143);
	public static BufferedImage[][] DoorVillageSquareClosed = load("/Art/Sprites/Doodads/DoorVillageSquareClosed.png", 120, 120);
	public static BufferedImage[][] DoorVillageSquareOpening = load("/Art/Sprites/Doodads/DoorVillageSquareOpening.png", 120, 120);
	public static BufferedImage[][] DoorVillageSquareOpened = load("/Art/Sprites/Doodads/DoorVillageSquareOpened.png", 120, 120);
	public static BufferedImage[][] CampFire				= load("/Art/Sprites/Doodads/CampFire.png", 64, 64);
	public static BufferedImage[][] LeverOpened				= load("/Art/Sprites/Doodads/LeverOpened.png", 26, 29);
	public static BufferedImage[][] LeverClosed				= load("/Art/Sprites/Doodads/LeverClosed.png", 26, 29);
	public static BufferedImage[][] LeverOpening				= load("/Art/Sprites/Doodads/LeverOpening.png", 26, 29);
	public static BufferedImage[][] LeverClosing				= load("/Art/Sprites/Doodads/LeverClosing.png", 26, 29);
	
	
	
	//Effects
	public static BufferedImage[][] Teleport 				= load("/Art/Sprites/Effects/Teleport.png", 192, 192);
	public static BufferedImage[][] MagicShield				= load("/Art/Sprites/Effects/MagicShield.png", 192, 192);
	public static BufferedImage[][] Poff					= load("/Art/Sprites/Effects/Poff.png", 512, 512);
	
	// Foreground
	public static BufferedImage[][] GameOver				= load("/Art/HUD/Foregrounds/GameOver.png", 544, 416);
	public static BufferedImage[][] ConversationGUI			= load("/Art/HUD/Foregrounds/Conversation GUI.png", 454, 138);
	public static BufferedImage[][] ConversationGUIEndConversation = load("/Art/HUD/Foregrounds/ConversationGUIEndConversation.png", 206, 35);
	public static BufferedImage[][] InventorySquare			= load("/Art/HUD/Foregrounds/InventorySquare.png", 60, 60);
	public static BufferedImage[][] InventoryBackground		= load("/Art/HUD/Foregrounds/InventoryBackground.png", 424, 348);
	public static BufferedImage[][] OptionConfirm			= load("/Art/HUD/Foregrounds/OptionConfirm.png", 60, 60);
	public static BufferedImage[][] OptionDeny				= load("/Art/HUD/Foregrounds/OptionDecline.png", 60, 60);
	public static BufferedImage[][] OptionBackground		= load("/Art/HUD/Foregrounds/OptionBackground.png", 424, 348);
	
//	public static BufferedImage[][] BossHealthBarFrame		= load("/Art/HUD/Bars/BossHealthBarFrame.png")
	public static BufferedImage bossBar;
	public static BufferedImage bossHealthBar;
	
	// Portraits
	public static BufferedImage[][] PortraitPlayer			=load("/Art/HUD/Portraits/PortraitPlayer.png", 94, 94);
	public static BufferedImage[][] PortraitSuccubus		=load("/Art/HUD/Portraits/PortraitSuccubus.png", 94, 94);
	
	public static BufferedImage[][] PortraitCampFire		=load("/Art/HUD/Portraits/PortraitCampFire.png", 94, 94);
	public static BufferedImage[][] PortraitChestCommon		=load("/Art/HUD/Portraits/PortraitChestCommon.png", 94, 94);
	public static BufferedImage[][] PortraitChestUncommon	=load("/Art/HUD/Portraits/PortraitChestUncommon.png", 94, 94);
	public static BufferedImage[][] PortraitChestRare		=load("/Art/HUD/Portraits/PortraitChestRare.png", 94, 94);
	public static BufferedImage[][] PortraitKeyUncommon		=load("/Art/HUD/Portraits/PortraitKeyUncommon.png", 94, 94);
	public static BufferedImage[][] PortraitKeyRare			=load("/Art/HUD/Portraits/PortraitKeyRare.png", 94, 94);
	public static BufferedImage[][] PortraitKeyBoss			=load("/Art/HUD/Portraits/PortraitKeyBoss.png", 94, 94);
	public static BufferedImage[][] PortraitLever			=load("/Art/HUD/Portraits/PortraitLever.png", 94, 94);
	public static BufferedImage[][] PortraitPotionHealing	=load("/Art/HUD/Portraits/PortraitPotionHealing.png", 94, 94);
	public static BufferedImage[][] PortraitPotionMana		=load("/Art/HUD/Portraits/PortraitPotionMana.png", 94, 94);
	public static BufferedImage[][] PortraitPotionStamina	=load("/Art/HUD/Portraits/PortraitPotionStamina.png", 94, 94);
	public static BufferedImage[][] PortraitShrine			=load("/Art/HUD/Portraits/PortraitShrine.png", 94, 94);
	public static BufferedImage[][] PortraitSign			=load("/Art/HUD/Portraits/PortraitSign.png", 94, 94);
	public static BufferedImage[][] PortraitStatueSave		=load("/Art/HUD/Portraits/PortraitStatueSave.png", 94, 94);
	public static BufferedImage[][] PortraitDoorBoss		=load("/Art/HUD/Portraits/PortraitDoorBoss.png", 94, 94);
	public static BufferedImage[][] PortraitDoorVillage		=load("/Art/HUD/Portraits/PortraitDoorVillage.png", 94, 94);
	
	
	
	public static enum mapMusic 
	{ 
		Menu,
		GameOver,
		Tutorial,
		LorasCavern,
		MysteriousDungeon,
		MysteriousBattle,
		MysteriousConversation,
		FionasSanctum,
		DeepWoods
	}
	
	public static void runningWithinEclipseLoad()
	{
		for(int i = 1; i <= 6; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Player/Attack" + (i < 10 ? "0" : "")+ i + ".mp3", "PlayerAttack" + (i < 10 ? "0" : "")+ i);
		}
		
		JukeBox.load("/Sound/CharacterSounds/Player/EnterDungeon.mp3", "PlayerEnterDungeon");
		
		for(int i = 1; i <= 7; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Player/Hurt" + (i < 10 ? "0" : "")+ i + ".mp3", "PlayerHurt" + (i < 10 ? "0" : "")+ i);
		}
		
		for(int i = 1; i <= 6; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Player/Jump" + (i < 10 ? "0" : "")+ i + ".mp3", "PlayerJump" + (i < 10 ? "0" : "")+ i);
		}
		
		for(int i = 1; i <= 4; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Player/Loot" + (i < 10 ? "0" : "")+ i + ".mp3", "PlayerLoot" + (i < 10 ? "0" : "")+ i);
		}
		
		for(int i = 1; i <= 3; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Player/CannotOpen" + (i < 10 ? "0" : "")+ i + ".mp3", "PlayerCannotOpen" + (i < 10 ? "0" : "")+ i);
		}
		
		for(int i = 1; i <= 6; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Fiona/Attack" + (i < 10 ? "0" : "")+ i + ".mp3", "FionaAttack" + (i < 10 ? "0" : "")+ i);
		}
				
		JukeBox.load("/Sound/CharacterSounds/Fiona/Chargeup01.mp3", "FionaChargeup01");
		JukeBox.load("/Sound/CharacterSounds/Fiona/Chargeup02.mp3", "FionaChargeup02");
		
		for(int i = 1; i <= 9; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Fiona/Hurt" + (i < 10 ? "" + (i < 10 ? "0" : ""): "") + i + ".mp3", "FionaHurt" + (i < 10 ? "" + (i < 10 ? "0" : ""): "") + i);
		}
		
		for(int i = 1; i <= 3; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Fiona/Hit" + (i < 10 ? "0" : "")+ i + ".mp3", "FionaHit" + (i < 10 ? "0" : "")+ i);
		}
		
		for(int i = 1; i <= 3; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Fiona/Recover" + (i < 10 ? "0" : "")+ i + ".mp3", "FionaRecover" + (i < 10 ? "0" : "")+ i);
		}


		
		for(int i = 1; i <= 4; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Succubus/Attack" + (i < 10 ? "0" : "")+ i + ".mp3", "SuccubusAttack" + (i < 10 ? "0" : "")+ i);
		}

		for(int i = 1; i <= 3; i++)
		{
			JukeBox.load("/Sound/CharacterSounds/Succubus/Hurt" + (i < 10 ? "0" : "")+ i + ".mp3", "SuccubusHurt" + (i < 10 ? "0" : "")+ i);
		}
		
		// Projectile sound effects
		JukeBox.load("/Sound/SpellEffects/FireBallLargeLaunch.mp3", 				"FireBallLargeLaunch");
		JukeBox.load("/Sound/SpellEffects/FireBallSmallLaunch.mp3", 				"FireBallSmallLaunch");	
		
		JukeBox.load("/Sound/SpellEffects/EnergyBallChargeUp.mp3", 					"EnergyBallChargeUp");
		JukeBox.load("/Sound/SpellEffects/ElectricBallActive.mp3", 					"ElectricBallActive");
		
		JukeBox.load("/Sound/SpellEffects/ElectricBallChargeUp.mp3", 				"ElectricBallChargeUp");
		JukeBox.load("/Sound/SpellEffects/ElectricBallLaunch.mp3", 					"ElectricBallLaunch");
		JukeBox.load("/Sound/SpellEffects/ElectricBallImpact.mp3", 					"ElectricBallImpact");
		
		JukeBox.load("/Sound/SpellEffects/ArcaneBallImpact.mp3", 					"ArcaneBallImpact");
		
		JukeBox.load("/Sound/SpellEffects/FireBallLargeImpact.mp3", 				"FireBallLargeImpact");
		JukeBox.load("/Sound/SpellEffects/FireBallSmallImpact.mp3", 				"FireBallSmallImpact");
		
		// Spell sound effect
		JukeBox.load("/Sound/SpellEffects/Teleport.mp3", "Teleport");
		
		// Music
		JukeBox.load("/Sound/Music/Menu.mp3", 										"Menu");
		JukeBox.load("/Sound/Music/LorasCavern.mp3", 								"LorasCavern");
		JukeBox.load("/Sound/Music/MysteriousDungeon.mp3", 							"MysteriousDungeon");
		JukeBox.load("/Sound/Music/MysteriousConversation.mp3", 					"MysteriousConversation");
		JukeBox.load("/Sound/Music/MysteriousBattle.mp3", 							"MysteriousBattle");
		JukeBox.load("/Sound/Music/DeepWoods.mp3", 									"DeepWoods");
		JukeBox.load("/Sound/Music/Tutorial.mp3", 									"Tutorial");
		JukeBox.load("/Sound/Music/FionasSanctum.mp3", 								"FionasSanctum");
		
		JukeBox.load("/Sound/Music/GameOver.mp3",  									"GameOver");
		
		// Doodad sound effect
		JukeBox.load("/Sound/Doodads/OpenChestUncommon.mp3", 						"OpenChestUncommon");
		JukeBox.load("/Sound/Doodads/OpenChestCommon.mp3", 							"OpenChestCommon");
		JukeBox.load("/Sound/Doodads/OpenChestRare.mp3", 							"OpenChestRare");
		JukeBox.load("/Sound/Doodads/Save.mp3", 									"Save");
		JukeBox.load("/Sound/Doodads/DecisionChange.mp3", 							"DecisionChange");
		JukeBox.load("/Sound/Doodads/DecisionMake.mp3", 							"DecisionMake");		
		JukeBox.load("/Sound/Doodads/PotionDrink.mp3", 								"PotionDrink");		
		JukeBox.load("/Sound/Doodads/Coin.mp3", 									"Coin");		
		JukeBox.load("/Sound/Doodads/Close.mp3", 									"Close");
		JukeBox.load("/Sound/Doodads/Unlock.mp3", 									"Unlock");
		JukeBox.load("/Sound/Doodads/DoorOpen.mp3", 								"DoorOpen");
		JukeBox.load("/Sound/Doodads/Switch01.mp3", 								"Switch01");
		JukeBox.load("/Sound/Doodads/Switch02.mp3", 								"Switch02");
		JukeBox.load("/Sound/Doodads/Switch03.mp3", 								"Switch03");
		
		
		// Background sound effect
		JukeBox.load("/Sound/BackgroundSound/Darkness.mp3", 						"Darkness");
	}
	
	public static void loadContent()
	{						
		try
		{
			bossHealthBar = ImageIO.read
			(
				Content.class.getResource
				(
					"/Art/HUD/Bars/BossHealthBar.png"
				)
			);
			
			bossBar = ImageIO.read
			(
				Content.class.getResource
				(
					"/Art/HUD/Bars/BossHealthBarFrame.png"
				)
			);
			
			if(Content.class.getResource(Content.class.getSimpleName() + ".class").getFile().startsWith("/"))
			{
				System.out.println("Running within Eclipse.");
				runningWithinEclipseLoad();
			}
			
			else
			{
				String path = Content.class.getResource(Content.class.getSimpleName() + ".class").getFile();
				path = ClassLoader.getSystemClassLoader().getResource(path).getFile();
				String jarFileName = new File(path.substring(0, path.lastIndexOf('!'))).getName();
				URL location = Content.class.getProtectionDomain().getCodeSource().getLocation();
				
				JarFile jarFile = new JarFile(location.getPath() + "/" + jarFileName);
				for(@SuppressWarnings("rawtypes")
				Enumeration enumeration = jarFile.entries(); enumeration.hasMoreElements();)
				{
					String fileName = enumeration.nextElement().toString();
					if(fileName.substring(fileName.length()-4, fileName.length()).equals(".mp3"))
					{
						char[] charArray = fileName.toCharArray();
						
						int tempInt = charArray.length - 1;
						String tempString = "";
						int stopAtTwo = 0;
						int start = 0;
						int end = 0;
						while(stopAtTwo != 2 || tempInt < 0)
						{
							if(charArray[tempInt] == '/')
							{
								stopAtTwo++;
								if(stopAtTwo == 1)
									end = tempInt;
								else
									start = tempInt + 1;
							}
							
							tempString += charArray[tempInt];
							tempInt--;
							if(tempInt < 0)
								tempString = null;
						}
						if(tempString != null)
						{
							
							tempString = new StringBuilder(tempString).reverse().toString();

							if(start != 0 && end != 0)
							{
								String folderName = fileName.substring(start, end);
								fileName = "/" + fileName;
								System.out.println("tempString: " + tempString);
								if(folderName.equals("Doodads") || folderName.equals("Music") || folderName.equals("SpellEffects"))
									JukeBox.load(fileName, fileName.substring(end + 2, fileName.length() - 4));
								else
								{
									JukeBox.load(fileName, fileName.substring(start + 1, end + 1) + fileName.substring(end + 2, fileName.length() - 4));
								}			
							}	
						}
					}
				}
				jarFile.close();	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
