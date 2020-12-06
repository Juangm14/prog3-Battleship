package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class NextToAnotherCraftException.
 */
public class NextToAnotherCraftException extends BattleshipException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Instantiates a new next to another craft exception.
	 *
	 * @param coordenada the coordenada
	 */
	public NextToAnotherCraftException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " esta junto a una coordenada ocupada." ;
	}
}

