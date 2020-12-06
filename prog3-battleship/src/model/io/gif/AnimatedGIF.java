package model.io.gif;

import java.io.File;
import java.io.IOException;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;

import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * The Class AnimatedGIF.
 */
public class AnimatedGIF {
	private GifImage gifImage;

	/**
	 * Instantiates a new animated GIF.
	 */
	public AnimatedGIF() {
		gifImage = new GifImage();
		gifImage.setDefaultDelay(50);
		gifImage.setLoopNumber(0);
	}
	
	/**
	 * Adds the frame.
	 *
	 * @param gif the gif
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public void addFrame(FrameGIF gif) throws BattleshipIOException {
        try {
			gifImage.addGifFrame(new GifFrame(gif.getBufferedImage()));
		} catch (InterruptedException e) {
			throw new BattleshipIOException("Error: problem adding frame");
		}
	}

	/**
	 * Save file.
	 *
	 * @param file the file
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public void saveFile(File file) throws BattleshipIOException {
	    try {
	        GifEncoder.encode(gifImage, file);
		} catch (IOException e) {
			throw new BattleshipIOException("Error: problem saving file");
		}
	}
}