package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public abstract class Board {

	/** The hit symbol. */
	public static char HIT_SYMBOL = '•';
	
	/** The water symbol. */
	public static char WATER_SYMBOL = ' ';
	
	/** The notseen symbol. */
	public static char NOTSEEN_SYMBOL = '?';
	
	/** The Board SEPARATOR. */
	public static char Board_SEPARATOR = '|';
	
	/** The max board size. */
	public static int MAX_BOARD_SIZE = 20;
	
	/** The min board size. */
	public static int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	private Map<Coordinate,Craft> board = new HashMap<>();
	private Set<Coordinate> seen = new HashSet<>();
	

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		numCrafts = 0;
		destroyedCrafts = 0;
		board = new HashMap<>();
		seen = new HashSet<>();
		
		if(size >= MIN_BOARD_SIZE && size <= MAX_BOARD_SIZE) {
			this.size = size;
		}else {
			this.size = MIN_BOARD_SIZE;
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	abstract public boolean checkCoordinate(Coordinate c);
	
	/**
	 * Adds the craft.
	 *
	 * @param craft the craft
	 * @param position the position
	 * @return true, if successful
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		
		Set<Coordinate> coordenadasAbsolutas = craft.getAbsolutePositions(position);
		Set<Coordinate> coordenadasAdyacentes = new HashSet<>();
		Set<Coordinate> coordenadasBoard = board.keySet();
		
		for(Coordinate auxiliarAbsolutas:coordenadasAbsolutas) {
			if(!checkCoordinate(auxiliarAbsolutas)) {
				throw new InvalidCoordinateException(auxiliarAbsolutas);
			}
		}
		
		for(Coordinate auxiliarAbsolutas:coordenadasAbsolutas) {	
			if(coordenadasBoard.contains(auxiliarAbsolutas)){
				throw new OccupiedCoordinateException(auxiliarAbsolutas);
			}else {
				for(Coordinate vecino:auxiliarAbsolutas.adjacentCoordinates()) {
						coordenadasAdyacentes.add(vecino);
				}
			} 
		}
		
		for(Coordinate vecino:coordenadasAdyacentes) {
			if(coordenadasBoard.contains(vecino)) {
				throw new NextToAnotherCraftException(vecino);
			}
		}
		
		for(Coordinate pos:coordenadasAbsolutas) {
			board.put(pos, craft);
		}
		
		numCrafts++;
		craft.setPosition(position);
		return true;	
	}


	/**
	 * Gets the craft.
	 *
	 * @param c the c
	 * @return the craft
	 */
	public Craft getCraft(Coordinate c) {	
		return board.get(c);
	}


	/**
	 * Checks if is seen.
	 *
	 * @param c the c
	 * @return true, if is seen
	 */
	public boolean isSeen(Coordinate c) {
		if(seen.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return the cell status
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public CellStatus hit(Coordinate c) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		
		Craft craft = board.get(c);
		Set<Coordinate> coordenadasTablero = board.keySet();
		
		if(!checkCoordinate(c)) {
			throw new InvalidCoordinateException(c);

		}else {
			seen.add(c.copy());
		}
		
		if (!coordenadasTablero.contains(c)) {
			return CellStatus.WATER;
		}else if(craft.isHit(c)) {
			throw new CoordinateAlreadyHitException(c);
		}else if(craft.hit(c)) {
			if(craft.isShotDown()) {
				for(Coordinate vecino:getNeighborhood(craft, craft.getPosition())) {
						seen.add(vecino.copy());
				}
				destroyedCrafts++;
				return CellStatus.DESTROYED;
			}else {
				return CellStatus.HIT;
			}
		}else {
			return CellStatus.WATER;
		}	
	}


	/**
	 * Are all crafts destroyed.
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() {
		if(destroyedCrafts == numCrafts) {
			return true;
		}
		
		return false;
	}


	/**
	 * Gets the neighborhood.
	 *
	 * @param craft the craft
	 * @param position the position
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft craft, Coordinate position) {
		
		Objects.requireNonNull(craft);
		Objects.requireNonNull(position);
		
		Set<Coordinate>	coordenadasAbsolutas =	craft.getAbsolutePositions(position);
		Set<Coordinate> neighborhood = new HashSet<>();
		
		for(Coordinate absoluta:coordenadasAbsolutas) {
			Set<Coordinate> adyacentes = absoluta.adjacentCoordinates();
			for(Coordinate vecino:adyacentes) {
				if(checkCoordinate(vecino) && !neighborhood.contains(vecino) && !coordenadasAbsolutas.contains(vecino)) {
					neighborhood.add(vecino);
				}
			}
		}				
		return neighborhood;
	}

	/**
	 * Gets the neighborhood.
	 *
	 * @param craft the craft
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft craft) {
			return getNeighborhood(craft, craft.getPosition());	
	}
	

	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public abstract String show(boolean unveil);
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String cadena = "Board " + size + ";"+ " crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
		
		return cadena;
	}
	
	//MIOS
	

	public void deleteCrafts(Coordinate coor) {
		
		Craft c = this.getCraft(coor);
		
		Set<Coordinate> conj = c.getAbsolutePositions();
	
		for(Coordinate crd:conj) {
			c = board.remove(crd);
		}
		
		if(c != null) {
			numCrafts--;
		}
	}
	
	
	public void deleteCrafts2(Craft crf) {
		
		Set<Coordinate> conjCoord= crf.getAbsolutePositions();
		Craft c = null;
		
		for(Coordinate coord:conjCoord) {
			c = board.remove(coord);
		}
		
		if(c != null) {
			numCrafts--;
		}
	}	
	
	public void deleteCraftsType(char symbol) {
		
		Collection<Craft> conjNaves = board.values();

		for(Craft nave:conjNaves) {
			if(nave.getSymbol() == symbol) {
			}
		}
		
	}

}

