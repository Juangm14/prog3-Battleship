package model.aircraft;
import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate3D.
 */
public class Coordinate3D extends Coordinate{

	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Coordinate3D(int x, int y,int z){
		super(3);
		set(0,x);
		set(1,y);
		set(2,z);
	}

	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param c the c
	 */
	public Coordinate3D(Coordinate3D c) {
		super(c);
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 3 D
	 */
	@Override
	public Coordinate3D copy() {
		return new Coordinate3D(this);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates() {
		
		Set<Coordinate> adyacentes = new HashSet<>();
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				for(int k = -1; k <= 1; k++) {
					if(i == 0 && j == 0 && k == 0) {
						continue;
					}else {
						adyacentes.add(CoordinateFactory.createCoordinate(i + this.get(0), j + this.get(1), k + this.get(2)));
					}
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
		concatenation += ", ";
		concatenation += this.get(2);
		concatenation += ")";
		
		return concatenation;
	}
}
