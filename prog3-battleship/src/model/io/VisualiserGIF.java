package model.io;

import java.awt.Color;

import model.Board;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF implements IVisualiser{

	private Game game;
	
	VisualiserGIF(Game game) throws NullPointerException{
		if(game != null) {
			this.game = game;
		}else {
			throw new NullPointerException();
		}
	}
	
	public void show() throws BattleshipIOException {
		AnimatedGIF agif = null;
		String tablero1 = this.game.getBoard1().show(false);
		String tablero2 = this.game.getBoard2().show(false);
		
		FrameGIF frame = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
		int contb1 = 0;
		int contb2 = 0;
		for(int i = 0; i < this.game.getBoard1().getSize() * 2; i++) {
			if(tablero1.charAt(i) == Board.NOTSEEN_SYMBOL) {
				frame.printSquare(contb1,i, Color.LIGHT_GRAY);
			}else if(tablero1.charAt(i) == '\n') {
				contb1++;
			}else if(tablero1.charAt(i) == Board.WATER_SYMBOL) {
				frame.printSquare(contb1, i, Color.BLUE);
			}else if(tablero1.charAt(i) == Board.HIT_SYMBOL) {
				frame.printSquare(contb1, i, Color.RED);
			}
		}
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			frame.printSquare(this.game.getBoard1().getSize(), i, Color.ORANGE);
		}
		
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			frame.printSquare(i, this.game.getBoard1().getSize(), Color.DARK_GRAY);
		}
		
		for(int i = this.game.getBoard1().getSize(); i < this.game.getBoard2().getSize() * 4; i++) {
			if(tablero2.charAt(i) == Board.NOTSEEN_SYMBOL) {
				frame.printSquare(contb2,i, Color.LIGHT_GRAY);
			}else if(tablero2.charAt(i) == '\n') {
				contb2++;
			}else if(tablero2.charAt(i) == Board.WATER_SYMBOL) {
				frame.printSquare(contb2, i, Color.BLUE);
			}else if(tablero2.charAt(i) == Board.HIT_SYMBOL) {
				frame.printSquare(contb2, i, Color.RED);
			}
		}
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			frame.printSquare(this.game.getBoard1().getSize(), i, Color.ORANGE);
		}
		
		agif.addFrame(frame);
	}
	
	public void close() {
		
	}
}
