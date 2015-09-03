package Sound;

import javax.sound.sampled.*;

public class SoundPlayer 
{
	private Clip clip;
	
	public SoundPlayer(String soundPath)
	{
		try
		{
			AudioInputStream soundInputStream = AudioSystem.getAudioInputStream
					(
						getClass().getResourceAsStream
						(
								soundPath
						)
					);
			
			AudioFormat baseFormat = soundInputStream.getFormat();
			AudioFormat decodeFormat = new AudioFormat
					(
							AudioFormat.Encoding.PCM_SIGNED,
							baseFormat.getSampleRate(),
							16,
							baseFormat.getChannels(),
							baseFormat.getChannels() * 2,
							baseFormat.getSampleRate(),
							false
					);
			AudioInputStream decodedSoundInputStream =
					AudioSystem.getAudioInputStream(
							decodeFormat, soundInputStream
							);
			
			clip = AudioSystem.getClip();
			clip.open(decodedSoundInputStream);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void play()
	{
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop()
	{
		if(clip.isRunning()) clip.stop();
	}
	
	public void close()
	{
		stop();
		clip.close();
	}
	
	
}
