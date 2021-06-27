package musichub.controller;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import musichub.model.Album;
import musichub.model.PlayList;

public class AudioElementController {

	public UUID getRamdomPlaylistSong(PlayList playList) {
		
		Random rand = new Random();
		return playList.getElements().get(rand.nextInt(playList.getElements().size()));
		
	}

	public void random(PlayList playList) {
		// TODO Auto-generated method stub
		
	}
	
}
