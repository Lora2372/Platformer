package Main;

import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GameState.GameStateManager;

import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class GamePanel 
					extends JPanel 
					implements Runnable, KeyListener
{
	// Dimensions
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int WIDTH = 1200;
	public static int HEIGHT = 900;
	
	public static final String version = "0.1.036.1";
		
	// Game thread
	private Thread thread;
	private Boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// Image
	private BufferedImage image;
	private Graphics2D graphics;
	
	// Game state manager
	private GameStateManager gameStateManager;
	
	public GamePanel()
	{
		super();
		
		if(HEIGHT > screenSize.getHeight()) HEIGHT = (int) screenSize.getHeight() - 100;
		if(WIDTH > screenSize.getWidth()) WIDTH = (int) screenSize.getWidth();
		
		setPreferredSize(
				new Dimension(WIDTH, HEIGHT)
				);
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		super.addNotify();
		if(thread == null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	public void run()
	{
		image = new BufferedImage(
				WIDTH,
				HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();
		
		draw();
		drawToScreen();
		Content.loadContent();
		running = true;
		gameStateManager = new GameStateManager();

		
		long start;
		long elapsed;
		long wait;
		
		// Game loop:
		while(running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait <= 0)wait = 5;
			
			try
			{
				Thread.sleep(wait);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void update()
	{
		gameStateManager.update();
	}
	
	private void draw()
	{
		if(running == null)
		{
			try 
			{
				BufferedImage loadingImage = ImageIO.read(
						getClass().getResource(
								"/Art/HUD/Foregrounds/Loading.png"
								)
						);
				
				graphics.drawImage(loadingImage, 
						(int)0, 
						(int)0, 
						GamePanel.WIDTH,
						GamePanel.HEIGHT,
						null);
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return;
		}
		gameStateManager.draw(graphics);
	}
	
	private void drawToScreen()
	{
		Graphics graphics2 = getGraphics();
		graphics2.drawImage(image,0,0, null );
		graphics2.dispose();
	}
	
	public void keyTyped(KeyEvent key)
	{
		
	}
	
	public void keyPressed(KeyEvent key)
	{
		gameStateManager.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key)
	{
		gameStateManager.keyReleased(key.getKeyCode());
	}
	
}
