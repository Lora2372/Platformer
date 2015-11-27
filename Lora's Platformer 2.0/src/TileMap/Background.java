package TileMap;

import java.awt.image.*;

import javax.imageio.ImageIO;

import Main.GamePanel;

import java.awt.*;
import java.net.URL;


public class Background 
{
	private BufferedImage image;
	
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(URL url, double ms)
	{
		try
		{
			image = ImageIO.read(url
					);
			moveScale = ms;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setPosition(double x, double y)
	{
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale /50) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update()
	{
		x+= dx;
		y += dy;
	}
	
	public void draw(Graphics2D graphics)
	{
		if(graphics == null)
		{
			System.exit(0);
		}
		
		graphics.drawImage(image, 
				(int)x, 
				(int)y, 
				GamePanel.WIDTH,
				GamePanel.HEIGHT,
				null);
		if(x < 0)
		{
			graphics.drawImage(image, 
					(int)x + GamePanel.WIDTH,
					(int)y,
					GamePanel.WIDTH,
					GamePanel.HEIGHT,
					null
					);
		}
		if(x > 0)
		{
			graphics.drawImage(
					image,
					(int)x - GamePanel.WIDTH,
					(int)y,
					GamePanel.WIDTH,
					GamePanel.HEIGHT,
					null
					);
		}
	}
	
}
