package Entity;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Entity.Doodad.SummoningEffect;
import Main.GamePanel;
import TileMap.TileMap;

public abstract class MapObject 
{
	
	// Tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double mapPositionX;
	protected double mapPositionY;
	
	protected boolean hidden;
	
	protected boolean rotaded;
	protected double rotation;
	
	protected boolean flying;
	protected boolean gliding;
	protected boolean spawning;
	protected boolean initializeSpawning;
	protected SummoningEffect summoningEffect;
	protected boolean inControl;
	
	// Position and vector
	protected double locationX;
	protected double locationY;
	protected double directionX;
	protected double directionY;
	
	protected double spawnX;
	protected double spawnY;
	
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
	
	protected boolean untouchable; // Attacks ignore you.
	protected boolean invulnerable; // Attacks are able to hit you but you won't take damage.
	protected boolean unkillable; // Attacks can hit you and you can take damage, but you won't die.
	
	public void setHidden(boolean b) { hidden = b; }
	
	// Constructor
	public MapObject(TileMap tileMap)
	{
		if(tileMap == null) return;
		this.tileMap = tileMap;
		tileSize = tileMap.getTileSize();
		animation = new Animation();
		setPosition(locationX, locationY);
	}
	
	public void setTileMap(TileMap tileMap)
	{
		this.tileMap = tileMap;
		tileSize = tileMap.getTileSize();
		animation = new Animation();
		setPosition(locationX, locationY);
	}
	
	public void setCollisionWidth(int i)
	{
		this.collisionWidth = i;
	}
	
	public void setCollisionHeight(int i)
	{
		this.collisionHeight = i;
	}
	
	public boolean intersects(MapObject mapObject)
	{
		if(untouchable || mapObject.untouchable) return false;
		Rectangle r1 = getRectangle();
		Rectangle r2 = mapObject.getRectangle();
				
		return r1.intersects(r2);
	}
	
	public boolean intersects(Rectangle rectangle)
	{
		return getRectangle().intersects(rectangle);
	}
	
