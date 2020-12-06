package model;
import model.ship.Coordinate2D;
import model.aircraft.Coordinate3D;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Coordinate objects.
 */
public class CoordinateFactory {

	/**
	 * Creates a new Coordinate object.
	 *
	 * @param coords the coords
	 * @return the coordinate
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static Coordinate createCoordinate(int... coords) throws IllegalArgumentException{
		
	    if(coords.length < 2 || coords.length > 3) {
	    	throw new IllegalArgumentException();
	    }else {
	    	if(coords.length == 2) {
	    		return new Coordinate2D(coords[0],coords[1]);
	    	}else {
	    		return new Coordinate3D(coords[0],coords[1], coords[2]);
	    	}
	    }
	}
}
