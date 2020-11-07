package model.exceptions;
import  model.Coordinate;

public class InvalidCoordinateException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public InvalidCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " no es valida, esta fuera de los rangos del tablero.";
	}
}
