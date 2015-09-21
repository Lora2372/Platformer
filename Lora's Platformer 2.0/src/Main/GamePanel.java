package Main;

import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.JPanel;
import GameState.GameStateManager;

import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel 
					extends JPanel 
					implements Runnable, KeyListener
{
	// Dimensions
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	public static final String version = "0.1.022";
		
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
	
	private void initialize()
	{
		Content.loadContent();
		
		image = new BufferedImage(
				WIDTH,
				HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();
		running = true;
		

		gameStateManager = new GameStateManager();
	}
	
	public void run()
	{
		initialize();
		

		
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
