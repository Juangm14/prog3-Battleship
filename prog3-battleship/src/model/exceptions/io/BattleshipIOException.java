package model.exceptions.io;

public class BattleshipIOException extends Exception{

	private static final long serialVersionUID = 1L;
	private String msg;

	public BattleshipIOException(String message) {
		msg = message;
	}
	
	public String getMessage() {
		
		return msg;
		
	}
}