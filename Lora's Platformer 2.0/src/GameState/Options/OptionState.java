package GameState.Options;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Entity.Player.Player;
import GameState.GameState;
import GameState.GameStateManager;
import Main.Content;
import Main.GamePanel;
import TileMap.Background;



public class OptionState extends GameState implements ChangeListener
{
	private Background background;
	
	protected boolean addStuff;
	protected GamePanel gamePanel;
	
	protected Player player;
	
	protected ArrayList<OptionObject> optionObjects;
	
	public OptionState(GameStateManager gameStateManager, GamePanel gamePanel)
	{
		this.gameStateManager = gameStateManager;
		this.gamePanel = gamePanel;
		
		optionObjects = new ArrayList<OptionObject>();
		OptionObject useMouse = new OptionObject(200, 200, 50, 50, 1, 0, 1, new String[] { "Aim with mouse"});
		optionObjects.add(useMouse);
		
		OptionObject goBack = new OptionObject(200, 400, 100, 50, 1, 0, 1, new String[] { "Go back" } );
		optionObjects.add(goBack);
		
		try
		{
			background = new Background(getClass().getResource("/Art/HUD/Foregrounds/ScreenPaused.png"), 0);
			background.setVector(0, 0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void initialize() 
	{
		
	}
	
	public void load(boolean load)
	{
		if(load)
		{

			System.out.println("Added stuff");

		}
		
	}

	public void update() 
	{
		
	}

	public void draw(Graphics2D graphics) 
	{
		background.draw(graphics);
		for(int i = 0; i < optionObjects.size(); i++)
		{
			try
			{
				double locationX = optionObjects.get(i).getLocationX();
				double locationY = optionObjects.get(i).getLocationY();
				
				
				
				graphics.drawString(optionObjects.get(i).getText()[0], (int)locationX, (int)locationY);
				graphics.drawImage
				(
					Content.OptionConfirm[0][0],
					(int)locationX,
					(int)locationY,
					optionObjects.get(i).getWidth(),
					optionObjects.get(i).getHeight(),
					null
					
				);
				
				
				graphics.setColor(optionObjects.get(i).getColor());
				graphics.fillRect
				(
						(int)optionObjects.get(i).getLocationX(),
						(int)optionObjects.get(i).getLocationY(),
						optionObjects.get(i).getWidth(),
						optionObjects.get(i).getHeight()
				);
				
				}
				catch(NullPointerException exception)
				{
					exception.printStackTrace();
				}
		}
		
		
	}

	public void keyPressed(int k) 
	{
		
	}

	public void keyReleased(int k) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent mouse) 
	{
		Rectangle mouseRectangle = new Rectangle
			(
				(int)MouseInfo.getPointerInfo().getLocation().getX(),
				(int)MouseInfo.getPointerInfo().getLocation().getY(),
				1,
				1
			);
		
		for(int i = 0; i < optionObjects.size(); i++)
		{
			if(mouseRectangle.intersects(optionObjects.get(i).getRectangle()))
			{
				System.out.println("Intersecting the click!");
				if(i == 0)
				{
					player.setUsingMouse(!player.getUsingMouse());
					
					if(player.getUsingMouse())
					{
						optionObjects.get(i).setColor(Color.BLUE);
					}
					else
					{
						optionObjects.get(i).setColor(Color.RED);
					}
				}
				else
				{
					gameStateManager.options(false);
				}
			}
		}	
	}

	public void mouseEntered(MouseEvent mouse) 
	{	
		
	}

	public void mouseExited(MouseEvent mouse) 
	{
		
	}

	public void mousePressed(MouseEvent mouse) 
	{
		
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		
	}


	public void stateChanged(ChangeEvent e) 
	{
		
	}

}
