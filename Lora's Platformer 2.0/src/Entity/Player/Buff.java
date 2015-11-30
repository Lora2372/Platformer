package Entity.Player;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Entity.Unit.Unit;

public class Buff 
{
	
	protected BufferedImage sprites;
	
	protected int width = 60;
	protected int height = 60;
	
	protected int locationX;
	protected int locationY;
	
	protected String buffDescription;
	protected String buffDescriptionName;
	
	protected int duration;
	protected int startDuration;
	
	protected double restoreHealthAmount;
	protected double restoreManaAmount;
	protected double restoreStaminaAmount;
	
	protected int restoreHealthAmountRemaining;
	protected int restoreManaAmountRemaining;
	protected int restoreStaminaAmountRemaining;
	
	
	protected Rectangle rectangle;
	
	protected long elapsed;
	protected long start;
	
	protected boolean expired;
	
	protected Unit owner;
	
	protected double totalHealingDone = 0;
	
	protected int tick = 0;
	
	public Buff
		(
			CreateBuff.Buffs buff,
			int duration,
			double restoreAmount,
			Unit owner,
			BufferedImage sprites
		)
	{
		this.restoreHealthAmount = restoreAmount;
		this.duration = duration;
		this.startDuration = duration;
		this.buffDescriptionName = CreateBuff.getDescriptionName(buff.toString());
		this.buffDescription = CreateBuff.getDescription(buff.toString());
		this.owner = owner;
		rectangle = new Rectangle(0, 0, width, height);

		start = System.nanoTime() / 100000000;
		System.out.println("Duration: " + (duration) );
		
	}
	
	
	public void update()
	{													//1000000000
		if( (System.nanoTime() / 100000000) - start > 1)
		{							//  10000000
			start = System.nanoTime() / 100000000;
			owner.addHealth(restoreHealthAmount / startDuration / 10);
			System.out.println("Tick, healing: " + (restoreHealthAmount / startDuration / 10));
			
			totalHealingDone += (restoreHealthAmount / startDuration / 10);
			tick++;
			
			if(tick >= 10)
			{
				tick = 0;
				duration--;
				System.out.println("Duration: " + (duration) );
			}
		}
		
		if(duration <= 0)
		{
			System.out.println("Amount to restore: " + restoreHealthAmount + "\nTotal health restored: " + totalHealingDone);
			expired = true;
		}
		
		
	}
	
	public boolean getExpired()
	{
		return expired;
	}
	
	public int getDuration()
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
	
	public int getHeight()
	{
		return height;
	}
	
	public void mouseMoved(MouseEvent mouse) 
	{
		
	}
	
	public void mouseClicked(MouseEvent mouse) 
	{
		
	}
	
}
