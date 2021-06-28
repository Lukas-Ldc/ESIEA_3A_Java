package musichub.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CoversManager {
	
	public static void iAutoManager(String path, String uuid){
		File i = new File(iPathChecker(path));
		if (iTester(i)) {
			iResizer(i);
			File n = new File(MusicHub.getCoversPath() + uuid + ".png");
			iDeleter(n);
			iMover(i, n);
		}
	}
	
	public static void iResizer(File i) {
		if (iTester(i)) {
			try {
				BufferedImage iB = ImageIO.read(i);
		        Image iBR = iB.getScaledInstance(450, 450, Image.SCALE_SMOOTH);
		        BufferedImage iB2 = new BufferedImage(iBR.getWidth(null), iBR.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g2D = iB2.createGraphics();
		        g2D.drawImage(iBR, 0, 0, null);
		        g2D.dispose();
		        ImageIO.write(iB2,"png", i);
			} catch (IOException e) {
				System.out.println("Error, image could not be resized.");
			}
		}
	}
	
	public static void iMover(File i, File n) {
		if (iTester(i)) {
			iDeleter(n);
			i.renameTo(n);
		}
	}
	
	public static String iPathChecker(String file) {
		if (file.contains("/")) {
			return file;
		}
		else {
			return MusicHub.getCoversPath() + file;
		}
	}
	
	public static void iDeleter(File i) {
		if (iTester(i)) {
			i.delete();
		}
	}
	
	public static boolean iTester(File i) {
		if (i.exists() && !i.isDirectory()) {
			return true;
		}
		else {
			return false;
		}
	}

}