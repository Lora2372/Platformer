package Audio;

import javax.sound.sampled.*;
import java.util.HashMap;

public class JukeBox 
{
	private static HashMap<String, Clip> allSounds;
	private static HashMap<String, Clip> musicSounds;
	private static HashMap<String, Clip> characterSounds;
	private static HashMap<String, Clip> effectSounds;
	
	
	
	private static int gap;
	private static boolean mute = false;
	
	public static void initialize()
	{
		allSounds = new HashMap<String, Clip>();
		musicSounds = new HashMap<String, Clip>();
		characterSounds = new HashMap<String, Clip>();
		effectSounds = new HashMap<String, Clip>();
		
		gap = 0;
	}
	
	
	public static void load(String string, String n)
	{
		if(allSounds == null) initialize();
		if(allSounds.get(n) != null) return;
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
			allSounds.put(n, clip);
			
			
			if(string.contains("Music"))
			{
				musicSounds.put(n, clip);
			}
			if(string.contains("BackgroundSound"))
			{
				musicSounds.put(n, clip);
			}
			if(string.contains("Doodads"))
			{
				effectSounds.put(n, clip);
			}
			if(string.contains("SpellEffects"))
			{
				effectSounds.put(n, clip);
			}
			if(string.contains("CharacterSounds"))
			{
				characterSounds.put(n, clip);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean checkIfClipExists(String specificSound)
	{
		if(allSounds.get(specificSound) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void play(String specificSound)
	{
		play(specificSound,gap);
	}
	
	public static void play(String specificSound, int i)
	{
		if(mute) return;
		Clip clip = allSounds.get(specificSound);
		if(clip == null) return;
		if(clip.isRunning()) clip.stop();
		clip.setFramePosition(i);
		while(!clip.isRunning()) clip.start();
	}
	
	public static void setVolume(String specificSound, int volume)
	{
		Clip clip = allSounds.get(specificSound);
		if(clip == null) return;
		if(!clip.isRunning()) return;
//		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.VOLUME);
//		
//		double currentVolume =  gainControl.getValue();
//		gainControl.Set
//		
//		gainControl.setValue(volume);
	}
	
	public static void stop(String specificSound)
	{
		if(allSounds.get(specificSound) == null) return;
		if(allSounds.get(specificSound).isRunning()) allSounds.get(specificSound).stop();
	}
	
	public static void resume(String specificSound) 
	{
		if(mute) return;
		if(allSounds.get(specificSound).isRunning()) return;
		allSounds.get(specificSound).start();
	}
	
	public static void loop(String specificSound)
	{
		loop(specificSound,gap, gap, allSounds.get(specificSound).getFrameLength() - 1);
	}
	public static void loop(String specificSound, int frame)
	{
		loop(specificSound,frame, gap, allSounds.get(specificSound).getFrameLength() -1);
	}
	
	public static void loop(String specificSound, int start, int end)
	{
		loop(specificSound,gap, start, end);
	}
	
	public static void loop(String specificSound, int frame, int start, int end)
	{
		stop(specificSound);
		if(mute) return;
		allSounds.get(specificSound).setLoopPoints(start, end);
		allSounds.get(specificSound).setFramePosition(frame);
		allSounds.get(specificSound).loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public static void setPosition(String specificSound, int frame)
	{
		allSounds.get(specificSound).setFramePosition(frame);
	}
	
	public static int getFrames(String specificSound) { return allSounds.get(specificSound).getFrameLength(); }
	public static int getPosition(String specificSound) { return allSounds.get(specificSound).getFramePosition(); }	
	
}
