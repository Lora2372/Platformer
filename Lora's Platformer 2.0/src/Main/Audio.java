package Main;
import java.io.*;

import sun.audio.*;

public class Audio 
{

	private InputStream inputStream;
	private AudioStream audioStream;
	
	
	public void playSound(String soundPath)
	throws Exception
	{
		

		inputStream = new FileInputStream(soundPath);
		
		audioStream = new AudioStream(inputStream);
		
		AudioPlayer.player.start(audioStream);
//		
//		AudioPlayer.player.stop();
		
	}
	@SuppressWarnings("deprecation")
	public void stop() 
	{ 
		AudioPlayer.player.stop();
	}
	
	public void start()
	{
		AudioPlayer.player.start(audioStream);
	
	}
	
}
