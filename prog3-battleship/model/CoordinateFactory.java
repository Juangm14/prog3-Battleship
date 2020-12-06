package model;
import model.ship.Coordinate2D;
import model.aircraft.Coordinate3D;

public class CoordinateFactory {

	/**
	 * Crea coordenadas 2D o 3D dependiendo del tamaño que se le pase
	 * @param coords vector de ints para la creacion de la coordenada
	 * @return la nueva coordenada creada
	 * @throws IllegalArgumentException
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
