package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateAlreadyHitException.
 */
public class CoordinateAlreadyHitException extends CoordinateException {

	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Instantiates a new coordinate already hit exception.
	 *
	 * @param coordenada the coordenada
	 */
	public CoordinateAlreadyHitException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " ha sido alcanzada en un intento anterior." ;
	}
}
