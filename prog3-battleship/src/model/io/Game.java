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

	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2){
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
		
		try {
			jugador1.putCrafts(tablero1);
			jugador2.putCrafts(tablero2);
			
			gameStarted = true;
			shootCounter = 0;
			nextToShoot = 1;
		} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException
				| BattleshipIOException e) {
			throw new RuntimeException();
		}
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
			try {
				jugador1.nextShoot(tablero2);
				nextToShoot = 2;
				shootCounter++;
				return true;
			} catch (InvalidCoordinateException | BattleshipIOException e) {
				throw new RuntimeException();
			}catch(CoordinateAlreadyHitException e) {
				System.out.println("Action by " + jugador1.getName());
			}
		}else if(nextToShoot == 2) {
			try {
				jugador2.nextShoot(tablero1);
				nextToShoot = 1;
				shootCounter++;
				return true;
			} catch (InvalidCoordinateException | BattleshipIOException e) {
				throw new RuntimeException();
			}catch(CoordinateAlreadyHitException e){
				System.out.println("Action by " + jugador2.getName());
			}
			

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
		
	
		
	}

	public String toString() {
		
		String game = "";
		
		if(gameStarted ==  false) {
			game += "=== GAME NOT STARTED ===\n";
			game += "==================================\n";
		}else if(gameEnded()){
			game += "=== GAME ENDED ===\n";
			game += "==================================\n";
		}else if(gameStarted) {
			game += "=== ONGOING GAME ===\n";
			game += "==================================\n";
		}
		
		game += jugador1.getName() + "\n";
		game += "==================================\n";
		game += tablero1.show(false) + "\n";
		game += "==================================\n";
		game += jugador2.getName() + "\n";
		game += "==================================\n";
		game += tablero2.show(false) + "\n";
		game += "==================================\n";
		game += "Number of shots: " + shootCounter;
		if(gameEnded()) {
			
			if(tablero1.areAllCraftsDestroyed()) {
				game += jugador1.getName() + " wins";
			}else if(tablero2.areAllCraftsDestroyed()){
				game += jugador2.getName() + " wins";
			}
		}
		return game;
	}
}











