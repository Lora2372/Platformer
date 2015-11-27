package TileMap;

import java.awt.image.*;

import javax.imageio.ImageIO;

import Main.GamePanel;

import java.awt.*;
import java.net.URL;


public class GameOver 
{
	private BufferedImage image;
	
	private double x;
	private double y;
		
	
	public GameOver(URL url)
	{
		try
		{
			image = ImageIO.read(url
					);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void draw(Graphics2D graphics)
	{
		if(graphics == null)
		{
			return;
		}
//		AlphaComposite alphacomposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 50);
//		graphics.setComposite(alphacomposite);
		
		graphics.drawImage(image, 
				(int)x, 
				(int)y, 
				GamePanel.WIDTH,
				GamePanel.HEIGHT,
				null);
	}
	
}
