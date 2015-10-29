package Entity.Player;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class HUD 
{
	private Player player;
	
	private BufferedImage[] image = new BufferedImage[20];
	
	private ArrayList<BufferedImage> spellbarUsable = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> spellbarUnusable = new ArrayList<BufferedImage>();
	
	private ArrayList<Integer> spellcost = new ArrayList<Integer>();
	
	private Entity.Unit boss;
	
	public HUD(Player p)
	{
		player = p;
		
		try{
			image[0] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerBar.png"
							)
					);
			
			image[1] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerHealthBar.png"
							)
					);
			
			image[2] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerManaBar.png"
							)
					);
			
			image[3] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerStaminaBar.png"
							)
					);
			
			image[4] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/BossHealthBar.png"
							)
					);
			
			
			
			image[5] = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/BossHealthBarFrame.png"
							)
					);
			
			
			spellcost.add(player.getFireBallSmallManaCost());
			
			BufferedImage tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallSmallUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallSmallUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);

			spellcost.add(player.getFireBallLargeManaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallLargeUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallLargeUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getDashStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/DashUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/DashUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getPunchStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/PunchUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/PunchUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);


			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addBoss(Entity.Unit boss)
	{
		this.boss = boss;
	}
	
	public void removeBoss()
	{
		this.boss = null;
	}
	
	public void draw(Graphics2D graphics)
	{
		
		double t1 = player.getHealth();
		double t2 = player.getMaxHealth();
		
		graphics.drawImage(image[1], 
				94, 
				27, 
				(int)((t1/t2) * image[1].getWidth()),
				image[1].getHeight(),
				null);
		
		t1 = player.getStamina();
		t2 = player.getMaxStamina();
		
		graphics.drawImage(image[3], 
				106, 
				86, 
				(int)((t1/t2) * image[3].getWidth()),
				image[3].getHeight(),
				null);
		
		
		t1 = player.getMana();
		t2 = player.getMaxMana();
		
		graphics.drawImage(image[2], 
				113, 
				56, 
				(int)((t1/t2) * image[2].getWidth()),
				image[2].getHeight(),
				null);
		
		graphics.drawImage(image[0], 
				0, 
				10, 
				null);
		
		
		// YARR, there be gold and silver a plenty!
		//			(Drawing currency)
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		int currencyX = GamePanel.WIDTH - 100;
		int currencyY = GamePanel.HEIGHT - 100;
		graphics.drawString("SILVER: " + player.getSilver(), currencyX, currencyY);
		graphics.drawString("GOLD: " + player.getGold(), currencyX, currencyY + 30);
		
		
		
		
		
		// The gold is ours, let's head back to the ship!
		
		if(!player.getInConversation())
		{
			int x = GamePanel.WIDTH / 2 - (spellbarUsable.get(0).getWidth() * 2);
			int y = GamePanel.HEIGHT - spellbarUsable.get(0).getHeight() - 5;
			
			for(int i = 0; i < spellbarUsable.size(); i++)
			{
				if( (player.getMana() >= spellcost.get(i) && i < 2) || (player.getStamina() >= spellcost.get(i) && i >=2) )
				{
					graphics.drawImage(spellbarUsable.get(i), x + i * spellbarUsable.get(i).getWidth(), y, null);
				}
				else
					graphics.drawImage(spellbarUnusable.get(i), x + i * spellbarUsable.get(i).getWidth(), y, null);
			}
		}
		
		
		if(boss != null)
		{
			
			int imageWidth = GamePanel.WIDTH / 4;
			
			int x = GamePanel.WIDTH / 2 - imageWidth / 2;
			int y = 20;
			
			graphics.drawImage(image[5],
					x,
					y,
					imageWidth,
					image[5].getHeight(),
					null
					);
			
			t1 = boss.getHealth();
			t2 = boss.getMaxHealth();

			graphics.drawImage(image[4], 
					x, 
					y, 
					(int)((t1/t2) * imageWidth),
					image[4].getHeight(),
					null);			
		}
		
		
	}
	
}
