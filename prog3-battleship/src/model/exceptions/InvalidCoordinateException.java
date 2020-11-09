package model.exceptions;
import  model.Coordinate;

public class InvalidCoordinateException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	/**
	 * Excepcion que ocurre cuandola coordenad pasada esta fuera de los rangos del tablero
	 * @param coordenada coordenada donde se produce la excepcion
	 */
	public InvalidCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " no es valida, esta fuera de los rangos del tablero.";
	}
}
