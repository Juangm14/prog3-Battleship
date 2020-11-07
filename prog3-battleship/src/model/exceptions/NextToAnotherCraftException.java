package model.exceptions;
import model.Coordinate;

public class NextToAnotherCraftException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public NextToAnotherCraftException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	public String getMessage() {
		return "La coordenada " + c.toString() + " esta junto a una coordenada ocupada." ;
	}
}

