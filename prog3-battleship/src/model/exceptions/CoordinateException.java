package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateException.
 */
public abstract class CoordinateException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	/**
	 * Instantiates a new coordinate exception.
	 *
	 * @param coordenada the coordenada
	 */
	public CoordinateException(Coordinate coordenada) {
		c = coordenada;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "Error en la coordenada: " + c.toString();
	}
}
