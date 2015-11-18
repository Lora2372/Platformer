package GameState.Options;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Entity.Player.Player;
import GameState.GameState;
import GameState.GameStateManager;
import Main.Content;
import Main.GamePanel;


public class OptionState extends GameState implements ChangeListener
{
	
	protected boolean addStuff;
	protected GamePanel gamePanel;
	
	protected Player player;
	
	protected ArrayList<OptionObject> optionObjects;
	
	protected OptionObject goBack;
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	public OptionState(GameStateManager gameStateManager, GamePanel gamePanel)
	{
		this.gameStateManager = gameStateManager;
		this.gamePanel = gamePanel;
		
		optionObjects = new ArrayList<OptionObject>();
		ToggleObject useMouse = new ToggleObject
			(
					400, 
					200, 
					50, 
					50, 
					2, 
					1, 
					1, 
					new String[] 
						{ 
							"Aim with arrow keys",
							"Aim with mouse"
							
						}, 
					new BufferedImage[] 
						{ 
							Content.LeverOpened[0][0], 
							Content.LeverClosed[0][0] 
						},
						"Mouse",
						player
			);
		optionObjects.add(useMouse);
		
		goBack = new OptionObject
			(
				GamePanel.WIDTH - 200, 
				GamePanel.HEIGHT - 200, 
				100, 
				50, 
				1, 
				1, 
				1, 
				new String[] 
				{ 
				},
				new BufferedImage[]
					{
						
					},
				"Back"
			);
		optionObjects.add(goBack);
		

	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
		for(int i = 0; i < optionObjects.size(); i++)
		{
			optionObjects.get(i).setPlayer(player);
		}
		
	}
	
	public void initialize() 
	{
		
	}
	
	public void update() 
	{
		for(int i = 0; i < optionObjects.size(); i++)
		{
			optionObjects.get(i).update();
		}
	}
	
	public void reset()
	{
		
	}
	
	
	protected int shiftNorth(int coordinate, int distance) 
	{
	   return (coordinate - distance);
	}
	
	protected int shiftSouth(int coordinate, int distance) {
	   return (coordinate + distance);
	}
	
	protected int shiftEast(int coordinate, int distance) 
	{
	   return (coordinate + distance);
	}
	
	protected int shiftWest(int coordinate, int distance) 
	{
	   return (coordinate - distance);
	}
	

	public void draw(Graphics2D graphics) 
	{
		
		graphics.drawImage
		(
			Content.OptionBackground[0][0],
			0,
			0,
			GamePanel.WIDTH,
			GamePanel.HEIGHT,
			null
		);
		
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		graphics.setColor(Color.WHITE);
		
		for(int i = 0; i < optionObjects.size(); i++)
		{
			try
			{
				OptionObject optionObject = optionObjects.get(i);

				
				int locationX;
				int locationY;
				
				
				if(optionObject.getText() != null)
				{
					locationX = (int)optionObject.getLocationX() - 300;
					locationY = (int)optionObject.getLocationY() + optionObject.getHeight();
					String textString = optionObject.getText();
					
					graphics.setColor(Color.BLACK);
					graphics.drawString(textString, shiftWest(locationX, 1), shiftNorth(locationY, 1));
					graphics.drawString(textString, shiftWest(locationX, 1), shiftSouth(locationY, 1));
					graphics.drawString(textString, shiftEast(locationX, 1), shiftNorth(locationY, 1));
					graphics.drawString(textString, shiftEast(locationX, 1), shiftSouth(locationY, 1));
					
					graphics.setColor(Color.WHITE);					
					graphics.drawString(textString, locationX, locationY);

				}
				
				if(optionObject.getImage() != null)
				{
					graphics.drawImage
					(
						optionObject.getImage(),
						(int)optionObject.getLocationX(),
						(int)optionObject.getLocationY(),
						optionObject.getWidth(),
						optionObject.getHeight(),
						null
					);
				}
				else
				{
					graphics.setColor(optionObject.getColor());
					graphics.fillRect
					(
							optionObject.getRectangle().x,
							optionObject.getRectangle().y,
							optionObject.getRectangle().width,
							optionObject.getRectangle().height
					);
				}
				
			}
			catch(Exception exception)
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

	public void mouseClicked(MouseEvent mouse) 
	{
		Rectangle mouseRectangle = new Rectangle
			(
				(int)mouseLocationX,
				(int)mouseLocationY,
				1,
				1
			);
		
		for(int i = 0; i < optionObjects.size(); i++)
		{
			if(mouseRectangle.intersects(optionObjects.get(i).getRectangle()))
			{
				if(optionObjects.get(i) == goBack)
				{
					gameStateManager.options(false);
					return;
				}
				optionObjects.get(i).click();
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
	
	public void mouseMoved(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}


	public void stateChanged(ChangeEvent e) 
	{
		
	}

}
