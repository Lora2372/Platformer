package GameState.Options;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
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
import TileMap.Background;



public class OptionState extends GameState implements ChangeListener
{
	private Background background;
	
	protected boolean addStuff;
	protected GamePanel gamePanel;
	
	protected Player player;
	
	protected ArrayList<OptionObject> optionObjects;
	
	protected OptionObject goBack;
	
	public OptionState(GameStateManager gameStateManager, GamePanel gamePanel)
	{
		this.gameStateManager = gameStateManager;
		this.gamePanel = gamePanel;
		
		optionObjects = new ArrayList<OptionObject>();
		ToggleObject useMouse = new ToggleObject
			(
					200, 
					200, 
					50, 
					50, 
					2, 
					1, 
					1, 
					new String[] 
						{ 
							"Aim with mouse",
							"Aim with arrow keys"
						}, 
					new BufferedImage[] 
						{ 
							Content.OptionConfirm[0][0], 
							Content.OptionDeny[0][0] 
						}
			);
		optionObjects.add(useMouse);
		
		goBack = new OptionObject
			(
				200, 
				400, 
				100, 
				50, 
				1, 
				1, 
				1, 
				new String[] 
				{ 
					"Go back" 
				},
				new BufferedImage[]
					{
						
					}
			);
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
				OptionObject optionObject = optionObjects.get(i);
				graphics.setFont(new Font("Arial", Font.PLAIN, 14));
				
				double stringWidth;
				
				double locationX;
				double locationY;
				
				
				if(optionObject.getText() != null)
				{
					stringWidth = (int)graphics.getFontMetrics().getStringBounds(optionObject.getText(), graphics).getWidth();
					locationX = optionObject.getLocationX() - stringWidth;
					locationY = optionObject.getLocationY();
					graphics.drawString(optionObject.getText(), (int)locationX, (int)locationY);
				}
				
				if(optionObject.getImage() != null)
				{
					graphics.drawImage
					(
						optionObject.getImage(),
						(int)optionObject.getLocationX(),
						(int)optionObject.getLocationY() - optionObject.getHeight() / 2,
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
							optionObject.getRectangle().y  - optionObject.getRectangle().height / 2,
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
				if(optionObjects.get(i) == goBack)
				{
					gameStateManager.options(false);
					return;
				}
				System.out.println("Intersecting the click!");
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


	public void stateChanged(ChangeEvent e) 
	{
		
	}

}
