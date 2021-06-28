package musichub.controller;

import java.util.*;
import musichub.model.AudioElement;

public class AudioElementController {
	public static List<String> searchAudioElement(String title)
	{
		MusicHub theHub = new MusicHub ();
		List<String> research = new ArrayList<String>();
		List<AudioElement> list = theHub.getAudioElements();
		
		for (AudioElement el : list) {
			if (el.getTitle().toLowerCase().contains(title.toLowerCase()))
			{
				research.add(el.getTitle());
			}
		}
		return research;
	}
}