	public Rectangle getRectangle()
	{		
		return new Rectangle(				
				(int)locationX - collisionWidth,
				(int)locationY - collisionHeight,
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
	
	
	protected void getNextPosition()
	{
		if(spawning) return;
		// Movement
		if(left && inControl)
		{

			directionX -= moveSpeed;
			if(directionX < -maxSpeed)
			{
				directionX = -maxSpeed;
			}
		}
		else if(right && inControl)
		{

			directionX += moveSpeed;
			if(directionX > maxSpeed)
			{
				directionX = maxSpeed;
			}
		}
		else
		{
			if(directionX > 0)
			{
				directionX -= stopSpeed;
				if(directionX < 0)
				{
					directionX = 0;
				}
			}
			else if(directionX < 0)
			{
				directionX += stopSpeed;
				if(directionX > 0)
				{
					directionX = 0;
				}
			}
		}
		
		// Cannot move while attacking
		//Might implement this later
		
		if(flying)
		{
			if(down)
			{
				directionY += moveSpeed;
				if(directionY > maxSpeed) directionY = maxSpeed; 
			}
			else if(up)
			{
				directionY -= moveSpeed;
				if(directionY*-1 > maxSpeed) directionY = maxSpeed*-1;
			}
			
			if(right)
			{
				directionX += moveSpeed;
				if(directionX > maxSpeed) directionX = maxSpeed;
			}
			else if(left)
			{
				directionX -= moveSpeed;
				if(directionX*-1 > maxSpeed) directionX = maxSpeed*-1;
			}
			
		}
		
		
		// Jumping
		if(jumping && !falling && inControl && !flying)
		{
			// I'll leave the jump sound commented out until we find a better one.
//			JukeBox.play("jump");
			playJumpSound();
			directionY = jumpStart;
			falling = true;
		}
		
		// Falling
//		System.out.println("character name: " + getName() + ", falling: " + falling);
		if( (falling || swimming) && !flying)
		{
			if(directionY > 0 && gliding) directionY += fallSpeed * 0.1;
			else directionY += fallSpeed;
		
			if(directionY > 0) jumping = false;
			if(directionY < 0 && !jumping) directionY += stopJumpSpeed;
			
			if(directionY > maxFallSpeed) directionY = maxFallSpeed;
		}
	}
	
	public void playJumpSound() { }
	
	public void checkTileMapCollision()
	{
		currentCol = (int)locationX / tileSize;
		currentRow = (int)locationY / tileSize;
		
		xdest = locationX + directionX;
		ydest = locationY + directionY;
		
		xtemp = locationX;
		ytemp = locationY;
		
		calculateCorners(locationX, ydest);
		
		
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
		if(directionY < 0)
		{
			// Since we're going upwards we have to check the top tiles
			if(topLeft == 1 || topRight == 1)
			{
				// If they are there then we have to stop going upwards (otherwise we'll move through the wall).
				directionY = 0;
				setHit();
				ytemp = currentRow * tileSize +collisionHeight / 2;
			}
			else
			{
				ytemp += directionY;
			}
		}
		
		// We're going downwards
		if(directionY > 0)
		{
			// Checking if we have landed on something
			if(bottomLeft == 1 || bottomRight == 1)
			{
				setHit();
				directionY = 0;
				falling = false;
				ytemp = (currentRow + 1) * tileSize - collisionHeight / 2;
			}
			else
			{
				ytemp += directionY;
			}
		}
		
		calculateCorners(xdest, locationY);
		// Moving left;
		if(directionX < 0)
		{
			// If we've hit a block on our left side
			if(topLeft == 1 || bottomLeft == 1)
			{
				directionX = 0;
				xtemp = currentCol * tileSize + collisionWidth / 2;
			}
			else
			{
				xtemp += directionX;
			}
		}
		
		// Moving right
		if(directionX > 0)
		{
			// If we've hit a block on the right side
			if(topRight == 1 || bottomRight == 1)
			{
				directionX = 0;
				xtemp = (currentCol + 1) * tileSize - collisionWidth / 2;
			}
			else
			{
				xtemp += directionX;
			}
		}
		
		// If we are not falling
		if(!falling)
		{
			// Check the corners around the object, one tile down to see if we should start falling.
			calculateCorners(locationX, ydest + 1);
			
			// So if there is no block on the bottom left or right, then we're not standing on anything.
			if(bottomLeft != 1 && bottomRight != 1)
			{
				falling = true;
			}
		}
	}
	public void setHit() { }
	
	public int getx() { return (int)locationX; }
	public int gety() { return (int)locationY; }
	public int getWidth() { return (int)width; }
	public int getHeight() { return (int)height; }
	public int getCollisionWidth() { return (int)collisionWidth; }
	public int getCollisionHeight() { return (int)collisionHeight; }
	
	public void setPosition(double x, double y)
	{
		// Regular position
		this.locationX = x;
		this.locationY = y;
	}
	
	public void setVetor(double directionX, double directionY)
	{
		this.directionX = directionX;
		this.directionY = directionY;
	}
	
	public void setMapPosition()
	{
		// Map position, if our player was at 2000, 1000, then he would be off the screen, we have to find out
		// how far the tilemap has moved in order to offset the player back on to the screen, and that's the final position,
		// x + mapPositionX, y + mapPositionY.
		mapPositionX = tileMap.getX();
		mapPositionY = tileMap.getY();
	}
	
	public void setLeft(Boolean b) { left = b; }
	public void setRight(Boolean b) { right = b; }
	public void setUp(Boolean b) { up = b; }
	public void setDown(Boolean b) { down = b; }
	
	public void setJumping(Boolean b) 
	{ 
		jumping = b; 		
	}
	
	
	public void draw(java.awt.Graphics2D graphics)
	{
		setMapPosition();
		
		if(hidden) return;
		
		if(rotaded)
		{
			
			double rotationX = width / 2;
			double rotationY = height / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotation + Math.PI, rotationX, rotationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			
			
			graphics.drawImage(
					op.filter(animation.getImage(), null),
							(int)(locationX + mapPositionX - width / 2), 
							(int)(locationY + mapPositionY - height / 2),
							null
							);
			return;
			
		}
		
		if(facingRight)
		{
			graphics.drawImage(
					animation.getImage(),
					(int) (locationX + mapPositionX - width / 2 + width),
					(int) (locationY + mapPositionY - height / 2),
					-width,
					height,
					null
				);

		}
		else
		{
			graphics.drawImage(
					animation.getImage(),
					(int) (locationX + mapPositionX - width / 2),
					(int) (locationY + mapPositionY - height / 2),
							null
					);
		}
	}
	
	
	// Since there is no point in drawing objects that are not on the scren, this
	// function will determine whether they even are on the screen.
	public Boolean notOnScreen()
	{
		// Again, x + mapPositionX is the final position on the game screen itself.
		return 
				//If the object is beyond the left side of the screen
				locationX + mapPositionX + width < 0 ||
				// if the object is beyond the right side of the screen
				locationY + mapPositionX - width > GamePanel.WIDTH ||
				//if the object is above the screen
				locationY + mapPositionY + height < 0||
				//if the object is below the screen
				locationY + mapPositionY - height > GamePanel.HEIGHT;
				
	}
	
	
}
