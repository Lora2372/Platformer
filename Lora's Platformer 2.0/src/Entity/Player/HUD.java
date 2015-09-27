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
							"/HUD/PlayerBar.png"
							)
					);
			
			image[1] = ImageIO.read(
					getClass().getResource(
							"/HUD/HealthBar.png"
							)
					);
			
			image[2] = ImageIO.read(
					getClass().getResource(
							"/HUD/ManaBar.png"
							)
					);
			
			image[3] = ImageIO.read(
					getClass().getResource(
							"/HUD/StaminaBar.png"
							)
					);
			
			image[4] = ImageIO.read(
					getClass().getResource(
							"/HUD/BossHealthBar.png"
							)
					);
			
			
			
			image[5] = ImageIO.read(
					getClass().getResource(
							"/HUD/BossHealthBarFrame.png"
							)
					);
			
			
			spellcost.add(player.getFireballSmallManaCost());
			
			BufferedImage tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/FireballSmallUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/FireballSmallUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);

			spellcost.add(player.getFireballLargeManaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/FireballLargeUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/FireballLargeUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getDashStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/DashUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/DashUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getPunchStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/PunchUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/HUD/PunchUnusable.png"
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
			int x = GamePanel.WIDTH / 4;
			int y = GamePanel.HEIGHT - spellbarUsable.get(0).getHeight() - image[5].getHeight() - 10;
			
			int imageWidth = GamePanel.WIDTH / 2;
			
			
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
