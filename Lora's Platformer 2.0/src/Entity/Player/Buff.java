package Entity.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import Entity.Unit.Unit;
import Main.Content;
import Main.DrawingConstants;
import Main.GamePanel;

public class Buff 
{
	
	protected BufferedImage sprites;
	
	protected int width = 60;
	protected int height = 60;
	
	protected int locationX;
	protected int locationY;
	
	protected String buffDescription;
	protected String buffDescriptionName;
	
	protected double duration;
	protected double startDuration;
	
	protected double objectValueTotal;	
	
	protected Rectangle rectangle;
	
	protected double start;
	protected double tick = 0;
	
	protected boolean expired;
	
	protected boolean expiring;
	
	protected Unit owner;
	
	protected double totalRestoringDone = 0;
	protected double totalTimePassed = 0;
	
	protected boolean mouseOver = false;
	
	protected CreateBuff.Buffs buff;
	
	public Buff
		(
			CreateBuff.Buffs buff,
			int duration,
			double objectValueTotal,
			Unit owner,
			BufferedImage sprites
		)
	{
		this.buff = buff;
		this.objectValueTotal = objectValueTotal;
		this.duration = duration;
		this.startDuration = this.duration;
		this.buffDescriptionName = CreateBuff.getDescriptionName(buff.toString());
		this.buffDescription = CreateBuff.getDescription(buff.toString());
		this.owner = owner;
		rectangle = new Rectangle(0, 0, width, height);
		this.sprites = sprites;
		
		expiring = duration != -1;
		
		System.out.println("expiring: " + expiring);
		
		
		
		start = System.currentTimeMillis();
	}
	
	
	public void update()
	{
		if(!expiring)
		{
			return;
		}

		double currentTime = System.currentTimeMillis();
		totalTimePassed += currentTime - start;
		if( (currentTime - start)  >= 16)
		{

			start = currentTime;
			double objectValueTotalPiece = objectValueTotal / startDuration / 62;

			if(buff.equals(CreateBuff.Buffs.RestoreHealth))
			{
				owner.restoreHealth(objectValueTotalPiece);
			}
			else if(buff.equals(CreateBuff.Buffs.RestoreMana))
			{
				owner.restoreMana(objectValueTotalPiece);
			}
			else if(buff.equals(CreateBuff.Buffs.RestoreStamina))
			{
				owner.restoreStamina(objectValueTotalPiece);
			}

			
			totalRestoringDone += objectValueTotalPiece;
			
			tick++;
			if(tick >= 62)
			{
				tick = 0;
				duration--;
			}
		}
		
//		if( (currentTime) - start >= 1000)
//		{
//			start = System.currentTimeMillis();
//
//		}
		
		if(duration <= 0 && expiring)
		{
			System.out.println("Amount to restore: " + objectValueTotal + "\n" +
					"Total " + buffDescriptionName + " restored: " + totalRestoringDone + "\n" +
						"Amount of time to pass: " + startDuration + ", Total time passed: " + (totalTimePassed / 1000));
			expired = true;
		}
		
		
	}
	
	public CreateBuff.Buffs getBuff()
	{
		return buff;
	}
	
	public boolean getExpired()
	{
		return expired;
	}
	
	public void setExpire()
	{
		expired = true;
		expiring = true;
	}
	
	public double getDuration()
	{
		return duration;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public double getLocationX()
	{
		return locationX;
	}
	
	public void setLocation(int locationX, int locationY)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		rectangle.setLocation(locationX, locationY);
	}
	
	public double getLocationY()
	{
		return locationY;
	}
	
	public void setBuff(CreateBuff.Buffs newBuff)
	{
		this.buff = newBuff;
		this.buffDescriptionName = CreateBuff.getDescriptionName(buff.toString());
		this.buffDescription = CreateBuff.getDescription(buff.toString());
	}
	
	public void setObjectValueTotal(double objectValueTotal)
	{
		this.objectValueTotal = objectValueTotal;
	}
	
	public void setSprites(BufferedImage sprites)
	{
		this.sprites = sprites;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void mouseMoved(MouseEvent mouse) 
	{
		mouseOver = rectangle.intersects(new Rectangle((int)mouse.getX(), (int)mouse.getY(), 1, 1) );
	}
	
	public void mousePressed(MouseEvent mouse) 
	{
		if(SwingUtilities.isRightMouseButton(mouse))
		if(mouseOver)
		{
			if(expiring)
			{
				System.out.println("Click!");
				expired = true;
			}
		}
	}
	
	public void draw(Graphics2D graphics)
	{
		try
		{
			graphics.drawImage
				(
					Content.BuffIcon[0][0],
					locationX,
					locationY,
					width,
					height,
					null
				);
			
			graphics.drawImage
				(
					sprites,
					locationX + 5,
					locationY + 5,
					width - 10,
					height - 10,
					null
				);
			
			int textLocationX = locationX + width / 2;
			int textLocationY = locationY + height + 10;
			
			if(expiring)
			{
				graphics.setFont (new Font("Arial", Font.PLAIN, 14) );
				graphics.setColor(Color.BLACK);
				graphics.drawString( (int)duration + "", DrawingConstants.shiftWest( (int) textLocationX, 1), DrawingConstants.shiftNorth( (int) textLocationY, 1));
				graphics.drawString( (int)duration + "", DrawingConstants.shiftWest( (int) textLocationX, 1), DrawingConstants.shiftSouth( (int) textLocationY, 1));
				graphics.drawString( (int)duration + "", DrawingConstants.shiftEast( (int) textLocationX, 1), DrawingConstants.shiftNorth( (int) textLocationY, 1));
				graphics.drawString( (int)duration + "", DrawingConstants.shiftEast( (int) textLocationX, 1), DrawingConstants.shiftSouth( (int) textLocationY, 1));
				
				graphics.setColor(Color.WHITE);
				graphics.drawString( (int)duration + "", textLocationX , textLocationY);
			}
			


			if(mouseOver)
			{
				int stringLength = DrawingConstants.getStringWidth(CreateBuff.getDescriptionName(buff.toString()), graphics);
				int stringHeight = DrawingConstants.getStringHeight(CreateBuff.getDescriptionName(buff.toString()), graphics);
				
				textLocationX = locationX - stringLength / 4;
				textLocationY += 10;
				
				graphics.drawImage
					(
						Content.ConversationGUIEndConversation[0][0],
						textLocationX,
						textLocationY,
						stringLength + 20,
						stringHeight + 10,
						null
					);
				graphics.setColor(Color.WHITE);
				graphics.drawString(CreateBuff.getDescriptionName(buff.toString()), textLocationX + 10, textLocationY + 15);
				
				stringLength = DrawingConstants.getStringWidth(CreateBuff.getDescription(buff.toString()), graphics);
				stringHeight = DrawingConstants.getStringHeight(CreateBuff.getDescription(buff.toString()), graphics);
				textLocationX = locationX - stringLength / 3;
				if(textLocationX + stringLength > GamePanel.WIDTH)
				{
					textLocationX = GamePanel.WIDTH - stringLength - 10;
				}
				textLocationY += stringHeight;
				
				graphics.drawImage
				(
					Content.ConversationGUIEndConversation[0][0],
					textLocationX,
					textLocationY,
					stringLength + 20,
					stringHeight + 10,
					null
				);
			graphics.setColor(Color.WHITE);
			graphics.drawString(CreateBuff.getDescription(buff.toString()), textLocationX + 10, textLocationY + 15);
			}

			
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
