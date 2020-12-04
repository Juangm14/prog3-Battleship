package model.io;

import java.io.IOException;

import model.Board;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

public class Game {
	
	private Board tablero1;
	private Board tablero2;
	private IPlayer jugador1;
	private IPlayer jugador2;
	private Boolean gameStarted;
	private int shootCounter;
	private int nextToShoot;

	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) throws NullPointerException{
		if(b1 == null || b2 == null || p1 == null || p2 == null) {
			throw new NullPointerException();
		}else {
			tablero1 = b1;
			tablero2 = b2;
			jugador1 = p1;
			jugador2 = p2;
			gameStarted = false;
		}
	}
	
	public Board getBoard1() {
		return tablero1;
	}
	
	public Board getBoard2() {
		return tablero2;
	}
	
	public IPlayer getPlayer1() {
		return jugador1;
	}
	
	public IPlayer getPlayer2() {
		return jugador2;
	}
	
	public void start() throws BattleshipIOException {
		
		
		jugador1.putCrafts(tablero1);
		jugador2.putCrafts(tablero2);

		
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 1;
	}
	
	public Boolean gameEnded() {
		if((tablero1.areAllCraftsDestroyed() || tablero2.areAllCraftsDestroyed()) && gameStarted == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean playNext() throws BattleshipIOException{
		
		if(nextToShoot == 1) {
			jugador1.nextShoot(tablero2);
			nextToShoot = 2;
			shootCounter++;
			return true;

		}else if(nextToShoot == 2) {
			jugador2.nextShoot(tablero1);
			nextToShoot = 1;
			shootCounter++;
			return true;
		}
		
		return false;
	}
	
	public IPlayer getPlayerLastShoot() {
		
		if(nextToShoot == 1) {
			return jugador2;
		}else if(nextToShoot == 2) {
			return jugador1;
		}else {
			return null;
		}
	}
	
	public void playGame(IVisualiser iv) throws BattleshipIOException {
		
		this.start();

		iv.show();
		
		while(this.playNext() != false) {
			iv.show();
		}
		
		iv.close();
	}

	public String toString() {
		
		String game = "";
		if(this.gameStarted == false) {
			game += "=== GAME NOT STARTED ===\n";
			game += "==================================\n";
			game += jugador1.getName() + "\n";
			game += "==================================\n";
			game += tablero1.toString() + "\n";
			game += "==================================\n";
			game += jugador2.getName() + "\n";
			game += "==================================\n";
			game += tablero2.toString() + "\n";
			game += "==================================\n";
		}else if(this.gameEnded()) {
			game += "=== GAME ENDED ===\n";
			game += "==================================\n";
			game += jugador1.getName() + "\n";
			game += "==================================\n";
			game += tablero1.toString() + "\n";
			game += "==================================\n";
			game += jugador2.getName() + "\n";
			game += "==================================\n";
			game += tablero2.toString() + "\n";
			game += "==================================\n";
			game += "Number of shots: " + shootCounter;
			if(tablero1.areAllCraftsDestroyed()) {
				game += jugador1.getName() + " wins";
			}else {
				game += jugador2.getName() + " wins";
			}
			
		}else if(this.gameStarted == true){
			game += "=== ONGOING GAME ===\n";
			game += "==================================\n";
			game += jugador1.getName() + "\n";
			game += "==================================\n";
			game += tablero1.toString() + "\n";
			game += "==================================\n";
			game += jugador2.getName() + "\n";
			game += "==================================\n";
			game += tablero2.toString() + "\n";
			game += "==================================\n";
			game += "Number of shots: " + shootCounter;
		}
		
		return game;
	}
}











