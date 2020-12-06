package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.exceptions.*;

public abstract class Craft {

	public static int BOUNDING_SQUARE_SIZE = 5;
	private int HIT_VALUE = -1;
	private int CRAFT_VALUE = 1;
	private char symbol;
	
	private String name;
	private Orientation orientation;
	private Coordinate position;
	
	protected int shape[][];

	/**
	 * Constructor de barcos
	 * @param o orientacion del barco
	 * @param s symbolo del barco
	 * @param n nombre del barco
	 */
	public Craft(Orientation o, char s, String n) {
		orientation = o;
		symbol = s;
		name = n;
		position = null;
	}

	/**
	 * 
	 * @return devolvemos el nombre del Barco
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return null, si no sabemos posicion porque no la hemos establecido
	 * @return la coordenada, si ya hemos establecido una.
	 */
	public Coordinate getPosition() {
		if(position == null) {
			return null;
		}else{
			return position.copy();
		}
	}

	/**
	 * @return orientacion del barco(Norte, Sur, Este y Oeste)
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * 
	 * @param position: Coordenada para establecer la posicion del barco.
	 */
	public void setPosition(Coordinate position) {
		this.position = position.copy();
	}

	/**
	 * 
	 * @return simbolo con el que mostraremos el barco en pantalla.
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * 
	 * @return vector del barco.
	 */
	public int[][] getShape() {
		return shape;
	}

	/**
	 * 
	 * @param c: Coordenada
	 * @return calculo del valor de la coordenada que pertenece a shape
	 */
	public int getShapeIndex(Coordinate c) {
		
		Objects.requireNonNull(c);
		
		if(c.get(0) >= 0 && c.get(1) < BOUNDING_SQUARE_SIZE && c.get(1) >= 0 && c.get(0) < BOUNDING_SQUARE_SIZE) {
			return  c.get(0) + c.get(1) * BOUNDING_SQUARE_SIZE;
		}
		return -1;
	}

	/**
	 * 
	 * @param c coordenada de la que se quieren coger las coordenadas absolutas del barco
	 * @return coodenadas absolutas del barco
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c) {
		
		Objects.requireNonNull(c);
		
		Set<Coordinate> absolutePositions = new HashSet<Coordinate>();
		
		if(c != null) {
			for(int i = 0;i < shape[orientation.ordinal()].length; i++) {
				if(shape[orientation.ordinal()][i] == CRAFT_VALUE || shape[orientation.ordinal()][i] == HIT_VALUE) {
					
					Coordinate copia = c.copy();
					
					int xCoord = i%BOUNDING_SQUARE_SIZE + c.get(0);
					int yCoord = i/BOUNDING_SQUARE_SIZE + c.get(1);
					
					copia.set(0, xCoord);
					copia.set(1, yCoord);
					
					absolutePositions.add(copia);
				}
			}
		}
		
		return absolutePositions;
	}

	/**
	 * 
	 * @return coordenadas absolutas del barco, todas las coordenadas que ocupa el mismo. 
	 */
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}

	/**
	 * Comprobamos si la coordenada que le pasamos esta entre las coordenadas absolutas del barco, en caso de estarlo,
	 * sacamos la posicion del vector SHAPE y lo alcanzamos, y en caso de no estarla, no hacemos nada.
	 * @param c coordenada
	 * @return devolvemos true si la coordenada actual ha sido alcanzada y false en caso contrario.
	 * @throws CoordinateAlreadyHitException
	 */
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException{
		
		if(isHit(c)) {
			throw new CoordinateAlreadyHitException(c);
		}
		else {
			Set<Coordinate> thisPosAbsolutas = this.getAbsolutePositions();
			
			if(thisPosAbsolutas.contains(c)) {
				int posShape = getShapeIndex((c.subtract(position)).copy());
				
				if(shape[orientation.ordinal()][posShape] == CRAFT_VALUE) {
					shape[orientation.ordinal()][posShape] = HIT_VALUE;
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Comprobamos si todas las coordenadas del barco(SHAPE) han sido alcanzadas.
	 * 
	 * @return true en caso de que este hundo el barco, y false en caso contrario.
	 */
	public boolean isShotDown() {
				
		for(int i = 0; i < shape[orientation.ordinal()].length;i++) {
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Comprobamos si la coordenda que nos pasan ha sido alcanzada.
	 * @param c coordenada
	 * @return true si ha sido alcanzada y false en caso contrario.
	 */
	public boolean isHit(Coordinate c) {
		
		Set<Coordinate> thisPosAbsolutas = this.getAbsolutePositions();
		
		if(thisPosAbsolutas.contains(c)) {
			
			int posShape = getShapeIndex((c.subtract(position)).copy());
			
			if(shape[orientation.ordinal()][posShape] == HIT_VALUE) {
				return true;
			}
		}
		
		return false;
	}

	public String toString() {
		
		String imprimirShip = name + " (" + orientation + ")\n";
		
		for(int i = 0; i < shape[orientation.ordinal()].length; i++) {
			if(i == 0) {
				imprimirShip += " ";
				for(int j = 0; j < BOUNDING_SQUARE_SIZE; j++) {
					imprimirShip += "-";
				}
				imprimirShip += "\n|";
			}else if(i%5 == 0) {
				imprimirShip += "\n|";
			}
			
			if(shape[orientation.ordinal()][i] == HIT_VALUE) {
				imprimirShip += Board.HIT_SYMBOL;
			}else if(shape[orientation.ordinal()][i] ==	CRAFT_VALUE) {
				imprimirShip += symbol;
			}else if(shape[orientation.ordinal()][i] == 0) {
				imprimirShip += Board.WATER_SYMBOL;
			}
			
			if(i == shape[orientation.ordinal()].length - 1) {
				imprimirShip += "|\n ";
				for(int j = 0; j < BOUNDING_SQUARE_SIZE; j++) {
					imprimirShip += "-";
				}
			}else if(i%5 == 4) {
				imprimirShip += "|";
			}	
		}
		
		return imprimirShip;
	}

}