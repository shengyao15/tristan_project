/**
 * Author by metaphy
 * Date Feb 22, 2008
 * All Rights Reserved.
 */
package chess;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

public class SoundPlayer {
	/**
	 * play the sound once
	 */
	public static void play(AudioInputStream stream) {
		try {
			AudioFormat format = stream.getFormat();
			// Create the clip
			DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
					((int) stream.getFrameLength() * format.getFrameSize()));
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			// Start playing
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SoundPlayer.play(GameResources.getAudio("DIE"));
		Thread.sleep(3000);
	}
}