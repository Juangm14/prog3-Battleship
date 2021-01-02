package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.exceptions.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Craft.
 */
public abstract class Craft {

	/** The bounding square size. */
	public static int BOUNDING_SQUARE_SIZE = 5;
	private int HIT_VALUE = -1;
	private int CRAFT_VALUE = 1;
	private char symbol;
	
	private String name;
	private Orientation orientation;
	private Coordinate position;
	
	/** The shape. */
	protected int shape[][];

	/**
	 * Instantiates a new craft.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Craft(Orientation o, char s, String n) {
		orientation = o;
		symbol = s;
		name = n;
		position = null;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		if(position == null) {
			return null;
		}else{
			return position.copy();
		}
	}

	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position) {
		this.position = position.copy();
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int[][] getShape() {
		return shape;
	}

	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		
		Objects.requireNonNull(c);
		
		if(c.get(0) >= 0 && c.get(1) < BOUNDING_SQUARE_SIZE && c.get(1) >= 0 && c.get(0) < BOUNDING_SQUARE_SIZE) {
			return  c.get(0) + c.get(1) * BOUNDING_SQUARE_SIZE;
		}
		return -1;
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @param c the c
	 * @return the absolute positions
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
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
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
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
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
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
	
	public abstract int getValue();

}