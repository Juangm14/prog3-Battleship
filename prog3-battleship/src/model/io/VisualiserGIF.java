package model.io;

import java.awt.Color;
import java.io.IOException;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF implements IVisualiser{

	private Game game;
	private AnimatedGIF agif;
	
	VisualiserGIF(Game game){
		if(game != null) {
			this.game = game;
			agif = new AnimatedGIF();
		}else {
			throw new NullPointerException();
		}
	}
	
	public void show() throws IOException {

		String tablero1 = this.game.getBoard1().show(false);
		String tablero2 = this.game.getBoard2().show(false);
		
		FrameGIF frame = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
		int contb1 = 0;
		int contb2 = 0;
		for(int i = 0; i < this.game.getBoard1().getSize() * 2; i++) {
			if(tablero1.charAt(i) == Board.NOTSEEN_SYMBOL) {
				try {
					frame.printSquare(contb1,i, Color.LIGHT_GRAY);
				}catch(BattleshipIOException e) {
					e.printStackTrace();
				}
				
			}else if(tablero1.charAt(i) == '\n') {
				contb1++;
			}else if(tablero1.charAt(i) == Board.WATER_SYMBOL) {
				try {
					frame.printSquare(contb1, i, Color.BLUE);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}else if(tablero1.charAt(i) == Board.HIT_SYMBOL) {
				try {
					frame.printSquare(contb1, i, Color.RED);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}
		}
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			try {
				frame.printSquare(this.game.getBoard1().getSize(), i, Color.ORANGE);
			} catch (BattleshipIOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			try {
				frame.printSquare(i, this.game.getBoard1().getSize(), Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = this.game.getBoard1().getSize(); i < this.game.getBoard2().getSize() * 4; i++) {
			if(tablero2.charAt(i) == Board.NOTSEEN_SYMBOL) {
				try {
					frame.printSquare(contb2,i, Color.LIGHT_GRAY);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}else if(tablero2.charAt(i) == '\n') {
				contb2++;
			}else if(tablero2.charAt(i) == Board.WATER_SYMBOL) {
				try {
					frame.printSquare(contb2, i, Color.BLUE);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}else if(tablero2.charAt(i) == Board.HIT_SYMBOL) {
				try {
					frame.printSquare(contb2, i, Color.RED);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}
		}
		for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
			try {
				frame.printSquare(this.game.getBoard1().getSize(), i, Color.ORANGE);
			} catch (BattleshipIOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			agif.addFrame(frame);
		} catch (BattleshipIOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		
	}
}
