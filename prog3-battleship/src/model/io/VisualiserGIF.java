package model.io;

import model.Board;
import model.exceptions.io.BattleshipIOException;

import java.awt.Color;
import java.io.File;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF {

	private Game game;
	AnimatedGIF animatedG;
	
	VisualiserGIF(Game game) throws NullPointerException{
		if(game != null) {
			this.game = game;
			
		}else {
			throw new NullPointerException();
		}
	}
	
	public void show() {
		String printTablero1 = this.game.getBoard1().show(false);
		String printtablero2 = this.game.getBoard2().show(false);
		
		
		FrameGIF frame1 = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
		FrameGIF frame2 = new FrameGIF(this.game.getBoard2().getSize(), this.game.getBoard2().getSize()*2+1);
		
		
	}
	
	public void close() throws BattleshipIOException {
		
		
		animatedG.saveFile(new File("files/output.gif"));
	}
}
