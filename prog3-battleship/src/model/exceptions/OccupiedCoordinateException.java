package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class OccupiedCoordinateException.
 */
public class OccupiedCoordinateException extends CoordinateException {
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Instantiates a new occupied coordinate exception.
	 *
	 * @param coordenada the coordenada
	 */
	public OccupiedCoordinateException(Coordinate coordenada) {
		super(coordenada);
		c = coordenada;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "La coordenada " + c.toString() + " ya esta ocupada por otra nave." ;
	}
}
