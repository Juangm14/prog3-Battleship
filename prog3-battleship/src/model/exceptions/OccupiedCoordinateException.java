package model.exceptions;
import model.Coordinate;

public class OccupiedCoordinateException extends BattleshipException {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	/**
	 * Excepcion que se produce cuando quieres introducir un barco/aircraft cuando la coordenada que le pasas esta ocupada por otro
	 * @param coordenada coordenada donde se produce la excepcion
	 */
	public OccupiedCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	/**
	 * Mensaje aclarando que tipo de excepcion ha ocurrido
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " ya esta ocupada por otra nave." ;
	}
}
