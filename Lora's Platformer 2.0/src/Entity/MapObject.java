package Entity;

import java.awt.Rectangle;





import javax.swing.JOptionPane;

import Main.GamePanel;
import TileMap.TileMap;

public abstract class MapObject 
{
	
	// Tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	
	
	// Position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	// Dimensions
	protected int width;
	protected int height;
	
	// Collision box
	protected int collisionWidth;
	protected int collisionHeight;
	
	// Collision
	protected int currentRow;
	protected int currentCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected int topLeft;
	protected int topRight;
	protected int bottomLeft;
	protected int bottomRight;
	
	// Animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected Boolean facingRight;
	
	// Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	protected boolean swimming;
	
	// Movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	protected double dashSpeed;
	// Constructor
	public MapObject(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		
		tileSize = tileMap.getTileSize();
	}
	
	public Boolean intersects(MapObject mapObject)
	{
		
		Rectangle r1 = getRectangle();
		Rectangle r2 = mapObject.getRectangle();
				
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle()
	{		
		return new Rectangle(				
				(int)x - collisionWidth,
				(int)y - collisionHeight,
				collisionWidth,
				collisionHeight
				);
	}
	
	public void calculateCorners(double x, double y)
	{
		int leftTile 	= (int) (x - collisionWidth / 2) 		/ tileSize;
		int rightTile 	= (int) (x + collisionWidth / 2 - 1) 	/ tileSize;
		
		int topTile 	= (int) (y - collisionHeight / 2) 		/ tileSize;
		int bottomTile 	= (int) (y + collisionHeight / 2 - 1) 	/ tileSize;
		
		
		topLeft = tileMap.getType(topTile, leftTile);		
		topRight = tileMap.getType(topTile, rightTile);
		bottomLeft = tileMap.getType(bottomTile,leftTile);
		bottomRight = tileMap.getType(bottomTile, rightTile);		
	}
	
	
	public void checkTileMapCollision()
	{
		currentCol = (int)x / tileSize;
		currentRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		
		
		if(!swimming && (topLeft == 2 || topRight == 2))
		{
			swimming = true;
//			System.out.println("swimming is true");
		}
		else if(swimming)
		{
//			System.out.println("swimming is false");
			swimming = false;
		}
		
		// We're going upwards
		if(dy < 0)
		{
			// Since we're going upwards we have to check the top tiles
			if(topLeft == 1 || topRight == 1)
			{
				// If they are there then we have to stop going upwards (otherwise we'll move through the wall).
				dy = 0;
				ytemp = currentRow * tileSize +collisionHeight / 2;
			}
			else
			{
				ytemp += dy;
			}
		}
		
		// We're going downwards
		if(dy > 0)
		{
			// Checking if we have landed on something
			if(bottomLeft == 1 || bottomRight == 1)
			{
				dy = 0;
				falling = false;
				ytemp = (currentRow + 1) * tileSize - collisionHeight / 2;
			}
			else
			{
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		// Moving left;
		if(dx < 0)
		{
			// If we've hit a block on our left side
			if(topLeft == 1 || bottomLeft == 1)
			{
				dx = 0;
				xtemp = currentCol * tileSize + collisionWidth / 2;
			}
			else
			{
				xtemp += dx;
			}
		}
		
		// Moving right
		if(dx > 0)
		{
			// If we've hit a block on the right side
			if(topRight == 1 || bottomRight == 1)
			{
				dx = 0;
				xtemp = (currentCol + 1) * tileSize - collisionWidth / 2;
			}
			else
			{
				xtemp += dx;
			}
		}
		
		// If we are not falling
		if(!falling)
		{
			// Check the corners around the object, one tile down to see if we should start falling.
			calculateCorners(x, ydest + 1);
			
			// So if there is no block on the bottom left or right, then we're not standing on anything.
			if(bottomLeft != 1 && bottomRight != 1)
			{
				falling = true;
			}
		}
	}
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return (int)width; }
	public int getHeight() { return (int)height; }
	public int getCollisionWidth() { return (int)collisionWidth; }
	public int getCollisionHeight() { return (int)collisionHeight; }
	
	public void setPosition(double x, double y)
	{
		// Regular position
		this.x = x;
		this.y = y;
	}
	
	public void setVetor(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition()
	{
		// Map position, if our player was at 2000, 1000, then he would be off the screen, we have to find out
		// how far the tilemap has moved in order to offset the player back on to the screen, and that's the final position,
		// x + xmap, y + ymap.
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}
	
	public void setLeft(Boolean b) { left = b; }
	public void setRight(Boolean b) { right = b; }
	public void setUp(Boolean b) { up = b; }
	public void setDown(Boolean b) { down = b; }
	public void setJumping(Boolean b) { jumping = b; }
	
	
	// Since there is no point in drawing objects that are not on the scren, this
	// function will determine whether they even are on the screen.
	public Boolean notOnScreen()
	{
		// Again, x + xmap is the final position on the game screen itself.
		return 
				//If the object is beyond the left side of the screen
				x + xmap + width < 0 ||
				// if the object is beyond the right side of the screen
				x + xmap - width > GamePanel.WIDTH ||
				//if the object is above the screen
				y + ymap + height < 0||
				//if the object is below the screen
				y + ymap - height > GamePanel.HEIGHT;
				
	}
	
	
}
