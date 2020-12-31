package model.io;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import model.Board;
import model.Game;
import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;
import model.ship.Board2D;

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
		
		
		int contb1 = 0;
		int contb2 = 0;
		if(this.game.getBoard1() instanceof Board2D) {
			
			FrameGIF frame2d = new FrameGIF(this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
			
			for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
				for(int j = 0; j < this.game.getBoard1().getSize(); j++) {
					if(tablero1.charAt(i) == Board.NOTSEEN_SYMBOL) {
						try {
							frame2d.printSquare(j,i, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero1.charAt(i) == '\n') {
						contb1++;
					}else if(tablero1.charAt(i) == Board.WATER_SYMBOL) {
						try {
							frame2d.printSquare(contb1, i, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero1.charAt(i) == Board.HIT_SYMBOL) {
						try {
							frame2d.printSquare(contb1, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}
				}

			}
			
			for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
				try {
					frame2d.printSquare(i, this.game.getBoard1().getSize(), Color.DARK_GRAY);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}
			
			for(int i = 0; i < this.game.getBoard2().getSize(); i++) {
				for(int j = 0; j < this.game.getBoard2().getSize(); j++) {
					if(tablero2.charAt(i) == Board.NOTSEEN_SYMBOL) {
						try {
							frame2d.printSquare(j,i + this.game.getBoard2().getSize()+1, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero2.charAt(i) == '\n') {
						contb1++;
					}else if(tablero2.charAt(i) == Board.WATER_SYMBOL) {
						try {
							frame2d.printSquare(contb1, i, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero2.charAt(i) == Board.HIT_SYMBOL) {
						try {
							frame2d.printSquare(contb1, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			try {
				agif.addFrame(frame2d);
			} catch (BattleshipIOException e) {
				e.printStackTrace();
			}
			
		}else if (this.game.getBoard1() instanceof Board3D) {
			
			FrameGIF frame3d = new FrameGIF(this.game.getBoard1().getSize()*this.game.getBoard1().getSize(), this.game.getBoard1().getSize()*2+1);
			
			for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
				for(int j = 0; j < this.game.getBoard1().getSize()*this.game.getBoard1().getSize(); j++) {
						if(tablero1.charAt(i) == Board.NOTSEEN_SYMBOL) {
							try {
								frame3d.printSquare(j,i, Color.LIGHT_GRAY);
							}catch(BattleshipIOException e) {
								e.printStackTrace();
							}
							
						}else if(tablero1.charAt(i) == '\n') {
							contb1++;
						}else if(tablero1.charAt(i) == Board.WATER_SYMBOL) {
							try {
								frame3d.printSquare(contb1, i, Color.BLUE);
							} catch (BattleshipIOException e) {
								e.printStackTrace();
							}
						}else if(tablero1.charAt(i) == Board.HIT_SYMBOL) {
							try {
								frame3d.printSquare(contb1, i, Color.RED);
							} catch (BattleshipIOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			
				for(int i = 0; i < this.game.getBoard1().getSize(); i++) {
					try {
						frame3d.printSquare(this.game.getBoard1().getSize(), i, Color.ORANGE);
					} catch (BattleshipIOException e) {
						e.printStackTrace();
					}
				}
			

			try {
				agif.addFrame(frame3d);
			} catch (BattleshipIOException e) {
				e.printStackTrace();
			}
		}
				
	}

	
	public void close() {
        try {
            agif.saveFile(new File("files/output.gif"));
        } catch (BattleshipIOException e) {
            throw new RuntimeException();
        }
    }
}
