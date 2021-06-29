package musichub.view;

import musichub.exception.NoCoverFoundException;
import musichub.controller.MusicHub;
import musichub.model.Album;
import musichub.model.AudioElement;
import musichub.model.PlayList;

import javax.swing.*;
import java.awt.Dimension;
import java.util.List;
import java.util.UUID;
import java.io.File;

public class Covers {
	
	private MusicHub theHub = MusicHub.getInstance();
	private List<Album> albums = theHub.getAlbums();
	private List<PlayList> playlists = theHub.getPlaylists();
	private List<AudioElement> elements = theHub.getElements();
	
	public void showAlbumCover (String albumTitle) throws NoCoverFoundException {
		for (Album al : albums) {
	        if (al.getTitle().toLowerCase().equals(albumTitle.toLowerCase())) {
	            String imageName = al.getUUID().toString() + ".png";
	            String imagePath = MusicHub.getCoversPath() + imageName;
	            File theImage = new File(imagePath);
	            if (theImage.exists()) {
		            JFrame frame = new JFrame(al.getTitle());
		            frame.setSize(new Dimension(500,500));
		            frame.add(new JLabel(new ImageIcon(imagePath)));
		            frame.setVisible(true);
	            } else {
	            	throw new NoCoverFoundException();
	            }
	        }
	    }
	}
	
	public void showPlaylistCover (String playlistTitle) throws NoCoverFoundException {
		for (PlayList pl : playlists) {
	        if (pl.getTitle().toLowerCase().equals(playlistTitle.toLowerCase())) {
	            String imageName = pl.getUUID().toString() + ".png";
	            String imagePath = MusicHub.getCoversPath() + imageName;
	            File theImage = new File(imagePath);
	            if (theImage.exists()) {
		            JFrame frame = new JFrame(pl.getTitle());
		            frame.setSize(new Dimension(500,500));
		            frame.add(new JLabel(new ImageIcon(imagePath)));
		            frame.setVisible(true);
	            } else {
	            	throw new NoCoverFoundException();
	            }
	        }
	    }
	}
	
	public void showElementCover (String elementTitle) throws NoCoverFoundException {
		for (AudioElement ae : elements) {
			if (ae.getTitle().toLowerCase().equals(elementTitle.toLowerCase())) {
				String imageName = ae.getUUID().toString() + ".png";
	            String imagePath = MusicHub.getCoversPath() + imageName;
	            File theImage = new File(imagePath);
	            if (theImage.exists()) {
		            JFrame frame = new JFrame(ae.getTitle());
		            frame.setSize(new Dimension(500,500));
		            frame.add(new JLabel(new ImageIcon(imagePath)));
		            frame.setVisible(true);
	            } else {
	            	for (Album al : albums) {
	            		List<UUID> uuids = al.getSongs();
	            		for (UUID uuid : uuids) {
	            			if (uuid.equals(ae.getUUID())) {
	            				showAlbumCover(al.getTitle());
	            			}
	            		}
	            	}
	            }
			}
		}
	}
}