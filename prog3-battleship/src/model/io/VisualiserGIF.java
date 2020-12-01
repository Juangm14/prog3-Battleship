package model.io;

import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF {

	private Game game;
	
	VisualiserGIF(Game game) throws NullPointerException{
		if(game != null) {
			this.game = game;
			AnimatedGIF animatedG;
		}else {
			throw new NullPointerException();
		}
	}
	
	public void show() {
		this.game.getBoard1().show(false);
		this.game.getBoard2().show(false);
		
		FrameGIF frame1 = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
		FrameGIF frame2 = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
		
	}
	
	public void close() {
		
	}
}
