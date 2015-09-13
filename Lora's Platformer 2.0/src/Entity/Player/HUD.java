package Entity.Player;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class HUD 
{
	private Player player;
	
	private BufferedImage[] image = new BufferedImage[5];
	
	
	private Font font;
	
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
			
			font = new Font("Arial", Font.PLAIN, 14);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
		

		
		g.setFont(font);
		g.drawString(
				player.getHealth() + "/" + player.getMaxHealth(), 
				30 , 
				25
				);

		g.drawString(
				player.getMana() / 100 + "/" + player.getMaxMana() / 100, 
				30 , 
				45
				);
		
	}
	
}
