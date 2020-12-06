package model.exceptions;
import  model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipException.
 */
public abstract class BattleshipException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Instantiates a new battleship exception.
	 *
	 * @param coordenada the coordenada
	 */
	public BattleshipException(Coordinate coordenada) {
		c = coordenada;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "Error en la coordenada: " + c.toString();
	}
}
