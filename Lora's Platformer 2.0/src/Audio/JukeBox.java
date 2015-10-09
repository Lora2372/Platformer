package Audio;

import javax.sound.sampled.*;
import java.util.HashMap;

public class JukeBox 
{
	private static HashMap<String, Clip> clips;
	private static int gap;
	private static boolean mute = false;
	
	public static void initialize()
	{
		clips = new HashMap<String, Clip>();
		gap = 0;
	}
	
	
	public static void load(String string, String n)
	{
		if(clips == null) initialize();
		if(clips.get(n) != null) return;
		Clip clip;
		try
		{
			AudioInputStream audioInputStream = 
					AudioSystem.getAudioInputStream(
							JukeBox.class.getResourceAsStream(string));
			AudioFormat baseFormat = audioInputStream.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
					);
			
			AudioInputStream decodeAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
			clip = AudioSystem.getClip();
			clip.open(decodeAudioInputStream);
			clips.put(n, clip);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("added: " + n);
	}
	
	public static boolean checkIfClipExists(String s)
	{
		System.out.println("clip s: " + s);
		if(clips.get(s) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void play(String s)
	{
		play(s, gap);
	}
	
	public static void play(String s, int i)
	{
		if(mute) return;
		Clip clip = clips.get(s);
		if(clip == null) return;
		if(clip.isRunning()) clip.stop();
		clip.setFramePosition(i);
		while(!clip.isRunning()) clip.start();
	}
	
	public static void stop(String s)
	{
		if(clips.get(s) == null) return;
		if(clips.get(s).isRunning()) clips.get(s).stop();
	}
	
	public static void resume(String s) 
	{
		if(mute) return;
		if(clips.get(s).isRunning()) return;
		clips.get(s).start();
	}
	
	public static void loop(String s)
	{
		loop(s, gap, gap, clips.get(s).getFrameLength() - 1);
	}
	public static void loop(String s, int frame)
	{
		loop(s, frame, gap, clips.get(s).getFrameLength() -1);
	}
	
	public static void loop(String s, int start, int end)
	{
		loop(s, gap, start, end);
	}
	
	public static void loop(String s, int frame, int start, int end)
	{
		stop(s);
		if(mute) return;
		clips.get(s).setLoopPoints(start, end);
		clips.get(s).setFramePosition(frame);
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public static void setPosition(String s, int frame)
	{
		clips.get(s).setFramePosition(frame);
	}
	
	public static int getFrames(String s) { return clips.get(s).getFrameLength(); }
	public static int getPosition(String s) { return clips.get(s).getFramePosition(); }	
	
}
