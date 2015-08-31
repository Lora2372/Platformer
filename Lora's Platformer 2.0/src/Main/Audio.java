package Main;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
	public void stop() 
	{ 
		AudioPlayer.player.stop();
	}
	
	public void start()
	{
		AudioPlayer.player.start(audioStream);
	
	}
	
}
