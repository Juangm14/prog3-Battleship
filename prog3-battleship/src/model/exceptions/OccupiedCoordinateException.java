package model.exceptions;
import model.Coordinate;

public class OccupiedCoordinateException extends BattleshipException {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public OccupiedCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " ya esta ocupada por otra nave." ;
	}
}
