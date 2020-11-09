package model.exceptions;
import model.Coordinate;

public class NextToAnotherCraftException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;
	/**
	 * Excepcion que se produce cuando la coordenada pasada esta junto a una coordenada ocupada por un barco
	 * @param coordenada coordenada coordenada donde se produce la excepcion
	 */
	public NextToAnotherCraftException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	/**
	 * Mensaje aclarando que tipo de excepcion ha ocurrido
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " esta junto a una coordenada ocupada." ;
	}
}

