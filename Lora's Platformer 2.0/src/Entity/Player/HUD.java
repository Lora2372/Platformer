package Entity.Player;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class HUD 
{
	private Player player;
	
	private BufferedImage[] image = new BufferedImage[6];
	
	
	
	private Entity.Character boss;
	
	public HUD(Player p)
	{
		player = p;
		
		try{
			image[0] = ImageIO.read(
					getClass().getResource(
							"/HUD/playerBar.png"
							)
					);
			
			image[1] = ImageIO.read(
					getClass().getResource(
							"/HUD/healthBar.png"
							)
					);
			
			image[2] = ImageIO.read(
					getClass().getResource(
							"/HUD/manaBar.png"
							)
					);
			
			image[3] = ImageIO.read(
					getClass().getResource(
							"/HUD/staminaBar.png"
							)
					);
			
			image[4] = ImageIO.read(
					getClass().getResource(
							"/HUD/bossHealthBar.png"
							)
					);
			
			image[5] = ImageIO.read(
					getClass().getResource(
							"/HUD/bossHealthBarFrame.png"
							)
					);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addBoss(Entity.Character boss)
	{
		this.boss = boss;
	}
	
	public void removeBoss()
	{
		this.boss = null;
	}
	
	public void draw(Graphics2D g)
	{
		
		double t1 = player.getHealth();
		double t2 = player.getMaxHealth();
		
		
		
		
		
		g.drawImage(image[1], 
				94, 
				27, 
				(int)((t1/t2) * image[1].getWidth()),
				image[1].getHeight(),
				null);
		
		t1 = player.getStamina();
		t2 = player.getMaxStamina();
		
		g.drawImage(image[3], 
				106, 
				86, 
				(int)((t1/t2) * image[3].getWidth()),
				image[3].getHeight(),
				null);
		
		
		t1 = player.getMana();
		t2 = player.getMaxMana();
		
		g.drawImage(image[2], 
				113, 
				56, 
				(int)((t1/t2) * image[2].getWidth()),
				image[2].getHeight(),
				null);
		
		g.drawImage(image[0], 
				0, 
				10, 
				null);
		
		
		
		
		
		if(boss != null)
		{
			int x = GamePanel.WIDTH / 4;
			int y = GamePanel.HEIGHT - image[5].getHeight() - 30;
			
			int imageWidth = GamePanel.WIDTH / 2;
			
			
			g.drawImage(image[5],
					x,
					y,
					imageWidth,
					image[5].getHeight(),
					null
					);
			
			t1 = boss.getHealth();
			t2 = boss.getMaxHealth();

			g.drawImage(image[4], 
					x, 
					y, 
					(int)((t1/t2) * imageWidth),
					image[4].getHeight(),
					null);			
		}
		
		
	}
	
}
