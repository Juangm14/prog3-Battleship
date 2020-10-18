package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
	
    public static char HIT_SYMBOL = '•';//Simbolo si cierta posicion ha sido alcanzada por un torpedo
    public static char WATER_SYMBOL = ' ';//Simbolo si cierta posicion no existe un barco
    public static char NOTSEEN_SYMBOL = '?';//Simbolo si no se ha visto cierta posicion
    private static int MAX_BOARD_SIZE = 20;//Maximo tamaño del tablero
    private static int MIN_BOARD_SIZE = 5;//Minimo tamaño del tablero
	
	private int size;//Tamaño del tablero
	private int numCrafts;//Numero de barcos en el tablero
	private int destroyedCrafts;//Numero de barcos destruidos
	private Map<Coordinate,Ship> board = new HashMap<>();//Tablero con sus barcos y las posiciones de estos.
	private Set<Coordinate> seen = new HashSet<>();//Conjunto de coordenadas que han sido vistas
	
	/**
	 * Constructor del tablero
	 * @param size tamaño del tablero size * size
	 */
	public Board(int size) {
		
		if(size >= MIN_BOARD_SIZE && size <= MAX_BOARD_SIZE) {
			this.size = size;
		}else {
			size = MIN_BOARD_SIZE;
			System.err.println("No esta dentro del rango del tablero");
		}
		
		numCrafts = 0;
		destroyedCrafts = 0;
		board = new HashMap<>();
		seen = new HashSet<>();
	}
	/**
	 * 
	 * @return tamaño del tablero
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Comprueba si una coordenada dentro del tablero
	 * @param c coordenada
	 * @return true si esta dentro del tablero y false en caso contrario.
	 */
	public boolean checkCoordinate(Coordinate c) {
		if(c.get(0) >= 0 && c.get(0) < size && c.get(1) >= 0 && c.get(1) < size){
			return true;
		}
		
		return false;
	}
	/**
	 * Añadimos un barco al tablero
	 * @param ship barco
	 * @param position coordenada del barco
	 * @return true si se ha añadido correctamente y false en caso que no se haya podido añadir.
	 */
	public boolean addShip(Ship ship, Coordinate position) {
		
		Set<Coordinate> coordenadasAbsolutas = ship.getAbsolutePositions(position);
		Set<Coordinate> coordenadasAdyacentes = new HashSet<>();
		Set<Coordinate> coordenadasBoard = board.keySet();
		
		for(Coordinate auxiliarAbsolutas:coordenadasAbsolutas) {
			if(!checkCoordinate(auxiliarAbsolutas)) {
				System.err.println("La coordenada no se encuentra dentro del rango del tablero.");
				return false;
			}else if(coordenadasBoard.contains(auxiliarAbsolutas)){
				System.err.println("Hay un barco en una de las coordenadas absolutas.");
				return false;
			}else {
				for(Coordinate vecino:auxiliarAbsolutas.adjacentCoordinates()) {
					if(checkCoordinate(vecino)) {
						coordenadasAdyacentes.add(vecino);
					}
				}
			} 
		}
		
		for(Coordinate vecino:coordenadasAdyacentes) {
			if(coordenadasBoard.contains(vecino)) {
				System.err.println("Existe un barco en la vecindad.");
				return false;
			}
		}
		
		for(Coordinate pos:coordenadasAbsolutas) {
			board.put(pos, ship);
		}
		
		numCrafts++;
		ship.setPosition(position);
		return true;	
	}
	/**
	 * Metodo donde cogemos un barco con cierta coordenada
	 * @param c coordenada
	 * @return barco al que pertenece la coordenada que le hemos pasado
	 */
	public Ship getShip(Coordinate c) {	
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
	 */
	public CellStatus hit(Coordinate c) {
		
		Ship ship = board.get(c);
		Set<Coordinate> coordenadasTablero = board.keySet();
		
		if(!checkCoordinate(c)) {
			System.err.println("Error HIT: La coordenada no entra dentro del rango del tablero.");
			return CellStatus.WATER;
		}else {
			seen.add(c);
			
			if (!coordenadasTablero.contains(c)) {
				seen.add(c);
				return CellStatus.WATER;
			}else if(ship.isHit(c)) {
				return CellStatus.WATER;
			}else if(ship.hit(c)) {
				if(ship.isShotDown()) {
					Set<Coordinate> vecinos = getNeighborhood(ship);
					for(Coordinate vecino:vecinos) {
						seen.add(vecino);
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
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		Set<Coordinate>	coordenadasAbsolutas =	ship.getAbsolutePositions();
		Set<Coordinate> neighborhood = new HashSet<>();
		
		for(Coordinate absoluta:coordenadasAbsolutas) {
			Set<Coordinate> adyacentes = absoluta.adjacentCoordinates();
			if(checkCoordinate(absoluta)) {
				for(Coordinate vecino:adyacentes) {
					if(checkCoordinate(vecino) && !neighborhood.contains(vecino) && !coordenadasAbsolutas.contains(vecino)) {
						neighborhood.add(vecino);
					}
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
	public Set<Coordinate> getNeighborhood(Ship ship) {
			return getNeighborhood(ship, ship.getPosition());	
	}
	/**
	 * 
	 * @param unveil si es true devolvemos el tablero se muestra el tablero completo y false como lo veria el adversario
	 * @return cadena de caracteres representando el tablero
	 */
	public String show(boolean unveil) {
		
		String mapa = "";
		Set<Coordinate> tablero = board.keySet();
	
		if(unveil) {
			for(int i = 0; i < size; i++){
				if(i != 0) {
					mapa += "\n";
				}
				for(int j = 0; j < size; j++) {
					if(tablero.contains(new Coordinate(j, i))) {
						if(isSeen(new Coordinate(j, i))) {
							if(board.get(new Coordinate(j, i)).isHit(new Coordinate(j, i))) {
								mapa += HIT_SYMBOL;
							}else {
								mapa += WATER_SYMBOL;
							}
						}else {
							mapa += board.get(new Coordinate(j, i)).getSymbol();
						}
				
					}else {
						mapa += WATER_SYMBOL;
					}
				}
			}
			
		}else {
			for(int i = 0; i < size; i++){
				if(i != 0) {
					mapa += "\n";
				}
				for(int j = 0; j < size; j++) {
					if(tablero.contains(new Coordinate(j, i))) {
						if(isSeen(new Coordinate(j, i))) {
							if(board.get(new Coordinate(j, i)).isShotDown()) {
								mapa += board.get(new Coordinate(j, i)).getSymbol();
							}else if(board.get(new Coordinate(j, i)).isHit(new Coordinate(j, i))) {
								mapa += HIT_SYMBOL;
							}else {
								mapa += WATER_SYMBOL;
							}
						}else {
							mapa += NOTSEEN_SYMBOL;
						}
				
					}else {
						mapa += NOTSEEN_SYMBOL;
					}
				}
			}
		}
		
		return mapa;
	}
	
	public String toString(){
		String cadena = "Board " + size + ";"+ " crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
		
		return cadena;
	}
}
