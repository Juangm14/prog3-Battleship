package model.exceptions;
import model.Coordinate;

public class CoordinateAlreadyHitException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	/**
	 * Excepcion de que la coordenada que nos pasa ha sido anteriormente golpeada
	 * @param coordenada coordenada donde se produce la excepcion
	 */
	public CoordinateAlreadyHitException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " ha sido alcanzada en un intento anterior." ;
	}
}
