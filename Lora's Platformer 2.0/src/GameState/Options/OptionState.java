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
					400, 
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
		
		graphics.drawImage
		(
			Content.OptionBackground[0][0],
			0,
			0,
			GamePanel.WIDTH,
			GamePanel.HEIGHT,
			null
		);
		
		for(int i = 0; i < optionObjects.size(); i++)
		{
			try
			{
				OptionObject optionObject = optionObjects.get(i);
				graphics.setFont(new Font("Arial", Font.PLAIN, 14));
				
				double locationX;
				double locationY;
				
				
				if(optionObject.getText() != null)
				{
					locationX = optionObject.getLocationX() - 300;
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
