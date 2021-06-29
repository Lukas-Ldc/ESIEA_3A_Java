package musichub.controller;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import musichub.model.Album;
import musichub.model.PlayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import musichub.model.AudioElement;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;


public class AudioElementController {
	
	private String root = System.getProperty("user.dir");
	private Clip clip = null;
	private long timeStopPos = 0;
	private Thread currentActiveThread = null;
	private static AudioElementController INSTANCE = null;
	
	public static AudioElementController getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE = new AudioElementController();
		}
		
		return INSTANCE;
		
	}
	
	private AudioElementController() {
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void play(UUID uuid) {
		
		String path = root + "/files/audio/" + uuid.toString() + ".wav";
		File file = new File(path);
		timeStopPos = 0;
		
		try {
			
			if(file.exists()) {

				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				clip.open(audioStream);
				clip.start();
				
//				// need an active thread to be able to read the audio file
//				JOptionPane.showMessageDialog(null, "Click OK to stop music");
//				Thread.sleep(clip.getMicrosecondLength()/1000);
				CoversManager.iAutoManager(root + "/files/covers", uuid.toString());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void stop() {
		timeStopPos = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void continueAudio() {
		clip.setMicrosecondPosition(timeStopPos);
		clip.start();
		JOptionPane.showMessageDialog(null, "Click OK to stop music");
	}
	
	public void playSingleSong(UUID uuid) {
		play(uuid);
		JOptionPane.showMessageDialog(null, "Click OK to stop music");
	}
	
	public void playLoop(UUID uuid) {
		play(uuid);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		JOptionPane.showMessageDialog(null, "Click OK to stop music");
	}
	


	public static List<String> searchAudioElement(String title)
	{
		MusicHub theHub = MusicHub.getInstance();
		List<String> research = new ArrayList<String>();
		List<AudioElement> list = theHub.getElements();
		
		for (AudioElement el : list) {
			if (el.getTitle().toLowerCase().contains(title.toLowerCase()))
			{
				research.add(el.getTitle());
			}
		}
		return research;
	}

	public UUID getRamdomPlaylistSong(PlayList playList) {
		
		Random rand = new Random();
		return playList.getElements().get(rand.nextInt(playList.getElements().size()));
		
	}

	public void random(PlayList playList) {
		
		UUID uuid = getRamdomPlaylistSong(playList);
//		System.out.println("uuid : " + uuid);
		play(uuid);
		
	}
	
}
