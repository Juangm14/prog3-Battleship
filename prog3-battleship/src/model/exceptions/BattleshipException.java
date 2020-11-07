package model.exceptions;
import  model.Coordinate;

public abstract class BattleshipException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public BattleshipException(Coordinate coordenada) {
		c = coordenada;
	}
	
	
	public String getMessage() {
		
		return "Error en la coordenada: " + c.toString();
	}
}
