/**
 * @author Juan García Martínez 20085694R
 */
package model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

public abstract class Board {

	public static char HIT_SYMBOL = '•';
	public static char WATER_SYMBOL = ' ';
	public static char NOTSEEN_SYMBOL = '?';
	public static char Board_SEPARATOR = '|';
	public static int MAX_BOARD_SIZE = 20;
	public static int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	private Map<Coordinate,Craft> board = new HashMap<>();
	private Set<Coordinate> seen = new HashSet<>();
	
	/**
	 * Construtor del tablero donde si se comprueba que el tamaño pasado esta dentro del rango del tablero
	 * @param size Tamaño del tablero
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
	 * 
	 * @return tamaño del tablero
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Comprobamos si una coordenada 2D o 3D esta dentro del rango del tablero
	 * @param c coordenada que vamos a evaluar
	 * @return devuelve un booleano confirmando si esta dentro del rango
	 */
	abstract public boolean checkCoordinate(Coordinate c);
	
	/**
	 * Añadimos un barco 2D o 3D al tablero
	 * @param ship barco
	 * @param position coordenada del barco
	 * @return true si se ha añadido correctamente y false en caso que no se haya podido añadir.
	 * @throws InvalidCoordinateException 
	 * @throws OccupiedCoordinateException 
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
	 * Metodo donde cogemos un barco con cierta coordenada
	 * @param c coordenada
	 * @return barco al que pertenece la coordenada que le hemos pasado
	 */
	public Craft getCraft(Coordinate c) {	
		return board.get(c);
	}

	/**
	 * Metodo con el que vemos si cierta coordenada ha sido vista
	 * @param c coordenada
	 * @return si la coordenada que le pasamos ha sido vista devuelve true, sino false
	 */
	public boolean isSeen(Coordinate c) {
		if(seen.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Simula el lanzamiento de un torpedo en una coordenada c del tablero
	 * @param c coordenada
	 * @return devuelve agua en caso de que no alcance un objetivo(barco), destroyed si era la ultima coordenada del barco
	 * y hit si alcanza a una coordenada del barco pero no es la ultima.
	 * @throws CoordinateAlreadyHitException 
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
	 * Comprobamos si todos los barcos han sido destruidos.
	 * @return true si todos los barcos han sido destruidos y false en caso contrario
	 */
	public boolean areAllCraftsDestroyed() {
		if(destroyedCrafts == numCrafts) {
			return true;
		}
		
		return false;
	}

	/**
	 * Metodo donde cogemos todos los vecinos de un barco, sin incluir las absolutas del barco y las que esten fuera del
	 * rango del tablero
	 * @param ship barco del que queremos coger los vecinos
	 * @param position coordenada del barco
	 * @return conjunto de coordenadas vecinas
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
	 * Llamamos a la funcion getNeighborhood con la posicion del barco
	 * @param ship barco
	 * @return conjunto de coordenadas vecinas
	 */
	public Set<Coordinate> getNeighborhood(Craft craft) {
			return getNeighborhood(craft, craft.getPosition());	
	}
	
	/**
	 * Mostramos el tablero con los barcos por pantalla
	 * @param unveil si esta en true lo vemos como si fuesemos enemigos y en false lo veriamos como si fuesemos aliados
	 * @return devuelve el tablero por pantalla
	 */
	public abstract String show(boolean unveil);
	
	public String toString() {
		String cadena = "Board " + size + ";"+ " crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
		
		return cadena;
	}

}

