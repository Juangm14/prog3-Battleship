package model.exceptions;
import  model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidCoordinateException.
 */
public class InvalidCoordinateException extends CoordinateException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Instantiates a new invalid coordinate exception.
	 *
	 * @param coordenada the coordenada
	 */
	public InvalidCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " no es valida, esta fuera de los rangos del tablero.";
	}
}
