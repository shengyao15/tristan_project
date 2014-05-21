/**
 * Author by metaphy
 * Date Apr 12, 2008
 * All Rights Reserved.
 */
package chess;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLClassLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class GameResources {
	private static URL sampleLineup = null ;
	private static String[] audioNames = {"MOVE", "CAPTURE", "DIE", "EAT",
			"EQUAL" };
	
	public GameResources() {
		
	}

	public static AudioInputStream getAudio(String audio) {
		AudioInputStream audioStream = null;
		try {
			URLClassLoader urlLoader = (URLClassLoader) (Main.class
					.getClassLoader());
			URL aurl = urlLoader.findResource("resources/SOUND_" + audio
					+ ".WAV");
			audioStream = AudioSystem.getAudioInputStream(aurl);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return audioStream;
	}

	public static URL getSampleLineup(){
		if (sampleLineup==null){
			URLClassLoader urlLoader =(URLClassLoader)Main.class.getClassLoader();
			sampleLineup = urlLoader.findResource("resources/lineup.txt");
		}
		return sampleLineup ;
	}
}
