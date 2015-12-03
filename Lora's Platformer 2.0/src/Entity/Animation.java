package Entity;

import java.awt.image.BufferedImage;

public class Animation 
{
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private Boolean playedOnce;
	
	// Constructor
	public Animation()
	{
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames)
	{
		if(frames == null)
		{
			currentFrame = 0;
		}
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setDelay(long d) { delay = d; }
	
	// In case we need to be able to set the frame ourselves
	public void setFrame(int i) { currentFrame = i; }
	
	// Handles the logic of whether or not to move on to the next frame.
	public void update()
	{
		// If delay is -1 there is no animation
		if(delay == -1) return;
		
		// We need to find out how long it has been since the last frame came up
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		
		// If that amount of time is longer than the delay then we need to move on to the next frame
		if(elapsed > delay)
		{
			currentFrame++;
			startTime = System.nanoTime();
		}
		// We have to make sure that we don't go past the amount of frames we have.
		if(frames != null)
		{
			if(currentFrame == frames.length)
			{
				currentFrame = 0;
				playedOnce = true;
			}
		}

	}
	
	
	public int getFrame() { return currentFrame; }
	public int getFramesLength() { return frames.length; }
	public BufferedImage[] getFrames() { return frames; }
	
	public BufferedImage getImage() 
	{ 
		try
		{
			return frames[currentFrame]; 
		}
		catch(Exception exception)
		{
			throw exception;
		}
		
	}
	
	public Boolean hasPlayedOnce() { return playedOnce; }
}
