import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Threaded Sound Effects class for playing sounds
 * @author Rony Edde
 *
 */
public class SoundEffects extends Thread
{
	Clip clip;
	File url;
	AudioInputStream audioIn;
	
	/**
	 * Constructor to SoundEffects that tries to load audio for playing
	 * @param soundPath the path to the audio file
	 */
	public SoundEffects(String soundPath)
	{
		try 
		{
			File url = new File(soundPath);
			this.audioIn = AudioSystem.getAudioInputStream(url);
			this.clip = AudioSystem.getClip();
			this.clip.open(audioIn);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * plays the audio until
	 */
	public void run()
	{
		//this.clip.stop();
		this.clip.setFramePosition(0);
		this.clip.start();
		//this.clip.loop(1);
	}
}
