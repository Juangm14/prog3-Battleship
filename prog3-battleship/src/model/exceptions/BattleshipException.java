package model.exceptions;
import  model.Coordinate;

public abstract class BattleshipException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	/**
	 * Constructor de la excepcion 
	 * @param coordenada donde se produce cierta excepcion
	 */
	public BattleshipException(Coordinate coordenada) {
		c = coordenada;
	}
	
	/**
	 * Mensaje que salta cuando se produce una excepcion
	 */
	public String getMessage() {
		
		return "Error en la coordenada: " + c.toString();
	}
}
