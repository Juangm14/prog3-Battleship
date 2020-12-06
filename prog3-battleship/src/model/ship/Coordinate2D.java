package model.ship;
import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate2D.
 */
public class Coordinate2D extends Coordinate{

	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate2D(int x, int y){
		super(2);
		set(0,x);
		set(1,y);
	}

	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param c the c
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 2 D
	 */
	public Coordinate2D copy() {
		return new Coordinate2D(this);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates() {
		
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				else {
					adyacentes.add(CoordinateFactory.createCoordinate(i + this.get(0), j + this.get(1)));
				}
			}
		}

		return adyacentes;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public final String toString(){
		   
		String concatenation = "";
		   

		concatenation += "(";
		concatenation += this.get(0);
		concatenation += ", ";
		concatenation += this.get(1);
		concatenation += ")";
			
		return concatenation;
	}



}
