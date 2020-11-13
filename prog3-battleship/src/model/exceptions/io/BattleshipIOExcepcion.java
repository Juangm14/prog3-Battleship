package model.exceptions.io;

public class BattleshipIOExcepcion extends Exception{

	private static final long serialVersionUID = 1L;
	private String msg;
	/**
	 * Constructor para los errores de entrada/salida
	 * @param message que tiene que devolver la excepcion
	 */
	public BattleshipIOExcepcion(String message) {
		msg = message;
	}
	
	/**
	 * @return Devuelve el mensaje de error que se ha creado anteriormente en el constructor
	 */
	public String getMessage() {
		
		return msg;
	}
}