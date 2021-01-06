package model.exceptions.io;

import model.exceptions.BattleshipException;

public class BattleshipIOException extends BattleshipException{

	private static final long serialVersionUID = 1L;
	private String msg;

	public BattleshipIOException(String message) {
		msg = message;
	}
	
	public String getMessage() {
		
		return msg;
		
	}
}