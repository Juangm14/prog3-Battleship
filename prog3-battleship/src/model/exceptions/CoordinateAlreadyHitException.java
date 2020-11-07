package model.exceptions;
import model.Coordinate;

public class CoordinateAlreadyHitException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public CoordinateAlreadyHitException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " ha sido alcanzada en un intento anterior." ;
	}
}
