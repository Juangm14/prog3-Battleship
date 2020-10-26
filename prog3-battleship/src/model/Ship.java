/**
 * @author Juan García Martínez
 */

package model;

import java.util.HashSet;
import java.util.Set;

public class Ship {
	
	//Constantes
	private int BOUNDING_SQUARE_SIZE = 5;//Tamaño de la tablero
	private int HIT_VALUE = -1;//Cuando una parte del barco es alcanzada.
	private int CRAFT_VALUE = 1;//Cuando la posicion esta ocupada por un barco
	
	private char symbol;//Symbolo con el que representamos el barco en el tablero
	private String name;//Nombre del barco
	private Orientation orientation;//Orientacion del barco (Norte, Sur, Este, Oeste)
	private Coordinate position;
	private int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};             //          ·····
          
	/**
	 * Constructor donde se le asignan los valores a los atributos
	 * @param o Orientacion del barco(ship)
	 * @param s Symbolo para representar el barco en el tablero
	 * @param n Nombre del barco
	 */
          
    public Ship(Orientation o, char s, String n) {
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
			return new Coordinate(position);
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
		this.position = new Coordinate(position);
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
	public int[][] getShape(){
		return shape;
	}
	/**
	 * 
	 * @param c: Coordenada
	 * @return calculo del valor de la coordenada que pertenece a shape
	 */
	public int getShapeIndex(Coordinate c) {
		if(c.get(0) >= 0 && c.get(1) < BOUNDING_SQUARE_SIZE && c.get(1) >= 0 && c.get(0) < BOUNDING_SQUARE_SIZE) {
			return  c.get(0) + c.get(1) * BOUNDING_SQUARE_SIZE;
		}
		return -1;
	}
	/*
	 * A partir de una coordenada position devolvemos un conjunto con las coordenadas absolutas
	 * @param c: Coordenada
	 * @return: Devolvemos las posiciones absolutas del barco
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		Set<Coordinate> absolutePositions = new HashSet<Coordinate>();
		
		if(c != null) {
			for(int i = 0;i < shape[orientation.ordinal()].length; i++) {
				if(shape[orientation.ordinal()][i] == CRAFT_VALUE || shape[orientation.ordinal()][i] == HIT_VALUE) {
					absolutePositions.add(new Coordinate(i%BOUNDING_SQUARE_SIZE + c.get(0),i/BOUNDING_SQUARE_SIZE + c.get(1)));
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
	 */
	
	public boolean hit(Coordinate c) {
		
		Set<Coordinate> thisPosAbsolutas = this.getAbsolutePositions();
		
		if(thisPosAbsolutas.contains(c)) {
			int posShape = getShapeIndex(new Coordinate (c.subtract(position)));
			
			if(shape[orientation.ordinal()][posShape] == CRAFT_VALUE) {
				shape[orientation.ordinal()][posShape] = HIT_VALUE;
				return true;
			}
		}
		return false;
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
			
			int posShape = getShapeIndex(new Coordinate (c.subtract(position)));
			
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
