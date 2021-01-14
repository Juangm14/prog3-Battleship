package model;

import java.io.IOException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.score.CraftScore;
import model.score.HitScore;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {
	
	private Board tablero1;
	private Board tablero2;
	private IPlayer jugador1;
	private IPlayer jugador2;
	private Boolean gameStarted;
	private int shootCounter;
	private int nextToShoot;
	private HitScore hitScore1;
	private HitScore hitScore2;
	private CraftScore craftScore1;
	private CraftScore craftScore2;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param b1 the b 1
	 * @param b2 the b 2
	 * @param p1 the p 1
	 * @param p2 the p 2
	 */
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2){
		if(b1 == null || b2 == null || p1 == null || p2 == null) {
			throw new NullPointerException();
		}else {
			
			tablero1 = b1;
			tablero2 = b2;
			jugador1 = p1;
			jugador2 = p2;
			gameStarted = false;
			hitScore1 = new HitScore(p1);
			hitScore2 = new HitScore(p2);
			craftScore1 = new CraftScore(p1);
			craftScore2 = new CraftScore(p2);
		}
	}
	
	/**
	 * Gets the board 1.
	 *
	 * @return the board 1
	 */
	public Board getBoard1() {
		return tablero1;
	}
	
	/**
	 * Gets the board 2.
	 *
	 * @return the board 2
	 */
	public Board getBoard2() {
		return tablero2;
	}
	
	/**
	 * Gets the player 1.
	 *
	 * @return the player 1
	 */
	public IPlayer getPlayer1() {
		return jugador1;
	}
	
	/**
	 * Gets the player 2.
	 *
	 * @return the player 2
	 */
	public IPlayer getPlayer2() {
		return jugador2;
	}
	
	/**
	 * Start.
	 */
	public void start() {
		
		try {
			jugador1.putCrafts(tablero1);
			jugador2.putCrafts(tablero2);
		} catch (IllegalArgumentException | BattleshipIOException | InvalidCoordinateException | 
				OccupiedCoordinateException | NextToAnotherCraftException e) {
			throw new RuntimeException();
		}
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 1;
	}
	
	/**
	 * Game ended.
	 *
	 * @return the boolean
	 */
	public Boolean gameEnded() {

		if(tablero1.areAllCraftsDestroyed() && gameStarted) {
			return true;
		}else if(tablero2.areAllCraftsDestroyed() && gameStarted) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Play next.
	 *
	 * @return the boolean
	 */
	public Boolean playNext(){
		
		CellStatus cellStatus;
		Coordinate c;
		if(nextToShoot == 1) {
			try {
				if((c = jugador1.nextShoot(tablero2)) != null) {
					cellStatus = jugador1.getLastShotStatus();
					hitScore1.score(cellStatus);
					if(cellStatus.equals(CellStatus.DESTROYED)) {
						craftScore1.score(tablero2.getCraft(c));
					}
					shootCounter++;
					nextToShoot = 2;
					return true;
				}
			}catch(BattleshipIOException | InvalidCoordinateException e) {
				throw new RuntimeException();
			}catch(CoordinateAlreadyHitException e) {
				System.out.println("Action by "+ jugador1.getName() + e.getMessage());
	            shootCounter++;
	            nextToShoot = 2;
	            return true;
			}
		}else if(nextToShoot == 2) {
			try {
				if((c = jugador2.nextShoot(tablero1)) != null) {
					cellStatus = jugador2.getLastShotStatus();
					hitScore2.score(cellStatus);
					if(cellStatus.equals(CellStatus.DESTROYED)) {
						craftScore2.score(tablero1.getCraft(c));
					}
					shootCounter++;
					nextToShoot = 1;
					return true;
				}
			}catch(BattleshipIOException | InvalidCoordinateException e) {
				throw new RuntimeException();
			}catch(CoordinateAlreadyHitException e) {
				System.out.println("Action by "+ jugador2.getName() + e.getMessage());
	            shootCounter++;
	            nextToShoot = 1;
	            return true;
			}
		}
	return false;
	}
	
	/**
	 * Gets the player last shoot.
	 *
	 * @return the player last shoot
	 */
	public IPlayer getPlayerLastShoot() {
		
		if(shootCounter > 0) {
			if(nextToShoot == 1) {
				return jugador2;
			}else if(nextToShoot == 2) {
				return jugador1;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/**
	 * Play game.
	 *
	 * @param iv the iv
	 */
	public void playGame(IVisualiser iv) {
		
		this.start();
		
        try {
			iv.show();
		} catch (IOException e) {
			throw new RuntimeException();
		}
        
        while(playNext() != false && gameEnded() != true) {
            try {
				iv.show();
			} catch (IOException e) {
				throw new RuntimeException();
			}
        }
        
        try {
			iv.show();
		} catch (IOException e) {
			throw new RuntimeException();
		}

        iv.close();
		
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
				game += "\n" + jugador2.getName() + " wins";
			}else if(tablero2.areAllCraftsDestroyed()){
				game += "\n" + jugador1.getName() + " wins";
			}
		}
		return game;
	}
	
	public HitScore getHitScorePlayer1() {
		return hitScore1;
	}
	
	public HitScore getHitScorePlayer2() {
		return hitScore2;
	}
	
	public CraftScore getCraftScorePlayer1() {
		return craftScore1;
	}
	
	public CraftScore getCraftScorePlayer2() {
		return craftScore2;
	}
	
	public String getScoreInfo() {
		
		String info = "";
		
		info += "Player 1\n";
		info += "HitScore: " + getHitScorePlayer1().toString() + "\n";
		info += "CraftScore: " + getCraftScorePlayer1().toString() + "\n";
		info += "--------------\n";
		info += "Player 2\n";
		info += "HitScore: " + getHitScorePlayer2().toString() + "\n";
		info += "CraftScore: " + getCraftScorePlayer2().toString();
		
		return info;
	}

	public Coordinate moveCrafts(Craft crf, Coordinate coor) {
		if(!this.gameStarted) {
			if(crf.getPosition() != coor) {
				crf.setPosition(coor);
				return coor;
			}else {
				return null;
			}
		}else {
			System.out.println("El juego ha empezado no se puede mover la nave.");
			return null;
		}
	}
}












