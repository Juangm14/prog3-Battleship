package model.io.gif;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * The Class FrameGIF.
 */
public class FrameGIF {

	private static final int PIXELS_SQUARE = 50;
	private int width;
	private int heigh;

	private Graphics2D ig2;
	private BufferedImage bi; 

	/**
	 * Instantiates a new frame GIF.
	 *
	 * @param w the w
	 * @param h the h
	 */
	public FrameGIF(int w, int h) {
		this.width = w;
		this.heigh = h;
		
		bi = new BufferedImage(w*PIXELS_SQUARE, h*PIXELS_SQUARE, BufferedImage.TYPE_3BYTE_BGR);
	    ig2 = bi.createGraphics();
	    ig2.fillRect(0, 0, w*PIXELS_SQUARE - 1, h*PIXELS_SQUARE - 1);
	}
	

	/**
	 * Prints the square.
	 *
	 * @param x the x
	 * @param y the y
	 * @param colour the colour
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public void printSquare(int x, int y, Color colour) throws BattleshipIOException {
		if (x<0 || x>=width || y<0 || y>=heigh) {
			throw new BattleshipIOException("Error: wrong position to print a square: ("+x+","+y+")");
		}
		
		ig2.setPaint(colour);
		ig2.fill(new Rectangle(x*PIXELS_SQUARE, y*PIXELS_SQUARE, PIXELS_SQUARE, PIXELS_SQUARE));
	}

	/**
	 * Gets the buffered image.
	 *
	 * @return the buffered image
	 */
	BufferedImage getBufferedImage() {
		return bi;
	}
	
	/**
	 * Save file.
	 *
	 * @param file the file
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public void saveFile(File file) throws BattleshipIOException {
	    try {
			ImageIO.write(bi, "GIF", file);
		} catch (IOException e) {
			throw new BattleshipIOException("Error: problem saving file");
		}
	}
	
	
}