package model.io;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import model.Board;
import model.Game;
import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;
import model.ship.Board2D;
import model.ship.Coordinate2D;

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

		if(this.game.getBoard1() instanceof Board2D) {
			tablero1 = tablero1.replace("\n", "");
			tablero2 = tablero2.replace("\n", "");
			System.err.println(tablero1.length());
			
			int tam = game.getBoard1().getSize();
			
			FrameGIF frame2d = new FrameGIF(tam, tam*2+1);
			
			for(int i = 0; i < tam; i++) {
				for(int j = 0; j < tam; j++) {
					if(tablero1.charAt(j+i*tam) == Board.NOTSEEN_SYMBOL) {
						try {
							frame2d.printSquare(j,i, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero1.charAt(j+i*game.getBoard1().getSize()) == Board.WATER_SYMBOL) {
						try {
							frame2d.printSquare(j, i, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero1.charAt(j+i*game.getBoard1().getSize()) == Board.HIT_SYMBOL) {
						try {
							frame2d.printSquare(j, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero1.charAt(j+i*game.getBoard1().getSize()) == game.getBoard1().getCraft(new Coordinate2D(j,i)).getSymbol()) {
						try {
							frame2d.printSquare(j, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			for(int i = 0; i < tam; i++) {
				try {
					frame2d.printSquare(i, tam, Color.DARK_GRAY);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}
			
			for(int i = 0; i < tam; i++) {
				for(int j = 0; j < tam; j++) {
					
					if(tablero2.charAt(j+i*tam) == Board.NOTSEEN_SYMBOL) {
						try {
							frame2d.printSquare(j,i+tam+1, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero2.charAt(j+i*game.getBoard1().getSize()) == Board.WATER_SYMBOL) {

						try {
							frame2d.printSquare(j, i+tam+1, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}

					}else if(tablero2.charAt(j+i*game.getBoard2().getSize()) == Board.HIT_SYMBOL) {
						try {
							frame2d.printSquare(j, i+tam+1, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero2.charAt(j+i*game.getBoard2().getSize()) == game.getBoard2().getCraft(new Coordinate2D(j,i)).getSymbol()) {
						try {
							frame2d.printSquare(j, i+tam+1, Color.RED);
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
			int tam = game.getBoard1().getSize();
			
			FrameGIF frame3d = new FrameGIF(tam*tam+tam-1, tam*2+1);
			
			
			for(int i = 0; i < tam;i++) {
				for(int j = 0; j < tam*tam+tam-1; j++) {
					if(tablero1.charAt(j+i*(tam*tam+tam)) == Board.NOTSEEN_SYMBOL) {
						try {
							frame3d.printSquare(j,i, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero1.charAt(j+i*(tam*tam+tam)) == Board.WATER_SYMBOL) {
						try {
							frame3d.printSquare(j, i, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero1.charAt(j+i*(tam*tam+tam)) == Board.HIT_SYMBOL) {
						try {
							frame3d.printSquare(j, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero1.charAt(j+i*(tam*tam+tam)) == Board.Board_SEPARATOR) {
						try {
							frame3d.printSquare(j, i, Color.ORANGE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							frame3d.printSquare(j, i, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			for(int i = 0; i < tam*tam+tam-1; i++) {
				try {
					frame3d.printSquare(i, tam, Color.DARK_GRAY);
				} catch (BattleshipIOException e) {
					e.printStackTrace();
				}
			}
			
			for(int i = 0; i < tam;i++) {
				for(int j = 0; j < tam*tam+tam-1; j++) {
					if(tablero2.charAt(j+i*(tam*tam+tam)) == Board.NOTSEEN_SYMBOL) {
						try {
							frame3d.printSquare(j,i+tam+1, Color.LIGHT_GRAY);
						}catch(BattleshipIOException e) {
							e.printStackTrace();
						}
						
					}else if(tablero2.charAt(j+i*(tam*tam+tam)) == Board.WATER_SYMBOL) {
						try {
							frame3d.printSquare(j, i+tam+1, Color.BLUE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero2.charAt(j+i*(tam*tam+tam)) == Board.HIT_SYMBOL) {
						try {
							frame3d.printSquare(j, i+tam+1, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else if(tablero2.charAt(j+i*(tam*tam+tam)) == Board.Board_SEPARATOR) {
						try {
							frame3d.printSquare(j, i+tam+1, Color.ORANGE);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							frame3d.printSquare(j, i+tam+1, Color.RED);
						} catch (BattleshipIOException e) {
							e.printStackTrace();
						}
					}
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
